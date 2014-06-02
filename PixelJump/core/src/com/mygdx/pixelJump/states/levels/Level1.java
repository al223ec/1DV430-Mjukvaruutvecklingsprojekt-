package com.mygdx.pixelJump.states.levels;
import static com.mygdx.pixelJump.handlers.B2DVars.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.pixelJump.PixelJump;
import com.mygdx.pixelJump.gameObjects.Background;
import com.mygdx.pixelJump.gameObjects.Player;
import com.mygdx.pixelJump.handlers.B2DVars;
import com.mygdx.pixelJump.handlers.GInput;
import com.mygdx.pixelJump.handlers.GameStateManager;
import com.mygdx.pixelJump.states.GameOver;
import com.mygdx.pixelJump.states.LevelCompleteState;

public class Level1 extends LevelState {
	
	public Level1(GameStateManager gsm) {
		super(gsm, "level1");  
		background = new Background(gsm.getCamera()); 
	}

	protected void handleInput(){
		if(GInput.isPressed()){
			if(GInput.x < Gdx.graphics.getWidth() / 2){
				player.jump();
			}else{
				player.flip();
			}
		}
	}
	
	public void update(float dt) {
		if(!player.isPlayerDead()){
			handleInput();
			world.step(PixelJump.STEP, 1, 1); 	
			background.update();
			player.update(dt);
			elapsedTime += dt;
		}else{
			gsm.playNextState(new GameOver(gsm, this));
			return;
		}
		if(player.playerHasCompletedTheLevel){
			gsm.playNextState(new LevelCompleteState(gsm, this)); 
			return; 
		} 
	}
	
	public void updateWorldAndPlayer(float dt){
		world.step(PixelJump.STEP, 1, 1); 	
		player.update(dt);
	}
	@Override
	public void dispose() {}

	protected void createPlayer(){
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		
		bdef.position.set(160 / PPM, 400 / PPM); 
		bdef.type = BodyType.DynamicBody; 
		
		Body body = world.createBody(bdef);
		player = new Player(body); 

		shape.setAsBox(hitBoxSize/2 /PPM, hitBoxSize/2 /PPM); 
		fdef.shape = shape; 
		fdef.filter.categoryBits = B2DVars.BIT_PLAYER; 
		fdef.filter.maskBits = B2DVars.BIT_GROUND | B2DVars.BIT_BALL; 

		fdef.friction = 0; //Antar detta måste sättas
		body.createFixture(fdef).setUserData("player");
		
		//Fot sensor
		shape.setAsBox((hitBoxSize - 5)/2/PPM, 4/PPM, new Vector2(0, -hitBoxSize/2/PPM), 0);
		fdef.shape = shape; 
		fdef.filter.categoryBits = B2DVars.BIT_PLAYER; 
		fdef.filter.maskBits = B2DVars.BIT_GROUND; 
		fdef.isSensor = true; 
		body.createFixture(fdef).setUserData("footSensor");
		shape.dispose(); 
		
	}
}
