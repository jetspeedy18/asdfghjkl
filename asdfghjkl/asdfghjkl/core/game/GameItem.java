package core.game;

import core.engine.ItemHandler;

public abstract class GameItem extends MovableEntity {

	protected boolean collntnt = false;
	
	public abstract void tick(ItemHandler handler);

	public int health = 1;
	
	public void damage() {
		health --;		
	}

	public int getHealth() {
		return health;
	}
	
	public boolean getCollect(){
		return collntnt;
	}
	
}
