package com.HiItsMe;

import static com.HiItsMe.Scouting2019.*;

class Rocket {
	private HatchCargo[] stations = new HatchCargo[6];
	int x, y;
	Rocket(int x, int y) {
		this.x = x; this.y = y;
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 3; j++) {
				stations[i*3+j] = new HatchCargo(x + i*100 - 50, y + j*50 - 75, 2-(i*2));
			}
		}
	}
	void draw() {
		for(int i = 0; i < 6; i++) {
			stations[i].draw();
		}
		sketch.line(x - 100, y - 100, x - 50, y - 200);
		sketch.line(x + 100, y - 100, x + 50, y - 200);
		sketch.line(x - 50, y - 200, x + 50, y - 200);
	}
	void click(int x, int y, int button) {
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 3; j++) {
				if(((i < 1) == (x < this.x)) && (y > this.y + j*50 - 100) && (y < this.y + j*50 - 50)) {
					stations[i*3+j].click(x, y);
				}
			}
		}
	}
	double[][][] getState() {
		double[][][] state = new double[6][2][3];
		for(int i = 0; i < stations.length; i++) {
			state[i] = stations[i].getState();
		}
		return state;
	}
	void setState(double[][][] state) {
		for(int i = 0; i < stations.length; i++) {
			stations[i].set(state[i]);
			stations[i].set(state[i]);
		}
	}
}
