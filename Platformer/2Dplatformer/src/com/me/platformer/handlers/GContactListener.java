package com.me.platformer.handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class GContactListener implements ContactListener {
	private int numContacts; 
	public boolean playerCanJump; 
	public boolean playerCanFlip; 
	//Jag kanske borde ha en referens till spelaren i denna 
	//klass och sätt canjump och can flip till spelaren
	
	public boolean isOnGround () { return numContacts > 0; }
	
	@Override
	public void beginContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		if(fa == null || fb == null) return;
		
		if(fa.getUserData() != null && fa.getUserData().equals("foot")) {
			numContacts++;
		}
		if(fb.getUserData() != null && fb.getUserData().equals("foot")) {
			numContacts++;
		}
	}

	@Override
	public void endContact(Contact contact) {
		
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		if(fa == null || fb == null) return;
		
		if(fa.getUserData() != null && fa.getUserData().equals("foot")) {
			numContacts--;
		}
		if(fb.getUserData() != null && fb.getUserData().equals("foot")) {
			numContacts--;
		}

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {}
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
	}
}
