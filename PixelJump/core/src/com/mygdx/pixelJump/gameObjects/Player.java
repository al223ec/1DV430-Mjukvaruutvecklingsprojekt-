package com.mygdx.pixelJump.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.pixelJump.PixelJump;
import com.mygdx.pixelJump.handlers.B2DVars;
import com.mygdx.pixelJump.handlers.PlayerSettings;

public class Player extends B2DSprite{
	private boolean playerIsDead; 
	private float speed = 2.5f; 

	private boolean isOnGround () { return numOfFootContacts > 0; }
	private boolean playerIsFlipping; 

	public int numOfFootContacts; 
	
	public boolean playerHasCompletedTheLevel; 
	public void setPlayerIsDead(){ playerIsDead = true; } 
	public boolean isPlayerDead(){ return playerIsDead; }
	
	private float currentRadian; 
	private Texture hatTexture; 
	
	private PlayerSettings playerSettings; 
	public Player(Body body){
		super(body); 
		playerIsDead = false;
		playerIsFlipping = false; 
		
		playerSettings = PixelJump.playerSettings; 
		hatTexture = playerSettings.getCurrentHatTexture(); 
		
		Texture run = PixelJump.cont.getTexture("runSprites");
		TextureRegion[] runSprites = TextureRegion.split(run, 50, 70)[0];
		setAnimationframes(runSprites, 1/32f);
		
		
	}


	public void update(float dt) {
		checkBounds(); 
		if(body.getLinearVelocity().x < speed){
			body.setLinearVelocity(speed, body.getLinearVelocity().y); //Rör sig automatiskt i sidled
		}

		//hat.update(dt);		
		if(playerIsFlipping){
	
			if(currentRadian > 1){
				currentRadian *= 1.2f; 
			}else{
				currentRadian += 0.2f;
			}
			
			System.out.println(currentRadian); 
			if(currentRadian > 6.28f){
				body.setTransform(body.getPosition(), 0f);
				currentRadian = 0; 
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
		sb.begin(); 
		if(playerIsFlipping){
			sb.draw(animation.getFrame(), (getX() * B2DVars.PPM)-width/2, (getY() * B2DVars.PPM ) - height/2 + 8,
					animation.getFrameWidth()/2, animation.getFrameHeight()/2,
					animation.getFrameWidth(), animation.getFrameHeight(), 1f, 1f,  currentRadian * MathUtils.radiansToDegrees); 	
		}
		else{
			sb.draw(animation.getFrame(), (getX() * B2DVars.PPM)-width/2, (getY() * B2DVars.PPM ) - height/2 + 8);
		}
		sb.end(); 
		//hat.setPosition(body.getPosition()); 
		//hat.render(sb); 
	}
	
	public void jump(){
		if(isOnGround()){
			//body.applyForceToCenter(0, 200, true);
			body.applyLinearImpulse(0, 2f, getX(), getY(), true);
		}
	}
	//Denna funktion ska flippa spelaren, dvs roteara 180 grader, detta har jag inte lyckats med fullt ut och kommer inte hinna implementera 
	//Så spelaren får helt enkelt bara hoppa högre
	public void flip(){
		if(isOnGround()){
			body.applyLinearImpulse(0, 2f, getX(), getY(), true);
			playerIsFlipping = true; 
		}
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
	public void setBody(Body body) {
		this.body = body; 
	}
}
