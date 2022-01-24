package core.engine;

import java.util.Random;

import core.engine.graphics.Renderer;
import core.engine.graphics.Window;
import core.engine.graphics.menus.Menu;
import core.engine.graphics.menus.Pause;
import core.engine.graphics.menus.Screen;
import core.engine.input.KeyMap;
import core.game.MapHandler;

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
		
		map = new MapHandler();
		
		isPlaying = true;
		
		camera = new Camera();
		handler = new ItemHandler();
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
			handler.tick(keys, camera);
		} else {
			screen.tick();
		}
	}
	
	private void render(){

		glClear(GL_COLOR_BUFFER_BIT);
		
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
		}
		
		//if(window.isPaused() || !isPlaying) screen.render();

	}
		
}
