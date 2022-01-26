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
	
	private bullet b1;
	private bullet b2;
	private bullet b3;
	
	private Shield s;
	
	private boolean bossKill = false;

	
	public ItemHandler(){
		clear();
		try {
			player = new Player(new Mesh(new Texture(Texture.loadTex("res/images/test.jpg"))));
			b1 = new bullet(player.getX(), player.getY(), player);
			b2 = new bullet(player.getX(), player.getY(), player);
			b3 = new bullet(player.getX(), player.getY(), player);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addItem(GameItem item){
		items.add(item);
	}
	
	public void removeItem(int i) {
		items.remove(i);
		
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
			if (player.getAmmo() == 1) {
				b1.setrBullet(true);
			}
			else if (player.getAmmo() == 2) {
				b2.setrBullet(true);
			}
			else if (player.getAmmo() == 3) {
				b3.setrBullet(true);
			}
			
			if (b1.getrBullet()) {
				if (!b1.getInMotion()) {
					b1.go(player.getX(), player.getY(), player);
					b1.setInMotion(true);
				} 
				if (b1.getInMotion()){ 
					bossKill = b1.tick(items);
					b1.mapClamp(map);
					if(b1.kill()){
						player.endShot(b1);
					}
				}
			}
			else if (b2.getrBullet()) {
				if (!b2.getInMotion()) {
					b2.go(player.getX(), player.getY(), player);
					b2.setInMotion(true);
				} 
				if (b2.getInMotion()) { 
					bossKill = b2.tick(items);
					b2.mapClamp(map);
					if(b2.kill()){
						player.endShot(b2);
					}
				}
			}
			else if (b3.getrBullet()) {
				if (!b3.getInMotion()) {
					b3.go(player.getX(), player.getY(), player);
					b3.setInMotion(true);
				} 
				if (b3.getInMotion()) { 
					bossKill = b3.tick(items);
					b3.mapClamp(map);
					if(b3.kill()){
						player.endShot(b3);
					}
				}
			}
			
			/*
			if (b1.getrBullet() || b2.getrBullet() || b3.getrBullet()) {			
				bossKill = b1.tick(items);
				b1.mapClamp(map);
				if(b1.kill()){
					player.endShot(b1);
				}
				
				bossKill = b2.tick(items);
				b2.mapClamp(map);
				if(b2.kill()){
					player.endShot(b2);
				}
				
				bossKill = b3.tick(items);
				b3.mapClamp(map);
				if(b3.kill()){
					player.endShot(b3);
				}
			}
			*/
		}
		else {
			if (player.getAmmo() == 1) {
				b1.setrBullet(false);
			}
			if (player.getAmmo() == 2) {
				b2.setrBullet(false);
			}
			if (player.getAmmo() == 3) {
				b3.setrBullet(false);
			}
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
				
		if (player.hasShot() && b1.inBounds() && player.getAmmo() == 1) {
				program.setUniform("trans", b1.getPosMat());
				b1.getMesh().render();
			}
		if (player.hasShot() && b2.inBounds() && player.getAmmo() == 2) {
			program.setUniform("trans", b2.getPosMat());
			b2.getMesh().render();
		}
		if (player.hasShot() && b3.inBounds() && player.getAmmo() == 3) {
			program.setUniform("trans", b3.getPosMat());
			b3.getMesh().render();
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
