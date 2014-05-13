package com.me.platformer.states;

import static com.me.platformer.handlers.B2DVars.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.me.platformer.PlatformerGame;
import com.me.platformer.gameObjects.Player;
import com.me.platformer.handlers.B2DVars;
import com.me.platformer.handlers.GInput;
import com.me.platformer.handlers.GameStateManager;

public class Test extends LevelState {
	public Test(GameStateManager gsm) {
		super(gsm, "res/maps/test.tmx");
	}

	protected void handleInput() {
		// keyboard input
		if(GInput.isPressed(GInput.BUTTONJUMP)){
			player.jump();			
		}
		
		//Touch inputs
		if(GInput.isPressed()){
			if(GInput.x < Gdx.graphics.getWidth() / 2){//Om hen toucher vänstra delen hoppa högt 
				System.out.println("left");
				player.jump();
			}else{
				System.out.println("right");
				player.jump();
				//world.setGravity(new Vector2(0, 9.81f));
				//player.flip(); 
			}
		}
	}
	
	public void update(float dt) {
		handleInput(); 
		player.update(dt); 
		world.step(dt, 6, 2); 		
	}
	
	public void render(float dt) {
		Gdx.gl10.glClear(GL10.GL_COLOR_BUFFER_BIT); 
		
		//	if(player.getY() > 3.5){ 
			/*
			cam.position.set(player.getX() * PPM + PlatformerGame.WIDTH/4, player.getY() * PPM - (PlatformerGame.HEIGHT / 5), 0f);  
			if(debug){
				b2dCam.position.set(player.getX() + PlatformerGame.WIDTH/4/PPM, player.getY() - (PlatformerGame.HEIGHT / 5)/PPM, 0f);  
				b2dCam.update();
			}*/
		
		//cam.position.set(player.getX() * PPM + PlatformerGame.WIDTH/4, PlatformerGame.HEIGHT / 2 , 0f);  
		cam.position.lerp(new Vector3(player.getX() * PPM + PlatformerGame.WIDTH/4, player.getY() * PPM + (PlatformerGame.HEIGHT)/6, 0f), 0.08f);
		
		if(debug){
			//b2dCam.position.set(player.getX() + PlatformerGame.WIDTH/4/PPM, PlatformerGame.HEIGHT / 2/PPM, 0f);  
			b2dCam.position.lerp(new Vector3(player.getX() + PlatformerGame.WIDTH/4/PPM, player.getY() + (PlatformerGame.HEIGHT)/6/PPM, 0f), 0.08f);		
			b2dCam.update();
		}
	

		cam.update();
		tmr.setView(cam); 
		tmr.render(); 
		
		sb.setProjectionMatrix(cam.combined);//Sätter vad som ska renderas 
		sb.begin();
		player.render(sb);
		sb.end();
		
		//Rita box2d
		if(debug){
			b2dr.render(world, b2dCam.combined); 
		}
		if(player.playerHasCompletedTheLevel){
			gsm.playNextState(new LevelCompleteState(gsm)); 
			return; 
		}
		if(player.isPlayerDead()){
			gsm.playNextState(new GameOver(gsm, this)); 
			return; 
		}
	}
	
	@Override
	public void dispose() {}
	
	protected void createPlayer(){
		BodyDef bdef = new BodyDef();
		FixtureDef fDef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		
		bdef.position.set(160 / PPM, 400 / PPM); 
		bdef.type = BodyType.DynamicBody; 
		
		Body body = world.createBody(bdef);
		player = new Player(body); 
				
		shape.setAsBox(hitBoxSize/2 /PPM, hitBoxSize/2 /PPM); 
		
		fDef.shape = shape; 
		fDef.filter.categoryBits = B2DVars.BIT_PLAYER; 
		fDef.filter.maskBits = B2DVars.BIT_GROUND | B2DVars.BIT_BALL; 

		fDef.friction = 0; //Antar detta måste sättas
		body.createFixture(fDef).setUserData("player");

		//Fot sensor
		shape.setAsBox((hitBoxSize - 5)/2/PPM, 4/PPM, new Vector2(0, -hitBoxSize/2/PPM), 0);
		fDef.shape = shape; 
		fDef.filter.categoryBits = B2DVars.BIT_PLAYER; 
		fDef.filter.maskBits = B2DVars.BIT_GROUND; 
		fDef.isSensor = true; 
		body.createFixture(fDef).setUserData("footSensor");
	/*
		//Höger sensor
		shape.setAsBox(2/PPM, (hitBoxSize/2 -2)/PPM, new Vector2(hitBoxSize/2/PPM, 0), 0);
		fDef.shape = shape; 
		fDef.filter.categoryBits = B2DVars.BIT_PLAYER; 
		fDef.filter.maskBits = B2DVars.BIT_GROUND; 
		fDef.isSensor = true; 
		body.createFixture(fDef).setUserData("rightSensor");
		*/
		shape.dispose(); 
	}
	@Override
	public void resetLevel() {
		// TODO Auto-generated method stub
		
	}
}
