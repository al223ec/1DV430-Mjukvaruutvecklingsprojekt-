package com.mygdx.pixelJump.states;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
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
	
	private float selectSize = 210;
	private TextButtonStyle selectButtonStyle; 
	
	private Skin buttonSkin; 
	
	public EditPlayer(GameStateManager gsm) {
		super(gsm); 			
		mainMenuButton = new TextButton("Main menu", buttonstyle); 
		startGameButton = new TextButton("Start game", buttonstyle); 

		selectButtonStyle = new TextButtonStyle();
		selectButtonStyle.font = PixelJump.cont.getFont();
	
		buttonAtlas = PixelJump.cont.getTextureAtlas("hats");
		buttonSkin = new Skin(); 
		buttonSkin.addRegions(buttonAtlas); 
	
		setUpMenu();
	}
	
	private void startGame(){
		gsm.playNextState(new SelectLevel(gsm));
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
	private TextButtonStyle getButtonStyle(String key){
		TextButtonStyle tbstyle = new TextButtonStyle(); 
		tbstyle.font = PixelJump.cont.getFont(); 
		tbstyle.up = buttonSkin.getDrawable(key); 
		tbstyle.down = buttonSkin.getDrawable(key); 
		return tbstyle;  
	}
	
	private SelectButton getSelectButton(String key){
		SelectButton selectButton;
		selectButton = new SelectButton("", getButtonStyle(key), key);
		selectButton.addListener(new ChangeListener(){
			public void changed(ChangeEvent event, Actor actor){
				selectedHat(actor); 
			}
		}); 
		
		return selectButton; 
	}
	 
	private void initHatSelection(){
		upperTable.add("EditPlayer").colspan(3).left(); 
		upperTable.add("Hats"); 
		
		upperTable.row();
		upperTable.add().width(selectSize).height(selectSize).center();
		upperTable.add().width(selectSize).height(selectSize).center();
		upperTable.add().width(selectSize/2).height(selectSize).center();	
			
		upperTable.add(getSelectButton("robo")).center().width(selectSize).height(selectSize).pad(10);
		upperTable.add(getSelectButton("dredd")).center().width(selectSize).height(selectSize).pad(10);
		upperTable.row();
		
		upperTable.add().width(selectSize).height(selectSize).center();
		upperTable.add().width(selectSize).height(selectSize).center();	
		upperTable.add().width(selectSize/2).height(selectSize).center();
		
		upperTable.add(getSelectButton("spider")).center().width(selectSize).height(selectSize).pad(10);
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
