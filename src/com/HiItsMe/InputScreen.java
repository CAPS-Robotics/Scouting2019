package com.HiItsMe;

import static com.HiItsMe.Scouting2019.*;

class InputScreen {
	private CargoShip cs;
	private Rocket r0;
	private Rocket r1;
	private Key k;
	private Climbing c;
	private Button exitButton;
	private Button loadButton;
	private Button saveButton;
	private Button imageButton;
	InputScreen() {
		cs = new CargoShip(750/2, 475/2);
		r0 = new Rocket(750/6, 475/2);
		r1 = new Rocket(5*750/6, 475/2);
		k = new Key(r0.x, r0.y + 125);
		c = new Climbing(r1.x, r1.y + 125);
		exitButton = new Button(r0.x, r0.y-175, 75, 37, "Exit", ()->sketch.screen = 0, false);
		loadButton = new Button(r0.x, r0.y-125, 75, 37, "Load Sheet", this::load, false);
		saveButton = new Button(r1.x, r1.y-175, 75, 37, "Save Sheet", this::save, false);
		imageButton = new Button(r1.x, r1.y-125, 75, 37, "Save As Image", ()->sketch.save((cs.getTeam() + "_" + cs.getMatch()).toUpperCase() + ".png"), false);
		
	}
	void draw() {
		sketch.image(sketch.bg, 0, 0, 750, 475);
		cs.draw();
		r0.draw();
		r1.draw();
		k.draw();
		c.draw();
		exitButton.draw();
		loadButton.draw();
		saveButton.draw();
		imageButton.draw();
	}
	void click(int x, int y, int button) {
		int state = 3;
		if(button == LEFT) {
			state = 1;
		} else if(button == RIGHT) {
			state = 2;
		}
		if(x > r0.x - 100 && x < r0.x + 100 && y > r0.y - 100 && y < r0.y + 50) {
			r0.click(x, y, state);
		} else if(x > r1.x - 100 && x < r1.x + 100 && y > r1.y - 100 && y < r1.y + 50) {
			r1.click(x, y, state);
		}
		cs.click(x, y, state);
		c.click(x, y);
		exitButton.click(x, y);
		loadButton.click(x, y);
		saveButton.click(x, y);
		imageButton.click(x, y);
	}
	void type(char key) {
		cs.type(key);
	}
	private void save() {
		try {
			sketch.saveBytes((cs.getTeam() + "_" + cs.getMatch()).toUpperCase() + ".dat", serialize(new SaveData(cs.getNotes(), cs.getState(), r0.getState(), r1.getState(), c.getState(), cs.getScout())));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void load() {
		try {
			SaveData s = (SaveData)deserialize(sketch.loadBytes((cs.getTeam() + "_" + cs.getMatch()).toUpperCase() + ".dat"));
			cs.setState(s.cs);
			r0.setState(s.r0);
			r1.setState(s.r1);
			cs.setNotes(s.notes);
			cs.setScout(s.scout);
			c.setState(s.climb);
		} catch(NullPointerException e) {
			cs.setState(new double[][][]{{{0, 0, 0}, {0, 0, 0}}, {{0, 0, 0}, {0, 0, 0}}, {{0, 0, 0}, {0, 0, 0}}, {{0, 0, 0}, {0, 0, 0}}, {{0, 0, 0}, {0, 0, 0}}, {{0, 0, 0}, {0, 0, 0}}, {{0, 0, 0}, {0, 0, 0}}, {{0, 0, 0}, {0, 0, 0}}});
			r0.setState(new double[][][]{{{0, 0, 0}, {0, 0, 0}}, {{0, 0, 0}, {0, 0, 0}}, {{0, 0, 0}, {0, 0, 0}}, {{0, 0, 0}, {0, 0, 0}}, {{0, 0, 0}, {0, 0, 0}}, {{0, 0, 0}, {0, 0, 0}}});
			r1.setState(new double[][][]{{{0, 0, 0}, {0, 0, 0}}, {{0, 0, 0}, {0, 0, 0}}, {{0, 0, 0}, {0, 0, 0}}, {{0, 0, 0}, {0, 0, 0}}, {{0, 0, 0}, {0, 0, 0}}, {{0, 0, 0}, {0, 0, 0}}});
			cs.setNotes("");
			c.setState(new double[][][]{{{1, 0}},{{1, 0},{0, 0}}});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
