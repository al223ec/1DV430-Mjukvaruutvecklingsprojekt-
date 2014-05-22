package com.mygdx.pixelJump.states;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.pixelJump.handlers.GameStateManager;

public class EditPlayer extends MenuState {
	
	private TextButton startGameButton;
	private TextButton mainMenuButton; 

	
	public EditPlayer(GameStateManager gsm) {
		super(gsm); 			
		mainMenuButton = new TextButton("Main menu", buttonstyle); 
		startGameButton = new TextButton("Start game", buttonstyle); 
		setUpMenu();
		
		upperTable.add("EditPlayer");
	}
	
	private void startGame(){
		gsm.playNextState(new Test(gsm));
	}
	private void mainMenu(){
		gsm.playNextState(new Menu(gsm)); 
	}
	
	private void setUpMenu(){
		super.setUpMenu(startGameButton, mainMenuButton); 
		startGameButton.addListener(new ChangeListener(){
			public void changed(ChangeEvent event, Actor actor){
				startGame(); 
			}
		});
		
		mainMenuButton.addListener(new ChangeListener(){
			public void changed(ChangeEvent event, Actor actor){
				mainMenu(); 
			}
		});
	}
	
	@Override
	public void handleInput(){ }
	@Override
	public void update(float dt) {} 
	@Override
	public void render(float dt) {
		super.render(dt); 
	}

	@Override
	public void dispose() {
		System.out.println("EditPlayer disposed"); 
		super.dispose(); 
	}
}
