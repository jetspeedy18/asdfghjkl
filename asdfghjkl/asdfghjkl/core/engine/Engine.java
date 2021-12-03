package core.engine;

import java.util.Random;

import core.game.Menu;
import core.game.Mesh;
import core.game.Renderer;
import core.game.Screen;
import core.game.Texture;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class Engine implements Runnable {

	private Window window;
	
	private Random r;
	
	private Mesh player;
	
	private Screen screen;
	
	private Renderer renderer;
	
	public Engine(){
		window = new Window();
		screen = new Menu(this);
		try {
			renderer = new Renderer();
			player = new Mesh(new Texture(Texture.loadTex("res/images/test.jpg")));
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
	}
	
	private void render(){

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		renderer.bind();
		
		screen.render();
		
		renderer.render();
		
		player.render();
		
		renderer.unbind();
		
		window.render();

	}
	
	public void changeScreens(){
		//TODO
	}
		
}
