package core.engine;

import org.lwjgl.opengl.GL;

import core.game.Camera;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {
	
	private long window;
	
	private boolean paused;

	public Window(Camera camera){
		paused = false;
		try{
			if(!glfwInit()){
				throw new Exception("AHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
			}
			
			glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
			
			window = glfwCreateWindow(1920, 1080, "hcsfwe", NULL, NULL);
			
			if(window == NULL){
				throw new Exception("AHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
			}
			
			glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
				if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
					glfwSetWindowShouldClose(window, true);
				if(key == GLFW_KEY_K && action == GLFW_RELEASE)
					paused = !paused;
			});
			
			glfwSetWindowSizeCallback(window, (window, w, h) -> {
				camera.updateScreenRes(w, h);
				glViewport(0, 0, w, h);
			});
			
			glfwMakeContextCurrent(window);
			GL.createCapabilities();
			
			camera.updateScreenRes(1920, 1080);
			glViewport(0, 0, 1920, 1080);
						
			glfwSwapInterval(1);
			
			glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
			
			glfwShowWindow(window);			

			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void poll(){
		glfwPollEvents();
	}
	
	public void render(){
		glfwSwapBuffers(window);
	}
	
	public boolean shouldClose() {
		return glfwWindowShouldClose(window);
	}
	
	public void close(){
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);
				
		glfwTerminate();
		
		System.exit(42);
	}

	
	public long getWindow(){
		return window;
	}

	public boolean isPaused() {
		return paused;
	}
}
