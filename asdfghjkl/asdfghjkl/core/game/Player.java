package core.game;

import java.util.List;

import core.engine.input.KeyMap;
import core.engine.input.KeyMap.ACTIONS;

public class Player extends MovableEntity {
	
	private float speedFactor;
	private int health;
	private int dir;
	private boolean shot;
	private boolean sActive;
	private static int count;
	private static int shieldTimer;
	private static int shieldUses;

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
		sActive = false;

		health = 1000000000;
		shieldUses = 3;

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
	
	public void endShot(){
		shot = false;
	}
	public boolean hasShield() {
		return sActive;
	}
	public void tick(KeyMap keys, List<GameItem> items){
		double inx = 0;
		double iny = 0;
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
		
		if (count>=15) {
			this.shot = false;
			if (keys.getKeyPos(ACTIONS.SPACE_BAR)) {
				this.shot = true;
				count=0;
			}
		}
		
		if (shieldTimer>=400) {
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
		}		
		
		count ++;
		shieldTimer++;
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
