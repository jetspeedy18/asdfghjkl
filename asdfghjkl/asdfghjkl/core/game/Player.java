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
<<<<<<< HEAD
		health = 100;
=======
		health = 1;
>>>>>>> b2f25b0aff14a4823db4240ce7945f781eec45af
			
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
	}
	
	
	
	public int getHealth(){
		return health;
	}
	
	public float getMaxSpeed(){
		return speedFactor;
	}
	
}
