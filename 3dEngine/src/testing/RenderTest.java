package testing;

import core.Bounds3D;
import engine3D.Camera;
import engine3D.Engine;
import engine3D.RenderPolygon;

public class RenderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Engine rendering = new Engine();
		Bounds3D box = new Bounds3D(0,0,0,1,1,1);
		Camera cam = new Camera(3, 1, 1, 0);
		rendering.useCamera(cam);
		RenderPolygon[] polys = rendering.debuggingRendering(box);
		for(RenderPolygon temp:polys)
			System.out.print(temp);
	}

}
