package com.me.platformer.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.me.platformer.PlatformerGame;
import com.me.platformer.handlers.GInput;
import com.me.platformer.handlers.GameStateManager;

public class Menu extends GameState {
	private Stage stage; 
	private Label welcomeLabel; 
	private LabelStyle ls; 
	
	private BitmapFont font; 
	private Skin skin; 
	private TextureAtlas buttonAtlas;
	 
	private Button startGameButton;
	private Button editPlayerButton; 
	
	private Texture splash; 
	
	public Menu(GameStateManager gsm) {
		super(gsm); 	

		stage = new Stage(); 
		stage.setViewport(PlatformerGame.WIDTH, PlatformerGame.HEIGHT); 	
		
		Table table = new Table(); 
		table.debug(); 
		table.setWidth(PlatformerGame.WIDTH);
		table.setHeight(PlatformerGame.HEIGHT);
		table.left().bottom(); 
		
		buttonAtlas = new TextureAtlas(Gdx.files.internal("res/buttons/buttons.pack")); 
		Skin buttonSkin = new Skin(); 
		buttonSkin.addRegions(buttonAtlas); 
		 
		splash = PlatformerGame.cont.getTexture("splash");
		
		ButtonStyle startBstyle = new ButtonStyle(); 
		startBstyle.down = buttonSkin.getDrawable("startGamePress"); 
		startBstyle.up = buttonSkin.getDrawable("startGame"); 
		startGameButton = new Button(startBstyle);
		
		
		ButtonStyle editBstyle = new ButtonStyle(); 
		editBstyle.down = buttonSkin.getDrawable("editPlayerPress"); 
		editBstyle.up = buttonSkin.getDrawable("editPlayer");
		editPlayerButton = new Button(editBstyle);
		
		
		Gdx.input.setInputProcessor(stage); 	
		
		startGameButton.addListener(new ChangeListener(){
			public void changed(ChangeEvent event, Actor actor){
				playNext(); 
			}
		});
		
		/*
		LabelStyle labelStyle = new LabelStyle(); 
		labelStyle.font = new BitmapFont(); 
		Skin tableSkin = new Skin();
		tableSkin.add("default", labelStyle); 		
		table.setSkin(tableSkin); 
		*/
		table.row().padBottom(33); 
		table.add(startGameButton).padLeft(40).padRight(94);
		table.add(editPlayerButton).padLeft(94);
		
		stage.addActor(table); 
	}
	
	private void createButtons(){
		
	}
	private void playNext(){
		gsm.playNextState(new Test(gsm));
	}
	private void editPlayer(){
		
	}
	private void setUpMenu(){
		
		
	}
	
	public void handleInput(){
	}
	@Override
	public void update(float dt) {
		handleInput();
	
	} 

	@Override
	public void render(float dt) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); 
		
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		sb.draw(splash, 0, 0);

		sb.end();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		Table.drawDebug(stage); 
	}

	@Override
	public void dispose() {}

}
