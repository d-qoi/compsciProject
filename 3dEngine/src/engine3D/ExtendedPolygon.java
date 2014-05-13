package engine3D;

import java.awt.Color;
import java.awt.Polygon;

public class ExtendedPolygon extends Polygon {

	private static final long serialVersionUID = -5598379283631784927L;
	
	public Color color;
	
	public ExtendedPolygon(int[] xPoints, int[] yPoints, int number, Color color) {
		super(xPoints, yPoints, number);
		this.color = color;
	}

}
