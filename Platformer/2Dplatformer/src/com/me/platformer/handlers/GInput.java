package com.me.platformer.handlers;

public class GInput {
	public static int x; 
	public static int y; //Vet inte om jag kommer defakto använda y kordinaten
	
	public static boolean touchDown; 
	public static boolean ptouchDown; //Tveksam om jag behöver denna

	//Statisk klass som kommer hantera input vilken knapp som trycks, vilken föregående knapp som trycktes
	public static boolean[] keys; //aktuella
	public static boolean[] pkeys; //prev
	
	public static final int NUM_KEYS = 2; //Antalet kontroller el knappar du kan trycka på
	public static final int BUTTONJUMP = 0;
	public static final int BUTTONFLIP = 1; 
	
	
	static {
		keys = new boolean[NUM_KEYS]; 
		pkeys = new boolean[NUM_KEYS]; 
	}
	
	public static void update(){
		for(int i =0; i < NUM_KEYS; i++){
			 pkeys[i] = keys[i];
		}
	}
	
	public static boolean isDown(){
		return touchDown; 
	}
	
	public static boolean isPressed(){
		return touchDown && !ptouchDown; 
	}
	
	public static boolean isReleased(){
		return !touchDown && ptouchDown; 
	}
	
	public static void setKey(int i, boolean b){
		keys[i] = b; 
	}
	public static boolean isDown(int i){
		return keys[i]; 
	}
	public static boolean isPressed(int i){ 
		return keys[i] && !pkeys[i]; 
	} 
}
