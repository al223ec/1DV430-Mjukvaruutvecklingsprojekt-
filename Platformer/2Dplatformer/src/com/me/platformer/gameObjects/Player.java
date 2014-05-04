package com.me.platformer.gameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.me.platformer.handlers.GContactListener;

public class Player extends B2DSprite{
	private GContactListener gcl;
	
	private boolean playerIsDead; 
	private float speed = 2.5f; 
	
	public boolean isPlayerDead(){ return playerIsDead || gcl.isCollidingRight(); }
	
	public Player(Body body, GContactListener gcl) {
		super(body);
		this.gcl = gcl; 
		playerIsDead = false; 
	}

	public void update(float dt) {
		checkBounds(); 
		if(!gcl.isCollidingRight()){
			body.setLinearVelocity(speed, body.getLinearVelocity().y); //Rör sig automatiskt i sidled
		}
	}

	public void render(SpriteBatch sb) {
		//Rendera spelarens textur här
	}
	
	public void jump(){
		if(gcl.isOnGround() && !gcl.isCollidingRight()){
			body.applyForceToCenter(0, 200, true);
		}
	}
	private void checkBounds(){
		if(body.getPosition().y < -2){
			playerIsDead = true; 
		}
		System.out.println(body.getPosition().x);
		//Kontrollerar om spelaren är nära slutet på banan
		if(body.getPosition().x > 120){//Spelaren har klarat leveln
		}
	}
}
