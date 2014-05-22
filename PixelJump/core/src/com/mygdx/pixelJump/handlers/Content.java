package com.mygdx.pixelJump.handlers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Content {
//Denna klass ska sköta importeringen av texturer andra saker. 
	
	private HashMap<String, Texture> textures; 
	private BitmapFont font; 
	private BitmapFont headerFont; 
	
	private HashMap<String, TextureAtlas> textureAtlases; 
	
	public Content (){
		textures = new HashMap<String, Texture>(); 
		textureAtlases = new HashMap<String, TextureAtlas>();
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("res/font/Oboe.ttf")); 
		FreeTypeFontParameter parameter = new FreeTypeFontParameter(); 
		parameter.size = 46; 
		parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS;
		font = generator.generateFont(parameter); 
		parameter.size = 100; 
		headerFont = generator.generateFont(parameter); 
		generator.dispose();
	}
	
	public BitmapFont getFont(){
		return font; 
	}
	
	public BitmapFont getHeaderFont(){
		return headerFont; 
	}
	public void loadTextures(String path, String key){
		System.out.println("loadingfile: " + path + " key: " + key); 
		Texture tex = new Texture(Gdx.files.internal(path)); 
		textures.put(key, tex); 		
	}
	
	public Texture getTexture(String key){
		return textures.get(key); 
	}
	
	public TextureAtlas getTextureAtlas(String key){
		return textureAtlases.get(key); 
	}
	
	public void loadTextureAtlas(String path, String key){
		TextureAtlas texAtlas = new TextureAtlas(Gdx.files.internal(path)); 
		textureAtlases.put(key, texAtlas);
	}
	
	public void disposeTexture(String key){
		Texture tex = textures.get(key); 
		if(tex != null){
			tex.dispose(); 
		}
	}

	public void disposeAll() {
		// TODO Auto-generated method stub
		//Ta bort alla texturer
		
	}
}
