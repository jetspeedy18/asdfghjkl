package core.game;

import core.engine.graphics.Texture;

public class Shield extends MovableEntity {
	public Shield(float x, float y, Player p) {
		this.mesh = new Mesh(new Texture(Texture.safeLoadTex("res/images/vd4yrq7ofue51.png")));
		this.x = x;
		this.y = y;
		rot = 0;
		scale = 1;
	}
	
	public void tick() {
		
	}
}
