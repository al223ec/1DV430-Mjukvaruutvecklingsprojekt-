package com.me.platformer.states;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.me.platformer.handlers.GInput;
import com.me.platformer.handlers.GameStateManager;

public class Menu extends GameState{

	private BitmapFont font = new BitmapFont(); 
	public Menu(GameStateManager gsm) {
		super(gsm); 
		font.scale(10);
	}

	public void handleInput(){
	//Touch
		if(GInput.isPressed()){
			gsm.setState(GameStateManager.TEST);
		}
		//Keybouad
		if(GInput.isPressed(GInput.BUTTONJUMP)){
			gsm.setState(GameStateManager.TEST);
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
		font.draw(sb, "Menustate", 10, 500);
		sb.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
