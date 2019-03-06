package com.HiItsMe;

class Key {
	private TextBox[] t = new TextBox[3];
	private HatchCargo[] hc = new HatchCargo[3];
	Key(int x, int y) {
		for(int i = 0; i < 3; i++) {
			t[i] = new TextBox(x - 50, y - 50 + i*50, 100, 50, new String[]{"Preplaced:", "Sandstorm:", "Tele-Op:"}[i]);
			hc[i] = new HatchCargo(x + 50, y - 50 + i*50, 2);
			double[][] t = new double[][]{{0, 0, 0}, {0, 0, 0}};
			t[0][i] = 1; t[1][i] = 1;
			hc[i].set(t); hc[i].set(t);
		}
	}
	void draw() {
		for(int i = 0; i < 3; i++) {
			t[i].draw();
			hc[i].draw();
		}
	}
}
