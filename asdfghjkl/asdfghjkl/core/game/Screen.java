package core.game;

import core.engine.Window;

public abstract class Screen {
	// represents a state . . .level or menu
	
	private static Pause pause = new Pause();
	
	public static Pause getPause(){
		return pause;
	}
	
	public abstract void pollInputs(Window window);
	
	public abstract void render();
	
	public abstract void tick();
	
}
