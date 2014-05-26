package com.mygdx.pixelJump.handlers;

import com.mygdx.pixelJump.states.GameState;
import com.mygdx.pixelJump.states.Menu;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.pixelJump.PixelJump;

public class GameStateManager {
	
	private PixelJump game; 
	private GameState currentGameState; 
	
	public GameStateManager(PixelJump game) {
		this.game = game; 
		currentGameState = new Menu(this); 
	}
	
	public void update(float dt){
		currentGameState.update(dt);
	}
	public void render(){
		currentGameState.render(); 
	}
	public void playNextState(GameState state){	
		GInput.resetKeys();
		currentGameState.dispose(); 
		currentGameState = state; 
	}
	
	public SpriteBatch getSpriteBatch() {
		return game.getSpriteBatch(); 
	}

	public OrthographicCamera getCamera() {
		return game.getCamera();
	}
	
	public OrthographicCamera getHudCamera() {
		return game.getHudCamera();
	}
}
