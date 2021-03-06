package core.game;

import java.util.List;
import core.engine.ItemHandler;
import core.engine.input.KeyMap;
import core.engine.input.KeyMap.ACTIONS;

public class Player extends MovableEntity {
	
	private float speedFactor;
	private int health;
	private int dir;
	private boolean shot;
	private boolean shotl;
	private boolean sActive;
	private static int shieldTimer;
	private static int shieldUses;
	private static int cbc;
	private int shrinkcount;
	public Player(Mesh mesh){
	this.mesh = mesh;
		x = 0;
		y = 0;
		rot = 0;
		scale = 1;
		speedFactor = 10;
		health = 100;
		dir = 0;
		shot = false;
		shotl = false;
		sActive = false;
		health = 169;
		
		shieldUses = 3;
		cbc=0;
		shrinkcount = 0;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	public int getDir() {
		return dir;
	}
	public boolean hasShot() {
		return shot;
	}
	public void detach(){
		cbc--;
	}
	
	public boolean hasShield() {
		return sActive;
	}
	
	public boolean tick(KeyMap keys, List<GameItem> items, ItemHandler handler){
		shrinkcount --;
		if(shrinkcount < 0) scale = 1;
		
		double inx = 0;
		double iny = 0;
		if(keys.getKeyPos(ACTIONS.MOVE_UP)){
			iny++;
		}
		if(keys.getKeyPos(ACTIONS.MOVE_DOWN)){
			iny--;
		}
		if(keys.getKeyPos(ACTIONS.MOVE_LEFT)){
			inx--;
		}
		if(keys.getKeyPos(ACTIONS.MOVE_RIGHT)){
			inx++;
		}
		
		if(inx != 0 && iny != 0){
			inx /= Math.sqrt(2);
			iny /= Math.sqrt(2);
			
		}
		
		if(inx != 0 || iny != 0){
			dir = (int) Math.toDegrees(Math.atan2(iny, inx));
		}
				
		
		x += inx * speedFactor;
		y += iny * speedFactor;
		
		if (keys.getKeyPos(ACTIONS.SPACE_BAR) && !shotl) {
			this.shot = true;
			shotl = true;
			if(cbc < 3){
				handler.addBullet(new bullet(x, y, dir, true));
				cbc++;
			}
		}
		else if(!keys.getKeyPos(ACTIONS.SPACE_BAR)){
			shotl = false;
			shot = false;
		}
		if (shieldTimer>=200) {
			this.sActive = false;
			if (keys.getKeyPos(ACTIONS.F_KEY) && shieldUses>0) {
				this.sActive = true;
				shieldTimer = 0;
				shieldUses--;
			}
		}
		if (!sActive) {
			GameItem byby = null;
			for (GameItem Item: items) {
				if (isCollided(Item)) {
					if(Item.getCollect()){
						if(Item instanceof ShieldDrop){
							shieldUses++;
						} else if(Item instanceof HealthDrop){
							health += 26;
						} else if(Item instanceof ShrinkDrop){
							scale = 0.5f;
							shrinkcount = 180;
						}
						byby = Item;
					} else{
						health --;
					}
				}
			}
			
			if(byby != null) items.remove(byby);
		}	else {
			GameItem byby = null;
			
			for (GameItem Item: items) {
				if (isCollided(Item)) {
					byby = Item;
					if(Item.getCollect()) {
						if(Item instanceof ShieldDrop){
							shieldUses++;
						} else if(Item instanceof HealthDrop){
							health += 26;
						} else if(Item instanceof ShrinkDrop){
							scale = 0.5f;
							shrinkcount = 180;
						}
						
					}
					Item.damage();
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
		}
		
		shieldTimer++;
		return true;
	}
	
	public boolean isPlayerDeadOrJustInsane(){
		return health<=0;
	}
	
	public int getHealth(){
		return health;
	}
	
	public int getShields() {
		return shieldUses;
	}
	
	public float getMaxSpeed(){
		return speedFactor;
	}
}

	