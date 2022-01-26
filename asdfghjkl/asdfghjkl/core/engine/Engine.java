package core.engine;

import java.util.Random;

import core.engine.graphics.Renderer;
import core.engine.graphics.Window;
import core.engine.graphics.menus.Menu;
import core.engine.graphics.menus.Screen;
import core.engine.input.KeyMap;
import core.game.BaseDumbEnemey;
import core.game.BasicStalkerEnemy;
import core.game.Boss;
import core.game.DeathThereGoodSIr;
import core.game.MapHandler;
import core.game.endScreen;

import static org.lwjgl.opengl.GL11.*;

public class Engine implements Runnable {

	private Window window;
	
	private Random r;
	
	private Camera camera;
	
	private ItemHandler handler;
	
	private MapHandler map;
	
	private Screen screen;
	
	private Renderer renderer;
	
	private KeyMap keys;
	
	private boolean isPlaying = false;
	
	private boolean win = false;
	private boolean boss = false;
	private boolean dead = false;
	
	private endScreen end;
	private DeathThereGoodSIr death;
	
	private int counter;
	
	public Engine(){
		keys = new KeyMap();
		window = new Window();
		window.BindKeys(keys);
		
		screen = new Menu(this);
		
		r = new Random();
		

		//screen = null;
		end = new endScreen();
		death = new DeathThereGoodSIr();
		
		camera = new Camera();
		handler = new ItemHandler();
		
		try {
			renderer = new Renderer(camera, handler);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		run();
	}

	
	public void createNewGame(){
		if(!isPlaying){
			map = new MapHandler();
			map.reset();
			
			isPlaying = true;
			win = false;
			dead = false;
			boss = false;
			
			camera = new Camera();
			handler = new ItemHandler();
			handler.clear();
			handler.queAddItem(new BaseDumbEnemey(r.nextInt(20)+1));
			handler.queAddItem(new BaseDumbEnemey(r.nextInt(20)+1));
			handler.queAddItem(new BasicStalkerEnemy(2, map.getMapBounds()));
			try {
				renderer = new Renderer(camera, handler);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			counter = 0;
			
			glClearColor(0.0f,0.0f,0.0f,0.0f);
		}
		window.setWindowRes(camera);
	}
	
	@Override
	public synchronized void run() {		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		//	int frames = 0;
		try {
			while(!window.shouldClose()) {
			
				long now = System.nanoTime();
				delta += (now - lastTime) / ns;
				lastTime = now;
				pollInputs();
				while(delta >= 1) {
					tick();
					delta--;
				}
				if(!window.shouldClose()){
					render();
					window.render();
				}
		//	frames++;
			}
		} finally {
			window.close();
		}
	}
	
	private void pollInputs(){
		window.poll();
		screen.pollInputs(window);
	}
	
	private void tick(){
		if(!window.isPaused() && isPlaying){
			handler.tick(keys, camera, map);
			dead = handler.isPlayerDeadOrJustInsane();
			if(dead){
				glClearColor(0.8f,0.1f,0.1f,0.0f);
				isPlaying = false;
			}
		} else {
			screen.tick();
		}
	}
	
	private void render(){

		if(dead){
			glClearColor(0.8f,0.1f,0.1f,0.0f);
		}
		
		glClear(GL_COLOR_BUFFER_BIT);
		
		if(!win && !dead){
			if(isPlaying){
				renderer.bind();
				renderer.resetUniforms(map.getScale());
				map.render();
			}
			
			try{
				screen.render();
			} catch (Exception e){}
			
			if(isPlaying){

				window.setTitle("Health: " + String.valueOf(handler.getPlayer().getHealth()) + "  Time: " + counter/60 + "  Shields: " + handler.getPlayer().getShields() + " Wave: " + String.valueOf(map.getScale()/5));
				
				renderer.render();
				
				renderer.unbind();
				
				counter++;
				if(handler.empty()) counter = map.getScale()*map.getScale()*map.getScale() + 1;
				if(counter > map.getScale()*map.getScale()*map.getScale()){
					if(!boss){
						if(boss = map.advanceLevel()){
							handler.queAddItem(new Boss());
						} else {
							for(int i = 0; i < map.getScale()*map.getScale()/5; i++){
								if(r.nextInt(10) == 3){
									handler.queAddItem(new BasicStalkerEnemy(r.nextInt(5)+2,map.getMapBounds()));
								} else {
									handler.queAddItem(new BaseDumbEnemey(r.nextInt(40)+1));
								}
							}
						}
					}
				}
				if(boss){
					win = handler.bossKill();
				}
			}
				
		}
		if (win) {
			glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
			renderer.bind();
			renderer.resetUniforms(handler.getPlayer().getPosMat(),end.getScale());
			end.render();
			renderer.unbind();
		} 
		if(dead && !isPlaying) {
			renderer.bind();
			renderer.resetUniforms(handler.getPlayer().getPosMat(),death.getScale());
			death.render();
			renderer.unbind();
		}
		
		//if(window.isPaused() || !isPlaying) screen.render();

	}
		
}
