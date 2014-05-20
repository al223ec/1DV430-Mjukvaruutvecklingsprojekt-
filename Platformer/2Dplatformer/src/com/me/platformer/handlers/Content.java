package com.me.platformer.handlers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Content {
//Denna klass ska sköta importeringen av texturer andra saker. 
	
	private HashMap<String, Texture> textures; 
	
	private HashMap<String, TextureAtlas> textureAtlases; 
	
	public Content (){
		textures = new HashMap<String, Texture>(); 
		textureAtlases = new HashMap<String, TextureAtlas>(); 
	}
	
	public void loadTextures(String path, String key){
		System.out.println("loadingfile: " + path + " key: " + key); 
		Texture tex = new Texture(Gdx.files.internal(path)); 
		textures.put(key, tex); 		
	}
	
	public Texture getTexture(String key){
		return textures.get(key); 
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
}
