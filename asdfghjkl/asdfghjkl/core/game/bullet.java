package core.game;

import java.util.List;

import core.engine.graphics.Texture;
import core.engine.input.KeyMap;
import core.engine.input.KeyMap.ACTIONS;

public class bullet extends MovableEntity {
	private float speedFactor;
	private int dir;
	public List<bullet> bullets;
	
	
	public bullet(float x, float y, Player p) {
		this.mesh = new Mesh(new Texture(Texture.safeLoadTex("res/images/bullet.png")));
		this.x = x;
		this.y = y;
		rot = 0;
		scale = 1;
		speedFactor = 20;
		dir = this.dir=p.getDir();
	}
	
	public void setX(double x) {
		this.x = (float) x;
	}
	public void setY(double d) {
		this.y = (float) d;
	}

	public void tick(List<GameItem> items){
	
		double tx = 0;
		double ty = 0;
		if(this.dir == 90){
			ty++;
		}
		if(this.dir == 270){
			ty--;
		}
		if(this.dir == 180){
			tx--;
		}
		if(this.dir == 0){
			tx++;
		}
		

		this.x += tx * speedFactor;
		this.y += ty * speedFactor;
		
		GameItem byby = null;
		
		for (GameItem Item: items) {
			if (isCollided(Item)) {
				byby = Item;
			}
		}
		
		if(byby != null) items.remove(byby);
	}
}
