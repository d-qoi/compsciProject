package core;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import engine3D.Engine;

public class GameWindow extends JFrame{

	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_WIDTH = 800;
	public static final int DEFAULT_HEIGHT = 600;

	public JPanel mainPanel;
	public RenderPanel renderPanel;
	
	public GameListener listener;
	
	public Engine engine;
	
	public GameTick gameTick;
	public Thread gameThread;

	public GameWindow() {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public GameWindow(int width, int height) {
		super.setTitle("Game");
		super.setSize(width + 6, height + 28);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setVisible(true);

		this.setContentPane(mainPanel);
		this.setFocusable(true);
		this.setVisible(true);
	}
	
	public void setGameTick(GameTick tick) {
		gameTick = tick;
		tick.panel = renderPanel;
		tick.window = this;
		gameThread = new Thread(gameTick);
	}
	
	public void setGameListener(GameListener listener) {
		this.listener = listener;
		
		this.addKeyListener(listener);
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
	}
	
	public void setRenderPanel(RenderPanel panel) {
		renderPanel = panel;
		mainPanel.add(renderPanel);
	}
	
	public void start() {
		gameThread.start();
		onStart();
	}
	
	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
		this.renderPanel.engine = engine;
	}

	
	public void onStart() {
		
	}

}
