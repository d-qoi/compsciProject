package engine3D;

public class Camera {
	private int x,y,rotation, z;
	
	public Camera(int x, int y, int z, int rotation) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.rotation = rotation;
	}

	public Camera(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.rotation = 0;
	}
	public Camera(int z) {
		this.x = 0;
		this.y = 0;
		this.z = z;
		this.rotation = 0;
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
	
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;	
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
	
	public int distanceToXY(int x, int y) {
		return (int)(Math.sqrt(Math.pow(x + this.x, 2) - Math.pow(y + this.y, 2)));
	}
	public int getDeltaX(int x) {
		return this.x - x;
	}
	public int getDeltaY(int y) {
		return this.y - y;
	}
	public int getDeltaZ(int z) {
		return this.z - z;
	}
}
