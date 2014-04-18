package com.me.platformer.handlers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class GInputProcessor extends InputAdapter{
	public boolean mouseMoved(int x, int y) {
		return true;
	}
	
	public boolean touchDragged(int x, int y, int pointer) {
		return true;
	}
	
	public boolean touchDown(int x, int y, int pointer, int button) {
		return true;
	}
	
	public boolean touchUp(int x, int y, int pointer, int button) {
		return true;
	}
	
	public boolean keyDown(int k) {
		if(k == Keys.Z) GInput.setKey(GInput.BUTTONJUMP, true);
		if(k == Keys.X) GInput.setKey(GInput.BUTTONFLIP, true); 
		return true;
	}
	
	public boolean keyUp(int k) {
		if(k == Keys.Z) GInput.setKey(GInput.BUTTONJUMP, false);
		if(k == Keys.X) GInput.setKey(GInput.BUTTONFLIP, false); 
		return true;
	}
}
