package core.game;

import java.util.Random;

import core.engine.ItemHandler;
import core.engine.graphics.Texture;

public class Boss extends GameItem {

	private Random r = new Random();
	
	private int counter;
	
	public Boss(){
		mesh = new Mesh(new Texture(Texture.safeLoadTex("res/images/enemies/pixil-frame-0 (9).png")));
		x = 0;
		y = 0;
		rot = 0;
		scale = 2;
		counter = 0;
		health = 3;
	}

	@Override
	public void tick(ItemHandler handler) {
		float difX = x - handler.getPlayer().getX();
		float difY = y - handler.getPlayer().getY();
		
		float ispify = (float) Math.sqrt(difX*difX+difY*difY);
				
		x -= difX/ispify;
		y -= difY/ispify;
		
		counter ++;
		
		if(counter%300==0){
			if(r.nextInt(10) == 3){
				handler.queAddItem(new BasicStalkerEnemy(r.nextInt(5)+2, x, y));
			} else {
				handler.queAddItem(new BaseDumbEnemey(r.nextInt(20)));
			}
		}
	}
	
}
