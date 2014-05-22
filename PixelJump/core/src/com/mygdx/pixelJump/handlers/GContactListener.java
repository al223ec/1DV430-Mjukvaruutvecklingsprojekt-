package com.mygdx.pixelJump.handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.pixelJump.gameObjects.Player;

public class GContactListener implements ContactListener {
	private Player player;  

	public GContactListener(Player player){
		this.player = player; 
	}
	
	@Override
	public void beginContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		if(fa == null || fb == null){ 
			return;
		}
		if(fa.getUserData() != null && fa.getUserData().equals("footSensor")) {
			player.numOfFootContacts++;
		}
		
		if(fb.getUserData() != null && fb.getUserData().equals("footSensor")) {
			player.numOfFootContacts++;
		}
		
		if(fa.getUserData() != null && fa.getUserData().equals("damage")) {
			player.setPlayerIsDead();
		}
		
		if(fb.getUserData() != null && fb.getUserData().equals("damage")) {
			player.setPlayerIsDead();
		}
		
		if(fa.getUserData() != null && fa.getUserData().equals("goal")) {
			player.playerHasCompletedTheLevel = true;
		}
		if(fb.getUserData() != null && fb.getUserData().equals("goal")) {
			player.playerHasCompletedTheLevel = true;
		}		
	}

	@Override
	public void endContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		if(fa == null || fb == null){ 
			return;
		}
		
		if(fa.getUserData() != null && fa.getUserData().equals("footSensor")) {
			player.numOfFootContacts--;
		}
		if(fb.getUserData() != null && fb.getUserData().equals("footSensor")) {
			player.numOfFootContacts--;
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {}
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {}
}
