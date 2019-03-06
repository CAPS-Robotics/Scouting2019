package com.HiItsMe;

import processing.core.*;
import java.io.*;

public class Scouting2019 extends PApplet {
	
	private InputScreen input;
	private TitleScreen title;
	private AnalyzeScreen analyze;
	int screen = 0;
	static Scouting2019 sketch;
	PImage bg;
	public static double scaleFactor = 1;
	
	public void settings() {
		size((int)(750*scaleFactor), (int)(475*scaleFactor));
	}
	
	public void setup() {
		textAlign(CENTER, CENTER);
		rectMode(CENTER);
		ellipseMode(CENTER);
		stroke(0);
		strokeCap(ROUND);
		strokeWeight(2);
		scale(50);
		noFill();
		bg = loadImage("bg.png");
		textFont(loadFont("MarketDeco-14.vlw"));
		input = new InputScreen();
		title = new TitleScreen();
		analyze = new AnalyzeScreen();
	}
	
	public void draw() {
		scale((float)scaleFactor);
		background(255);
		switch(screen) {
			case 0:
				title.draw();
				break;
			case 1:
				input.draw();
				break;
			case 2:
				analyze.draw();
				break;
		}
	}
	public void mousePressed() {
		switch(screen) {
			case 0:
				title.click((int)(mouseX/scaleFactor), (int)(mouseY/scaleFactor), mouseButton);
				break;
			case 1:
				input.click((int)(mouseX/scaleFactor), (int)(mouseY/scaleFactor), mouseButton);
				break;
			case 2:
				analyze.click((int)(mouseX/scaleFactor), (int)(mouseY/scaleFactor), mouseButton);
				break;
		}
	}
	public void keyPressed() {
		switch(screen) {
			case 0:
				title.type(key);
				break;
			case 1:
				input.type(key);
				break;
			case 2:
				analyze.type(key);
				break;
		}
	}
	
	public static void main(String[] args) {
		sketch = new Scouting2019();
		try {
			if(args.length == 1 && Double.parseDouble(args[0]) > 0) scaleFactor = Double.parseDouble(args[0]);
		} catch(NumberFormatException e) {}
		PApplet.runSketch(new String[]{"Scouting2019"}, sketch);
	}
	static byte[] serialize(Object obj) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(out);
		os.writeObject(obj);
		return out.toByteArray();
	}
	static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		ObjectInputStream is = new ObjectInputStream(in);
		return is.readObject();
	}
}
