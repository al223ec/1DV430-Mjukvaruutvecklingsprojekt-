package com.me.platformer.handlers;

import java.util.Stack;

import com.me.platformer.PlatformerGame;
import com.me.platformer.states.*;

public class GameStateManager {

	private PlatformerGame game; 
	private Stack<GameState> gameStates; //Tveksamt om jag bör använda än stack
	public static final int STARTMENU = 912837; 
	public static final int LEVEL1 = 234337;
	public static final int TEST = 211323;
	
	public GameStateManager(PlatformerGame game) {
		this.game = game; 
		gameStates = new Stack<GameState>();
		pushState(TEST); 
	}
	
	public PlatformerGame game(){
		return game; 
	}
	public void update(float dt){
		gameStates.peek().update(dt); 
	}
	public void render(){
		gameStates.peek().render(); 	
	}
	
	private GameState getState(int state){
		if(state == STARTMENU) return new Menu(this);
		if(state == LEVEL1) return new Play(this);
		if(state == TEST) return new Test(this);
		return null; //Kanske raise error??  
	}
	public void setState(int state){
		popState();
		pushState(state); 
	}
	private void pushState(int state){
		gameStates.push(getState(state)); 
	}
	
	private void popState(){
		GameState g = gameStates.pop();
		g.dispose();
	}

}
