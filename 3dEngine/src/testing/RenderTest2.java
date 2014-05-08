package testing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Date;

import core.*;
import engine3D.Camera;
import engine3D.Engine;

public class RenderTest2 {

	public static Engine engine;
	public static RenderPanel panel;

	public static void main(String[] args) {
		GameWindow window = new GameWindow();
		panel = new RenderPanel() {

		};
		GameTick tick = new GameTick(panel);
		GameListener listener = new GameListener() {

		};
		window.setRenderPanel(panel);
		window.setGameTick(tick);
		window.setGameListener(listener);

		window.start();

		engine = new Engine();
		Camera cam = new Camera(200, 0, 100, 10);
		engine.useCamera(cam);
		Render2D render = new Render2D() {
			public Polygon[] polygons;
			public Bounds3D box;

			public void onInit() {
				box = new Bounds3D(100, 95, 50, 10, 40, 30);

			}

			public void tick() {

				int rot = (int) (Math.sin(new Date().getTime() * 0.00125) * 30 + 20);
				engine.camera.setZ(rot);
				polygons = RenderTest2.engine.debuggingRendering(box,
						RenderTest2.panel.getWidth(),
						RenderTest2.panel.getHeight());
			}

			public void draw(Graphics2D r) {
				if (polygons == null)
					return;
				r.setColor(Color.RED);
				for (int i = 0; i < polygons.length; i++) {
					r.fillPolygon(polygons[i]);
				}
			}

		};

		panel.add(render);

	}

}

/*
 * objects.add(new Render2D() {public int x, y; public void draw(Graphics2D r) {
 * x += (GameListener.keyDown['a'] ? -1 : 0) + (GameListener.keyDown['d'] ? 1 :
 * 0); y += (GameListener.keyDown['w'] ? -1 : 0) + (GameListener.keyDown['s'] ?
 * 1 : 0); r.setColor(Color.RED); r.fillRect(x, y, 50, 50); } });
 */