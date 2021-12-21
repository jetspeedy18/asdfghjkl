package core.engine;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera {

	private Matrix4f ortho;
	
	private float x, y;
	
	public Camera(){
		x = y = 0;
		ortho = new Matrix4f();
	}
	
	public void updateScreenRes(int w, int h){
		ortho = ortho.identity().ortho2D(-w/2, w/2, -h/2, h/2);
	}
	
	public Matrix4f getCameraPos() {
		return new Matrix4f(ortho).translate(new Vector3f(x, y, 0));
	}
	
	public void tick(float px, float py, float speedFactor){
		float lx = tick1D(-px, x);
		float ly = tick1D(-py, y);
		
		x += lx;
		y += ly;
		
	}
	
	private float tick1D(float p, float c){
		float error = p-c;
		
		return error*error*error*0.00001f;
	}
	
}
