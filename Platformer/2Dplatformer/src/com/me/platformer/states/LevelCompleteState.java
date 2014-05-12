package com.me.platformer.states;

import com.me.platformer.handlers.GInput;
import com.me.platformer.handlers.GameStateManager;

public class LevelCompleteState extends GameState{

	public LevelCompleteState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void handleInput() {
		// TODO Auto-generated method stub
		if(GInput.isPressed()){
			gsm.playNextState(new Test(gsm));
		}
		//Keybouad
		if(GInput.isPressed(GInput.BUTTONJUMP)){
			gsm.playNextState(new Test(gsm));
		}
	}

	@Override
	public void update(float dt) {
		handleInput();
		System.out.println("Levelcomplete"); 
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
