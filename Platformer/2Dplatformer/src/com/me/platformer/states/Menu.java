package com.me.platformer.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.me.platformer.PlatformerGame;
import com.me.platformer.handlers.GInput;
import com.me.platformer.handlers.GameStateManager;

public class Menu extends GameState {
	private Stage stage; 
	private Label welcomeLabel; 
	private LabelStyle ls; 
	private TextButton startGameButton;
	private TextButtonStyle textButtonStyle; 
	private BitmapFont font; 
	private Skin skin; 
	private TextureAtlas buttonAtlas; 
	
	public Menu(GameStateManager gsm) {
		super(gsm); 	
		stage = new Stage(); 
		stage.setViewport(PlatformerGame.WIDTH, PlatformerGame.HEIGHT); 	
		
		Gdx.input.setInputProcessor(stage); 
		font = new BitmapFont(); 
		skin = new Skin(); //behöver nog hantera skins
		//buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/buttons.pack")); 
		//skin.addRegions(buttonAtlas); 
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = font; 
		
		ls = new LabelStyle(); 
		ls.font = font; 
		
		welcomeLabel = new Label("Välkommen", ls); 
		//textButtonStyle.up = skin.getDrawable("up-button"); 
		//textButtonStyle.down = skin.getDrawable("checked-button"); 
		startGameButton = new TextButton("- Touch here to start game -", textButtonStyle); 

		startGameButton.addListener(new ChangeListener(){
			public void changed(ChangeEvent event, Actor actor){
				System.out.println("Button pressed"); 
				playNext(); 
			}
		});
	
		Table table = new Table(); 
		table.setFillParent(true); 
		stage.addActor(table); 
		table.add(welcomeLabel).spaceBottom(50);
		table.row(); 
		table.add(startGameButton).size(300f, 60f).uniform().spaceBottom(50); 
		table.row(); 
		
	}
	private void playNext(){
		gsm.playNextState(new Test(gsm));
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
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		//sb
	}

	@Override
	public void dispose() {}

}
