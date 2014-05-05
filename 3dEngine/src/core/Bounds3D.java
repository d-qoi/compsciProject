package core;

import java.awt.Rectangle;

public class Bounds3D {

	public int x, y, z, width, height, depth;

	public Bounds3D(int x, int y, int z, int width, int depth, int height) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.width = width;
		this.depth = depth;
		this.height = height;
	}

	public Bounds3D(Rectangle base, int z, int height) {
		this.x = base.x + base.width / 2;
		this.y = base.y + base.height / 2;
		this.z = z;
		this.width = base.width;
		this.depth = base.height;
		this.height = height;
	}

	public int[] getBounds() {
		int[] bounds = new int[6];

		bounds[0] = x - width / 2; // min x
		bounds[1] = y - depth / 2; // min y
		bounds[2] = z - height / 2; // min z
		bounds[3] = x + width / 2; // min x
		bounds[4] = y + depth / 2; // min y
		bounds[5] = z + height / 2; // min z

		return bounds;
	}

	public Rectangle[] getDirBounds() {
		Rectangle[] bounds = new Rectangle[3];
		int[] points = getBounds();

		bounds[0] = new Rectangle(points[0], points[1], points[3], points[4]); // XY
																				// Plane
		bounds[1] = new Rectangle(points[0], points[2], points[3], points[5]); // XZ
																				// Plane
		bounds[2] = new Rectangle(points[1], points[2], points[4], points[5]); // YZ
																				// Plane

		return bounds;
	}

	// TODO Rewrite Intersection code
	public boolean intersects(Bounds3D other) {
		Rectangle[] aBounds = getDirBounds(), bBounds = other.getDirBounds();

		return aBounds[0].intersects(bBounds[0]) // such inefficient, much bad
				&& aBounds[1].intersects(bBounds[1])
				&& aBounds[2].intersects(bBounds[2]);
	}

}
