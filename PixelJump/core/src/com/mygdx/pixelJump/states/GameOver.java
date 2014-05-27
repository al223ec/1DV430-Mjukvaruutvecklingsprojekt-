package com.mygdx.pixelJump.states;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.pixelJump.PixelJump;
import com.mygdx.pixelJump.handlers.GameStateManager;
import com.mygdx.pixelJump.states.levels.LevelState;

public class GameOver extends MenuState {
	private TextButton restartGameButton;
	private TextButton mainMenuButton; 
	
	private Label label; 

	private LevelState playedLevel;
	
	public GameOver(GameStateManager gsm, LevelState playedLevel) {
		super(gsm);
		this.playedLevel = playedLevel;
		
		mainMenuButton = new TextButton("Main menu", buttonstyle); 
		restartGameButton = new TextButton("Restart game", buttonstyle); 
		
		setUpMenu();
		
		LabelStyle ls = new LabelStyle();
		ls.font = PixelJump.cont.getHeaderFont();
		ls.fontColor = Color.RED; 
		label = new Label("Game Over!", ls); 
		
		upperTable.center(); 
		upperTable.add(label); 
	}
	
	private void restartGame(){
		playedLevel.resetLevel();
		gsm.playNextState(playedLevel);
	}
	private void mainMenu(){
		gsm.playNextState(new Menu(gsm)); 
	}
	
	private void setUpMenu(){
		super.setUpMenu(restartGameButton, mainMenuButton); 
		restartGameButton.addListener(new ChangeListener(){
			public void changed(ChangeEvent event, Actor actor){
				restartGame(); 
			}
		});
		
		mainMenuButton.addListener(new ChangeListener(){
			public void changed(ChangeEvent event, Actor actor){
				mainMenu(); 
			}
		});
	}
	
	public void render(){
		if(playedLevel != null){
			playedLevel.render(); 
		}
		super.render(); 
	}
	@Override
	public void handleInput(){ }
	@Override
	public void update(float dt) {
		if(playedLevel != null){
			playedLevel.updateWorldAndPlayer(dt);
		}
		super.update(dt);	
	} 

	@Override
	public void dispose() {
		System.out.println("Game over disposed"); 
		super.dispose(); 
	}
}
