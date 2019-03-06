package com.HiItsMe;

import static com.HiItsMe.Scouting2019.*;

class CargoShip {
	private HatchCargo[] stations = new HatchCargo[8];
	private TextBox notes;
	private TextBox team;
	private TextBox match;
	private TextBox comp;
	private TextBox scout;
	private int x, y;
	CargoShip(int x, int y) {
		notes = new TextBox(x, y - 100, 200, 100, "Notes");
		team = new TextBox(x - 100, y - 175, 100, 50, "Team");
		comp = new TextBox(x, y - 175 - 12, 100, 25, "Competition");
		match = new TextBox(x, y - 175 + 13, 100, 25, "Match");
		scout = new TextBox(x + 100, y - 175, 100, 50, "Scout");
		this.x = x; this.y = y;
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 3; j++) {
				stations[i*3+j] = new HatchCargo(x + i*100 - 50, y + j*50 - 25, 2-(i*2));
			}
		}
		for(int i = 0; i < 2; i++) {
			stations[6+i] = new HatchCargo(x + i*50 - 25, y + 150, 1);
		}
	}
	void draw() {
		for(int i = 0; i < 8; i++) {
			stations[i].draw();
		}
		notes.draw();
		team.draw();
		comp.draw();
		match.draw();
		scout.draw();
		sketch.line(x - 100, y + 100, x - 50, y + 200);
		sketch.line(x + 100, y + 100, x + 50, y + 200);
	}
	void click(int x, int y, int button) {
		notes.click(x, y);
		team.click(x, y);
		comp.click(x, y);
		match.click(x, y);
		scout.click(x, y);
		if(x < this.x - 100 || x > this.x + 100) return;
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 3; j++) {
				if(((i < 1) == (x < this.x)) && (y > this.y + j*50 - 50) && (y < this.y + j*50)) {
					stations[i*3+j].click(x, y);
					return;
				}
			}
		}
		if(y > this.y + 100 && x > this.x-50 && x < this.x+50) {
			stations[6 + (x < this.x ? 0 : 1)].click(x, y);
		}
	}
	double[][][] getState() {
		double[][][] state = new double[8][2][3];
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
	void setNotes(String t) {
		notes.text = t;
	}
	void setScout(String s) {
		scout.text = s;
	}
	void type(char key) {
		notes.type(key);
		team.type(key);
		comp.type(key);
		match.type(key);
		scout.type(key);
	}
	String getNotes() {
		return notes.text;
	}
	String getTeam() {
		return team.text;
	}
	String getMatch() {
		return comp.text + "_" + match.text;
	}
	String getScout() {
		return scout.text;
	}
}
