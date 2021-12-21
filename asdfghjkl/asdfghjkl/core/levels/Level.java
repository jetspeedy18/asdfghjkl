package core.levels;

import core.engine.ItemHandler;
import core.engine.graphics.menus.Screen;
import core.game.MapHandler;

public abstract class Level extends Screen {
	
	public MapHandler mHandler;
	
	public ItemHandler oHandler;
	
	public Level(ItemHandler oHandler){
		this.oHandler = oHandler;
	}
	
	public static void save(){
		
	}
	
	public static Level load(){
		return null;
	}

}
