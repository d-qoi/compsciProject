package core;

import java.awt.Graphics2D;

import engine3D.Engine;


public class StaticObject extends GameObject {

	//TODO Write more constructors
	//TODO Create rendering methods
	public CubeTexture texture;
	
	public StaticObject() {
		this(new Bounds3D(0,0,0,0,0,0));
	}
	
	public StaticObject(Bounds3D poly) {
		this.body = poly;
	}

	public void calculate(Engine engine) {
		
	}
	
	public void draw(Graphics2D render) {

	}

	public int compareTo(GameObject object) {
		return 0;
	}

	public void tick() {
		
	}
	
	public CubeTexture getTexture() {
		return texture;
	}

	public void setTexture(CubeTexture texture) {
		this.texture = texture;
	}


}
