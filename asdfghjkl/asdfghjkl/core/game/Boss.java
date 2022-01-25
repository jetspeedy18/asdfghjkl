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
		hitMesh = new Mesh(new Texture(Texture.safeLoadTex("res/images/enemies/pixil-frame-0 (11).png")));
		x = 0;
		y = 0;
		rot = 0;
		scale = 2;
		counter = 0;
		health = 5;
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
		
		if(counter%45==0){
			for(int i = 0; i < 2; i++){
				if(r.nextInt(15) == 3){
					handler.queAddItem(new BasicStalkerEnemy(r.nextInt(5)+2, x, y));
				} else {
					handler.queAddItem(new BaseDumbEnemey(r.nextInt(20)+1, x, y));
				}
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
