package core;

import java.util.Date;

public class GameTick implements Runnable {

	public static final long THREAD_DELAY = 5;
	public static final long RENDER_DELAY = 20;

	public long lastRender;
	public boolean running;
	public RenderPanel panel;

	public GameTick() {
		this(null);
	}
	
	public GameTick(RenderPanel panel) {

		running = true;
		lastRender = 0;

		this.panel = panel;

	}
	
	public void setPanel(RenderPanel panel) {
		this.panel = panel;
	}

	public void tick() {
		if(panel == null)
			return;
		panel.tick();
		long time = new Date().getTime();
		if (lastRender + RENDER_DELAY < time) {
			
			lastRender = time;
			panel.repaint();

		}
	}

	public void run() {
		while (running) {
			try {
				tick();
				Thread.currentThread();
				Thread.sleep(THREAD_DELAY);
			} catch (Exception e) {
				System.out.println("Game Thread Exception");
				e.printStackTrace();
			}
		}
	}

}
