package com.me.platformer.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.me.platformer.PlatformerGame;
import com.me.platformer.handlers.Animation;
import com.me.platformer.handlers.B2DVars;

public class Hat {
	private Animation animation; 
	
	private float width; 
	private float height; 
	
	private float drawX; 
	private float drawY; 
	
	public void setPosition(Vector2 pos){
		drawX = pos.x; 
		drawY = pos.y; 
	}
	
	public Hat(){
		animation = new Animation(); 
		Texture hat = PlatformerGame.cont.getTexture("hat");
		TextureRegion[] runSprites = TextureRegion.split(hat, 68, 48)[0];
		setAnimationframes(runSprites, 1/32f); 
	}

	public void update(float dt) {
		animation.update(dt); 
	}

	public void render(SpriteBatch sb) {
		sb.draw(animation.getFrame(), (drawX * B2DVars.PPM)-width/2, (drawY * B2DVars.PPM ) + height/2); 
	}
	
	protected void setAnimationframes(TextureRegion[] reg, float delay){
		animation.setFrames(reg, delay); 
		width = reg[0].getRegionWidth();
		height = reg[0].getRegionHeight();
	}
}
