package com.mygdx.pixelJump.handlers;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.pixelJump.PixelJump;

public class PlayerSettings {

	private String itemName; 
	
	public Texture getCurrentHatTexture() {
		if(itemName == null)
			return null; 
		
		return PixelJump.cont.getTexture(itemName); 
	}

	public void setHatName(String itemName) {
		this.itemName = itemName; 
		
	}

	public String getItemName(){
		return itemName; 
	}
}
