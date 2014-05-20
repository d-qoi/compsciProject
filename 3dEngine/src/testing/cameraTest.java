package testing;

import engine3D.Camera;

public class cameraTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Camera cam = new Camera(10);
		for(int i = 0; i<360; i++) {
			System.out.printf("%d, %d\n", cam.getRotation(), cam.getRealRotation());
			cam.setRotation(i);
		}

	}

}
