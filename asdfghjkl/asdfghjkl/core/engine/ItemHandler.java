package core.engine;

import java.util.ArrayList;
import java.util.List;

import core.engine.graphics.ShaderLoader;
import core.engine.graphics.Texture;
import core.engine.input.KeyMap;
import core.game.BaseDumbEnemey;
import core.game.GameItem;
import core.game.MapHandler;
import core.game.Mesh;
import core.game.MovableEntity;
import core.game.Player;
import core.game.Shield;
import core.game.bullet;

public class ItemHandler {
	
	private List<GameItem> items;
	private List<GameItem> queue;
	
	private Player player;
	
	private bullet b;
	
	private Shield s;
	
	private boolean bossKill = false;

	
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
			item.tick(this);
			item.mapClamp(map);
		}
		player.tick(keys, items);
		player.mapClamp(map);
		camera.tick(player.getX(), player.getY(), player.getMaxSpeed());
		if (player.hasShot()) {
			if (b==null) {
				b = new bullet(player.getX(), player.getY(), player);
			}
			else if (b!=null) {
				bossKill = b.tick(items);
				if(b.kill()){
					player.endShot();
				}
			}
		} else {
			b=null;
		}
		if (player.hasShield()) {
			s = new Shield(player.getX(), player.getY(), player);
		}
		
		for(GameItem item : queue){
			items.add(item);
		}
		
		queue.clear();
		
		
	}
	
	public void render(ShaderLoader program){
		for(GameItem item : items){
			program.setUniform("trans", item.getPosMat());
			item.getMesh().render();
		}
		program.setUniform("trans", player.getPosMat());
		player.getMesh().render();
				
		if (player.hasShot()) {
			program.setUniform("trans", b.getPosMat());
			b.getMesh().render();
		}
		if (player.hasShield()) {
			program.setUniform("trans", s.getPosMat());
			s.getMesh().render();
		}		
	}
	
	public void clear(){
		items = new ArrayList<GameItem>();
		queue = new ArrayList<GameItem>();
	}
	
	public Player getPlayer(){
		return player;
	}

	public boolean isPlayerDeadOrJustInsane() {
		return player.isPlayerDeadOrJustInsane();
	}

	public void queAddItem(GameItem item) {
		queue.add(item);		
	}
	
	public void setBossKill(boolean k){
		bossKill = k;
	}
	
	public boolean bossKill(){
		return bossKill;
	}

}
