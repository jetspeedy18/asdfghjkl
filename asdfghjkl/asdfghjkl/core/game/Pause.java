package core.game;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.*;

import core.engine.Window;

public class Pause extends Screen {

	@Override
	public void pollInputs(Window window) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	@Override
	public void tick() {
		glClearColor(0, 0, 0, 0);
		
	}

}
