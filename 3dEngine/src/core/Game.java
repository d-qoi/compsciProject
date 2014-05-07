package core;

public class Game {

	public static void main(String[] args) {

		GameWindow game = new GameWindow();
		game.setRenderPanel(new RenderPanel());
		game.setGameListener(new GameListener());
		game.setGameTick(new GameTick(game.renderPanel));
		
	}

}
