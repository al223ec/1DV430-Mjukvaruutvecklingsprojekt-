package com.me.platformer.handlers;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.platformer.PlatformerGame;
import com.me.platformer.states.*;

public class GameStateManager {
	
	private PlatformerGame game; 
	private ArrayList<GameState> gameStates;
	private int currentState; 
	private final int numberOfStates = 3; 
	
	public static final int STARTMENU = 0; 
	public static final int TEST = 1;
	public static final int LEVEL1 = 2;
	
	public GameStateManager(PlatformerGame game) {
		this.game = game; 
		
		gameStates = new ArrayList<GameState>(numberOfStates);
		currentState = STARTMENU; 
		gameStates.add(STARTMENU, new Menu(this)); 
	}
	
	public void update(float dt){
		gameStates.get(currentState).update(dt); 
	}
	public void render(){
		gameStates.get(currentState).render();
	}
	
	public void setState(int state) throws IllegalArgumentException{	
		if(state == STARTMENU){
			gameStates.add(STARTMENU, new Menu(this)); 		
		}else if(state == TEST){
			gameStates.add(TEST, new Test(this));
		}else if(state == LEVEL1){
			gameStates.add(LEVEL1, new Play(this));			
		}else{
			throw new IllegalArgumentException("Denna state finns inte"); 
		}
		currentState = state;
	}
	
	public SpriteBatch getSpriteBatch() {
		return game.getSpriteBatch(); 
	}

	public OrthographicCamera getCamera() {
		return game.getCamera();
	}
}
