package com.me.platformer.gameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

public class Player extends B2DSprite{

	public Player(Body body) {
		super(body);
		// TODO Auto-generated constructor stub
	}

	public void update(float dt) {
		body.setLinearVelocity(1f, body.getLinearVelocity().y); //R�r sig automatiskt i sidled
		//Bug n�r spelaren st�r still mot en v�gg verkar som kraften laddas upp och d�rigenom kuka ur n�r man hoppar
		
	}

	public void render(SpriteBatch sb) {
		//Rendera spelarens textur h�r
	}
	
	public void jump(){
		body.applyForceToCenter(0, 270, true);
	}
}
