package com.me.platformer.handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.me.platformer.gameObjects.Player;

public class GContactListener implements ContactListener {
	private Player player;  
	//Jag kanske borde ha en referens till spelaren i denna 
	//klass och sätt canjump och can flip till spelaren
	//Det kan ju egentligen vara vettigare att denna klass sätter annat
	//DVs att denna klass berättar när något händer ist för att man behöver "lyssna på den"
	
	
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
		
		if(fa.getUserData() != null && fa.getUserData().equals("rightSensor")) {
			player.numOfRightContacts++;
		}
		if(fb.getUserData() != null && fb.getUserData().equals("rightSensor")) {
			player.numOfRightContacts++;
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
		
		if(fa.getUserData() != null && fa.getUserData().equals("rightSensor")) {
			player.numOfRightContacts--;
		}
		if(fb.getUserData() != null && fb.getUserData().equals("rightSensor")) {
			player.numOfRightContacts--;
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {}
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
	}
}
