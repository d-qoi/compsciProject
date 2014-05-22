package testing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.Date;

import ui.DString;
import ui.Position;
import core.Bounds3D;
import core.GameListener;
import core.GameTick;
import core.GameWindow;
import core.RenderPanel;
import core.StaticObject;
import core.UIObject;
import engine3D.Camera;
import engine3D.Engine;

public class TechDemo {

	public static StaticObject cube = null;

	public static void main(String[] args) {

		GameWindow game = new GameWindow();
		game.setRenderPanel(new RenderPanel() {

			public void onTick() {
				Camera camera = engine.camera;
				if (listener.keyDown['J'] || listener.keyDown['j'])
					camera.setRotation((camera.getRotation() - 1) % 360);
				if (listener.keyDown['L'] || listener.keyDown['l'])
					camera.setRotation((camera.getRotation() + 1) % 360);
				if (listener.keyDown['W'] || listener.keyDown['w']) {
					int rotation = camera.getRealRotation();
					int dx = (int) (Math.cos(Math.toRadians(rotation + 180)) * 10), dy = (int) (Math
							.sin(Math.toRadians(rotation + 180)) * 10);
					camera.setXY(camera.getX() + dx, camera.getY() + dy);
				}
				if (listener.keyDown['S'] || listener.keyDown['s']) {
					int rotation = camera.getRealRotation();
					int dx = (int) (Math.cos(Math.toRadians(rotation)) * 10), dy = (int) (Math
							.sin(Math.toRadians(rotation)) * 10);
					camera.setXY(camera.getX() + dx, camera.getY() + dy);
				}
				if (listener.keyDown['A'] || listener.keyDown['a']) {
					int rotation = camera.getRealRotation();
					int dx = (int) (Math.cos(Math.toRadians(rotation + 90)) * 10), dy = (int) (Math
							.sin(Math.toRadians(rotation + 90)) * 10);
					camera.setXY(camera.getX() + dx, camera.getY() + dy);
				}
				if (listener.keyDown['D'] || listener.keyDown['d']) {
					int rotation = camera.getRealRotation();
					int dx = (int) (Math.cos(Math.toRadians(rotation - 90)) * 10), dy = (int) (Math
							.sin(Math.toRadians(rotation - 90)) * 10);
					camera.setXY(camera.getX() + dx, camera.getY() + dy);
				}

			}

			public void onPostPaint(Graphics2D g) {
				g.setColor(Color.WHITE);
				g.fillOval(this.getWidth() / 2 - 5, this.getHeight() / 2 - 5,
						10, 10);
				int rotation = engine.camera.getRealRotation();
				int dx = (int) (Math.cos(Math.toRadians(rotation)) * 30), dy = (int) (Math
						.sin(Math.toRadians(rotation)) * 30);
				g.drawLine(this.getWidth() / 2, this.getHeight() / 2,
						this.getWidth() / 2 + dx, this.getHeight() / 2 + dy);
				for (int i = 0; i < objects.size(); i++) {
					if (objects.get(i).body != null) {
						g.drawRect(
								this.getWidth()
										/ 2
										+ (int) (engine.camera.getX() - objects
												.get(i).body.getCenter().x)
										/ 20 - 5,
								this.getHeight()
										/ 2
										+ (int) (engine.camera.getY() - objects
												.get(i).body.getCenter().y)
										/ 20 - 5, 10, 10);
					}
				}

			}
		});
		game.setGameListener(new GameListener());
		game.setGameTick(new GameTick());
		game.setEngine(new Engine()); //lol

		game.getEngine().useCamera(new Camera(0, 0, 0, 0));

		for (int i = 0; i < 30; i++)
			game.renderPanel.add(new StaticObject(new Bounds3D((int) (Math
					.random() * 30000), (int) (Math.random() * 30000),
					(int) (Math.random() * 600), 200, 200, 200), 1));

		game.start();

	}

}
