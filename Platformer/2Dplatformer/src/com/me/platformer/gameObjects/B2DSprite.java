package com.me.platformer.gameObjects;

import com.badlogic.gdx.physics.box2d.Body;
import com.me.platformer.handlers.Animation;

public class B2DSprite {

	protected Body body; 
	protected float width; 
	protected float height; 
	protected Animation animation; 
	
	public B2DSprite(Body body){
		this.body = body; 
	}
	
	public Body getBody(){
		return body; 
	}
}
