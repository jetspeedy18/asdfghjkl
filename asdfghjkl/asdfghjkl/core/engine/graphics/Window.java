package core.engine.graphics;

import org.lwjgl.opengl.GL;

import core.engine.Camera;
import core.engine.input.KeyMap;
import core.engine.input.MouseRecorder;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

public class Window {
	
	private long window;
	
	private boolean paused;

	public Window(){
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
			
			glfwSetWindowSizeCallback(window, (window, w, h) -> {
				//camera.updateScreenRes(w, h);
				// TODO: INSTALL THE VOID
				glViewport(0, 0, w, h);
			});
			
			glfwSetMouseButtonCallback(window, (window, button, action, mods)->{
				DoubleBuffer x = memAllocDouble(1);
				DoubleBuffer y = memAllocDouble(1);
				glfwGetCursorPos(window, x, y);
				MouseRecorder.INSTANCE.recordEvent(button, action, x.get(), y.get());
			});
			
			glfwMakeContextCurrent(window);
			GL.createCapabilities();
			
			glViewport(0, 0, 1920, 1080);
						
			glfwSwapInterval(1);
			
			glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
			
			glfwShowWindow(window);			

			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void BindKeys(KeyMap map){
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
				glfwSetWindowShouldClose(window, true);
			if(key == GLFW_KEY_K && action == GLFW_RELEASE)
				paused = !paused;
			map.setCallbacks(key, action);
		});
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
	
	public void setWindowRes(Camera camera){
		IntBuffer x = memAllocInt(1);
		IntBuffer y = memAllocInt(1);
		glfwGetWindowSize(window, x, y);
		camera.updateScreenRes(x.get(), y.get());
	}
}
