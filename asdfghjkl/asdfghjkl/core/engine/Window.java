package core.engine;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;

import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {
	
	private long window;

	public Window(){
		try{
			if(!glfwInit()){
				throw new Exception("AHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
			}
			
			glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
			glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
			
			window = glfwCreateWindow(1920, 1080, "hcsfwe", GLFW.glfwGetPrimaryMonitor(), NULL);
			
			if(window == NULL){
				throw new Exception("AHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
			}
			
			glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
				if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
					glfwSetWindowShouldClose(window, true);
			});
			
			glfwMakeContextCurrent(window);
			
			GLFW.glfwSwapInterval(1);
			
			GL.createCapabilities();
			
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
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		glfwSwapBuffers(window);
	}
	
	public boolean shouldClose() {
		return glfwWindowShouldClose(window);
	}

}
