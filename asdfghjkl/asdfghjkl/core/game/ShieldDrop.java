package core.game;

import core.engine.ItemHandler;
import core.engine.graphics.Texture;

public class ShieldDrop extends GameItem {

	public ShieldDrop(float x, float y){
		this.x = x;
		this.y = y;
		collntnt = true;

		mesh = new Mesh(new Texture(Texture.safeLoadTex("res/images/vd4yrq7ofue51.png")));
		rot = 0;
		scale = 1;
	}
	
	@Override
	public void tick(ItemHandler handler) {
		
	}

}
