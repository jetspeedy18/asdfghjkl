package core.game;

import core.engine.input.KeyMap;
import core.engine.input.KeyMap.ACTIONS;

public class Player extends MovableEntity {
	
	private float speedFactor;

	public Player(Mesh mesh){
		this.mesh = mesh;
		x = 0;
		y = 0;
		rot = 0;
		scale = 1;
		speedFactor = 10;
			
	}
	
	public void tick(KeyMap keys){
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
	}
	
}
