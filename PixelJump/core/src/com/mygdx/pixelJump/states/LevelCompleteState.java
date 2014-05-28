package com.mygdx.pixelJump.states;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.pixelJump.PixelJump;
import com.mygdx.pixelJump.handlers.GameStateManager;
import com.mygdx.pixelJump.states.levels.Level2;
import com.mygdx.pixelJump.states.levels.LevelState;


public class LevelCompleteState extends MenuState{
	private TextButton nextLevelButton;
	private TextButton mainMenuButton; 
	
	private Label label; 
	private LevelState playedLevel; 

	public LevelCompleteState(GameStateManager gsm, LevelState playedLevel) {
		this(gsm); 			
		this.playedLevel = playedLevel; 
	}
	
	public LevelCompleteState(GameStateManager gsm) {
		super(gsm); 			
		mainMenuButton = new TextButton("Main menu", buttonstyle); 
		nextLevelButton = new TextButton("Next level", buttonstyle); 
		setUpMenu();
		
		LabelStyle ls = new LabelStyle();
		ls.font = PixelJump.cont.getHeaderFont();

		label = new Label("       Level \nCompleted!", ls); 
		
		upperTable.center(); 
		upperTable.add(label); 
	}
	private void nextLevel(){
		gsm.playNextState(new Level2(gsm));
	}
	private void mainMenu(){
		gsm.playNextState(new Menu(gsm)); 
	}
	
	private void setUpMenu(){
		super.setUpMenu(nextLevelButton, mainMenuButton); 
		nextLevelButton.addListener(new ChangeListener(){
			public void changed(ChangeEvent event, Actor actor){
				nextLevel(); 
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
	public void dispose() {
		System.out.println("LevelCompleted disposed"); 
		super.dispose(); 
	}
}
