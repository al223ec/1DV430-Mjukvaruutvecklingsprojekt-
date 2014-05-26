package com.mygdx.pixelJump.states.myMenuItems;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class SelectButton extends TextButton{

	private int itemNumber; 
	private String itemName;  
	
	public int getItemNumber(){ 
		return itemNumber; 
	}
	
	public String getItemName(){
		return itemName;
	}
	
	public SelectButton(String text, TextButtonStyle selectButtonStyle, int itemNumber) {
		super(text, selectButtonStyle);
		this.itemNumber = itemNumber; 
	}

	public SelectButton(String text, TextButtonStyle selectButtonStyle, String itemName) {
		super(text, selectButtonStyle);
		this.itemName = itemName; 
	}

}
