package com.HiItsMe;

import static com.HiItsMe.Scouting2019.*;

public class Button {
	private int x, y, h, w;
	private Runnable action;
	private String label;
	private boolean image;
	Button(int x, int y, int w, int h, String label, Runnable action, boolean image) {
		this.x = x; this.y = y; this.h = h; this.w = w; this.label = label; this.action = action; this.image = image;
	}
	void click(int x, int y) {
		if(x > this.x - w/2 && x < this.x + w/2 && y > this.y - h/2 && y < this.y + h/2) action.run();
	}
	void draw() {
		if(image) {
			sketch.pushStyle();
			sketch.imageMode(CENTER);
			sketch.image(sketch.loadImage(label+".png"), x, y, w, h);
			sketch.popStyle();
		} else {
			sketch.rect(x, y, w, h);
			sketch.pushStyle();
			sketch.fill(0);
			sketch.text(label, x, y, w, h);
			sketch.popStyle();
		}
	}
}
