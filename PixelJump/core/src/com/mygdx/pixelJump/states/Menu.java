package com.mygdx.pixelJump.states;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.pixelJump.PixelJump;
import com.mygdx.pixelJump.handlers.GameStateManager;

public class Menu extends MenuState {
	private TextButton startGameButton;
	private TextButton editPlayerButton; 
	
	public Menu(GameStateManager gsm) {
		super(gsm); 			
		cam.setToOrtho(false, PixelJump.WIDTH, PixelJump.HEIGHT);
		splash = PixelJump.cont.getTexture("splash");
		
		editPlayerButton = new TextButton("Edit player", buttonstyle); 
		startGameButton = new TextButton("Start game", buttonstyle); 
		setUpMenu();
	}
	
	private void playNext(){
		gsm.playNextState(new SelectLevel(gsm));
	}
	
	private void editPlayer(){
		gsm.playNextState(new EditPlayer(gsm)); 
	}
	
	private void setUpMenu(){
		super.setUpMenu(startGameButton, editPlayerButton); 
		startGameButton.addListener(new ChangeListener(){
			public void changed(ChangeEvent event, Actor actor){
				playNext(); 
			}
		});
		
		editPlayerButton.addListener(new ChangeListener(){
			public void changed(ChangeEvent event, Actor actor){
				editPlayer(); 
			}
		});
	}
	
	@Override
	public void dispose() {
		System.out.println("menu disposed"); 
		super.dispose(); 
	}
}
