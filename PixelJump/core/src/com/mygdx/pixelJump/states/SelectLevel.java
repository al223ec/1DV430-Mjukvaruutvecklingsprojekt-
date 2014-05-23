package com.mygdx.pixelJump.states;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.pixelJump.PixelJump;
import com.mygdx.pixelJump.handlers.GameStateManager;
import com.mygdx.pixelJump.states.levels.Test;
import com.mygdx.pixelJump.states.myMenuItems.SelectButton;

public class SelectLevel extends MenuState {
	
	private TextButton startGameButton;
	private TextButton mainMenuButton; 
	private float selectSize = 184; 	

	private TextButtonStyle selectButtonStyle; 
	private TextButtonStyle lockedSelectButtonStyle; 
	
	public SelectLevel(GameStateManager gsm) {
		super(gsm); 			
		splash = PixelJump.cont.getTexture("background");
		
		mainMenuButton = new TextButton("Main menu", buttonstyle); 
		startGameButton = new TextButton("Start game", buttonstyle);
		
		selectButtonStyle = new TextButtonStyle(); 
		lockedSelectButtonStyle = new TextButtonStyle(); 
		buttonAtlas = PixelJump.cont.getTextureAtlas("buttons");
		
		Skin buttonSkin = new Skin(); 
		buttonSkin.addRegions(buttonAtlas); 
	
		selectButtonStyle.font = PixelJump.cont.getFont(); 
		selectButtonStyle.down = buttonSkin.getDrawable("selectButtonPressed"); 
		selectButtonStyle.up = buttonSkin.getDrawable("selectButton"); 

		selectButtonStyle.fontColor = Color.BLACK; 

		lockedSelectButtonStyle.font  = PixelJump.cont.getFont(); 
		lockedSelectButtonStyle.down = buttonSkin.getDrawable("selectButtonPressed"); 
		lockedSelectButtonStyle.up = buttonSkin.getDrawable("selectButtonLocked"); 
		lockedSelectButtonStyle.fontColor = Color.BLACK;
		
		setUpMenu();
	}
	
	private void playNext(){
		gsm.playNextState(new Test(gsm));
	}
	
	private void mainMenu(){
		gsm.playNextState(new Menu(gsm)); 
	}
	
	private void playeSelectedLevel(Actor actor){
		SelectButton sBtn = (SelectButton) actor; 
		if(sBtn.getLevelNumber() == 1){
			gsm.playNextState(new Test(gsm));
		}
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
		SelectButton selectButton;
		upperTable.add("Select level").colspan(3).left().spaceBottom(20); 
		upperTable.row();

		int level = 1; 
		
		for(int y = 1; y < 3; y++){
			upperTable.add().width(selectSize/4).height(selectSize).center();
			for(int i = 1; i < 6; i++ ){
				if(level == 1){
					selectButton  = new SelectButton(Integer.toString(level), selectButtonStyle, level++);
				} else {
					selectButton  = new SelectButton(Integer.toString(level), lockedSelectButtonStyle, level++);
				}
				
				selectButton.addListener(new ChangeListener(){
					public void changed(ChangeEvent event, Actor actor){
						playeSelectedLevel(actor); 
					}
				}); 
				upperTable.add(selectButton).center().width(selectSize).height(selectSize).pad(10);
			}
			upperTable.row();
		}
		upperTable.padBottom(20); 
	}
	
	@Override
	public void dispose() {
		System.out.println("menu disposed"); 
		super.dispose(); 
	}

}
