package testing;

import engine3D.Camera;
import engine3D.Rays;

public class cameraTest {

	private static final int FOVAngleStep = 10;
	private static final int horizontalFOV = 90;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Camera cam = new Camera(10);
		for (int i = 0; i < 360; i++) {
			System.out.printf("%d, %d\n", cam.getRotation(),
					cam.getRealRotation());
			cam.setRotation(i);
		}

		Rays ray = new Rays(100, 10, 360, 10);

		int startAng = (cam.getRealRotation() - horizontalFOV / 2)
				/ FOVAngleStep;
		int stopAng = (cam.getRealRotation() + horizontalFOV / 2)
				/ FOVAngleStep;
		System.out.println(startAng + " " + stopAng + " " + cam.getRotation());
		int degMod = 0;
		for (int deg = startAng; deg < stopAng + 1; deg++) {
			if (deg < 0) {
				degMod = ray.rays.length + deg;
			} else if (deg > ray.rays.length - 1) {
				degMod = deg - ray.rays.length;
			}
			else {
				degMod = deg;
			}

			for (int depth = 0; depth < ray.rays[degMod].length - 1; depth++) {
				// System.out.printf("%d %d, %d\n", degMod, deg,
				// ray.rays.length);
				System.out.printf(
						"degMod : %d :: depth : %d :: x : %f, y : %f \n", degMod,
						depth, ray.rays[degMod][depth][0] + (int) cam.getX(),
						ray.rays[degMod][depth][1] + (int) cam.getY());
				// System.out.println("True");

			}
			System.out.println("-------------------------------------------------------");

		}
	}
	// System.out.println("False");

}
