package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

public class Overlay {

	public ArrayList<Drawable> objects;

	public JPanel panel;
	public boolean drawBackground;
	public Color backgroundColor;

	public Overlay(JPanel panel) {
		this.panel = panel;
		this.drawBackground = false;
		this.backgroundColor = Color.BLACK;
		objects = new ArrayList<Drawable>();
	}

	public void draw(Graphics2D g) {

		if (drawBackground) {
			g.setColor(backgroundColor);
			g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
		}

		Collections.sort(objects);

		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).draw(g);
		}

	}
	
	public DString addString(String text, int horiz, int vert) {
		DString string = new DString(text,new Position(panel,horiz,vert,new Point()));
		add(string);
		return string;
	}

	public void add(Drawable object) {
		objects.add(object);
	}

	public void remove(Drawable object) {
		objects.remove(object);
	}

	public void remove(int i) {
		objects.remove(i);
	}

	public boolean isDrawBackground() {
		return drawBackground;
	}

	public void setDrawBackground(boolean drawBackground) {
		this.drawBackground = drawBackground;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

}
