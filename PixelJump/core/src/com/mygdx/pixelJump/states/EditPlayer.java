package com.mygdx.pixelJump.states;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.pixelJump.PixelJump;
import com.mygdx.pixelJump.handlers.GameStateManager;
import com.mygdx.pixelJump.states.myMenuItems.SelectButton;

public class EditPlayer extends MenuState {
	
	private TextButton startGameButton;
	private TextButton mainMenuButton; 
	private com.esotericsoftware.tablelayout.Cell<Actor> currentActor; 
	
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
		
		splash = PixelJump.cont.getTexture("editSplash");
	
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
	
	private SelectButton getSelectButton(String key, String drawableKey){
		SelectButton selectButton;
		selectButton = new SelectButton("", getButtonStyle(drawableKey), key);
		selectButton.addListener(new ChangeListener(){
			public void changed(ChangeEvent event, Actor actor){
				selectItem(actor); 
			}
		}); 
		return selectButton; 
	}
	 
	private void initHatSelection(){
		upperTable.add("EditPlayer").left(); 
		upperTable.add("Hats"); 
		
		upperTable.row();
		
		currentActor = upperTable.add().height(selectSize + 160); 
		if(PixelJump.playerSettings.getItemName() != null){
			currentActor.setWidget(setCurrentItem(PixelJump.playerSettings.getItemName()));
		}
		upperTable.add(getSelectButton("robo", "roboSmall")).center().width(selectSize).height(selectSize).pad(10);
		upperTable.add(getSelectButton("dredd", "dreddSmall")).center().width(selectSize).height(selectSize).pad(10);
		upperTable.add(getSelectButton("spider", "spiderSmall")).center().width(selectSize).height(selectSize).pad(10);
		upperTable.row();
		
		upperTable.padBottom(20); 
	}
	
	private void selectItem(Actor actor) {
		SelectButton sbtn = (SelectButton) actor; 
		PixelJump.playerSettings.setHatName(sbtn.getItemName()); 
		currentActor.setWidget(setCurrentItem(sbtn.getItemName()));
		System.out.println(sbtn.getItemName()); 	
	}

	private ImageButton setCurrentItem(String itemName){
		ImageButton imgbtn = new ImageButton(buttonSkin.getDrawable(itemName));
		imgbtn.addListener(new ChangeListener(){
			public void changed(ChangeEvent event, Actor actor){
				resetSelectedItem(); 
			}
		}); 
		return imgbtn; 
	}
	private void resetSelectedItem(){
		PixelJump.playerSettings.setHatName(null); 
		currentActor.setWidget(null); 
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
