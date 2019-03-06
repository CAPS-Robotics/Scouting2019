package com.HiItsMe;

import static com.HiItsMe.Scouting2019.*;

class Selector {
	private int x, y;
	private String[][] names;
	private double[][] values;
	Selector(int x, int y, String[][] names) {
		this.x = x; this.y = y; this.names = names;
		values = new double[names.length][names[0].length];
		values[0][0] = 1;
	}
	void draw() {
		for(int i = 0; i < names.length; i++) {
			for(int j = 0; j < names[i].length; j++) {
				drawSub(j, i, names[i][j]);
			}
		}
	}
	private void drawSub(int x, int y, String txt) {
		sketch.pushStyle();
		sketch.fill(0, (int)(values[y][x]*256));
		x = this.x - (names[y].length-1)*50 + x*100;
		y = this.y - (names.length-1)*25 + y*50;
		sketch.ellipse(x + 33, y, 20, 20);
		sketch.fill(0);
		sketch.text(txt, x - 16, y, 66, 50);
		sketch.popStyle();
		sketch.rect(x, y, 100, 50);
	}
	void click(int x, int y) {
		for(int i = 0; i < names.length; i++) {
			for(int j = 0; j < names[i].length; j++) {
				int ax = this.x - (names[i].length-1)*50 + j*100;
				int ay = this.y - (names.length-1)*25 + i*50;
				if(x > ax - 50 && x < ax + 50 && y > ay - 25 && y < ay + 25) {
					for(int k = 0; k < values.length; k++) {
						for(int l = 0; l < values[k].length; l++) {
							values[k][l] = 0;
						}
					}
					values[i][j] = 1;
				}
			}
		}
	}
	double[][] get() {
		return values;
	}
	void set(double[][] val) {
		values = val;
	}
}
