package engine3D;

public class RenderPolygon {
	public int x1,y1,x2,y2,x3,y3,x4,y4;

	public RenderPolygon(int x1, int y1, int x2, int y2, int x3, int y3,
			int x4, int y4) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.x3 = x3;
		this.y3 = y3;
		this.x4 = x4;
		this.y4 = y4;
	}

	public RenderPolygon()
	{
		this.x1 = 0;
		this.y1 = 0;
		this.x2 = 0;
		this.y2 = 0;
		this.x3 = 0;
		this.y3 = 0;
		this.x4 = 0;
		this.y4 = 0;
	}
	
	public RenderPolygon(int[][] face)
	{
		this.x1 = face[0][0];
		this.y1 = face[0][1];
		this.x2 = face[1][0];
		this.y2 = face[1][1];
		this.x3 = face[2][0];
		this.y3 = face[2][1];
		this.x4 = face[3][0];
		this.y4 = face[3][1];
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public int getX3() {
		return x3;
	}

	public void setX3(int x3) {
		this.x3 = x3;
	}

	public int getY3() {
		return y3;
	}

	public void setY3(int y3) {
		this.y3 = y3;
	}

	public int getX4() {
		return x4;
	}

	public void setX4(int x4) {
		this.x4 = x4;
	}

	public int getY4() {
		return y4;
	}

	public void setY4(int y4) {
		this.y4 = y4;
	}
	
	public String toString() {
		String out = "";
		out += "X1 : " + x1 + " :: ";
		out += "Y1 : " + y1 + "\n";
		out += "X2 : " + x2 + " :: ";
		out += "Y2 : " + y2 + "\n";
		out += "X3 : " + x3 + " :: ";
		out += "Y3 : " + y3 + "\n";
		out += "X4 : " + x4 + " :: ";
		out += "Y4 : " + y4 + "\n";
		return out;
	}
	
	
	//TODO add other features
	//This is what the engine will return for the game to render?
	//TODO change as needed
	
	//TODO Pass this a pointer to the canvas and draw here.

}
