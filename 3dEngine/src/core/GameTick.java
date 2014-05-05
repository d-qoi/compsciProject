package core;

public class GameTick implements Runnable {

	public static final long THREAD_DELAY = 5;

	public boolean running;
	public RenderPanel panel;

	public GameTick(RenderPanel panel) {

		running = true;
		this.panel = panel;

	}

	public void tick() {
		panel.repaint();
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
