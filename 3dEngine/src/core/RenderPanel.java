package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import engine3D.Engine;

import javax.swing.JPanel;

public class RenderPanel extends JPanel {

	private static final long serialVersionUID = -7206037125084756503L;

	public BufferedImage renderImage;

	public ArrayList<GameObject> objects;
	
	public Engine engine;

	public RenderPanel() {
		super();
		this.setFocusable(true);

		objects = new ArrayList<GameObject>();

	}

	public void add(GameObject obj) {
		this.objects.add(obj);
		obj.setEngine(this.engine);
		obj.setGame(this);
	}

	public GameObject remove(GameObject obj) {
		int pos = this.objects.indexOf(obj);
		if (pos != -1)
		{
			return this.objects.remove(pos);
		}
		return null;
	}

	public void empty() {
		this.objects.clear();
	}

	public void tick() {
		for (GameObject obj : objects) {
			obj.tick();
		}
		onTick();
	}

	public void onTick() {

	}

	public void onPrePaint(Graphics2D render) {

	}

	public void onPostPaint(Graphics2D render) {

	}

	public void onPaint(Graphics2D render) {

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

		onPrePaint(render);

		handleRendering(render);

		onPaint(render);
		
		onPostPaint(render);

		canvas.drawImage(renderImage, 0, 0, null);
	}

	public void handleRendering(Graphics2D render) {

		Collections.sort(objects);
		for (GameObject obj : objects) { // Rendering objects
			obj.draw(render);
		}

	}

}
