package core;

public class GameTick implements Runnable {

	public static final long THREAD_DELAY = 25;

	public boolean running;

	public GameTick() {

		running = true;

	}

	public void tick() {

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
