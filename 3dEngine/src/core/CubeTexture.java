package core;

import java.awt.Color;

public class CubeTexture {

	public Color top, bottom, left, right, forward, backward;

	public CubeTexture(Color top, Color bottom, Color left, Color right,
			Color forward, Color backward) {
		this.top = top;
		this.bottom = bottom;
		this.left = left;
		this.right = right;
		this.forward = forward;
		this.backward = backward;
	}

	public CubeTexture(Color top, Color side) {
		this(top, top, side, side, side, side);
	}

	public CubeTexture(Color color) {
		this(color, color, color, color, color, color);
	}

	public CubeTexture() {
		this(Color.MAGENTA);
	}

	public Color get(int i) {
		switch (i) {
		case 0:
			return bottom;
		case 1:
			return forward;
		case 2:
			return backward;
		case 3:
			return left;
		case 4:
			return right;
		case 5:
			return top;
		default:
			return null;
		}
	}

}