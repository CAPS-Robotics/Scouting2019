package com.HiItsMe;

import static com.HiItsMe.Scouting2019.*;

class TextBox {
	private int x, y, h, w;
	String text = "";
	private boolean selected = false;
	private String placeHolder;
	TextBox(int x, int y, int w, int h, String placeHolder) {
		this.x = x; this.y = y; this.h = h; this.w = w; this.placeHolder = placeHolder;
	}
	void click(int x, int y) {
		selected = x > this.x-w/2 && x < this.x+w/2 && y > this.y-h/2 && y < this.y+h/2;
	}
	void draw() {
		sketch.rect(x, y, w, h);
		sketch.pushStyle();
		sketch.fill(0);
		sketch.text((text.equals("") && !selected) ? placeHolder : (text + ((System.currentTimeMillis()%1000 < 500 && selected) ? "|" : "")), x, y, w, h);
		sketch.popStyle();
	}
	void type(char c) {
		if(selected) {
			if(c == BACKSPACE) {
				if (text.length() > 0) {
					text = text.substring(0, text.length() - 1);
				}
			} else if(c >= ' ' && c <= '~') {
				text += c;
			}
		}
	}
}
