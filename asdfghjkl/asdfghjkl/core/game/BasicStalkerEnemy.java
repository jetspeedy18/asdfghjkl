package core.game;

import java.util.Random;

import core.engine.ItemHandler;
import core.engine.graphics.Texture;

public class BasicStalkerEnemy extends GameItem {
	
	private final float speedFactor;
	
	private static Random r = new Random();
	
	private static final Texture SLOW = new Texture(Texture.safeLoadTex("res/images/enemies/pixil-frame-0 (7).png"));
	private static final Texture FAST = new Texture(Texture.safeLoadTex("res/images/enemies/pixil-frame-0 (8).png"));	
	private static final Texture HIT = new Texture(Texture.safeLoadTex("res/images/enemies/pixil-frame-0 (12).png"));
	
	private Mesh mainMesh;
	private Mesh hitMesh;
	
	private int damage;
	
	public BasicStalkerEnemy(int speedFactor, int gBound){
		this(speedFactor, r.nextInt(gBound)-gBound/2, r.nextInt(gBound)-gBound/2);
		
	}
	
	public BasicStalkerEnemy(int speedFactor, float x, float y) {
		this.speedFactor = speedFactor;
		if(speedFactor < 3){
			mainMesh = new Mesh(SLOW);
		} else {
			mainMesh = new Mesh(FAST);
		}
		mesh = mainMesh;
		hitMesh = new Mesh(HIT);
		this.x = x;
		this.y = y;
		rot = 0;
		scale = 1;
		health = 2;
		damage = 0;
	}

	@Override
	public void tick(ItemHandler handler) {
		float difX = x - handler.getPlayer().getX();
		float difY = y - handler.getPlayer().getY();
		
		float ispify = (float) Math.sqrt(difX*difX+difY*difY);
				
		x -= difX/ispify*speedFactor;
		y -= difY/ispify*speedFactor;
		
		damage --;
		if(damage <= 0){
			mesh = mainMesh;
		}
		
	}
	
	@Override
	public void damage(){
		super.damage();
		mesh = hitMesh;
		damage = 15;
	}
}
