package com.me.platformer.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.me.platformer.PlatformerGame;
import com.me.platformer.handlers.B2DVars;
import com.me.platformer.handlers.GContactListener;

public class Player extends B2DSprite{
	
	private boolean playerIsDead; 
	private float speed = 2.5f; 

	private boolean isOnGround () { return numOfFootContacts > 0; }
	private boolean isCollidingRight () { return /*numOfRightContacts > 0;*/ false; }
	
	//PRivacy ??? 
	public int numOfFootContacts; 
	public int numOfRightContacts;
	public boolean playerHasCompletedTheLevel; 
	
	public boolean isPlayerDead(){ return playerIsDead || isCollidingRight(); }
	
	public Player(Body body) {
		super(body);
		playerIsDead = false;
		
		Texture run = PlatformerGame.cont.getTexture("runSprites");
		TextureRegion[] runSprites = TextureRegion.split(run, 50, 70)[0];
		setAnimationframes(runSprites, 1/32f); 
	}

	public void update(float dt) {
		checkBounds(); 
		if(!isCollidingRight()){
			body.setLinearVelocity(speed, body.getLinearVelocity().y); //Rör sig automatiskt i sidled
		}
		if(isOnGround()){
			animation.update(dt);
		}
	}

	public void render(SpriteBatch sb) {
		//Rendera spelarens textur här
		sb.draw(animation.getFrame(), (getX() * B2DVars.PPM)-width/2, (getY() * B2DVars.PPM ) - height/2 + 8); 
	}
	
	public void jump(){
		if(isOnGround() && !isCollidingRight()){
			//body.applyForceToCenter(0, 200, true);
			body.applyLinearImpulse(0, 2f, getX(), getY(), true);
		}
	}
	private void checkBounds(){
		if(getY() < -2){
			playerIsDead = true; 
		}

	}
}
