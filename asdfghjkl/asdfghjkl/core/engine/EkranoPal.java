package core.engine;

import core.engine.graphics.Texture;
import core.game.GameItem;
import core.game.Mesh;
import core.game.TankPal;

public class EkranoPal extends GameItem {

	private TankPal pal;
	
	public EkranoPal(float x, float y, TankPal pal){
		mesh = new Mesh(new Texture(Texture.safeLoadTex("res/images/Lun Class No BG.png")));
		this.x = x;
		this.y = y;
		rot = 0;
		scale = 2;
		this.pal = pal;
		
	}
	
	@Override
	public void tick(ItemHandler handler) {
		float difX = x - pal.getX();
		float difY = y - pal.getY();
		
		float ispify = (float) Math.sqrt(difX*difX+difY*difY)+10;
		
		if(ispify > 100){
			x -= difX/ispify*8;
			y -= difY/ispify*8;
		}
	}

}
