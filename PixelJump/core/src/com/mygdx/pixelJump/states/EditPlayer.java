package com.mygdx.pixelJump.states;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.mygdx.pixelJump.PixelJump;
import com.mygdx.pixelJump.handlers.GameStateManager;
import com.mygdx.pixelJump.states.levels.Test;
import com.mygdx.pixelJump.states.myMenuItems.SelectButton;

public class EditPlayer extends MenuState {
	
	private TextButton startGameButton;
	private TextButton mainMenuButton; 
	
	private float selectSize = 184;
	private TextButtonStyle selectButtonStyle; 
	
	public EditPlayer(GameStateManager gsm) {
		super(gsm); 			
		mainMenuButton = new TextButton("Main menu", buttonstyle); 
		startGameButton = new TextButton("Start game", buttonstyle); 

		selectButtonStyle = new TextButtonStyle();
		selectButtonStyle.font = PixelJump.cont.getFont();
		
		upperTable.add("EditPlayer").colspan(2).left(); 
		setUpMenu();

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
		
		initHatSelection(); 
	}
	
	private void initHatSelection(){
		SelectButton selectButton;
		upperTable.row();

		for(int y = 1; y < 3; y++){
			upperTable.add().width(selectSize).height(selectSize).center();
			upperTable.add().width(selectSize).height(selectSize).center();
			for(int i = 1; i < 3; i++ ){
				selectButton  = new SelectButton(Integer.toString(i), selectButtonStyle, "itemName");
				selectButton  = new SelectButton(Integer.toString(i), selectButtonStyle, "itemName");

				selectButton.addListener(new ChangeListener(){
					public void changed(ChangeEvent event, Actor actor){
						selectedHat(actor); 
					}
				}); 
				upperTable.add(selectButton).center().width(selectSize).height(selectSize).pad(10);
			}
			upperTable.row();
		}
		upperTable.padBottom(20); 
	}
	
	private void selectedHat(Actor actor) {
		SelectButton sbtn = (SelectButton) actor; 
		System.out.println(sbtn.getItemName()); 
		
	}

	@Override
	public void handleInput(){ }
	@Override
	public void update(float dt) {} 
	@Override
	public void dispose() {
		System.out.println("EditPlayer disposed"); 
		super.dispose(); 
	}
}
