package core;

import java.awt.Rectangle;

import org.ejml.data.FixedMatrix3x3_64F;

public class Bounds3D {

	public int x, y, z, width, height, depth, rotX, rotY, rotZ;
	public int[] bounds;
	public int[][] cornors;
	
	public Bounds3D(int x, int y, int z, int width, int depth, int height) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.width = width;
		this.depth = depth;
		this.height = height;
		this.rotX = 0;
		this.rotY = 0;
		this.rotZ = 0;
		calcBounds();
	}

	public Bounds3D(Rectangle base, int z, int height) {
		this.x = base.x + base.width / 2;
		this.y = base.y + base.height / 2;
		this.z = z;
		this.width = base.width;
		this.depth = base.height;
		this.height = height;
		this.rotX = 0;
		this.rotY = 0;
		this.rotZ = 0;
		calcBounds();
	}

	private void calcBounds() {
		bounds = new int[6];

		bounds[0] = x - width / 2; // min x
		bounds[1] = y - depth / 2; // min y
		bounds[2] = z - height / 2; // min z
		bounds[3] = x + width / 2; // max x
		bounds[4] = y + depth / 2; // max y
		bounds[5] = z + height / 2; // max z
		calcCornors();

	}
	
	private void calcCornors() {
		cornors = new int[8][3];

		cornors[0][0] = bounds[0];
		cornors[0][1] = bounds[1];
		cornors[0][2] = bounds[2];
		
		cornors[1][0] = bounds[3];
		cornors[1][1] = bounds[1];
		cornors[1][2] = bounds[2];
		
		cornors[2][0] = bounds[3];
		cornors[2][1] = bounds[4];
		cornors[2][2] = bounds[2];

		cornors[3][0] = bounds[0];
		cornors[3][1] = bounds[4];
		cornors[3][2] = bounds[2];
		
		cornors[4][0] = bounds[0];
		cornors[4][1] = bounds[1];
		cornors[4][2] = bounds[5];
		
		cornors[5][0] = bounds[3];
		cornors[5][1] = bounds[1];
		cornors[5][2] = bounds[5];
		
		cornors[6][0] = bounds[3];
		cornors[6][1] = bounds[4];
		cornors[6][2] = bounds[5];

		cornors[7][0] = bounds[0];
		cornors[7][1] = bounds[4];
		cornors[7][2] = bounds[5];
		
				
	}
	
	
	
	public int[] getBounds() {
		return this.bounds;
	}
	
	public int[][] getCornors() {
		return this.cornors;
	}
	
	public int [][][] getFaces() {
		
		int[][][] faces = new int[6][5][3];
		
		//face 0 is z1 unchanging base x, y, z		
		faces[0][0] = cornors[0];
		faces[0][1] = cornors[1];
		faces[0][2] = cornors[2];
		faces[0][3] = cornors[3];
		
		
		faces[0][4] = getCenterOfFace(faces[0]);
		
		//face 1 is y1 unchanging		
		faces[1][1] = cornors[0];
		faces[1][0] = cornors[1];
		faces[1][2] = cornors[4];
		faces[1][3] = cornors[5];

		
		faces[1][4] = getCenterOfFace(faces[1]);
		
		//face 2 is y2  unchanging
		
		faces[2][1] = cornors[2];
		faces[2][0] = cornors[3];
		faces[2][2] = cornors[6];
		faces[2][3] = cornors[7];
		
		
		faces[2][4] = getCenterOfFace(faces[2]);
		
		
		//face 3 x1 is unchanging
		faces[3][0] = cornors[0];
		faces[3][1] = cornors[3];
		faces[3][2] = cornors[7];
		faces[3][3] = cornors[4];
		
		faces[3][4] = getCenterOfFace(faces[3]);
		
		//face 4 is x2 unchanging
		faces[4][0] = cornors[1];
		faces[4][1] = cornors[2];
		faces[4][2] = cornors[6];
		faces[4][3] = cornors[5];
		
		faces[4][4] = getCenterOfFace(faces[4]);
		
		//face 5 is z2 unchanging		
		faces[5][0] = cornors[4];
		faces[5][1] = cornors[5];
		faces[5][2] = cornors[6];
		faces[5][3] = cornors[7];
		
		faces[5][4] = getCenterOfFace(faces[5]);
		return faces;
	}
	
	public int[] getCenterOfFace(int[][] face)
	{
		int[] center = new int[3];
		center[0] = (face[0][0] + face[3][0])/2;
		center[1] = (face[0][1] + face[3][1])/2;
		center[2] = (face[0][2] + face[3][2])/2;
		return center;
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

		return (aBounds[0].intersects(bBounds[0])?1:0) // such inefficient, much bad
				+ (aBounds[1].intersects(bBounds[1])?1:0)
				+ (aBounds[2].intersects(bBounds[2])?1:0) > 1;
	}
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
		calcBounds();
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
		calcBounds();
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
		calcBounds();
	}
	
	
	public static class Rotation3D {
		
		
		public void rotateZ(double deg) {
			deg = Math.toRadians(deg);
			FixedMatrix3x3_64F rotZ = new FixedMatrix3x3_64F(Math.cos(deg) ,-Math.sin(deg), 0,
															Math.sin(deg), Math.cos(deg), 0,
															0,0,1);
		}
		
		public void rotateX(double deg) {
			deg = Math.toRadians(deg);
			FixedMatrix3x3_64F rotX = new FixedMatrix3x3_64F(1, 0, 0,
															0, Math.cos(deg), -Math.sin(deg),
															0, Math.sin(deg), Math.cos(deg));
		}
		
		public void rotateY(double deg) {
			deg = Math.toRadians(deg);
			FixedMatrix3x3_64F rotY = new FixedMatrix3x3_64F(Math.cos(deg), 0, Math.sin(deg), 
															0, 1, 0,
															-Math.sin(deg), 0, Math.cos(deg));	
		}
	}

}
