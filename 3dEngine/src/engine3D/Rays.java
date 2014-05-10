package engine3D;

public class Rays {
	public int[][][] rays;
	private int viewDistance, distanceSteps, angle, angleSteps;
	
	public Rays(int viewDistance, int distanceSteps, int angle, int angleSteps)
	{
		this.viewDistance = viewDistance;
		this.setDistanceSteps(distanceSteps);
		this.setAngle(angle);
		this.setAngleSteps(angleSteps);
		
		generateRays(viewDistance, distanceSteps, angle, angleSteps);
	}
		
	private void generateRays(int viewDistance, int distanceSteps, int angle, int angleSteps)
	{
		rays = new int[angle/angleSteps + 1][viewDistance/distanceSteps + 1][2];
		for(int ang = 0; ang<angle/angleSteps + 1; ang++)
		{
			for(int dist = 0; dist<viewDistance/distanceSteps + 1; dist++)
			{
				int tempAng = ang*angleSteps;
				int tempDist = dist*distanceSteps;
				int x = (int)(tempDist*(Math.cos(Math.toRadians(tempAng))));
				int y = (int)(tempDist*(Math.sin(Math.toRadians(tempAng))));
				rays[ang][dist][0] = x;
				rays[ang][dist][1] = y;
				//System.out.println(x + " " + y + " " + tempAng + " " + tempDist);
			}
		}
	}
	
	public void recalculateRays()
	{
		generateRays(viewDistance, distanceSteps, angle, angleSteps);
	}
	
	public int getViewDistance() {
		return this.viewDistance;
	}
	
	public void setViewDistance(int viewDistance) {
		this.viewDistance = viewDistance;
		generateRays(viewDistance, viewDistance, viewDistance, viewDistance);
	}
	
	public int getDistanceSteps() {
		return distanceSteps;
	}
	
	public void setDistanceSteps(int distanceSteps) {
		this.distanceSteps = distanceSteps;
		generateRays(viewDistance, viewDistance, viewDistance, viewDistance);
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
		generateRays(viewDistance, viewDistance, viewDistance, viewDistance);
	}

	public int getAngleSteps() {
		return angleSteps;
	}

	public void setAngleSteps(int angleSteps) {
		this.angleSteps = angleSteps;
		generateRays(viewDistance, viewDistance, viewDistance, viewDistance);
	}
}
