package ui;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class Position {

	public static final int NORTH = 0, TOP = 0, UP = 0, EAST = 0, RIGHT = 0;
	public static final int SOUTH = 1, BOTTOM = 1, DOWN = 1, WEST = 1,
			LEFT = 1;
	public static final int CENTER = 2;
	public static final int NONE = -1, UNDEF = -1;

	public JPanel panel;
	public int x, y;
	public Point size;

	public Position(JPanel panel, int horiz, int vert, Point size) {
		this.panel = panel;
		this.x = horiz;
		this.y = vert;
		this.size = size;
	}

	public int getX() {
		switch (x) {
		case EAST:
			return panel.getWidth() - size.x;
		case WEST:
			return 0;
		case CENTER:
			return panel.getWidth() / 2 - size.x / 2;
		default:
			return size.x;
		}
	}

	public int getY() {
		switch (x) {
		case NORTH:
			return 0;
		case SOUTH:
			return panel.getHeight() - size.y;
		case CENTER:
			return panel.getHeight() / 2 - size.y / 2;
		default:
			return size.y;
		}
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public int getHoriz() {
		return x;
	}

	public void setVert(int vert) {
		this.y = vert;
	}

	public int getVert() {
		return y;
	}

	public void setHoriz(int horiz) {
		this.x = horiz;
	}

	public Point getSize() {
		return size;
	}

	public void setSize(Point size) {
		this.size = size;
	}

}
