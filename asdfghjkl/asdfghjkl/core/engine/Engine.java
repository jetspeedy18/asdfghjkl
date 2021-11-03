package core.engine;

public class Engine implements Runnable {

	private Window window;
	
	public Engine(){
		window = new Window();
		
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
		// update things
	}
	
	private void render(){
		// render things
	}
	
}
