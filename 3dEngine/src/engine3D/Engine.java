package engine3D;

public class Engine {
	
	private static int FOVAngleStep = 5;
	private static int FOVBoxDepth = 5;
	private int viewDistance;
	private int characterHeight;
	public int verticalFOV;
	public int horizontalFOV;
	public Camera camera;
	

	
	Rectangle[][] FOVBox; 
	
	
	public Engine(int verticalFOV, int horizontalFOV,
			int viewDistance, int characterHeight)
	{
		this.verticalFOV = verticalFOV;
		this.horizontalFOV = horizontalFOV;
		this.viewDistance = viewDistance;
		this.characterHeight = characterHeight;
		calcFOVBox();
		camera = new Camera(characterHeight);
		
	}

	public Engine(int verticalFOV, int horizontalFOV) {
		this.verticalFOV = verticalFOV;
		this.horizontalFOV = horizontalFOV;
		this.viewDistance = 1000;
		this.characterHeight = 85;
		calcFOVBox();
		camera = new Camera(characterHeight);
	}

	public Engine(int characterHeight) {
		this.characterHeight = characterHeight;
		this.verticalFOV = 22;
		this.horizontalFOV = 90;
		this.viewDistance = 1000;
		calcFOVBox();
		camera = new Camera(characterHeight);
	}
	public Engine()	{
		this(22,90,1000,85);
	}
	
	public void setViewDistance(int viewDistance) {
		this.viewDistance = viewDistance;
		calcFOVBox();
	}
	
	private void calcFOVBox() {
		
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
	private RenderPolygon[] convertToScreenWorld(core.Bounds3D that) {
		int[][][] faces = that.getFaces();
		int[][] faceX;
		int[][] faceY;
		RenderPolygon[] polys = new RenderPolygon[2];
		
		if(camera.distanceToXY(faces[3][4][0], faces[3][4][1]) < camera.distanceToXY(faces[4][4][0], faces[4][4][1]))
			faceX = faces[3];
		else
			faceX = faces[4];
		
		if(camera.distanceToXY(faces[1][4][0], faces[1][4][1]) < camera.distanceToXY(faces[2][4][0], faces[2][4][1]))
			faceY = faces[1];
		else
			faceY = faces[2];
		
		for(int i = 0; i<faceX.length; i++)
			faceX[i] = convertPointToScreen(faceX[i][0], faceX[i][1], faceX[i][2]);
		
		for(int i= 0; i<faceY.length; i++)
			faceY[i] = convertPointToScreen(faceY[i][0], faceY[i][1], faceY[i][2]);
		
		
		//TODO FIX THIS
		
		polys[0] = new RenderPolygon(faceX);
		polys[1] = new RenderPolygon(faceY);
		
		return polys;
	}
	
	private int[] convertPointToScreen(int x, int y, int z) {
		int[] point = new int[2];
		point[0] = camera.getDeltaX(x) * (int)Math.sin(Math.toRadians(camera.getRotation()));
		point[1] = camera.getDeltaY(y) * (int)Math.cos(Math.toRadians(camera.getRotation()));
		int convertedZ = camera.getDeltaZ(z) * (int)(camera.getDeltaZ(z)/camera.distanceToXY(x, y));
		
		//TODO check this code, just adding z to y becaust I think it may work.
		point[1] += convertedZ;
		
		return point;
	}
	public RenderPolygon[] debuggingRendering(core.Bounds3D that)
	{
		return convertToScreenWorld(that);
	}
}
