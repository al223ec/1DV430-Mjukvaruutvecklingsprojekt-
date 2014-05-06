package com.me.platformer.gameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.me.platformer.handlers.GContactListener;

public class Player extends B2DSprite{
	
	private boolean playerIsDead; 
	private boolean playerHasCompletedTheLevel; 
	private float speed = 2.5f; 

	private boolean isOnGround () { return numOfFootContacts > 0; }
	private boolean isCollidingRight () { return numOfRightContacts > 0; }
	
	//PRivacy ??? 
	public int numOfFootContacts; 
	public int numOfRightContacts;
	
	public boolean isPlayerDead(){ return playerIsDead || isCollidingRight(); }
	
	public Player(Body body) {
		super(body);
		playerIsDead = false; 
	}

	public void update(float dt) {
		checkBounds(); 
		if(!isCollidingRight()){
			body.setLinearVelocity(speed, body.getLinearVelocity().y); //Rör sig automatiskt i sidled
		}
	}

	public void render(SpriteBatch sb) {
		//Rendera spelarens textur här
	}
	
	public void jump(){
		if(isOnGround() && !isCollidingRight()){
			body.applyForceToCenter(0, 200, true);
		}
	}
	private void checkBounds(){
		if(body.getPosition().y < -2){
			playerIsDead = true; 
		}
		System.out.println(body.getPosition().x);
		//Kontrollerar om spelaren är nära slutet på banan
		if(body.getPosition().x > 15){//Spelaren har klarat leveln
			playerHasCompletedTheLevel = true; 
		}
	}
	public boolean hasPlayerCompletedGame() {
		return playerHasCompletedTheLevel;
	}
}
