package core.game;

import core.engine.graphics.Texture;

public class endScreen {

	private int scale = 7;
	
	private int TexId;
	
	private Mesh MapM;
	private Texture MapT;
	
	public endScreen(){
		
		try {
			TexId = Texture.loadTex("res/images/pixil-frame-0 (5).png");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MapT = new Texture(TexId);
		MapM = new Mesh(2, 2, MapT);
	}
	
	
	public void render(){
		MapM.render();
	}
	
	public int getScale(){
		return scale;
	}
	
}
