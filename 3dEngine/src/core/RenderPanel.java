package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.PriorityQueue;

import javax.swing.JPanel;

public class RenderPanel extends JPanel {

	private static final long serialVersionUID = -7206037125084756503L;
	
	public BufferedImage renderImage;

	public PriorityQueue<RenderObject> objects;

	public RenderPanel() {
		super();
		this.setFocusable(true);
		
		objects = new PriorityQueue<RenderObject>();
		
		objects.add(new Render2D(){ //Demo rendering object
			public int motion;
			
			public void draw(Graphics2D r) {
				motion ++;
				r.setColor(Color.RED);
				r.fillRect(motion % 500, 0, 50, 50);
			}
		});

	}

	public void paint(Graphics canvas) {
		super.paint(canvas);

		int width = this.getWidth(), height = this.getHeight();

		renderImage = (BufferedImage) createImage(width, height);
		Graphics2D render = renderImage.createGraphics();

		render.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		render.setColor(Color.BLACK);
		render.fillRect(0, 0, width, height);

		handleRendering(render);

		canvas.drawImage(renderImage, 0, 0, null);
	}

	public void handleRendering(Graphics2D render) {
		
		for (RenderObject obj : objects) { // Rendering objects
			obj.draw(render);
		}

	}

}
