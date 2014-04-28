package com.me.platformer.states;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.me.platformer.handlers.GInput;
import com.me.platformer.handlers.GameStateManager;

public class GameOver extends GameState{
	private BitmapFont font = new BitmapFont(); 

	public GameOver(GameStateManager gsm) {
		super(gsm);
		font.scale(10);
	}

	@Override
	protected void handleInput() {
		if(GInput.isPressed()){
			gsm.playNextState(GameStateManager.STARTMENU);
		}
		//Keybouad
		if(GInput.isPressed(GInput.BUTTONJUMP)){
			gsm.playNextState(GameStateManager.STARTMENU);
		}
	}

	@Override
	public void update(float dt) {
		handleInput(); 
	}

	@Override
	public void render() {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		font.draw(sb, "GAMEOVER PRESS TO RESTART", 10, 500);
		sb.end();
	}

	@Override
	public void dispose() {
		
	}

}
