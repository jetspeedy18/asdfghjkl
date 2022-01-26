package core.game;

import java.util.List;

import core.engine.input.KeyMap;
import core.engine.input.KeyMap.ACTIONS;

public class Player extends MovableEntity {
	
	private float speedFactor;
	private int health;
	private int dir;
	private boolean shot;
	private boolean shotl;
	private boolean sActive;
	private static int count;
	private static int shieldTimer;
	private static int shieldUses;
	private static int ammo;
	private static int cbc;

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
		ammo = 1;
		cbc=0;

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
	public int getCBC() {
		return this.cbc;
	}
	
	
	public void endShot(bullet b){
		b.setrBullet(false);
		b.setInMotion(false);
		cbc--;
		this.shot = false;
		
	}
	public boolean hasShield() {
		return sActive;
	}
	
	public boolean tick(KeyMap keys, List<GameItem> items){
		double inx = 0;
		double iny = 0;
		int tcbc = cbc;	
		if(keys.getKeyPos(ACTIONS.MOVE_UP)){
			iny++;
			this.dir = 90;
		}
		if(keys.getKeyPos(ACTIONS.MOVE_DOWN)){
			iny--;
			this.dir = 270;
		}
		if(keys.getKeyPos(ACTIONS.MOVE_LEFT)){
			inx--;
			this.dir = 180;
		}
		if(keys.getKeyPos(ACTIONS.MOVE_RIGHT)){
			inx++;
			this.dir = 0;
		}
		
		if(inx != 0 && iny != 0){
			inx /= Math.sqrt(2);
			iny /= Math.sqrt(2);
		}
		
		x += inx * speedFactor;
		y += iny * speedFactor;
		
	
			if (keys.getKeyPos(ACTIONS.SPACE_BAR) && !shotl) {
				cbc++;
				this.shot = true;
				shotl = true;
				ammo++;
				if (ammo >=4) {
					ammo = 1;
				}
				count=0;
			}
			else if(!keys.getKeyPos(ACTIONS.SPACE_BAR)){
				shotl = false;
				this.shot = false;
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
			for (GameItem Item: items) {
				if (isCollided(Item)) {
					health --;
				}
			}
		}	else {
			GameItem byby = null;
			
			for (GameItem Item: items) {
				if (isCollided(Item)) {
					byby = Item;
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
		
		count ++;
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

	public int getAmmo() {
		return ammo;
	}
	
}
