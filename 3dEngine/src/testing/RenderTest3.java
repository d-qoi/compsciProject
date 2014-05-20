package testing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
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

public class RenderTest3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GameWindow game = new GameWindow();
		game.setRenderPanel(new RenderPanel() {
			long lastRender = 0, lastSec = 0;
			int tickCount = 0, tickSec = 0, renderSec = 0, currTick = 0,
					currRender = 0;

			public void onTick() {
				tickCount++;
				tickSec++;
				
				engine.camera.setRotation((engine.camera.getRotation() + 1));
				//if(engine.camera.getRotation())
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
				g.drawString("ROT: " + engine.camera.getRotation(), 10, 105);
				tickCount = 0;
				lastRender = time;
			}
		});
		game.setGameListener(new GameListener());
		game.setGameTick(new GameTick());
		game.gameTick.setThreadDelay(50);
		game.setEngine(new Engine());
		game.engine.setVerticalFOV(30);

		game.getEngine().useCamera(new Camera(250,250, 50, 0));

		game.renderPanel.add(new StaticObject(new Bounds3D(500, 500, 50, 50, 50, 10), 1));
		game.renderPanel.add(new StaticObject(new Bounds3D(100, 100, 50, 50, 50, 10), 1));
		//game.renderPanel.add(new StaticObject(new Bounds3D(0, 0, 5, 4, 4, 4)));
		//game.renderPanel.add(new StaticObject(new Bounds3D(0, 0, 10, 4, 4, 4)));

		game.start();

	}

}
