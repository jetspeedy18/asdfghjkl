package core.game;

import java.util.Random;

import core.engine.ItemHandler;
import core.engine.graphics.Texture;

public class BaseDumbEnemey extends GameItem {

	private Random r = new Random();
	
	private final float speedFactorx;
	private final float speedFactory;	
	
	private boolean dirX = r.nextBoolean();
	private boolean dirY = r.nextBoolean();

	private static final Texture SLOW = new Texture(Texture.safeLoadTex("res/images/enemies/pixil-frame-0 (1).png"));
	private static final Texture FAST = new Texture(Texture.safeLoadTex("res/images/enemies/pixil-frame-0 (2).png"));
	
	public BaseDumbEnemey(int speedFactor){
		speedFactorx = r.nextInt(speedFactor);
		speedFactory = speedFactor - speedFactorx;
		if(speedFactor < 10){
			mesh = new Mesh(SLOW);
		} else {
			mesh = new Mesh(FAST);
		}
		x = 0;
		y = 0;
		rot = 0;
		scale = 1;
	}
	
	@Override
	public void tick(ItemHandler handler){
		
		if(dirY){
			y += speedFactorx + r.nextFloat() ;
		} else {
			y -= speedFactory + r.nextFloat() - 0.5;
		}
		if(dirX){
			x += speedFactorx + r.nextFloat() - 0.75;
		} else{
			x -= speedFactory + r.nextFloat();
		}	
		
	}
	
	@Override
	public void mapClamp(MapHandler map){
		double lx = x;
		double ly = y;
		super.mapClamp(map);
		
		if(lx != x) dirX = !dirX;
		if(ly != y) dirY = !dirY;
	}
	
	public void death() {
		
	}
	
}
