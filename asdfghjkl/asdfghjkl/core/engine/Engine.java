package core.engine;

import java.util.Random;

import core.engine.graphics.Renderer;
import core.engine.graphics.Window;
import core.engine.graphics.menus.Menu;
import core.engine.graphics.menus.Pause;
import core.engine.graphics.menus.Screen;
import core.engine.input.KeyMap;
import core.game.BaseDumbEnemey;
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
	private boolean dead = false;
	
	private endScreen end;
	private DeathThereGoodSIr death;
	
	private int counter = 0;
	
	public Engine(){
		keys = new KeyMap();
		window = new Window();
		window.BindKeys(keys);
		
		screen = new Menu(this);
		
		r = new Random();
		
		run();
	}

	
	public void createNewGame(){
		//screen = null;
		end = new endScreen();
		death = new DeathThereGoodSIr();
		
		map = new MapHandler();
		
		isPlaying = true;
		
		camera = new Camera();
		handler = new ItemHandler();
		handler.addItem(new BaseDumbEnemey(r.nextInt(20)));
		handler.addItem(new BaseDumbEnemey(r.nextInt(20)));
		handler.addItem(new BaseDumbEnemey(r.nextInt(10)));
		try {
			renderer = new Renderer(camera, handler);
			
		} catch (Exception e) {
			e.printStackTrace();
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
		} else {
			screen.tick();
		}
	}
	
	private void render(){

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
				
				renderer.render();
				
				renderer.unbind();
				
				counter++;
				if(counter > map.getScale()*120){
					if(win = map.advanceLevel()){
						glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
					} else {
						for(int i = 0; i < map.getScale()/5; i++){
							System.out.println("Sdfs");
							handler.addItem(new BaseDumbEnemey(r.nextInt(20)));
						}
						System.out.println("tesad");
					}
				}
			}
				
		} else if (win) {
			renderer.bind();
			renderer.resetUniforms(handler.getPlayer().getPosMat(),end.getScale());
			end.render();
			renderer.unbind();
		} else {
			renderer.bind();
			renderer.resetUniforms(handler.getPlayer().getPosMat(),end.getScale());
			death.render();
			renderer.unbind();
		}
		
		//if(window.isPaused() || !isPlaying) screen.render();

	}
		
}
