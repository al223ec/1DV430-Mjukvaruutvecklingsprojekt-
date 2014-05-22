package com.mygdx.pixelJump.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.pixelJump.PixelJump;
import com.mygdx.pixelJump.handlers.B2DVars;

public class Hat {

	private float width; 
	private float height; 
	
	private float drawX; 
	private float drawY; 

	private Texture hat; 
	
	private TextureAtlas allHats; 
	
	public final String StandardHat = "standardHat"; 
	public final String PirateHat = "pirateHat"; 
	

	private int currentHat = 0; 
	private int numOfHats = 5; 
	
	public void setPosition(Vector2 pos){
		drawX = pos.x; 
		drawY = pos.y; 
	}
	
	public Hat(){
		hat = PixelJump.cont.getTexture("hat");
		width = hat.getWidth(); 
		height = hat.getHeight(); 
		//allHats = new TextureAtlas(Gdx.files.internal("res/images/hats/hats.pack")); 
	}
	public Hat(Texture texture){
		hat = PixelJump.cont.getTexture("hat");
		width = hat.getWidth(); 
		height = hat.getHeight(); 
		//allHats = new TextureAtlas(Gdx.files.internal("res/images/hats/hats.pack")); 
	}

	public void update(float dt) {
		//animation.update(dt); 
	}
	public void setCurrentHat(String key){
		
	}
	

	public void render(SpriteBatch sb) {
		sb.draw(hat, (drawX * B2DVars.PPM)-width/2, (drawY * B2DVars.PPM ) + height/2); 	
	//	sb.draw(animation.getFrame(), (drawX * B2DVars.PPM)-width/2, (drawY * B2DVars.PPM ) + height/2); 
	}
	
	protected void setAnimationframes(TextureRegion[] reg, float delay){
		//animation.setFrames(reg, delay); 
		width = reg[0].getRegionWidth();
		height = reg[0].getRegionHeight();
	}
}
