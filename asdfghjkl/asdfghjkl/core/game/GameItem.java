package core.game;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public abstract class GameItem {
	protected float x;

	protected float y;
	
	protected float rot, scale;
	
	protected Mesh mesh;
	
	public Mesh getMesh(){
		return mesh;
	}

	public abstract void tick();
	
	public Matrix4f getPosMat(){
		return new Matrix4f().identity().translate(new Vector3f(x, y, 0)).rotateZ((float)Math.toRadians(rot)).scale(scale);
	}
}
