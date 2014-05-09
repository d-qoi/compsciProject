package core;

import java.awt.Rectangle;

import org.ejml.alg.dense.mult.MatrixMatrixMult;
import org.ejml.data.DenseMatrix64F;

public class Bounds3D {

	public int x, y, z, width, height, depth, rotX, rotY, rotZ;
	
	public int[] bounds;
	public double[] center;
	public DenseMatrix64F cornor;

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
		this.cornor = new DenseMatrix64F(8, 3);
		this.bounds = new int[6];
		this.center = new double[3];
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
		this.cornor = new DenseMatrix64F(8, 3);
		this.bounds = new int[6];
		this.center = new double[3];
		calcBounds();
	}

	private void calcBounds() {		

		bounds[0] = x - width / 2; // min x
		bounds[1] = y - depth / 2; // min y
		bounds[2] = z - height / 2; // min z
		bounds[3] = x + width / 2; // max x
		bounds[4] = y + depth / 2; // max y
		bounds[5] = z + height / 2; // max z
		center[0] = (bounds[0]+bounds[3])/2;
		center[1] = (bounds[1]+bounds[4])/2;
		center[2] = (bounds[2]+bounds[5])/2;
		calcCornors();

	}

	private void calcCornors() {			

		cornor.set(0, 0, bounds[0]);
		cornor.set(0, 1, bounds[1]);
		cornor.set(0, 2, bounds[2]);

		cornor.set(1, 0, bounds[3]);
		cornor.set(1, 1, bounds[1]);
		cornor.set(1, 2, bounds[2]);

		cornor.set(2, 0, bounds[3]);
		cornor.set(2, 1, bounds[4]);
		cornor.set(2, 2, bounds[2]);

		cornor.set(3, 0, bounds[0]);
		cornor.set(3, 1, bounds[4]);
		cornor.set(3, 2, bounds[2]);

		cornor.set(4, 0, bounds[0]);
		cornor.set(4, 1, bounds[1]);
		cornor.set(4, 2, bounds[5]);

		cornor.set(5, 0, bounds[3]);
		cornor.set(5, 1, bounds[1]);
		cornor.set(5, 2, bounds[5]);

		cornor.set(6, 0, bounds[3]);
		cornor.set(6, 1, bounds[4]);
		cornor.set(6, 2, bounds[5]);

		cornor.set(7, 0, bounds[0]);
		cornor.set(7, 1, bounds[4]);
		cornor.set(7, 2, bounds[5]);

	}

	public int[] getBounds() {
		return this.bounds;
	}

	public DenseMatrix64F getCornors() {
		return this.cornor;
	}

	public double[][][] getFaces() {
		double[][][] faces = new double[6][5][3];
		//face 0 is z1 unchanging base x, y, z		
		faces[0][0][0] = cornor.get(0,0);
		faces[0][0][1] = cornor.get(0,1);
		faces[0][0][2] = cornor.get(0,2);
		
		faces[0][1][0] = cornor.get(1,0);
		faces[0][1][1] = cornor.get(1,1);
		faces[0][1][2] = cornor.get(1,2);
		
		faces[0][2][0] = cornor.get(2,0);
		faces[0][2][1] = cornor.get(2,1);
		faces[0][2][2] = cornor.get(2,2);
		
		faces[0][3][0] = cornor.get(3,0);
		faces[0][3][1] = cornor.get(3,1);
		faces[0][3][2] = cornor.get(3,2);

		faces[0][4] = getCenterOfFace(faces[0]);
		
		//face 1 is y1 unchanging		
		faces[1][1][0] = cornor.get(0,0);
		faces[1][1][1] = cornor.get(0,1);
		faces[1][1][2] = cornor.get(0,2);
		
		faces[1][0][0] = cornor.get(1,0);
		faces[1][0][1] = cornor.get(1,1);
		faces[1][0][2] = cornor.get(1,2);
		
		faces[1][2][0] = cornor.get(4,0);
		faces[1][2][1] = cornor.get(4,1);
		faces[1][2][2] = cornor.get(4,2);

		faces[1][3][0] = cornor.get(5, 0);
		faces[1][3][1] = cornor.get(5, 1);
		faces[1][3][2] = cornor.get(5, 2);

		faces[1][4] = getCenterOfFace(faces[1]);
		
		//face 2 is y2  unchanging
		faces[2][1][0] = cornor.get(2,0);
		faces[2][1][1] = cornor.get(2,1);
		faces[2][1][2] = cornor.get(2,2);
		
		faces[2][0][0] = cornor.get(3,0);
		faces[2][0][1] = cornor.get(3,1);
		faces[2][0][2] = cornor.get(3,2);
		
		faces[2][2][0] = cornor.get(6,0);
		faces[2][2][1] = cornor.get(6,1);
		faces[2][2][2] = cornor.get(6,2);

		faces[2][3][0] = cornor.get(7, 0);
		faces[2][3][1] = cornor.get(7, 1);
		faces[2][3][2] = cornor.get(7, 2);

		faces[2][4] = getCenterOfFace(faces[2]);
		
		
		//face 3 x1 is unchanging
		faces[3][0][0] = cornor.get(0,0);
		faces[3][0][1] = cornor.get(0,1);
		faces[3][0][2] = cornor.get(0,2);
		
		faces[3][1][0] = cornor.get(3,0);
		faces[3][1][1] = cornor.get(3,1);
		faces[3][1][2] = cornor.get(3,2);
		
		faces[3][2][0] = cornor.get(7,0);
		faces[3][2][1] = cornor.get(7,1);
		faces[3][2][2] = cornor.get(7,2);

		faces[3][3][0] = cornor.get(4, 0);
		faces[3][3][1] = cornor.get(4, 1);
		faces[3][3][2] = cornor.get(4, 2);

		faces[3][4] = getCenterOfFace(faces[3]);
		
		//face 4 is x2 unchanging
		faces[4][0][0] = cornor.get(1,0);
		faces[4][0][1] = cornor.get(1,1);
		faces[4][0][2] = cornor.get(1,2);
		
		faces[4][1][0] = cornor.get(2,0);
		faces[4][1][1] = cornor.get(2,1);
		faces[4][1][2] = cornor.get(2,2);
		
		faces[4][2][0] = cornor.get(6,0);
		faces[4][2][1] = cornor.get(6,1);
		faces[4][2][2] = cornor.get(6,2);

		faces[4][3][0] = cornor.get(5, 0);
		faces[4][3][1] = cornor.get(5, 1);
		faces[4][3][2] = cornor.get(5, 2);

		faces[4][4] = getCenterOfFace(faces[4]);
		
		//face 5 is z2 unchanging		
		faces[5][0][0] = cornor.get(4,0);
		faces[5][0][1] = cornor.get(4,1);
		faces[5][0][2] = cornor.get(4,2);
		
		faces[5][1][0] = cornor.get(5,0);
		faces[5][1][1] = cornor.get(5,1);
		faces[5][1][2] = cornor.get(5,2);
		
		faces[5][2][0] = cornor.get(6,0);
		faces[5][2][1] = cornor.get(6,1);
		faces[5][2][2] = cornor.get(6,2);

		faces[5][3][0] = cornor.get(7, 0);
		faces[5][3][1] = cornor.get(7, 1);
		faces[5][3][2] = cornor.get(7, 2);

		faces[5][4] = getCenterOfFace(faces[5]);
		return faces;
	}

	public double[] getCenterOfFace(double[][] face) {
		double[] center = new double[3];
		center[0] = (face[0][0] + face[3][0]) / 2;
		center[1] = (face[0][1] + face[3][1]) / 2;
		center[2] = (face[0][2] + face[3][2]) / 2;
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

	// TODONE Rewrite Intersection code
	public boolean intersects(Bounds3D b) {
		// Rectangle[] aBounds = getDirBounds(), bBounds = other.getDirBounds();

		return !(b.x > x + width || b.x + b.width < x || b.y > y + depth
				|| b.y + b.depth < y || b.z > z + height || b.z + b.height < z);
		/*
		 * return (aBounds[0].intersects(bBounds[0])?1:0) // such inefficient,
		 * much bad + (aBounds[1].intersects(bBounds[1])?1:0) +
		 * (aBounds[2].intersects(bBounds[2])?1:0) > 1;
		 */
	}

	public void rotateZ(double deg) {
		deg = Math.toRadians(deg);
		DenseMatrix64F rotZ = new DenseMatrix64F(3, 3, true, Math.cos(deg),
				-Math.sin(deg), 0, Math.sin(deg), Math.cos(deg), 0, 0, 0, 1);
		
		DenseMatrix64F output = new DenseMatrix64F(8, 3);
		
		for(int i = 0; i<cornor.getNumRows(); i++)
		{
			
			for(int x = 0; x<3; x++) {
				for(int y = 0; y<3; y++) {
					output.add(i,x, rotZ.get(y, x) * (cornor.get(i,y) - center[y]));
				}
				output.add(i,x,center[x]);
			}
			
		}
		//output.print();
		cornor = output;
		//calcBounds();

	}
	
	public void rotateX(double deg) {
		deg = Math.toRadians(deg);
		DenseMatrix64F rotX = new DenseMatrix64F(3, 3, true, 1, 0, 0, 0,
				Math.cos(deg), -Math.sin(deg), 0, Math.sin(deg), Math.cos(deg));


		DenseMatrix64F output = new DenseMatrix64F(8, 3);
		
		for(int i = 0; i<cornor.getNumRows(); i++)
		{
			
			for(int x = 0; x<3; x++) {
				for(int y = 0; y<3; y++) {
					output.add(i,x, rotX.get(y, x) * (cornor.get(i,y) - center[y]));
				}
				output.add(i,x,center[x]);
			}
			
		}
		//output.print();
		cornor = output;
	}

	public void rotateY(double deg) {
		deg = Math.toRadians(deg);
		DenseMatrix64F rotY = new DenseMatrix64F(3, 3, true, Math.cos(deg), 0,
				Math.sin(deg), 0, 1, 0, -Math.sin(deg), 0, Math.cos(deg));
		
		DenseMatrix64F output = new DenseMatrix64F(8, 3);
		
		for(int i = 0; i<cornor.getNumRows(); i++)
		{
			
			for(int x = 0; x<3; x++) {
				for(int y = 0; y<3; y++) {
					output.add(i,x, rotY.get(y, x) * (cornor.get(i,y) - center[y]));
				}
				output.add(i,x,center[x]);
			}
			
		}
		//output.print();
		cornor = output;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		calcBounds();
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		calcBounds();
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
		calcBounds();
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

}
