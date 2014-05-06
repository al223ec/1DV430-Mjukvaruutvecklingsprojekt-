package com.me.platformer.states;
import com.me.platformer.handlers.GInput;
import com.me.platformer.handlers.GameStateManager;

public class GameOver extends GameState{
	private GameState playState;  

	public GameOver(GameStateManager gsm, GameState playState) {
		super(gsm);
		this.playState = playState; //Tveksamt om detta är en vettig lösning
	}

	@Override
	protected void handleInput() {
		if(GInput.isPressed()){
			gsm.playNextState(playState);
		}
		//Keybouad
		if(GInput.isPressed(GInput.BUTTONJUMP)){
			gsm.playNextState(playState);
		}
	}

	@Override
	public void update(float dt) {
		handleInput(); 
	}

	@Override
	public void render() {
		System.out.println("GameOver");

	}

	@Override
	public void dispose() {
		
	}

}
