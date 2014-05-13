package com.me.platformer.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.me.platformer.PlatformerGame;
import com.me.platformer.handlers.B2DVars;

public class Player extends B2DSprite{
	
	private boolean playerIsDead; 
	private float speed = 2.5f; 

	private boolean isOnGround () { return numOfFootContacts > 0; }
	private boolean isCollidingRight () { return /*numOfRightContacts > 0;*/ false; }
	private boolean playerIsFlipping; 
	//PRivacy ??? 
	public int numOfFootContacts; 
	public int numOfRightContacts;
	
	public boolean playerHasCompletedTheLevel; 
	
	public boolean isPlayerDead(){ return playerIsDead || isCollidingRight(); }
	
	private float currentRadian; 
	
	private Hat hat; 
	
	public Player(Body body) {
		super(body);
		playerIsDead = false;
		
		Texture run = PlatformerGame.cont.getTexture("runSprites");
		TextureRegion[] runSprites = TextureRegion.split(run, 50, 70)[0];
		setAnimationframes(runSprites, 1/32f);
		
		hat = new Hat(); 
	}

	public void update(float dt) {
		checkBounds(); 
		if(body.getLinearVelocity().x < speed){
			body.setLinearVelocity(speed, body.getLinearVelocity().y); //Rör sig automatiskt i sidled
		}

		hat.update(dt);		
		if(playerIsFlipping){
			currentRadian += 0.2f;
			if(currentRadian > 3.14f){
				body.setTransform(body.getPosition(), 3.14f);
				currentRadian = 3.14f; 
				playerIsFlipping = false; 
			}else{
				body.setTransform(body.getPosition(), currentRadian);
			}
			return;
		}
		
		if(isOnGround()){
			animation.update(dt);
		}
	}

	public void render(SpriteBatch sb) {
		//Rendera spelarens textur här
		sb.draw(animation.getFrame(), (getX() * B2DVars.PPM)-width/2, (getY() * B2DVars.PPM ) - height/2 + 8); 
		hat.setPosition(body.getPosition()); 
		hat.render(sb); 
	}
	
	public void jump(){
		if(isOnGround() && !isCollidingRight()){
			//body.applyForceToCenter(0, 200, true);
			body.applyLinearImpulse(0, 2f, getX(), getY(), true);
		}
	}
	public void flip(){
		playerIsFlipping = true; 
	}
	private void checkBounds(){
		if(getY() < -2){
			playerIsDead = true; 
		}
	}
	public void destroyBody(){
		World world = body.getWorld(); 
		world.destroyBody(body); 
	}
}
