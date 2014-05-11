package testing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Date;

import core.*;
import engine3D.Camera;
import engine3D.Engine;

public class CopyOfRenderTest2 {

	public static Engine engine;
	public static RenderPanel panel;
	public static GameListener listener;

	public static void main(String[] args) {
		
		//TODO Make tests have less ugly code
		
		GameWindow window = new GameWindow();
		panel = new RenderPanel() {
			long lastRender = 0, lastSec = 0;
			int tickCount = 0, tickSec = 0, renderSec = 0, currTick = 0,
					currRender = 0;

			public void onTick() {
				tickCount++;
				tickSec++;
			}

			public void onPostPaint(Graphics2D g) {
				renderSec++;
				long time = new Date().getTime();
				if (lastSec + 1000 < time) {
					lastSec = time;
					currTick = tickSec;
					currRender = renderSec;
					renderSec = 0;
					tickSec = 0;
				}
				g.setColor(Color.WHITE);
				g.setFont(new Font("Courier New", Font.BOLD, 20));
				g.drawString("MS: " + (time - lastRender), 10, 25);
				g.drawString("Ticks: " + tickCount, 10, 45);
				g.drawString("FPS: " + currRender, 10, 65);
				g.drawString("TPS: " + currTick, 10, 85);
				g.drawString("ROT: "+engine.camera.getRotation(), 10, 105);
				tickCount = 0;
				lastRender = time;
			}
		};
		
		GameTick tick = new GameTick(panel);
		tick.setRenderDelay(0);
		tick.setThreadDelay(10);
		listener = new GameListener() {

		};
		window.setRenderPanel(panel);
		window.setGameTick(tick);
		window.setGameListener(listener);

		window.start();
		engine = new Engine();
		engine.setVerticalFOV(30);
		Camera cam = new Camera(200, 0,50, 0);
		engine.useCamera(cam);
		engine.camera.setRotation(0);
		Render2D render = new Render2D() {
			public Polygon[] polygons;
			public Bounds3D box;
			
			public int x = 0, y = 0, z = 0, rotate = 0;

			public void onInit() {
				box = new Bounds3D(100, 95, 25, 100, 100, 10);

			}

			public void tick() {

				if(listener.keyDown['a'])
					rotate -= 1;
				if(listener.keyDown['d'])
					rotate += 1;
				if(listener.keyDown['w'])
					z -= 1;
				if(listener.keyDown['s'])
					z += 1;
				if(listener.keyDown['t']) {
					x += 1;
					box.setX(100 + x);
				}
				if(listener.keyDown['g']) {
					x -= 1;
					box.setX(100 + x);
				}
				
				
				
				//box.rotateZ(rotate);
				//int rot = (int) (new Date().getTime() * 0.1 % 360);
				//engine.camera.setRotation(rot);
				//int pos = (int) (new Date().getTime() * 0.05 % 200);
				//engine.camera.setZ(pos - 50);
				//engine.camera.setX(pos + 50);
				engine.camera.setZ(z);
				//box.setZ(z);
				//box.rotateX(rotate);
				//box.rotateY(rotate);
				//box.rotateZ(rotate);
				engine.camera.setRotation(rotate + 90);
				
				//System.out.println(engine.camera.getRotation());
				polygons = CopyOfRenderTest2.engine.debuggingRendering(box,
						CopyOfRenderTest2.panel.getWidth(),
						CopyOfRenderTest2.panel.getHeight());
			}

			public void draw(Graphics2D r) {
				if (polygons == null)
					return;
				r.setStroke(new BasicStroke(5));
				r.setColor(Color.RED);
				r.fillPolygon(polygons[0]);
				r.setColor(Color.WHITE);
				r.drawPolygon(polygons[0]);
				r.setColor(Color.GREEN);
				r.fillPolygon(polygons[1]);
				r.setColor(Color.WHITE);
				r.drawPolygon(polygons[1]);
				r.setColor(Color.BLUE);
				r.fillPolygon(polygons[2]);
				r.setColor(Color.WHITE);
				r.drawPolygon(polygons[2]);
				
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
