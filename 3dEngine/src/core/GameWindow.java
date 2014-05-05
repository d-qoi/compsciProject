package core;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow extends JFrame{

	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_WIDTH = 800;
	public static final int DEFAULT_HEIGHT = 600;

	public JPanel mainPanel;
	public RenderPanel renderPanel;
	
	public GameListener listener;
	
	public GameTick gameTick;
	public Thread gameThread;

	public GameWindow() {

		super.setTitle("Tank Game");
		super.setSize(DEFAULT_WIDTH + 6, DEFAULT_HEIGHT + 28);

		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setVisible(true);

		renderPanel = new RenderPanel();
		mainPanel.add(renderPanel);

		this.setContentPane(mainPanel);
		this.setFocusable(true);
		this.setVisible(true);
		
		this.addKeyListener(listener);
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
		
		gameTick = new GameTick(renderPanel);		
		gameThread = new Thread(gameTick);
		gameThread.start();
	}

}
