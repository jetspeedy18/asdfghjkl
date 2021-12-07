package core.engine;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera {

	private Matrix4f ortho;
	
	private int x, y;
	
	public Camera(){
		x = y = 0;
		ortho = new Matrix4f();
	}
	
	public void updateScreenRes(int w, int h){
		ortho = ortho.identity().ortho2D(-w/2, w/2, -h/2, h/2);
	}
	
	public Matrix4f getCameraPos() {
		return ortho.translate(new Vector3f(x, y, 0));
	}
	
}
