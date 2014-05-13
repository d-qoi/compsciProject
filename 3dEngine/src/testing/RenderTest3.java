package testing;
import core.Bounds3D;
import core.GameListener;
import core.GameTick;
import core.GameWindow;
import core.RenderPanel;
import core.StaticObject;
import engine3D.Camera;
import engine3D.Engine;

public class RenderTest3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GameWindow game = new GameWindow();
		game.setRenderPanel(new RenderPanel());
		game.setGameListener(new GameListener());
		game.setGameTick(new GameTick());
		game.setEngine(new Engine());

		game.getEngine().useCamera(new Camera(0, 0, 5, 0));

		game.renderPanel.add(new StaticObject(new Bounds3D(0,0,0,4,4,4)) {
		});
		
		game.start();

	}

}
