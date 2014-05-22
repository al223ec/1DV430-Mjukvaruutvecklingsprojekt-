package com.me.platformer.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.me.platformer.PlatformerGame;
import com.me.platformer.gameObjects.Hat;
import com.me.platformer.gameObjects.Player;
import com.me.platformer.handlers.GInput;
import com.me.platformer.handlers.GInputProcessor;
import com.me.platformer.handlers.GameStateManager;
import com.me.platformer.handlers.PlayerSettings;

public class EditPlayer extends GameState{

	private Hat hat; 
	
	private TextureAtlas allHats; 
	private Texture background; 
	
	private int currentHat = 0; 
	private int numOfHats = 5; 
	
	private PlayerSettings playerSettings; 
	
	private Stage stage; 
	private Table bTable;  
	private Table uTable;
	
	private TextureAtlas buttonAtlas; 
	private Button startGameButton;
	private Button mainMenuButton;
	
	
	public EditPlayer(GameStateManager gsm) {
		super(gsm);
		Gdx.input.setInputProcessor(new GInputProcessor());
		background = PlatformerGame.cont.getTexture("splash");
		playerSettings = PlatformerGame.playerSettings; 
		
		stage = new Stage(); 
		stage.setViewport(PlatformerGame.WIDTH, PlatformerGame.HEIGHT); 	
		uTable = new Table(); 
		uTable.debug(); 
		uTable.setWidth(PlatformerGame.WIDTH);
		uTable.setHeight(700);
		uTable.top().left(); 
		uTable.setOriginY(100); 
		
		bTable = new Table(); 
		bTable.debug(); 
		bTable.setWidth(PlatformerGame.WIDTH);
		bTable.setHeight(179);
		
		LabelStyle labelStyle = new LabelStyle(); 
		labelStyle.font = new BitmapFont(); 
		Skin tableSkin = new Skin();
		tableSkin.add("default", labelStyle); 		
		
		uTable.setSkin(tableSkin); 
		/*
		TextButtonStyle tbs = new TextButtonStyle(); 
		Button lb = new Button(tbs);
		lb.addListener(new ChangeListener(){
			public void changed(ChangeEvent event, Actor actor){
				nextHat(); 
			} 
		});
		Button rb = new Button(tbs);
		rb.addListener(new ChangeListener(){
			public void changed(ChangeEvent event, Actor actor){
				prevHat(); 
			} 
		});
		uTable.add(lb).height(542).width(556);
		uTable.add(rb).height(542).width(556);
		uTable.row(); 
		 */
		uTable.add("test"); 
		stage.addActor(uTable);
		bTable.left().bottom(); 
		setUpMenu(); 
	}
	private void setUpMenu(){
		buttonAtlas = PlatformerGame.cont.getTextureAtlas("startButtons");// new TextureAtlas(Gdx.files.internal("res/buttons/buttons.pack")); 
		Skin buttonSkin = new Skin(); 
		buttonSkin.addRegions(buttonAtlas); 
		
		ButtonStyle startBstyle = new ButtonStyle(); 
		startBstyle.down = buttonSkin.getDrawable("startGamePress"); 
		startBstyle.up = buttonSkin.getDrawable("startGame"); 
		startGameButton = new Button(startBstyle);
		
		
		ButtonStyle mainBstyle = new ButtonStyle(); 
		mainBstyle.down = buttonSkin.getDrawable("editPlayerPress"); 
		mainBstyle.up = buttonSkin.getDrawable("editPlayer");
		mainMenuButton = new Button(mainBstyle);
		
		
		Gdx.input.setInputProcessor(stage); 	
		
		startGameButton.addListener(new ChangeListener(){
			public void changed(ChangeEvent event, Actor actor){
				///playNext(); 
			}
		});
		
		mainMenuButton.addListener(new ChangeListener(){
			public void changed(ChangeEvent event, Actor actor){
				//editPlayer(); 
			}
		});
		
		bTable.row().padBottom(33); 
		bTable.add(startGameButton).padLeft(40).padRight(94);
		bTable.add(mainMenuButton).padLeft(94);
		
		stage.addActor(bTable); 	
	}

	@Override
	protected void handleInput() {
	}

	private void nextHat(){
		System.out.println("nextHat");
		
	}
	private void prevHat(){
		System.out.println("prevHat");
		
	}
	
	@Override
	public void update(float dt) {
		handleInput(); 
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float dt) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); 
		
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		sb.draw(background, 0, 0);
		sb.end();	
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		Table.drawDebug(stage); 
	}

	@Override
	public void dispose() {
		stage.dispose(); 
		// TODO Auto-generated method stub
		
	}

}
