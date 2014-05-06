package com.me.platformer.gameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.me.platformer.handlers.Animation;
import com.me.platformer.handlers.Content;

public abstract class B2DSprite {

	protected Body body; 
	protected float width; 
	protected float height; 
	
	protected Animation animation; 
	
	public B2DSprite(Body body){
		this.body = body; 
	}
		
	public float getX() { return body.getPosition().x; }
	public float getY() { return body.getPosition().y; }
	
	public abstract void update(float dt); 
	public abstract void render(SpriteBatch sb); 
}
