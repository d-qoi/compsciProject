package testing;

import core.Bounds3D;
import engine3D.Camera;
import engine3D.Engine;

public class RaycastingTests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Engine engine = new Engine();
		engine.setVerticalFOV(30);
		Camera cam = new Camera(200, 0,50, 0);
		engine.useCamera(cam);
		engine.camera.setRotation(0);
		
		Bounds3D box = new Bounds3D(160, 300, 0, 50, 50, 50);
		System.out.println(engine.renderCheckWorld(box));
		System.out.println(engine.ray.rays.length + " " + engine.ray.rays[0].length);
		for(int i = 0; i<engine.ray.rays.length; i++)
		{
			for(int j = 0; j<engine.ray.rays[i].length; j++)
			{
				
			}
		}
	}

}
