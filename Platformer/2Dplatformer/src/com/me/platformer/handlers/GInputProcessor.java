package com.me.platformer.handlers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class GInputProcessor extends InputAdapter{
	/*
	 * public interface InputProcessor
	 * An InputProcessor is used to receive input events from the keyboard and the touch screen 
	 * (mouse on the desktop). For this it has to be registered with the 
	 * Input.setInputProcessor(InputProcessor) method. It will be called each frame before the 
	 * call to ApplicationListener.render(). 
	 *  Each method returns a boolean in case you want to use this with the InputMultiplexer 
	 *  to chain input processors.
	 */
	public boolean mouseMoved(int x, int y) {
		return true;
	}
	/*
	 * Called when the screen was touched or a mouse button was pressed. The button parameter 
	 * will be Input.Buttons.LEFT on Android and iOS.
	 * Parameters:
	 * 	screenX - The x coordinate, origin is in the upper left corner
	 * 	screenY - The y coordinate, origin is in the upper left corner
	 * 	pointer - the pointer for the event.
	 * 	button - the button
	 * Returns:
	 * 	whether the input was processed
	*/
	public boolean touchDragged(int x, int y, int pointer) {
		GInput.x = x; 
		GInput.y = y;
		GInput.touchDown = true; 
		return true;
	}
	
	public boolean touchDown(int x, int y, int pointer, int button) {
		GInput.x = x; 
		GInput.y = y;
		GInput.touchDown = true; 
		return true;
	}
	
	public boolean touchUp(int x, int y, int pointer, int button) {
		GInput.x = x; 
		GInput.y = y; 
		GInput.touchDown = false; 
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
