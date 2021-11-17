package core.engine;

import java.util.Random;

import core.game.Menu;
import core.game.Mesh;
import core.game.Screen;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class Engine implements Runnable {

	private Window window;
	
	private Random r;
	
	private Mesh player;
	
	private Screen screen;
	
	public Engine(){
		window = new Window();
		screen = new Menu(this);
		//player = new Mesh();
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
					tick();
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
		screen.render();
		
		glBindVertexArray(player.getVaoId());
		glEnableVertexAttribArray(0);
		glDrawElements(GL_TRIANGLES, player.getVertexCount(), GL_UNSIGNED_INT, 0);
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
		
		window.render();

	}
	
	public void changeScreens(){
		
	}
	
}
