package core.engine.input;

import static org.lwjgl.glfw.GLFW.*;

public class MouseRecorder {
	
	public static MouseRecorder INSTANCE = new MouseRecorder();

	private boolean[] pressed;
	
	private double x, y;
	
	private MouseRecorder(){
		pressed = new boolean[3];
	}
	
	public double getLastClickPosX(){
		return x;
	}
	
	public double getLastClickPosY(){
		return y;
	}
	
	public boolean isPressed(int button){
		return pressed[button];
	}
	
	public void recordEvent(int button, int action, double x, double y){
		if(action == GLFW_PRESS) pressed[button] = true;
		if(action == GLFW_RELEASE) pressed[button] = false;
		this.x = x;
		this.y = y;
	}
}
