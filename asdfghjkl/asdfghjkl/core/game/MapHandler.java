package core.game;

import core.engine.graphics.Texture;

public class MapHandler {
	
	private int scale;
	private int mapTexId;
	
	private Mesh MapM;
	private Texture MapT;
	
	public MapHandler(){
		
		try {
			mapTexId = Texture.loadTex("res/maps/pixil-frame-0.png");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MapT = new Texture(mapTexId);
		MapM = new Mesh(2, 2, MapT);
		
		scale = 5;
	}
	
	public void reset(){
		scale = 5;
	}
	
	
	public void render(){
		MapM.render();
	}
	
	public boolean advanceLevel(){
		scale += 5;
		if(scale > 25){
			return true;
		}
		return false;
	}
	
	public int getMapBounds(){
		return 60*scale;
	}
	
	public int getScale(){
		return scale;
	}
	
}
