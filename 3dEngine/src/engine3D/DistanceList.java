package engine3D;

public class DistanceList implements Comparable<DistanceList> {

	public Object obj;
	public int distanceToCamera;
	
	public DistanceList(Object object, int distance)
	{
		this.obj = object;
		this.distanceToCamera = distance;
	}
	
	public int compareTo(DistanceList that) {
		if(that.distanceToCamera > this.distanceToCamera)
			return 1;
		if(that.distanceToCamera < this.distanceToCamera)
			return -1;
		return 0;
	}
	

}
