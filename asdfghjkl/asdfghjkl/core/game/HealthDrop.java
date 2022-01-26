package core.game;

import core.engine.ItemHandler;
import core.engine.graphics.Texture;

public class HealthDrop extends GameItem {

	public HealthDrop(float x, float y){
		this.x = x;
		this.y = y;
		collntnt = true;

		mesh = new Mesh(new Texture(Texture.safeLoadTex("res/images/pixil-frame-0 (13).png")));
		rot = 0;
		scale = 1;
	}
	
	@Override
	public void tick(ItemHandler handler) {
		
	}
}
