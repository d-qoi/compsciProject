package core;

import java.awt.Graphics2D;
import engine3D.Engine;

public class GameObject implements Comparable<GameObject>{

	public Bounds3D body;
	public RenderPanel game;
	public Engine engine;
	
	public int flag;

	public void draw(Graphics2D render) {

	}

	public Bounds3D getBounds() {
		return body;
	}

	public void tick() {

	}

	public void setGame(RenderPanel panel) {
		this.game = panel;
	}

	public RenderPanel getGame() {
		if (!this.game.objects.contains(this)) {
			this.game = null;
			this.engine = null;
			return null;
		}
		return this.game;
	}
	
	public double distCam() {
		return body.getCenter().distance(engine.camera.getLocation());
	}
	
	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}


	public int compareTo(GameObject o) {
		// TODO Auto-generated method stub
		if(o.distCam() > this.distCam())
			return -1;
		if(o.distCam() < this.distCam())
			return 1;
		return 0;
	}
}
