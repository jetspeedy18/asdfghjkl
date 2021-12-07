package core.engine.graphics.menus;

import core.engine.graphics.Window;

public abstract class Screen {
	// represents a state . . .level or menu
	
	public abstract void pollInputs(Window window);
	
	public abstract void render();
	
	public abstract void tick();
	
}
