package core.game;

import java.util.Random;

import core.engine.ItemHandler;
import core.engine.graphics.Texture;

public class BasicStalkerEnemy extends GameItem {
	
	private final float speedFactor;
	
	private Random r = new Random();
	
	private static final Texture SLOW = new Texture(Texture.safeLoadTex("res/images/enemies/pixil-frame-0 (7).png"));
	private static final Texture FAST = new Texture(Texture.safeLoadTex("res/images/enemies/pixil-frame-0 (8).png"));	
	
	public BasicStalkerEnemy(int speedCap, int gBound){
		this.speedFactor = speedCap;
		if(speedCap < 3){
			mesh = new Mesh(SLOW);
		} else {
			mesh = new Mesh(FAST);
		}
		x = r.nextInt(gBound)-gBound/2;
		y = r.nextInt(gBound)-gBound/2;
		
		rot = 0;
		scale = 1;
		
	}
	
	@Override
	public void tick(ItemHandler handler) {
		float difX = x - handler.getPlayer().getX();
		float difY = y - handler.getPlayer().getY();
		
		float ispify = (float) Math.sqrt(difX*difX+difY*difY);
				
		x -= difX/ispify*speedFactor;
		y -= difY/ispify*speedFactor;
		
	}

}
