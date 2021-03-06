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

public class RenderTest4 {
	
	public static StaticObject cube = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		GameWindow game = new GameWindow();
		game.setRenderPanel(new RenderPanel() {
			long lastRender = 0, lastSec = 0;
			int tickCount = 0, tickSec = 0, renderSec = 0, currTick = 0,
					currRender = 0, totaltick = 0;

			public void onTick() {
				tickCount++;
				tickSec++;
				totaltick++;
				
				
				//double time = new Date().getTime();
				System.out.println(totaltick);
				double time = Math.toRadians(3);
				double x,y;
				x = cube.body.x * Math.cos(time) - cube.body.y * Math.sin(time);
				y = cube.body.y * Math.cos(time) + cube.body.x * Math.sin(time);
				cube.body.x=(int)Math.round(x);
				cube.body.y=(int)Math.round(y);
				System.out.println(x + " " + y);
			}

			public void onPostPaint(Graphics2D g) {
				renderSec++;
				long time = (long)Math.toRadians(3);//onew Date().getTime();
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
				g.drawString("ROT: " + (Math.round(Math.toDegrees(time)) % 360), 10, 105);
				tickCount = 0;
				lastRender = time;
			}
		});
		game.setGameListener(new GameListener());
		game.setGameTick(new GameTick());
		game.setEngine(new Engine());

		game.getEngine().useCamera(new Camera(0,0,0,0));

		cube = new StaticObject(new Bounds3D(400, 400, 0, 500, 500, 500));
		game.renderPanel.add(cube);
		//game.renderPanel.add(new StaticObject(new Bounds3D(0, 0, 5, 4, 4, 4)));
		//game.renderPanel.add(new StaticObject(new Bounds3D(0, 0, 10, 4, 4, 4)));

		game.start();

	}

}
