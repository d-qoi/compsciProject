package engine3D;

public class Rectangle {
	
	private int x1,x2,y1,y2,z1,z2,distance, verticalFOV, playerHeight;
	
	
	public Rectangle(int angle, int distance, int verticalFOV, int playerHeight)
	{
		
		//the sin and cos may need to be switched... sticking with the same throughout
		double xoffleft = Math.sin(Math.toRadians(angle + 45));
		double yoffleft = Math.cos(Math.toRadians(angle + 45));
		double xoffright = Math.sin(Math.toRadians(angle - 45));
		double yoffright = Math.cos(Math.toRadians(angle - 45));
		x1 = (int)(distance * xoffleft);
		x2 = (int)(distance * xoffright);
		y1 = (int)(distance * yoffleft);
		y2 = (int)(distance * yoffright);
		z1 = (int)(Math.sin(Math.toRadians(verticalFOV/2))) * distance + playerHeight;
		int temp = (int)(Math.sin(Math.toRadians(-verticalFOV/2))) * distance + playerHeight;
		z2 = (temp < 0) ? 0 : temp;
		
		this.distance = distance;
		this.playerHeight = playerHeight;
		this.verticalFOV = verticalFOV;
		
	}
	
	public int getX1() {
		return x1; // left
	}

	public int getX2() {
		return x2; // right
	}

	public int getY1() {
		return y1; // left
	}

	public int getY2() {
		return y2; // right
	}
	
	public int getZ1() // top
	{
		return z1;
	}
	
	public int getZ2() // bottom
	{
		return z2;
	}
	
	public int getDistance()
	{
		return distance;
	}
	
	public int getVerticalFOV() {
		return verticalFOV;
	}

	public int getPlayerHeight() {
		return playerHeight;
	}

	public void setPlayerHeight(int playerHeight) {
		this.playerHeight = playerHeight;
	}

	public boolean intersects(int x, int y, int z, Camera camera)
	{
		if(z1 < z && z2 > z)
			return false;
		if(x1 > x2)
			if(x1 + camera.getX() < x && x2 + camera.getX() > x)
				return false;
		if(x2 > x1)
			if(x2 + camera.getX() < x && x1 + camera.getX() > x)
				return false;
		if(y1 > y2)
			if(y1 + camera.getY() < y && y2 + camera.getY() > y)
				return false;
		if(y2 > y1)
			if(y2 + camera.getY() < y && y1 + camera.getY() > y)
				return false;
		return true;
	}
	
	
}