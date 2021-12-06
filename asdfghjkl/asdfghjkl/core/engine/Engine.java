package core.engine;

import java.util.Random;

import core.game.Camera;
import core.game.ItemHandler;
import core.game.Menu;
import core.game.Renderer;
import core.game.Screen;

import static org.lwjgl.opengl.GL11.*;

public class Engine implements Runnable {

	private Window window;
	
	private Random r;
	
	private Camera camera;
	
	private ItemHandler handler;
	
	private Screen screen;
	
	private Renderer renderer;
	
	public Engine(){
		window = new Window();
		screen = new Menu(this);
		
		r = new Random();
		createNewGame();
		run();
	}

	
	private void createNewGame(){
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
					if(!window.isPaused()) tick();
					else Screen.getPause().tick();
					delta--;
				}
				if(!window.shouldClose()){
					if(!window.isPaused()) render();
					else Screen.getPause().render();
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
		glClearColor(r.nextFloat(), r.nextFloat(), r.nextFloat(), 0.0f);
		screen.tick();
		handler.tick();
	}
	
	private void render(){

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		renderer.bind();
		
		//screen.render();
		
		renderer.render();
		
		renderer.unbind();

	}
	
	public void changeScreens(){
		//TODO
	}
		
}
