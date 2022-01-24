package core.game;

import java.util.List;

import core.engine.input.KeyMap;
import core.engine.input.KeyMap.ACTIONS;

public class Player extends MovableEntity {
	
	private float speedFactor;
	private int health;

	public Player(Mesh mesh){
		this.mesh = mesh;
		x = 0;
		y = 0;
		rot = 0;
		scale = 1;
		speedFactor = 10;
		health = 00;
			
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public void tick(KeyMap keys, List<GameItem> items){
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
		
		x += inx * speedFactor;
		y += iny * speedFactor;
		
		for (GameItem Item: items) {
			if (isCollided(Item)) {
				health --;
			}
		}
		
		System.out.println(health);

	}
	
	public boolean isPlayerDeadOrJustInsane(){
		return health <= 0;
	}
	
	public float getMaxSpeed(){
		return speedFactor;
	}
	
}
