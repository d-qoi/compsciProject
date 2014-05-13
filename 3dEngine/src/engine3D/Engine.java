package engine3D;

import java.awt.Polygon;

import core.Bounds3D;
import core.GameObject;

public class Engine {
	
	private static int FOVAngleStep = 5;
	private static int FOVBoxDepth = 10;
	private int viewDistance;
	private int characterHeight;
	private double scalingX;
	private double scalingY;
	public int verticalFOV;
	public int horizontalFOV;
	public Camera camera;
	public Rays ray;
	
	//Rectangle[][] FOVBox; 
	
	
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
		//System.out.println(scalingX + " " + scalingY + " ingored for now"); //debugging code
	}

	public void useCamera(Camera camera) {
		this.camera = camera;
	}
		
	private void calculateRacasting() {
		ray = new Rays(viewDistance, FOVBoxDepth, 360, FOVAngleStep);
		/*  // debugging code
		for(int i = 0; i<ray.rays.length;i++)
		{
			for(int j = 0; j<ray.rays[i].length; j++)
			{
				System.out.print(ray.rays[i][j][0] + " " + ray.rays[i][j][1] + ", ");
			}
			System.out.println();
		}
		*/
	}
	
	public boolean renderCheckWorld(Bounds3D obj)	{
		//TODO get this working, needs to check if FOVbox intersects the world box.
		int modifiedAngle = (camera.getRotation() - horizontalFOV/2) - (camera.getRotation() - horizontalFOV/2)%FOVAngleStep;

		for(int deg = modifiedAngle; deg < modifiedAngle + horizontalFOV + FOVAngleStep; deg += FOVAngleStep)
		{
			int degMod = ((deg < 0)?360+deg : deg)/FOVAngleStep;
			for(int depth = 0; depth < ray.rays.length; depth++)
			{
				//debugging code
				//System.out.printf("%d %d %d %d %d\n",deg,degMod, depth, ray.rays[degMod][depth][0], ray.rays[degMod][depth][1]);
				if(obj.pointIsInsideXY(ray.rays[degMod][depth][0], ray.rays[degMod][depth][1])) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean renderCheckEntity()	{
		//TODO see if the entity intersects the FOVbox.
		
		return false;
	}


	
	private Polygon[] convertWorldToScreenNew(core.Bounds3D that, int width, int height)
	{
		Polygon[] poly = new Polygon[3];		
		double[][][] faces = that.getFaces();
		double[][] faceX;
		double[][] faceY;
		double[][] faceZ;
		
		//System.out.println("Chosing faces :: ");
		
		//if(camera.distanceToXY(faces[3][4][0], faces[3][4][1]) < camera.distanceToXY(faces[4][4][0], faces[4][4][1]))
		if(camera.distanceToXYZ(faces[3][4]) < camera.distanceToXYZ(faces[4][4])) {
			//System.out.println("Face 3");
			faceX = faces[3];
		}
		else {
			//System.out.println("Face 4");
			faceX = faces[4];
		}
		
		//if(camera.distanceToXY(faces[1][4][0], faces[1][4][1]) < camera.distanceToXY(faces[2][4][0], faces[2][4][1])) {
		if(camera.distanceToXYZ(faces[1][4]) < camera.distanceToXYZ(faces[2][4])) {	
			//System.out.println("Face 1");
			faceY = faces[1];
		}
		else {
			//System.out.println("Face 2");
			faceY = faces[2];
		}
		 //System.out.println(camera.getDeltaZ(faces[0][4][2]) + " " + camera.getDeltaZ(faces[5][4][2]) );
		
		//if(camera.getDeltaZ(faces[0][4][2]) < 0) {
		//System.out.println(camera.distanceToXYZ(faces[0][4]));
		
		if(camera.distanceToXYZ(faces[0][4]) < camera.distanceToXYZ(faces[5][4])) {
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
			//double[] tempPoint = pointToScreenNew(faceX[i], width, height);
			double[] tempPoint = pointToScreen2(faceX[i], width, height);
			faceX[i] = tempPoint;
			
		}
		
		for(int i = 0; i<faceY.length; i++)
		{
			//System.out.printf("FaceX point %d ");
			//double[] tempPoint = pointToScreenNew(faceY[i], width, height);
			double[] tempPoint = pointToScreen2(faceY[i], width, height);
			faceY[i] = tempPoint;
			
		}
		for(int i = 0; i<faceZ.length; i++)
		{
			//System.out.printf("FaceX point %d ");
			double[] tempPoint = pointToScreen2(faceZ[i], width, height);
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
			tempX[i] = (int)faceX[i][0];
			tempY[i] = (int)faceX[i][1];
		}
		poly[2] = new Polygon(tempX,tempY,4);
		
		//Yface polygon creation
		tempX = new int[4];
		tempY = new int[4];
		for(int i = 0; i<faceY.length-1; i++)
		{
			tempX[i] = (int)faceY[i][0];
			tempY[i] = (int)faceY[i][1];
		}
		poly[1] = new Polygon(tempX,tempY,4);
		
		//Zface polygon creation
		tempX = new int[4];
		tempY = new int[4];
		for(int i = 0; i<faceZ.length-1; i++)
		{
			
			tempX[i] = (int) faceZ[i][0];
			tempY[i] = (int) faceZ[i][1];
		}
		poly[0] = new Polygon(tempX,tempY,4);
		
		
		return poly;
	}
	

	
	private double[] pointToScreen2(double[] coord, int width, int height) {
		double[] point = new double[2];
		int CenterX = width/2;
		int CenterY = height/2;
		
		//TODO mess with positive and negatives here because they are weird.
		//TODO make sure scaling is correct
		
		//coord[0] += (int)(coord[0] * Math.cos(Math.toRadians(camera.getRotation())));
		//coord[1] += (int)(coord[1] * Math.sin(Math.toRadians(camera.getRotation())));
		
		int distance = camera.distanceToXY(coord[0], coord[1]);
		double rot = Math.toRadians(camera.getRotation());
		double deltaX = camera.getDeltaX(coord[0]);
		double deltaY = camera.getDeltaY(coord[1]);
		
		point[0] = CenterX;
		
		//TODO mess with haphazard rotation.
		
		double slopeX = (double)(Math.cos(rot)*deltaX - Math.sin(rot)*deltaY)/distance;
		point[0] += ((slopeX)*viewDistance) * scalingX; 
		
		slopeX = (double)(Math.sin(rot)*deltaX + Math.cos(rot)*deltaY)/distance;
		point[0] += ((slopeX)*viewDistance) * scalingX;
		
		
		point[1] = CenterY;
		
		//TODO finish this
		double slopeZ = (double)camera.getDeltaZ(coord[2])/distance;
		point[1] += (slopeZ*viewDistance) * scalingY;
		
		return point;
	}
	
	
	
	
	public void renderThis(GameObject... args) {
		
	}
	
	
	public Polygon[] debuggingRendering(core.Bounds3D that, int width, int height)
	{
		int[] x = {1,2,3};
		Polygon fals = new Polygon(x, x, 3);
		Polygon[] fal2 = new Polygon[4];
		fal2[0] = fals;
		fal2[1] = fals;
		fal2[2] = fals;
		fal2[3] = fals;
		calculateScaling(width, height);
		//System.out.println(renderCheckWorld(that)); // THis breaks the code for some reason...
		//if(renderCheckWorld(that))
		return convertWorldToScreenNew(that, width, height);
		//return fal2;
	}
}
