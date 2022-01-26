package core.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import core.engine.graphics.ShaderLoader;
import core.engine.graphics.Texture;
import core.engine.input.KeyMap;
import core.game.BaseDumbEnemey;
import core.game.GameItem;
import core.game.HealthDrop;
import core.game.MapHandler;
import core.game.Mesh;
import core.game.MovableEntity;
import core.game.Player;
import core.game.Shield;
import core.game.ShieldDrop;
import core.game.ShrinkDrop;
import core.game.TankPal;
import core.game.bullet;

public class ItemHandler {
	
	private List<GameItem> items;
	private List<GameItem> queue;
	
	private Player player;
	
	private bullet b1;
	private bullet b2;
	private bullet b3;
	
	private Shield s;
	
	private boolean canTank = true;
	private boolean canSea = true;
	
	private Random r = new Random();
	
	private boolean bossKill = false;
	
	private TankPal pal;

	
	public ItemHandler(){
		clear();
		try {
			player = new Player(new Mesh(new Texture(Texture.loadTex("res/images/Rat Knight No Background.png"))));
			b1 = new bullet(player.getX(), player.getY(), player.getDir());
			b2 = new bullet(player.getX(), player.getY(), player.getDir());
			b3 = new bullet(player.getX(), player.getY(), player.getDir());
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
					b1.go(player.getX(), player.getY(), player.getDir());
					b1.setInMotion(true);
				} else { 
					bossKill = b1.tick(items, this);
					b1.mapClamp(map);
					if(b1.kill()){
						player.endShot(b1);
					}
				}
			}
			else if (b2.getrBullet()) {
				if (!b2.getInMotion()) {
					b2.go(player.getX(), player.getY(), player.getDir());
					b2.setInMotion(true);
				} else { 
					bossKill = b2.tick(items, this);
					b2.mapClamp(map);
					if(b2.kill()){
						player.endShot(b2);
					}
				}
			}
			else if (b3.getrBullet()) {
				if (!b3.getInMotion()) {
					b3.go(player.getX(), player.getY(), player.getDir());
					b3.setInMotion(true);
				} else { 
					bossKill = b3.tick(items, this);
					b3.mapClamp(map);
					if(b3.kill()){
						player.endShot(b3);
					}
				}
			}
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

	public void kill(GameItem byby) {
		float lx = byby.getX();
		float ly = byby.getY();
		int drop = 16;// r.nextInt(20);
		if(drop < 5){
			items.add(new HealthDrop(lx, ly));
		} else if(drop < 10){
			items.add(new ShieldDrop(lx, ly));
		} else if(drop < 15){
			items.add(new ShrinkDrop(lx, ly));
		} else if(drop == 16){
			if(canTank) items.add(pal = new TankPal(lx, ly));
			else if(canSea) {
				items.add(new EkranoPal(lx, ly, pal));
				canSea = false;
			}
			canTank = false;
		}
		
		
		
		items.remove(byby);
		
	}

	public boolean empty(){
		return items.size()+queue.size() == (canTank ? (canSea ? 0 : 1) : (canSea ? 1 : 2));
	}
}
