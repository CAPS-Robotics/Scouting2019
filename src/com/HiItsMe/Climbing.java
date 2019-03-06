package com.HiItsMe;

class Climbing {
	private Selector start, climb;
	Climbing(int x, int y) {
		start = new Selector(x, y - 50, new String[][]{{"Starting Level 1", "Starting Level 2"}});
		climb = new Selector(x, y + 25, new String[][]{{"Climb Level 1", "Climb Level 2"}, {"Climb Level 3", "No Climb"}});
	}
	void draw() {
		start.draw();
		climb.draw();
	}
	void click(int x, int y) {
		start.click(x, y);
		climb.click(x, y);
	}
	void setState(double[][][] state) {
		start.set(state[0]);
		climb.set(state[1]);
	}
	double[][][] getState() {
		return new double[][][]{start.get(), climb.get()};
	}
}
