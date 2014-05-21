package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import core.GameListener;
import core.GameObject;
import core.GameTick;
import core.GameWindow;
import core.RenderPanel;
import engine3D.Engine;

public class Pong {

	public PongWindow game;
	public PongPanel panel;
	public GameListener listener;
	public GameTick tick;
	public Engine engine;

	public class PongWindow extends GameWindow {

		private static final long serialVersionUID = 1L;

		public void onStart() {
			// TODO initialize paddleA, paddleB
		}
	}

	public class PongPaddle extends GameObject {
		public char up = 0, down = 0;

		public void tick() {
			if (game.listener.keyDown[up])
				moveUp();
			if (game.listener.keyDown[down])
				moveDown();
		}

		public void moveUp() {
			// TODO write move up and down functions
		}

		public void moveDown() {

		}

		// TODO render as long, slender rectangular prism
	}

	public class PongBall extends GameObject {
		// TODO write cube code for ball
	}

	public class PongPanel extends RenderPanel {

		// TODO Pong ball class
		public GameObject paddelA, paddelB;
		public PongBall ball;

		private static final long serialVersionUID = 1L;

		public Font font = new Font("Courier New", Font.BOLD, 30);

		public void onPrePaint(Graphics2D g) {
			//background color?
		}

		public void onPostPaint(Graphics2D g) {
			g.setFont(font);
			g.setColor(Color.WHITE);

			FontMetrics metrics = g.getFontMetrics();
			String score = 1 + " : " + 1; //TODO replace 1's with actual score variables
			int adjust = metrics.stringWidth(score);

			int y = 40; //TODO figure out legit placement for scoreboard
			g.drawString(score, getWidth() / 2 - adjust / 2, y);
		}

		public void onTick() {
			if (ball == null) {
				//TODO handle ball creation
			} else {
				//TODO handle ball collision
			}
		}
	}

	public Pong() {
		game = new PongWindow();
		panel = new PongPanel();
		listener = new GameListener();
		tick = new GameTick();
		engine = new Engine();

		game.setRenderPanel(panel);
		game.setGameListener(listener);
		game.setGameTick(tick);
		game.setEngine(engine);

	}

	public static void main(String[] args) {
		new Pong();
	}

}