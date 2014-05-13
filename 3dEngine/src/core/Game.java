package core;

import engine3D.Engine;

public class Game {

	public static void main(String[] args) {

		GameWindow game = new GameWindow();
		game.setRenderPanel(new RenderPanel());
		game.setGameListener(new GameListener());
		game.setGameTick(new GameTick());
		game.setEngine(new Engine());
		
		game.start();
		
	}

}
