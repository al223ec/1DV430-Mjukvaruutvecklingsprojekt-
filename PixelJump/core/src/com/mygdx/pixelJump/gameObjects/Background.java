package com.mygdx.pixelJump.gameObjects;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.pixelJump.PixelJump;

public class Background {
	private Texture gameBackground; 
	private Texture gameBackgroundFront; 
	
	private OrthographicCamera gameCam; 

	private float x;
	private float dx;
	
	private float moveScale = 0.5f;
	
	private int width; 
	private int height; 
	
	public Background(OrthographicCamera gameCam){
		this.gameCam = gameCam; 
		
		gameBackground = PixelJump.cont.getTexture("gameBackground"); 
		gameBackgroundFront = PixelJump.cont.getTexture("gameBackgroundFront"); 
		
		width = gameBackgroundFront.getWidth();
		height = gameBackgroundFront.getHeight(); 
	}
	
	public void setVector(float x){
		this.x = x; 
	}
	
	public void draw(SpriteBatch sb) {
		System.out.println(gameCam.position.x); 
		x -= moveScale;
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
}
