package core.game;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public abstract class MovableEntity {
	
	protected float x;

	protected float y;
	
	protected float rot, scale;
	
	protected Mesh mesh;
	
	public Mesh getMesh(){
		return mesh;
	}
	
	public void mapClamp(MapHandler map){
		
		if(y + 16 > map.getMapBounds()){
			y = map.getMapBounds() - 16;
		} else if(y - 16 < -map.getMapBounds()){
			y = -map.getMapBounds() + 16;
		}
		
		if(x + 16 > map.getMapBounds()){
			x = map.getMapBounds() - 16;
		} else if(x - 16 < -map.getMapBounds()){
			x = -map.getMapBounds() + 16;
		}
		
	}
	
	public float getX() {
		return this.x;
	}
	public float getY() {
		return this.y;
	}
	
	public Matrix4f getPosMat(){
		return new Matrix4f().identity().translate(new Vector3f(x, y, 0)).rotateZ((float)Math.toRadians(rot)).scale(scale);
	}
	
	public boolean isCollided(Player p, MovableEntity n) {
		return (p.getX() > n.getX()-32f &&
				p.getX() < n.getX()+32f &&
				p.getY() > n.getY()-32f &&
				p.getY() < n.getY()+32f);	
	}

}
