package com.mygdx.pixelJump.states.myMenuItems;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.pixelJump.PixelJump;

public class SelectButton extends TextButton{

	private int itemNumber; 
	private String itemName;  
	private TextButtonStyle selectButtonStyle; 
	
	public int getItemNumber(){ 
		return itemNumber; 
	}
	
	public String getItemName(){
		return itemName;
	}
	
	public SelectButton(String text, Drawable drawable, String itemName){
		this(text, new TextButtonStyle(), itemName); 
		
		selectButtonStyle.up = drawable; 
		selectButtonStyle.down = drawable; 
		selectButtonStyle.font = PixelJump.cont.getFont();  
		selectButtonStyle.fontColor = Color.BLACK; 
	}
	
	public SelectButton(String text, TextButtonStyle selectButtonStyle, int itemNumber) {
		super(text, selectButtonStyle);
		this.selectButtonStyle = selectButtonStyle; 
		this.itemNumber = itemNumber; 
	}

	public SelectButton(String text, TextButtonStyle selectButtonStyle, String itemName) {
		super(text, selectButtonStyle);
		this.selectButtonStyle = selectButtonStyle; 
		this.itemName = itemName; 
	}

}
