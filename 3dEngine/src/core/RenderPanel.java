package core;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.PriorityQueue;

import javax.swing.JPanel;

public class RenderPanel extends JPanel {

	private static final long serialVersionUID = -7206037125084756503L;

	public GameWindow window = TankGame.WINDOW;
	public BufferedImage renderImage;

	public PriorityQueue<RenderObject> dynamicObjects, staticObjects;

	public RenderPanel() {
		super();
		super.setFocusable(true);

		dynamicObjects = new PriorityQueue<RenderObject>();
		staticObjects = new PriorityQueue<RenderObject>();

	}

	public void paint(Graphics canvas) {
		super.paint(canvas);

		int width = window.getWidth(), height = window.getHeight();

		renderImage = (BufferedImage) createImage(width, height);
		Graphics2D render = renderImage.createGraphics();

		render.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		handleRendering(render);

		canvas.drawImage(renderImage, 0, 0, null);
	}

	public void handleRendering(Graphics2D render) {

		for (RenderObject obj : staticObjects) { // Rendering static objects
			obj.draw(render);
		}

		for (RenderObject obj : dynamicObjects) { // Rendering dynamic objects
			obj.draw(render);
		}

	}

}
