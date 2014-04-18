package com.me.platformer.states;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.me.platformer.handlers.GameStateManager;

public class Menu extends GameState{

	private BitmapFont font = new BitmapFont(); 
	public Menu(GameStateManager gsm) {
		super(gsm); 
		font.scale(10);
	}

	public void handleInput(){
		
	}
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
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
