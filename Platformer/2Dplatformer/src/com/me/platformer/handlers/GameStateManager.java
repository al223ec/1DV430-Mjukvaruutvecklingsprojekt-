package com.me.platformer.handlers;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.platformer.PlatformerGame;
import com.me.platformer.states.*;

public class GameStateManager {
	
	private PlatformerGame game; 
	private GameState currentGameState; 
	
	public GameStateManager(PlatformerGame game) {
		this.game = game; 
		currentGameState = new Menu(this); 
	}
	
	public void update(float dt){
		currentGameState.update(dt);
	}
	public void render(){
		currentGameState.render(); 
	}
/*	
 * Vet inte hur bra den nuvarande gamestatemanagern är, skapas alltid nya objekt 
 * det borde inte vara nog stor fara om man läser in alla texturer och andra saker tidigare
	public void playNextState() throws IllegalArgumentException{	
		currentState++; 
		if(currentState == STARTMENU){
			gameStates.add(STARTMENU, new Menu(this)); 		
		}else if(currentState == TEST){
			gameStates.add(TEST, new Test(this));
		}else if(currentState == LEVEL1){
			gameStates.add(LEVEL1, new Play(this));			
		}else if(currentState == GAMEOVER){
			playNextState("Game Over"); 
		}else{
			throw new IllegalArgumentException("Denna state finns inte"); 	}
	}
	
	public void playNextState(String message){	
		currentState = GAMEOVER; 
		gameStates.add(GAMEOVER, new GameOver(this, message));			
	}
	
	public void playNextState(int state) throws IllegalArgumentException{	
		currentState = state; 
		if(currentState == STARTMENU){
			gameStates.add(STARTMENU, new Menu(this)); 		
		}else if(currentState == TEST){
			gameStates.add(TEST, new Test(this));
		}else if(currentState == LEVEL1){
			gameStates.add(LEVEL1, new Play(this));			
		}else if(currentState == GAMEOVER){

		}else{
			throw new IllegalArgumentException("Denna state finns inte"); 	}
	}	*/
	public void playNextState(GameState state){	
		currentGameState = state; 
	}
	
	public SpriteBatch getSpriteBatch() {
		return game.getSpriteBatch(); 
	}

	public OrthographicCamera getCamera() {
		return game.getCamera();
	}
}
