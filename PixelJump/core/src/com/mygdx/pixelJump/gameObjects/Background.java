package com.mygdx.pixelJump.gameObjects;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.pixelJump.PixelJump;

public class Background {
	
	private Texture gameBackground; 
	private Texture gameBackgroundFront; 	

	private float x;
	
	private float moveScale = 0.5f;
	
	
	public Background(OrthographicCamera gameCam){
		gameBackground = PixelJump.cont.getTexture("gameBackground"); 
		gameBackgroundFront = PixelJump.cont.getTexture("gameBackgroundFront"); 
	}
	
	public void setVector(float x){
		this.x = x; 
	}
	
	public void update(){
		x -= moveScale;
	}
	public void draw(SpriteBatch sb) {
		//Känner att jag inte har tid att lösa detta på ett vettigt sätt
		sb.begin();
		sb.draw(gameBackground,  0 , 0);
		sb.draw(gameBackground, gameBackground.getWidth() , 0);
		
		sb.draw(gameBackgroundFront,  x, 0);
		sb.draw(gameBackgroundFront,gameBackgroundFront.getWidth() + x, 0);
		
		//sb.draw(gameBackgroundFront,  x, 0);
		//sb.draw(gameBackgroundFront, x, 0, width/2, height, width/2, height, 1, 1); 
		//sb.draw(gameBackgroundFront, x + width, 0); 
		sb.end(); 
		
	}
	public void reset() {
		x = 0; 
	}
}
