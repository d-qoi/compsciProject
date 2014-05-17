package testing;

import engine3D.Rays;

public class TanAtanAtan2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Rays ray = new Rays(1000, 5, 360, 5);
		
		
		for(int i = 0; i<ray.rays.length; i++) {
			System.out.printf("%d,  %f \n\n",i*5,Math.toDegrees(Math.atan2(ray.rays[i][500/5][1],ray.rays[i][500/5][0])));
		}
	}

}
