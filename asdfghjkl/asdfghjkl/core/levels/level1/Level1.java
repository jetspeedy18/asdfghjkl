package core.levels.level1;


import core.engine.ItemHandler;
import core.engine.graphics.Window;
import core.game.MapHandler;
import core.levels.Level;

public class Level1 extends Level {

	public Level1(ItemHandler oHandler) {
		super(oHandler);
		try {
			mHandler = MapHandler.loadMap("res/maps/level1.lemondrop");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void pollInputs(Window window) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		mHandler.render();
	}

	@Override
	public void tick() {
		
	}

}
