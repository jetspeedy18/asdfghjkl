package core.engine.graphics.menus;

import core.engine.Engine;
import core.engine.graphics.Window;
import core.engine.input.MouseRecorder;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Menu extends Screen {
	
	public Engine engine;
	
	public Menu(Engine engine){
		this.engine = engine;
	}
	
	@Override
	public void pollInputs(Window window) {
		// TODO Auto-generated method stub
		if(MouseRecorder.INSTANCE.isPressed(GLFW_MOUSE_BUTTON_LEFT)){
			engine.createNewGame();
		}
	}

	@Override
	public void render() {
		
	}

	@Override
	public void tick() {
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
	}

}
