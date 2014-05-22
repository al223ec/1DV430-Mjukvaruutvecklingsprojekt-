package com.me.platformer.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.me.platformer.PlatformerGame;
import com.me.platformer.handlers.GInput;
import com.me.platformer.handlers.GameStateManager;

public class GameOver extends GameState{
	//private GameState playState;  

	private TextureAtlas buttonAtlas; 
	private Button restartGameButton; 
	private Button mainMenuButton; 
	
	private Stage stage; 
	private Table table; 
	
	public GameOver(GameStateManager gsm) {
		super(gsm);
		
		stage = new Stage(); 
		stage.setViewport(PlatformerGame.WIDTH, PlatformerGame.HEIGHT); 	
		
		table = new Table(); 
		table.debug(); 
		table.setWidth(PlatformerGame.WIDTH);
		table.setHeight(PlatformerGame.HEIGHT);
		table.left().bottom(); 
		
		setUpMenu();
	}
	private void setUpMenu(){
		buttonAtlas = PlatformerGame.cont.getTextureAtlas("startButtons");// new TextureAtlas(Gdx.files.internal("res/buttons/buttons.pack")); 
		Skin buttonSkin = new Skin(); 
		buttonSkin.addRegions(buttonAtlas); 
		
		ButtonStyle startBstyle = new ButtonStyle(); 
		startBstyle.down = buttonSkin.getDrawable("startGamePress"); 
		startBstyle.up = buttonSkin.getDrawable("startGame"); 
		restartGameButton = new Button(startBstyle);
		
		
		ButtonStyle mainBstyle = new ButtonStyle(); 
		mainBstyle.down = buttonSkin.getDrawable("editPlayerPress"); 
		mainBstyle.up = buttonSkin.getDrawable("editPlayer");
		mainMenuButton = new Button(mainBstyle);
		
		
		Gdx.input.setInputProcessor(stage); 	
		
		restartGameButton.addListener(new ChangeListener(){
			public void changed(ChangeEvent event, Actor actor){
				restartLevel(); 
			}
		});
		
		mainMenuButton.addListener(new ChangeListener(){
			public void changed(ChangeEvent event, Actor actor){
				mainMenu(); 
			}
		});
		
		table.row().padBottom(33); 
		table.add(restartGameButton).padLeft(40).padRight(94);
		table.add(mainMenuButton).padLeft(94);
		
		stage.addActor(table); 	
	}
	private void restartLevel(){
		gsm.playNextState(new Test(gsm));
	}
	private void mainMenu(){
		gsm.playNextState(new Menu(gsm));
	}
	@Override
	protected void handleInput() {}

	@Override
	public void update(float dt) {
		handleInput(); 
	}

	@Override
	public void render(float dt) {
		//Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); 
		
		sb.setProjectionMatrix(cam.combined);
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		Table.drawDebug(stage);	}

	@Override
	public void dispose() {
		stage.dispose(); 
		
	}

}
