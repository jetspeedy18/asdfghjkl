package core.game;

import java.util.ArrayList;
import java.util.List;

public class ItemHandler {
	
	private List<GameItem> items;
	
	private Player player;
	
	public ItemHandler(){
		clear();
		try {
			player = new Player(new Mesh(new Texture(Texture.loadTex("res/images/test.jpg"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addItem(GameItem item){
		items.add(item);
	}
	
	public List<GameItem> getItems(){
		return items;
	}
	
	public void tick(){
		for(GameItem item : items){
			item.tick();
		}
		player.tick();
	}
	
	public void render(ShaderLoader program){
		for(GameItem item : items){
			program.setUniform("trans", item.getPosMat());
			item.getMesh().render();
		}
		program.setUniform("trans", player.getPosMat());
		player.getMesh().render();
	}
	
	public void clear(){
		items = new ArrayList<GameItem>();
	}
	
	private Player getPlayer(){
		return player;
	}

}
