package engine3D;

public class Rays {
	public double[][][] rays;
	private int viewDistance, distanceSteps, angle, angleSteps;
	
	public Rays(int viewDistance, int distanceSteps, int angle, int angleSteps)
	{
		this.viewDistance = viewDistance;
		this.distanceSteps = distanceSteps;
		this.angle = angle;
		this.angleSteps = angleSteps;
		
		generateRays(viewDistance, distanceSteps, angle, angleSteps);
	}
		
	private void generateRays(int viewDistance, int distanceSteps, int angle, int angleSteps)
	{
		rays = new double[angle/angleSteps][viewDistance/distanceSteps][2];
		for(int ang = 0; ang<angle/angleSteps; ang++)
		{
			for(int dist = 1; dist<viewDistance/distanceSteps + 1; dist++)
			{
				int tempAng = ang*angleSteps;
				int tempDist = dist*distanceSteps;
				double x = (tempDist*(Math.cos(Math.toRadians(tempAng))));
				double y = (tempDist*(Math.sin(Math.toRadians(tempAng))));
				rays[ang][dist-1][0] = x;
				rays[ang][dist-1][1] = y;
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
