package core;

public class Location3D {

	public double x, y, z;

	public Location3D() {
		this(0,0,0);
	}
	
	public Location3D(Location3D location) {
		this(location.x, location.y, location.z);
	}
	
	public Location3D(double x, double y, double z) {
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	public boolean equals(Object object) {
		if(!(object instanceof Location3D))
			return false;
		Location3D loc = (Location3D)object;
		return loc.x == x && loc.y == y && loc.z == z;
	}
	
	public Location3D getLocation() {
		return this;
	}
	
	public void setLocation(Location3D location) {
		this.x = location.x;
		this.y = location.y;
		this.z = location.z;
	}
	
	public void setLocation(double x, double y, double z) {
		move(x,y,z);
	}
	
	public void move(double x, double y, double z) {
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	
	public void translate(double x, double y, double z) {
		this.x+=x;
		this.y+=y;
		this.z+=z;
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
	public String toString(){
		return "("+x+","+y+","+z+")";
	}
	
	public Location3D clone() {
		return new Location3D(this);
	}
	
	public double distanceSq(Location3D location) {
		return Math.pow(x-location.x,2) + Math.pow(y-location.y, 2) + Math.pow(z-location.z,2);
	}
	
	public double distance(Location3D location) {
		return Math.sqrt(distanceSq(location));
	}
	public static double distanceSq(Location3D a,Location3D b) {
		return Math.pow(a.x-b.x,2) + Math.pow(a.y-b.y, 2) + Math.pow(a.z-b.z,2);
	}
	
	public static double distance(Location3D a,Location3D b) {
		return Math.sqrt(distanceSq(a,b));
	}
}
