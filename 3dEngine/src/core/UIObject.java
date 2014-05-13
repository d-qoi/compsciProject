package core;

import java.awt.Color;	
import java.awt.Graphics2D;
import java.awt.Rectangle;

import ui.Overlay;

public class UIObject extends GameObject {
	
	Overlay overlay;
	
	public UIObject() {
		super();
		overlay = new Overlay(game);
	}
	
	public void setGame(RenderPanel game) {
		super.setGame(game);
		overlay.panel=game;
	}

	public void draw(Graphics2D render) {
		overlay.draw(render);
	}


	public int compareTo(GameObject object) {
		return object instanceof UIObject ? 0 : 1; 
	}

	public void tick() {
		
	}

}
