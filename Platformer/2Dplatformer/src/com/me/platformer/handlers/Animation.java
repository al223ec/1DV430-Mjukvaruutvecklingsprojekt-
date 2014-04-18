package com.me.platformer.handlers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {
	//Kommer att gå igenom de aktuella spritesen
	//Använder mig av textureRegioner
	private TextureRegion[] frames; 
	private float time; 
	private float delay; 
	private int currentFrame; 
	private int timesPlayed; 
	
	public Animation(){
	//Felaktigt animation objekt	
	}
	
	public Animation(TextureRegion[] frames){
		this(frames, 1/12f); 
	}
	
	public Animation(TextureRegion[] frames, float delay){
		this.delay = delay; 
		this.frames = frames;
		time = 0;
		currentFrame = 0; 
		timesPlayed = 0; 
	}
	
	public void setDelay(float delay) { this.delay = delay; }
	public void setCurrentFrame(int i){ 
		if(i < frames.length && i > 0){
			currentFrame = i; 
		}
	}
	public void setFrames(TextureRegion[] frames, float delay){
		this.frames = frames; 
		this.delay  = delay;
		time = 0;
		currentFrame = 0; 
		timesPlayed = 0; 
	}
	
	public void update(float dt){
		if(delay <= 0){
			return; 
		}
		time += dt; 
		while(time >= delay){
			step(); 
		}
	}
	private void step(){
		time -= delay; 
		currentFrame++;
		if(currentFrame == frames.length){
			currentFrame = 0; 
			timesPlayed++; 
		}
	}
	
	public TextureRegion getFrame() { return frames[currentFrame]; }
	public int getTimesPlayed(){ return timesPlayed; }
	public boolean hasPlayedOnce() { return timesPlayed > 0; }
}
