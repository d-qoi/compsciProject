package testing;

import java.awt.Color;
import java.awt.Graphics2D;

import core.*;

public class RenderTest2 {

	public static void main(String[] args) {
		GameWindow window = new GameWindow();
		RenderPanel panel = new RenderPanel() {

		};
		GameTick tick = new GameTick(panel);
		GameListener listener = new GameListener() {

		};
		window.setRenderPanel(panel);
		window.setGameTick(tick);
		window.setGameListener(listener);

		window.start();

	}

}

/*
 * objects.add(new Render2D() {public int x, y; public void draw(Graphics2D r) {
 * x += (GameListener.keyDown['a'] ? -1 : 0) + (GameListener.keyDown['d'] ? 1 :
 * 0); y += (GameListener.keyDown['w'] ? -1 : 0) + (GameListener.keyDown['s'] ?
 * 1 : 0); r.setColor(Color.RED); r.fillRect(x, y, 50, 50); } });
 */