package core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GameListener implements KeyListener, MouseListener,
		MouseMotionListener {
	
	public static boolean[] keyDown = new boolean[65536];
	public static boolean mouseDown = false;
	public static boolean mouseFocus = false;

	public GameListener() {
	}

	public void mouseDragged(MouseEvent arg0) {

	}

	public void mouseMoved(MouseEvent arg0) {

	}

	public void mouseClicked(MouseEvent arg0) {

	}

	public void mouseEntered(MouseEvent arg0) {
		mouseFocus = true;
	}

	public void mouseExited(MouseEvent arg0) {
		mouseFocus = false;
	}

	public void mousePressed(MouseEvent arg0) {
		mouseDown = true;
	}

	public void mouseReleased(MouseEvent arg0) {
		mouseDown = false;
	}

	public void keyPressed(KeyEvent arg0) {
		keyDown[arg0.getKeyChar()] = true;
	}

	public void keyReleased(KeyEvent arg0) {
		keyDown[arg0.getKeyChar()] = false;

	}

	public void keyTyped(KeyEvent arg0) {

	}

}
