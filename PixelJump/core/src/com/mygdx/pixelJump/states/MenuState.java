package com.mygdx.pixelJump.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.pixelJump.PixelJump;
import com.mygdx.pixelJump.handlers.GameStateManager;

public abstract class MenuState extends GameState {
	
	protected Stage stage; 
	protected TextureAtlas buttonAtlas;
	
	protected Table table; 

	protected Table upperTable; 
	
	protected Texture splash;
	protected TextButtonStyle buttonstyle; 
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
		
		cam.setToOrtho(false, PixelJump.WIDTH, PixelJump.HEIGHT);
		stage = new Stage( new StretchViewport(PixelJump.WIDTH, PixelJump.HEIGHT)); 
	
		table = new Table(); 
		table.debug(); 
		table.setWidth(PixelJump.WIDTH);
		table.setHeight(PixelJump.HEIGHT);
		table.left().bottom(); 
		setUpButtonStyle(); 
		
		upperTable = new Table(); 
		upperTable.debug(); 
		upperTable.setWidth(PixelJump.WIDTH);
		upperTable.setHeight(700);
		upperTable.top();
		upperTable.setOriginY(100); 
		
		LabelStyle labelStyle = new LabelStyle(); 
		labelStyle.font = PixelJump.cont.getFont(); 
		labelStyle.fontColor = Color.BLACK; 
		
		Skin tableSkin = new Skin();
		tableSkin.add("default", labelStyle);
		upperTable.setSkin(tableSkin); 
		stage.addActor(upperTable); 
	}
	
	private void setUpButtonStyle(){
		buttonstyle = new TextButtonStyle();  
		buttonAtlas = PixelJump.cont.getTextureAtlas("buttons");
		Skin buttonSkin = new Skin(); 
		buttonSkin.addRegions(buttonAtlas); 
	
		buttonstyle.font = PixelJump.cont.getFont(); 
		buttonstyle.down = buttonSkin.getDrawable("pressed"); 
		buttonstyle.up = buttonSkin.getDrawable("button"); 
		
		buttonstyle.fontColor = Color.BLACK; 
	}
	public void setUpMenu(TextButton button){}

	public void setUpMenu(TextButton leftButton, TextButton rightButton){
		Gdx.input.setInputProcessor(stage); 	
		table.row().padBottom(33); 
		table.add(leftButton).padLeft(40).padRight(94);
		table.add(rightButton).padLeft(94);
		stage.addActor(table); 	
	}

	
	@Override
	protected void handleInput(){}
	@Override
	public void update(float dt){}
	@Override
	public void render(){
		if(splash != null)
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
		
		cam.update();
		sb.setProjectionMatrix(cam.combined);
		if(splash != null){
			sb.begin();
			sb.draw(splash, 0, 0);
			sb.end();
		}
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		//Table.drawDebug(stage); 
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose(); 
	}

}
