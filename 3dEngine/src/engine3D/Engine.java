package engine3D;

public class Engine {
	
	private static int FOVAngleStep = 5;
	private static int FOVBoxDepth = 5;
	private int viewDistance;
	private int characterHeight;
	public int verticalFOV;
	public int horizontalFOV;
	

	
	Rectangle[][] FOVBox = new Rectangle[horizontalFOV/FOVAngleStep][viewDistance/FOVBoxDepth];
	
	
	public Engine(int verticalFOV, int horizontalFOV, int viewDistance, int characterHeight)
	{
		this.verticalFOV = verticalFOV;
		this.horizontalFOV = horizontalFOV;
		this.viewDistance = viewDistance;
		this.characterHeight = characterHeight;
		calcFOVBox();
		
	}

	public Engine(int verticalFOV, int horizontalFOV) {
		this.verticalFOV = verticalFOV;
		this.horizontalFOV = horizontalFOV;
		this.viewDistance = 1000;
		this.characterHeight = 85;
		calcFOVBox();
	}

	public Engine(int characterHeight) {
		this.characterHeight = characterHeight;
		this.verticalFOV = 22;
		this.horizontalFOV = 90;
		this.viewDistance = 1000;
		calcFOVBox();
	}
	public Engine()
	{
		this(22,90,1000,85);
	}
	
	public void setViewDistance(int viewDistance)
	{
		this.viewDistance = viewDistance;
		calcFOVBox();
	}
	
	private void calcFOVBox()
	{
		
		for(int angle = 0; angle<horizontalFOV; angle+=FOVAngleStep)
		{
			
			for(int depth = 0; depth<viewDistance; depth+=FOVBoxDepth)
			{
				FOVBox[angle][depth] = new Rectangle(angle, depth, verticalFOV, characterHeight);
			}
		}
	}
	
	private boolean renderCheckWorld()
	{
		//TODO get this working, needs to check if FOVbox intersects the world box.
		return false;
	}
	
	private boolean renderCheckEntity()
	{
		//TODO see if the entity intersects the FOVbox.
		return false;
	}
	
}
