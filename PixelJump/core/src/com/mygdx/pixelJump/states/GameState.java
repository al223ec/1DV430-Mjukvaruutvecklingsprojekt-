package com.mygdx.pixelJump.states;

//import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.pixelJump.PixelJump;
import com.mygdx.pixelJump.handlers.GameStateManager;

public abstract class GameState /* implements Screen*/ {

	protected GameStateManager gsm; 
	protected PixelJump game; 
	
	protected SpriteBatch sb; 
	protected OrthographicCamera  cam; 
	
	public GameState(GameStateManager gsm){
		this.gsm = gsm; 
		sb = gsm.getSpriteBatch(); 
		cam = gsm.getCamera(); 
	}
	
	protected abstract void handleInput();
	public abstract void update(float dt); 
	public abstract void render(); 
	public abstract void dispose(); 
}
