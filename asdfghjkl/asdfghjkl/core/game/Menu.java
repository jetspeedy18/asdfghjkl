package core.game;

import core.engine.Engine;
import core.engine.Window;

public class Menu implements Screen {

	public Engine engine;
	
	public Menu(Engine engine){
		this.engine = engine;
	}
	
	@Override
	public void pollInputs(Window window) {
		// TODO Auto-generated method stub
		if(false){
			engine.changeScreens();
		}
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
