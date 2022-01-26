package core.game;

import core.engine.graphics.Texture;

public class Shield extends MovableEntity {
	public Shield() {
		this.mesh = new Mesh(new Texture(Texture.safeLoadTex("res/images/rat shield backgroundnt.png")));
		this.x = 83;
		this.y = 2349;
		rot = 0;
		scale = 1;
	}
	
}
