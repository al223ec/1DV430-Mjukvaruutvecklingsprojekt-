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
			body.setLinearVelocity(speed, body.getLinearVelocity().y); //R�r sig automatiskt i sidled
		}
		//Bug n�r spelaren st�r still mot en v�gg verkar som kraften laddas upp och d�rigenom kuka ur n�r man hoppar
		System.out.println(body.getPosition().y);
	}

	public void render(SpriteBatch sb) {
		//Rendera spelarens textur h�r
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
	}
}
