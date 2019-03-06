package com.HiItsMe;

import processing.core.PImage;

import static com.HiItsMe.Scouting2019.*;

class TitleScreen {
	Button scout;
	Button analyze;
	PImage bg;
	TitleScreen() {
		scout = new Button(750/7, 475/2, 195, 198, "scouting", ()->sketch.screen = 1, true);
		analyze = new Button(6*750/7, 475/2, 195, 198, "analysis", ()->sketch.screen = 2, true);
		bg = sketch.loadImage("title.png");
	}
	void draw() {
		sketch.image(bg, 0, 0, 750, 475);
		scout.draw();
		analyze.draw();
	}
	void click(int x, int y, int button) {
		scout.click(x, y);
		analyze.click(x, y);
	}
	void type(char c) {}
}
