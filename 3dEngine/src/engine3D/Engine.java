package engine3D;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import core.Bounds3D;
import core.GameObject;

public class Engine {
	
	public static int FOVAngleStep = 5;
	public static int FOVBoxDepth = 10;
	public int viewDistance;
	public int characterHeight;
	private double scalingX;
	private double scalingY;
	public int verticalFOV;
	public int horizontalFOV;
	public Camera camera;
	public Rays ray;
	
	private static final double radOf45 = Math.toRadians(45);
	
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
		int modifiedAngle = (camera.getRotation() - horizontalFOV/2) - (camera.getRotation() + horizontalFOV/2)/FOVAngleStep;
		//System.out.printf("%d, %d",modifiedAngle,modifiedAngle+horizontalFOV);
		int degMod=0;
		for(int deg = modifiedAngle; deg < modifiedAngle + horizontalFOV + FOVAngleStep; deg += FOVAngleStep)
		{
			
			if(deg < 0)
			{
				degMod = ray.rays.length+deg;
			}
			else if(deg > ray.rays.length)
			{
				degMod = deg%ray.rays.length;
			}
			for(int depth = 0; depth < ray.rays.length; depth++)
			{
				//debugging code
				//System.out.printf("%d %d %d %d %d\n",deg,degMod, depth, ray.rays[degMod][depth][0], ray.rays[degMod][depth][1]);
				if(obj.pointIsInsideXY((int)ray.rays[degMod][depth][0], (int)ray.rays[degMod][depth][1])) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean renderCheckStatic(Bounds3D obj) {
		
		int startAng = (camera.getRealRotation() - horizontalFOV/2)/FOVAngleStep;
		int stopAng = (camera.getRealRotation() + horizontalFOV/2)/FOVAngleStep;
		//System.out.println(startAng + " " + stopAng + " " + camera.getRotation());
		int degMod = 0;
		for(int deg = startAng; deg <stopAng + 1; deg++) {
			if(deg < 0) {
				degMod = ray.rays.length + deg;
			}
			else if(deg > ray.rays.length - 1) {
				degMod = deg - ray.rays.length;
			}
			
			for(int depth = 0; depth < ray.rays[degMod].length-1; depth++) {
				//System.out.printf("%d %d, %d\n", degMod, deg, ray.rays.length);
				if(obj.pointIsInsideXY((int)ray.rays[degMod][depth][0],(int)ray.rays[degMod][depth][1])) {
					//System.out.println("True");
					return true;
				}
					
			}
		}
		//System.out.println("False");
		return false;
	}
	
	public boolean renderCheckDynamic(Bounds3D obj) {
		//TODO make better
		double deltaX = camera.getDeltaX(obj.x);
		double deltaY = camera.getDeltaY(obj.y);
		double objAngle = Math.atan2(deltaY, deltaX);
		objAngle = (objAngle<0) ? 360+objAngle : objAngle;
		
		double delta = Math.abs(objAngle - camera.getRealRotation());
		if(delta > horizontalFOV)
			return true;
		
		return false;
	}
	
	
	private ExtendedPolygon[] convertWorldToScreenNew(GameObject that, int width, int height)
	{
		ExtendedPolygon[] poly = new ExtendedPolygon[3];		
		double[][][] faces = that.getBounds().getFaces();
		double[][] faceX;
		double[][] faceY;
		double[][] faceZ;
		Color xFaceColor;
		Color yFaceColor;
		Color zFaceColor;
		
		//System.out.println("Chosing faces :: ");
		
		//if(camera.distanceToXY(faces[3][4][0], faces[3][4][1]) < camera.distanceToXY(faces[4][4][0], faces[4][4][1]))
		if(camera.distanceToXYZ(faces[3][4]) < camera.distanceToXYZ(faces[4][4])) {
			//System.out.println("Face 3");
			faceX = faces[3];
			xFaceColor = that.texture.get(3);
		}
		else {
			//System.out.println("Face 4");
			faceX = faces[4];
			xFaceColor = that.texture.get(4);
		}
		
		//if(camera.distanceToXY(faces[1][4][0], faces[1][4][1]) < camera.distanceToXY(faces[2][4][0], faces[2][4][1])) {
		if(camera.distanceToXYZ(faces[1][4]) < camera.distanceToXYZ(faces[2][4])) {	
			//System.out.println("Face 1");
			faceY = faces[1];
			yFaceColor = that.texture.get(1);
		}
		else {
			//System.out.println("Face 2");
			faceY = faces[2];
			yFaceColor = that.texture.get(2);
		}
		 //System.out.println(camera.getDeltaZ(faces[0][4][2]) + " " + camera.getDeltaZ(faces[5][4][2]) );
		
		//if(camera.getDeltaZ(faces[0][4][2]) < 0) {
		//System.out.println(camera.distanceToXYZ(faces[0][4]));
		
		if(camera.distanceToXYZ(faces[0][4]) < camera.distanceToXYZ(faces[5][4])) {
			//System.out.println("Face 0");
			faceZ = faces[0];
			zFaceColor = that.texture.get(0);
		}
		else {
			//System.out.println("Face 5");
			faceZ = faces[5];
			zFaceColor = that.texture.get(5);
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
		poly[2] = new ExtendedPolygon(tempX,tempY,4,xFaceColor);
		
		//Yface polygon creation
		tempX = new int[4];
		tempY = new int[4];
		for(int i = 0; i<faceY.length-1; i++)
		{
			tempX[i] = (int)faceY[i][0];
			tempY[i] = (int)faceY[i][1];
		}
		poly[1] = new ExtendedPolygon(tempX,tempY,4,yFaceColor);
		
		//Zface polygon creation
		tempX = new int[4];
		tempY = new int[4];
		for(int i = 0; i<faceZ.length-1; i++)
		{
			
			tempX[i] = (int) faceZ[i][0];
			tempY[i] = (int) faceZ[i][1];
		}
		poly[0] = new ExtendedPolygon(tempX,tempY,4,zFaceColor);
		
		
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
	
	
	
	@SuppressWarnings("unused")
	public BufferedImage drawThese(int width, int height, ArrayList<GameObject> these) {

		calculateScaling(width, height);
		
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D graphics = image.createGraphics();
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		ExtendedPolygon[] polys = null;
		
		
		for(int i = 0; i<these.size(); i++) {
			//System.out.println(renderCheckWorld(these.get(i).body));
			if(these.get(i).body == null)
				continue;
			if(these.get(i).flag == 0) {
				if(renderCheckStatic(these.get(i).getBounds())) {
					polys = convertWorldToScreenNew(these.get(i), width, height);
					//System.exit(1);
				}
				else {
					polys = null;
					
				}
				if(polys != null)
					for(ExtendedPolygon temp:polys)
						temp.draw(graphics);
			}
			else if(these.get(i).flag == 1) {
				if(renderCheckDynamic(these.get(i).getBounds())) {
					polys = convertWorldToScreenNew(these.get(i), width, height);
				}
				else {
					polys = null;
				}
				if(polys != null)
					for(ExtendedPolygon temp:polys)
						temp.draw(graphics);
			}
			
		}
		
		
		graphics.setColor(Color.RED);
		//graphics.fillRect(10, 10, 50, 50);
		
		return image;
		
	}
	
	public void drawThese2(int width, int height, ArrayList<GameObject> these, Graphics canvas) {

		calculateScaling(width, height);
		
		//BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		//Graphics2D graphics = image.createGraphics();
		//graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		ExtendedPolygon[] polys = null;
		
		calculateScaling(width, height);
		
		for(int i = 0; i<these.size(); i++) {
			//System.out.println(renderCheckWorld(these.get(i).body));
			if(these.get(i).body == null)
				continue;
			if(these.get(i).flag == 0) {
				if(renderCheckWorld(these.get(i).getBounds())) {
					polys = convertWorldToScreenNew(these.get(i), width, height);
					//System.exit(1);
				}
				else {
					polys = null;
					
				}
				if(polys != null)
					for(ExtendedPolygon temp:polys)
						temp.draw(canvas);
			}
			else if(these.get(i).flag == 1) {
				if(renderCheckDynamic(these.get(i).getBounds())) {
					polys = convertWorldToScreenNew(these.get(i), width, height);
				}
				else {
					polys = null;
				}
				if(polys != null)
					for(ExtendedPolygon temp:polys)
						temp.draw(canvas);
			}
		}
		
		
		//graphics.setColor(Color.RED);
		//graphics.fillRect(10, 10, 50, 50);
		
		
	}
	
	public ExtendedPolygon[] debuggingRendering(GameObject that, int width, int height)
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
