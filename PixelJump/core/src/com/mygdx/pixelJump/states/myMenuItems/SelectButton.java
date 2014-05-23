package com.mygdx.pixelJump.states.myMenuItems;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class SelectButton extends TextButton{

	private int levelNumber; 
	public int getLevelNumber(){ 
		return levelNumber; 
	}
	public SelectButton(String text, TextButtonStyle selectButtonStyle, int levelNumber) {
		super(text, selectButtonStyle);
		// TODO Auto-generated constructor stub
		this.levelNumber = levelNumber; 
	}

}
