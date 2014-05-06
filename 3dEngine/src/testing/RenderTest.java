package testing;

import core.Bounds3D;
import engine3D.Camera;
import engine3D.Engine;
import engine3D.RenderPolygon;

public class RenderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Engine rendering = new Engine();
		Bounds3D box = new Bounds3D(100,95,50,10,40,100);
		Camera cam = new Camera(150, 40, 85, 1);
		rendering.useCamera(cam);
		RenderPolygon[] polys = rendering.debuggingRendering(box);
		for(RenderPolygon temp:polys)
			System.out.print(temp);
	}

}
