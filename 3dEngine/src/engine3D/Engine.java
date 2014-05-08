package engine3D;

import java.awt.Polygon;

public class Engine {
	
	private static int FOVAngleStep = 5;
	private static int FOVBoxDepth = 5;
	private int viewDistance;
	private int characterHeight;
	private double scalingX;
	private double scalingY;
	public int verticalFOV;
	public int horizontalFOV;
	public Camera camera;
	public Rays ray;
	
	

	
	Rectangle[][] FOVBox; 
	
	
	public Engine(int verticalFOV, int horizontalFOV,
			int viewDistance, int characterHeight)
	{
		this.verticalFOV = verticalFOV;
		this.horizontalFOV = horizontalFOV;
		this.viewDistance = viewDistance;
		this.characterHeight = characterHeight;
		calculateRacasting();
		camera = new Camera(characterHeight);
		
	}

	public Engine(int verticalFOV, int horizontalFOV) {
		this.verticalFOV = verticalFOV;
		this.horizontalFOV = horizontalFOV;
		this.viewDistance = 1000;
		this.characterHeight = 85;
		calculateRacasting();
		camera = new Camera(characterHeight);
	}

	public Engine(int characterHeight) {
		this.characterHeight = characterHeight;
		this.verticalFOV = 22;
		this.horizontalFOV = 90;
		this.viewDistance = 1000;
		calculateRacasting();
		camera = new Camera(characterHeight);
	}
	public Engine()	{
		this(22,90,1000,85);
	}
	
	public void setViewDistance(int viewDistance) {
		this.viewDistance = viewDistance;
		ray.setViewDistance(viewDistance);
	}
	
	public void setHorizontalFOV(int FOV) {
		this.horizontalFOV = FOV;
	}
	
	public void setVerticalFOV(int FOV) {
		this.verticalFOV = FOV;
	}
	
	public void calculateScaling(int ScreenWidth, int ScreenHeight) {//not being used curently
		scalingX = (ScreenWidth / (Math.tan(Math.toRadians(horizontalFOV/2)) * viewDistance * 2));
		scalingY = (ScreenHeight / (Math.tan(Math.toRadians(verticalFOV/2)) * viewDistance * 2));
		//System.out.println(scalingX + " " + scalingY + " ingored for now");
	}
	
	private void calcFOVBox() { //old
		
		//System.out.println(viewDistance/FOVBoxDepth);
		FOVBox = new Rectangle[horizontalFOV/FOVAngleStep][viewDistance/FOVBoxDepth];
		for(int angle = 0; angle<horizontalFOV/FOVAngleStep; angle++)
		{
			
			for(int depth = 0; depth<viewDistance/FOVBoxDepth; depth++)
			{
				FOVBox[angle][depth] = new Rectangle(angle * FOVAngleStep, depth * FOVBoxDepth, 
						verticalFOV, characterHeight);
				//System.out.printf("Angle %d depth %d \n", angle, depth);
			}
		}
		
	}
	
	private void calculateRacasting() {
		ray = new Rays(viewDistance, FOVBoxDepth, 360, FOVAngleStep);
	}
	
	public void useCamera(Camera camera) {
		this.camera = camera;
	}
	
	private boolean renderCheckWorld()	{
		//TODO get this working, needs to check if FOVbox intersects the world box.
		return false;
	}
	
	private boolean renderCheckEntity()	{
		//TODO see if the entity intersects the FOVbox.
		return false;
	}


	
	private Polygon[] convertWorldToScreenNew(core.Bounds3D that, int width, int height)
	{
		
		int[][][] faces = that.getFaces();
		int[][] faceX;
		int[][] faceY;
		int[][] faceZ;
		Polygon[] poly = new Polygon[3];
		
		//System.out.println("Chosing faces :: ");
		
		if(camera.distanceToXY(faces[3][4][0], faces[3][4][1]) < camera.distanceToXY(faces[4][4][0], faces[4][4][1]))
		{
			//System.out.println("Face 3");
			faceX = faces[3];
		}
		else {
			//System.out.println("Face 4");
			faceX = faces[4];
		}
		
		if(camera.distanceToXY(faces[1][4][0], faces[1][4][1]) < camera.distanceToXY(faces[2][4][0], faces[2][4][1])) {
			//System.out.println("Face 1");
			faceY = faces[1];
		}
		else {
			//System.out.println("Face 2");
			faceY = faces[2];
		}
		if(camera.distanceToXY(faces[0][4][0], faces[0][4][1]) < camera.distanceToXY(faces[5][4][0], faces[5][4][1])) {
			//System.out.println("Face 0");
			faceZ = faces[0];
		}
		else {
			//System.out.println("Face 5");
			faceZ = faces[5];
		}
		
		for(int i = 0; i<faceX.length; i++)
		{
			//System.out.printf("FaceX point %d ");
			int[] tempPoint = pointToScreenNew(faceX[i], width, height);
			faceX[i] = tempPoint;
			
		}
		
		for(int i = 0; i<faceY.length; i++)
		{
			//System.out.printf("FaceX point %d ");
			int[] tempPoint = pointToScreenNew(faceY[i], width, height);
			faceY[i] = tempPoint;
			
		}
		for(int i = 0; i<faceZ.length; i++)
		{
			//System.out.printf("FaceX point %d ");
			int[] tempPoint = pointToScreenNew(faceZ[i], width, height);
			faceZ[i] = tempPoint;
			
		}
		//Xface polygon creation
		int[] tempX = new int[4];
		int[] tempY = new int[4];
		
		//System.out.println(faceX.length);
		// -1 is to get rid of the midpoint...
		for(int i = 0; i<faceX.length-1; i++)
		{
			//System.out.println(i);
			tempX[i] = faceX[i][0];
			tempY[i] = faceX[i][1];
		}
		poly[0] = new Polygon(tempX,tempY,4);
		
		//Yface polygon creation
		tempX = new int[4];
		tempY = new int[4];
		for(int i = 0; i<faceY.length-1; i++)
		{
			tempX[i] = faceY[i][0];
			tempY[i] = faceY[i][1];
		}
		poly[1] = new Polygon(tempX,tempY,4);
		
		//Zface polygon creation
		tempX = new int[4];
		tempY = new int[4];
		for(int i = 0; i<faceZ.length-1; i++)
		{
			tempX[i] = faceZ[i][0];
			tempY[i] = faceZ[i][1];
		}
		poly[2] = new Polygon(tempX,tempY,4);
		
		
		return poly;
	}
	
	private int[] pointToScreenNew(int[] coord, int width, int height) {
		int[] point = new int[2];
		int CenterX = width/2;
		int CenterY = height/2;
		
		//TODO mess with positive and negatives here because they are weird.
		//TODO make sure scaling is correct
		int distance = camera.distanceToXY(coord[0], coord[1]);
		
		point[0] = CenterX;
		
		//TODO mess with haphazzard rotation.
		double slopeX = (double)camera.getDeltaX(coord[0])/distance;
		point[0] += (int)(slopeX*viewDistance) * scalingX * (int)(Math.cos(Math.toRadians(camera.getRotation())));
		
		slopeX = (double)camera.getDeltaY(coord[1])/distance;
		point[0] += (int)(slopeX*viewDistance) * scalingX * (int)(Math.sin(Math.toRadians(camera.getRotation())));
		
				
		point[1] = CenterY;
		
		//TODO finish this
		double slopeZ = (double)camera.getDeltaZ(coord[2])/distance;
		point[1] += (int)(slopeZ*viewDistance) * scalingY;
		
		return point;
	}
	
	public Polygon[] debuggingRendering(core.Bounds3D that, int width, int height)
	{
		calculateScaling(width, height);
		return convertWorldToScreenNew(that, width, height);
	}
}
