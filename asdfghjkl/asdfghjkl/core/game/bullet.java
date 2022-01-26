package core.game;

import java.util.List;

import core.engine.graphics.Texture;
import core.engine.input.KeyMap;
import core.engine.input.KeyMap.ACTIONS;

public class bullet extends MovableEntity {
	private float speedFactor;
	private int dir;
	public List<bullet> bullets;
	private boolean done = false;
	private boolean inBounds;
	private boolean rbullet;
	private boolean inMotion;
	
	
	public bullet(float x, float y, Player p) {
		this.mesh = new Mesh(new Texture(Texture.safeLoadTex("res/images/bullet.png")));
		this.x = x;
		this.y = y;
		rot = 0;
		scale = 1;
		speedFactor = 20;
		dir = this.dir=p.getDir();
		inBounds = true;
		rbullet = false;
		inMotion = false;
	}
	
	public void setrBullet(boolean x) {
		this.rbullet = x;
	}
	public boolean getrBullet() {
		return rbullet;
	}

	
	public void setX(double x) {
		this.x = (float) x;
	}
	public void setY(double d) {
		this.y = (float) d;
	}
	
	public void go(float x, float y, Player p) {
		this.x = x;
		this.y = y;
		this.dir=p.getDir();
		inMotion = true;
		done = false;
	}

	public boolean tick(List<GameItem> items){
		double tx = 0;
		double ty = 0;
		if(this.dir == 90){
			this.rot=0;
			ty++;
		}
		if(this.dir == 270){
			this.rot=180;
			ty--;
		}
		if(this.dir == 180){
			this.rot=90;
			tx--;
		}
		if(this.dir == 0){
			this.rot=270;
			tx++;
		}
		

		this.x += tx * speedFactor;
		this.y += ty * speedFactor;
		
		GameItem byby = null;
		
		for (GameItem Item: items) {
			if (isCollided(Item)) {
				byby = Item;
				Item.damage();
				done = true;
				break;
			}
		}
		
		if(byby != null) {
			if(byby.getHealth() <= 0){
				if(byby instanceof Boss){
					return true;
				} else {
					items.remove(byby);
				}
			}
		}
		return false;
	}
	
	@Override
	public void mapClamp(MapHandler map){
		double lx = x;
		double ly = y;
		super.mapClamp(map);
		
		if(lx != x || ly != y) done = true;
	}
	public boolean inBounds() {
		return this.inBounds;
	}
	
	
	public boolean kill(){
		return done;
	}

	public boolean getInMotion() {
		return inMotion;
	}
	public void setInMotion(boolean x) {
		inMotion = x;
	}
}
