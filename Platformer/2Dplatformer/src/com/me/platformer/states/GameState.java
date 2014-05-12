package com.me.platformer.states;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.platformer.PlatformerGame;
import com.me.platformer.handlers.GameStateManager;

public abstract class GameState /* implements Screen*/ {

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
	public abstract void render(float dt); 
	public abstract void dispose(); 
	/*
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}*/
}
