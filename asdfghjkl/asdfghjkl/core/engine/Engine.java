package core.engine;

import java.util.Random;
import static org.lwjgl.opengl.GL11.*;

public class Engine implements Runnable {

	private Window window;
	
	private Random r;
	
	public Engine(){
		window = new Window();
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
	}
	
	private void pollInputs(){
		window.poll();
	}
	
	private void tick(){
		glClearColor(r.nextFloat(), r.nextFloat(), r.nextFloat(), 0.0f);
	}
	
	private void render(){
		window.render();
	}
	
}
