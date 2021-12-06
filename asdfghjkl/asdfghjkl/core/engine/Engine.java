package core.engine;

import java.util.Random;

import core.game.Camera;
import core.game.ItemHandler;
import core.game.Menu;
import core.game.Mesh;
import core.game.Player;
import core.game.Renderer;
import core.game.Screen;
import core.game.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Engine implements Runnable {

	private Window window;
	
	private Random r;
	
	private Camera camera;
	
	private ItemHandler handler = new ItemHandler();
	
	private Screen screen;
	
	private Renderer renderer;
	
	private Player player;
	
	public Engine(){
		camera = new Camera();
		window = new Window(camera);
		screen = new Menu(this);
		try {
			renderer = new Renderer(camera, handler);
			player = new Player(new Mesh(new Texture(Texture.loadTex("res/images/test.jpg"))));
			handler.addItem(player);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		r = new Random();
		run();
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
					delta--;
				}
				if(!window.shouldClose())
					render();
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
		player.tick();
	}
	
	private void render(){

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		renderer.bind();
		
		//screen.render();
		
		renderer.render();
		
		renderer.unbind();
		
		window.render();

	}
	
	public void changeScreens(){
		//TODO
	}
		
}
