package core.game;

import java.util.List;

import core.engine.EkranoPal;
import core.engine.ItemHandler;
import core.engine.graphics.Texture;

public class bullet extends MovableEntity {
	private float speedFactor;
	private int dir;
	private boolean done = false;
	private final boolean playerOwner;
	
	
	public bullet(float x, float y, int dir, boolean playerOwner) {
		this.mesh = new Mesh(new Texture(Texture.safeLoadTex("res/images/bullet.png")));
		this.x = x;
		this.y = y;
		rot = 0;
		scale = 1;
		speedFactor = 20;
		this.dir = dir;
		this.playerOwner = playerOwner;
	}
	
	public void setX(double x) {
		this.x = (float) x;
	}
	public void setY(double d) {
		this.y = (float) d;
	}
	
	public void go(float x, float y, int dir) {
		this.x = x;
		this.y = y;
		this.dir=dir;

		done = false;

	}

	public boolean tick(List<GameItem> items, ItemHandler handler){
		double tx = 0;
		double ty = 0;
		rot = dir-90;
		
		tx = Math.cos(Math.toRadians(dir));
		ty = Math.sin(Math.toRadians(dir));
		

		this.x += tx * speedFactor;
		this.y += ty * speedFactor;
		
		GameItem byby = null;
		
		for (GameItem Item: items) {
			if (isCollided(Item)) {
				byby = Item;
				Item.damage();
				if(!playerOwner){
					if(Item instanceof TankPal || Item instanceof EkranoPal){
						break;
					}
				}
				done = true;
				break;
			}
		}
		
		if(byby != null) {
			if(byby.getHealth() <= 0){
				if(byby instanceof Boss){
					return true;
				} else if(!(byby instanceof HealthDrop) && !(byby instanceof ShieldDrop) && !(byby instanceof ShrinkDrop) && !(byby instanceof TankPal) && !(byby instanceof  EkranoPal)){
					handler.kill(byby);
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

	
	public boolean kill(){
		return done;
	}


	public boolean playerOwner() {
		return playerOwner;
	}
}
