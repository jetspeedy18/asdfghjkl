package core.game;

import java.util.Random;

import core.engine.ItemHandler;
import core.engine.graphics.Texture;

public class TankPal extends GameItem{

	private int count;
	
	public TankPal(float x, float y){
		mesh = new Mesh(new Texture(Texture.safeLoadTex("res/images/testpanzernobg.png")));
		this.x = x;
		this.y = y;
		rot = 0;
		scale = 1;
		count = 0;
		
	}
	
	@Override
	public void tick(ItemHandler handler) {
		float difX = x - handler.getPlayer().getX();
		float difY = y - handler.getPlayer().getY();
		
		float ispify = (float) Math.sqrt(difX*difX+difY*difY)+10;
		
		if(ispify > 100){
			x -= difX/ispify*9;
			y -= difY/ispify*7;
		}
		
		count ++;
		
		if(count%15==0){
			handler.addBullet(new bullet(x, y, ((count/15)%4)*90, false));
		}
	}

}
