package com.me.platformer.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.platformer.PlatformerGame;
import com.me.platformer.handlers.GameStateManager;

public abstract class GameState {

	protected GameStateManager gsm; 
	protected PlatformerGame game; 
	
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
