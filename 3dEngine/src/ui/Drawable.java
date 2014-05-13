package ui;

import java.awt.Graphics2D;

public class Drawable implements Comparable<Drawable> {

	public int layer = 0;

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public void draw(Graphics2D g) {

	}

	public int compareTo(Drawable o) {
		if (o.getLayer() > layer)
			return -1;
		else if (o.getLayer() < layer)
			return 1;
		else
			return 0;
	}

}
