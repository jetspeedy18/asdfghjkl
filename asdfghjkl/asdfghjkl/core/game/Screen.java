package core.game;

import core.engine.Window;

public interface Screen {
	// represents a state . . .level or menu
	
	public abstract void pollInputs(Window window);
	
	public abstract void render();
	
	public abstract void tick();
	
}
