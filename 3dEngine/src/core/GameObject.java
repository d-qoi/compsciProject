package core;

import java.awt.Graphics2D;

public class GameObject implements Comparable<GameObject> {

	public Bounds3D body;
	public RenderPanel game;

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
			return null;
		}
		return this.game;
	}

	public int compareTo(GameObject o) {
		return 0;
	}
}
