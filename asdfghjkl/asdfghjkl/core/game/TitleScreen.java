package core.game;

import core.engine.graphics.Texture;

public class TitleScreen {
	private int scale = 10;
	
	private int TexId;
	
	private Mesh MapM;
	private Texture MapT;
	
	public TitleScreen(){
		
		try {
			TexId = Texture.loadTex("res/images/title.png");
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
