package core.game;

import core.engine.ItemHandler;

public abstract class GameItem extends MovableEntity {

	public abstract void tick(ItemHandler handler);
	
}
