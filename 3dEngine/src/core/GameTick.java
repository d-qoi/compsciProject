package core;

import java.util.Date;

public class GameTick implements Runnable {

	public long threadDelay = 5, renderDelay = 20;

	public long lastRender;
	public boolean running;
	public RenderPanel panel;
	public GameWindow window;
	
	public GameTick() {

		running = true;
		lastRender = 0;

	}

	public void setPanel(RenderPanel panel) {
		this.panel = panel;
	}

	public long getThreadDelay() {
		return threadDelay;
	}

	public void setThreadDelay(long threadDelay) {
		this.threadDelay = threadDelay;
	}

	public long getRenderDelay() {
		return renderDelay;
	}

	public void setRenderDelay(long renderDelay) {
		this.renderDelay = renderDelay;
	}
	
	public void tick() {
		if(panel == null)
			return;
		panel.tick();
		long time = new Date().getTime();
		if (lastRender + renderDelay < time) {
			
			lastRender = time;
			panel.repaint();

		}
	}

	public void run() {
		while (running) {
			try {
				tick();
				Thread.currentThread();
				Thread.sleep(threadDelay);
			} catch (Exception e) {
				System.out.println("Game Thread Exception");
				e.printStackTrace();
			}
		}
	}

}
