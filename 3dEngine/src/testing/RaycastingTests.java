package testing;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import core.Bounds3D;
import engine3D.Camera;
import engine3D.Engine;

public class RaycastingTests {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Engine engine = new Engine();
		engine.setVerticalFOV(30);
		Camera cam = new Camera(200, 0,50, 0);
		engine.useCamera(cam);
		engine.camera.setRotation(0);
		
		Bounds3D box = new Bounds3D(140, 300, 0, 50, 50, 50);
		System.out.println(engine.renderCheckWorld(box));
		System.out.println(engine.ray.rays.length + " " + engine.ray.rays[0].length);
	
		System.out.println(engine.renderCheckStatic(box));
		int startAng = engine.camera.getRealRotation() - engine.horizontalFOV/2;
		int stopAng = engine.camera.getRealRotation() + engine.horizontalFOV/2;
		int maxModAngle = engine.horizontalFOV/engine.FOVAngleStep;
		System.out.printf("%d, %d, %d %d :: %d\n", startAng, stopAng, startAng/engine.FOVAngleStep-1, stopAng/engine.FOVAngleStep+1,engine.horizontalFOV/engine.FOVAngleStep);
		for(int ang = startAng/engine.FOVAngleStep - 1; ang < stopAng/engine.FOVAngleStep + 1; ang++) {
			for(int depth = 0; depth<engine.viewDistance/engine.FOVBoxDepth; depth++) {
				int modAngle = (ang<0)?engine.ray.rays.length+ang : (ang>maxModAngle-1)? ang-engine.ray.rays.length : ang;
				System.out.printf("%d, %d :: %d :: %d, %d :: %d, %d\n", ang, depth,modAngle, ang*engine.FOVAngleStep, depth*engine.FOVBoxDepth, engine.ray.rays[modAngle][depth][0], engine.ray.rays[modAngle][depth][1]);
				
			}
		}
	}

}
