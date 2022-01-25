package core.engine;

import java.util.ArrayList;
import java.util.List;

import core.engine.graphics.ShaderLoader;
import core.engine.graphics.Texture;
import core.engine.input.KeyMap;
import core.game.GameItem;
import core.game.MapHandler;
import core.game.Mesh;
import core.game.MovableEntity;
import core.game.Player;
import core.game.bullet;

public class ItemHandler {
	
	private List<GameItem> items;
	
	private Player player;
	
	private bullet b;

	
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
	
	public void removeItem(int i) {
		items.remove(i);
		//destroy enemy - hide entity and remove from list
	}
	
	public List<GameItem> getItems(){
		return items;
	}
	
	public void tick(KeyMap keys, Camera camera, MapHandler map){
		for(GameItem item : items){
			item.tick();
			item.mapClamp(map);
		}
		player.tick(keys, items);
		player.mapClamp(map);
		camera.tick(player.getX(), player.getY(), player.getMaxSpeed());
		if (player.hasShot()) {
			b = new bullet(player.getX(), player.getY());
			b.tick();
		}
		
	}
	
	public void render(ShaderLoader program){
		for(GameItem item : items){
			program.setUniform("trans", item.getPosMat());
			item.getMesh().render();
		}
		program.setUniform("trans", player.getPosMat());
		player.getMesh().render();
		
		if (player.hasShot()) {
			System.out.println("Test");
			program.setUniform("trans", b.getPosMat());
			b.getMesh().render();
		}
		/*
		 *eventually going to figure out a way to have a list of bullets and to render each of them 
		 *
		for(bullet bullet: bullets) {
			program.setUniform("trans", ((MovableEntity) bullets).getPosMat());
			bullet.getMesh().render();
		}
		*/
		
	}
	
	public void clear(){
		items = new ArrayList<GameItem>();
	}
	
	public Player getPlayer(){
		return player;
	}

	public boolean isPlayerDeadOrJustInsane() {
		return player.isPlayerDeadOrJustInsane();
	}

}
