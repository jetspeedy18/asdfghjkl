package core.game;


public class Player extends GameItem {

	public Player(Mesh mesh){
		this.mesh = mesh;
		x = 0;
		y = 0;
		rot = 0;
		scale = 1;
			
	}
	
	public void tick(){
		rot += 0.1;
		scale += 0.01;
		x+=0.5;
	}
	
}
