package core.game;

import core.engine.ItemHandler;
import core.engine.graphics.Texture;

public class ShrinkDrop extends GameItem{

	public ShrinkDrop(float x, float y){
		this.x = x;
		this.y = y;
		collntnt = true;

		mesh = new Mesh(new Texture(Texture.safeLoadTex("res/images/pixil-frame-0 (14).png")));
		rot = 0;
		scale = 1;
	}
	
	@Override
	public void tick(ItemHandler handler) {
		
	}
}
