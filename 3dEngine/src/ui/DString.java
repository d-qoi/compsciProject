package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

public class DString extends Drawable{
	
	public String text;
	public Position position;
	public Font font;
	public Color color;
	
	public DString(String text, Position position) {
		this(text,position,null,null);
	}
	
	public DString(String text, Position position, Font font, Color color) {
		this.text = text;
		this.position = position;
		this.font = font;
		this.color = color;
	}
	
	public void draw(Graphics2D g) {
		if(color != null)
			g.setColor(color);
		if(font != null)
			g.setFont(font);
		g.drawString(text, position.getX(), position.getY());
	}
	
	public String getText() {
		return text;
	}

	public DString setText(String text) {
		this.text = text;
		return this;
	}

	public Position getPosition() {
		return position;
	}

	public DString setPosition(Position position) {
		this.position = position;
		return this;
	}

	public Font getFont() {
		return font;
	}

	public DString setFont(Font font) {
		this.font = font;
		return this;
	}

	public Color getColor() {
		return color;
	}

	public DString setColor(Color color) {
		this.color = color;
		return this;
	}



}
