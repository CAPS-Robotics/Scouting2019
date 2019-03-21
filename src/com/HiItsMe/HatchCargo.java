package com.HiItsMe;

import static com.HiItsMe.Scouting2019.*;

class HatchCargo {
	private int x, y, rotation;
	private double[] hatchState = {0, 0};
	int hs = 0;
	private double[] cargoState = {0, 0};
	int cs = 0;
	HatchCargo(int x, int y, int rotation) {
		this.x = x; this.y = y; this.rotation = rotation;
	}
	void draw() {
		sketch.rect(x+rot(25, 0)[0], y+rot(25, 0)[1], 50, 50);
		sketch.rect(x+rot(-25, 0)[0], y+rot(-25, 0)[1], 50, 50);
		sketch.ellipse(x+rot(-25, 0)[0], y+rot(-25, 0)[1], 40, 40);
		sketch.ellipse(x+rot(25, 0)[0], y+rot(25, 0)[1], 25, 40);
		sketch.ellipse(x+rot(25, 0)[0], y+rot(25, 0)[1], 10, 16);
		int[] hcx = new int[]{x+rot(25, 0)[0], x+rot(-25, 0)[0]};
		int[] hcy = new int[]{y+rot(25, 0)[1], y+rot(-25, 0)[1]};
		for(int i = 0; i < 2; i++) {
			double[] s = i < 1 ? hatchState : cargoState;
			sketch.pushStyle();
			sketch.stroke(0, (int)(s[0]*256));
			sketch.line(hcx[i]-25, hcy[i]-25, hcx[i]+25, hcy[i]+25);
			sketch.line(hcx[i]+25, hcy[i]-25, hcx[i]-25, hcy[i]+25);
			sketch.stroke(0, (int)(0*256));
			sketch.line(hcx[i]-25, hcy[i]+25, hcx[i], hcy[i]-25);
			sketch.line(hcx[i], hcy[i]+25, hcx[i]+25, hcy[i]-25);
			sketch.stroke(0, (int)(s[1]*256));
			sketch.line(hcx[i]-25, hcy[i], hcx[i], hcy[i]+25);
			sketch.line(hcx[i], hcy[i]+25, hcx[i]+25, hcy[i]-25);
			sketch.popStyle();
		}
	}
	void setState(boolean hatch) {
		if(hatch) { hs++; hs%=3; } else { cs++; cs%=3; }
		switch(hatch ? hs : cs) {
			case 0:
				if(hatch) hatchState = new double[]{0, 0}; else cargoState = new double[]{0, 0};
				break;
			case 1:
				if(hatch) hatchState = new double[]{1, 0}; else cargoState = new double[]{1, 0};
				break;
			case 2:
				if(hatch) hatchState = new double[]{0, 1}; else cargoState = new double[]{0, 1};
				break;
		}
	}
	void set(double[][] dat) {
		hatchState = dat[1];
		cargoState = dat[0];
	}
	private int[] rot(int x, int y) {
		switch(rotation) {
			case 0:
				return new int[]{x, y};
			case 1:
				return new int[]{y, x};
			case 2:
				return new int[]{-x, -y};
			default:
				return new int[]{-y, -x};
		}
	}
	void click(int x, int y) {
		x = rot(x-this.x, y-this.y)[0];
		if(x < 0) {
			setState(false);
		} else {
			setState(true);
		}
	}
	double[][] getState() {
		return new double[][]{cargoState, hatchState};
	}
}
