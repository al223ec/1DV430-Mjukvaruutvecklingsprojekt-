package com.mygdx.pixelJump.states;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.pixelJump.PixelJump;
import com.mygdx.pixelJump.handlers.GameStateManager;

public class SelectLevel extends MenuState {
	
	private TextButton startGameButton;
	private TextButton mainMenuButton; 
	private float selectSize = 184; 	
	
	public SelectLevel(GameStateManager gsm) {
		super(gsm); 			
		splash = PixelJump.cont.getTexture("splash");
		
		mainMenuButton = new TextButton("Main menu", buttonstyle); 
		startGameButton = new TextButton("Start game", buttonstyle); 
		setUpMenu();

	}
	
	private void playNext(){
		gsm.playNextState(new Test(gsm));
	}
	
	private void mainMenu(){
		gsm.playNextState(new Menu(gsm)); 
	}
	
	private void setUpMenu(){
		super.setUpMenu(startGameButton, mainMenuButton); 
		startGameButton.addListener(new ChangeListener(){
			public void changed(ChangeEvent event, Actor actor){
				playNext(); 
			}
		});
		
		mainMenuButton.addListener(new ChangeListener(){
			public void changed(ChangeEvent event, Actor actor){
				mainMenu(); 
			}
		});
		
		setUpLevelSelect(); 
	}
	private void setUpLevelSelect(){
		upperTable.add("Select level").colspan(4).left().spaceBottom(40); 
		upperTable.row().center();

		upperTable.add().width(selectSize).height(selectSize).center();
		upperTable.add("1").width(selectSize).height(selectSize).center();
		upperTable.add("2").width(selectSize).height(selectSize).center();
		upperTable.add("3").width(selectSize).height(selectSize).center();
		upperTable.add("4").width(selectSize).height(selectSize).center();
		upperTable.add("5").width(selectSize).height(selectSize).center();
		upperTable.add().width(selectSize/2).height(selectSize).center();
		upperTable.row().center();
		
		upperTable.add().width(selectSize).height(selectSize).center();
		upperTable.add("6").width(selectSize).height(selectSize).center();
		upperTable.add("7").width(selectSize).height(selectSize).center();
		upperTable.add("8").width(selectSize).height(selectSize).center();
		upperTable.add("9").width(selectSize).height(selectSize).center();
		upperTable.add("10").width(selectSize).height(selectSize).center();
		upperTable.add().width(selectSize/2).height(selectSize).center();
		
		
	}
	
	
	@Override
	public void render(float dt) {
		super.render(dt); 
	}

	@Override
	public void dispose() {
		System.out.println("menu disposed"); 
		super.dispose(); 
	}

}
