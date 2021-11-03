package core.engine;

import org.lwjgl.glfw.*;
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
			
			window = glfwCreateWindow(640, 480, "hcsfwe", GLFW.glfwGetPrimaryMonitor(), NULL);
			
			if(window == NULL){
				throw new Exception("AHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
			}
			
			glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
				if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
					glfwSetWindowShouldClose(window, true);
			});
			
			
			glfwShowWindow(window);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void poll(){
		glfwPollEvents();
	}
	
	public boolean shouldClose() {
		return glfwWindowShouldClose(window);
	}

}
