package testing;

import java.awt.Polygon;

import javax.swing.JFrame;

import core.Bounds3D;
import engine3D.Camera;
import engine3D.Engine;



public class RenderTest extends JFrame{

	private static final long serialVersionUID = 6426303617489911311L;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Engine rendering = new Engine();
		Bounds3D box = new Bounds3D(100,95,50,10,40,100);
		Camera cam = new Camera(200, 0, 85, 5);
		rendering.useCamera(cam);
		Polygon[] polys = rendering.debuggingRendering(box, 1000,1000);
		for(Polygon temp:polys)
		{
			for(int i = 0; i<temp.xpoints.length; i++)
			{
				System.out.println(temp.xpoints[i] + " " + temp.ypoints[i]);
			}
			System.out.println();
		}
		
	}

}
