package core.game;

import java.util.Random;

import core.engine.ItemHandler;
import core.engine.graphics.Texture;

public class Boss extends GameItem {

	private Random r = new Random();
	
	private int counter;
	
	private Mesh mainMesh;
	private Mesh hitMesh;
	
	private int damage;
	
	public Boss(){
		mainMesh = new Mesh(new Texture(Texture.safeLoadTex("res/images/enemies/pixil-frame-0 (9).png")));
		hitMesh = new Mesh(new Texture(Texture.safeLoadTex("res/images/enemies/pixil-frame-0 (10).png")));
		x = 0;
		y = 0;
		rot = 0;
		scale = 2;
		counter = 0;
		health = 3;
		mesh = mainMesh;
		damage = 0;
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
		
		damage --;
		if(damage <= 0){
			mesh = mainMesh;
		}
	}
	
	@Override
	public void damage(){
		super.damage();
		mesh = hitMesh;
		damage = 60;
	}
	
}
