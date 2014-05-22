package com.me.platformer.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.me.platformer.PlatformerGame;
import com.me.platformer.handlers.GameStateManager;

public class Menu extends GameState {
	private Stage stage; 
	private TextureAtlas buttonAtlas;
	
	private Table table; 
	private Button startGameButton;
	private Button editPlayerButton; 
	
	private Texture splash; 
	
	public Menu(GameStateManager gsm) {
		super(gsm); 	
		
		splash = PlatformerGame.cont.getTexture("splash");

		cam.setToOrtho(false, PlatformerGame.WIDTH, PlatformerGame.HEIGHT);
		stage = new Stage(); 
		stage.setViewport(PlatformerGame.WIDTH, PlatformerGame.HEIGHT); 	
		
		table = new Table(); 
		table.debug(); 
		table.setWidth(PlatformerGame.WIDTH);
		table.setHeight(PlatformerGame.HEIGHT);
		table.left().bottom(); 
		
		setUpMenu(); 
		/*
		LabelStyle labelStyle = new LabelStyle(); 
		labelStyle.font = new BitmapFont(); 
		Skin tableSkin = new Skin();
		tableSkin.add("default", labelStyle); 		
		table.setSkin(tableSkin); 
		*/
	}
	private void playNext(){
		gsm.playNextState(new Test(gsm));
	}
	private void editPlayer(){
		gsm.playNextState(new EditPlayer(gsm)); 
	}
	private void setUpMenu(){
		buttonAtlas = PlatformerGame.cont.getTextureAtlas("startButtons");// new TextureAtlas(Gdx.files.internal("res/buttons/buttons.pack")); 
		Skin buttonSkin = new Skin(); 
		buttonSkin.addRegions(buttonAtlas); 
	
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
		
		editPlayerButton.addListener(new ChangeListener(){
			public void changed(ChangeEvent event, Actor actor){
				editPlayer(); 
			}
		});
		
		table.row().padBottom(33); 
		table.add(startGameButton).padLeft(40).padRight(94);
		table.add(editPlayerButton).padLeft(94);
		
		stage.addActor(table); 	
	}
	
	@Override
	public void handleInput(){ }
	@Override
	public void update(float dt) {} 

	@Override
	public void render(float dt) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); 
		
		cam.update();
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		sb.draw(splash, 0, 0);
		sb.end();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		Table.drawDebug(stage); 
	}

	@Override
	public void dispose() {
		//startGameButton.remove(); 
		//editPlayerButton.remove();
		System.out.println("menu disposed"); 
		stage.dispose(); 
	}

}
