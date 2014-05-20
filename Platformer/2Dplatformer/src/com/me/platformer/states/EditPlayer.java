package com.me.platformer.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.me.platformer.gameObjects.Hat;
import com.me.platformer.gameObjects.Player;
import com.me.platformer.handlers.GInput;
import com.me.platformer.handlers.GameStateManager;

public class EditPlayer extends GameState{

	private Player player; 
	private Hat hat; 
	
	private TextureAtlas allHats; 
	
	private int currentHat = 0; 
	private int numOfHats = 5; 
	
	public EditPlayer(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void handleInput() {

		if(GInput.isPressed()){
			if(GInput.x < Gdx.graphics.getWidth() / 2){//Om hen toucher vänstra delen hoppa högt 	
				System.out.println("nextHat");
			}else{
				System.out.println("prevHat");
			}
		}
	}

	@Override
	public void update(float dt) {
		handleInput(); 
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
