package com.HiItsMe;

import processing.core.PImage;

import java.io.IOException;
import java.util.*;
import java.util.regex.*;

import static com.HiItsMe.Scouting2019.*;

class AnalyzeScreen {
	TextBox team;
	TextBox competition;
	Button exit;
	Button analyze;
	Pattern datName;
	PImage bg;
	AnalyzeScreen() {
		bg = sketch.loadImage("analyzebg.png");
		team = new TextBox(750/8, 475/8, 149, 86, "Team");
		competition = new TextBox(7*750/8, 475/8, 149, 86, "Competition");
		exit = new Button(750/8, 7*475/8, 149, 86, "exit", ()->sketch.screen = 0, true);
		analyze = new Button(7*750/8, 7*475/8, 149, 86, "analyze", this::analyze, true);
	}
	void draw() {
		sketch.image(bg, 0, 0, 750, 475);
		team.draw();
		competition.draw();
		exit.draw();
		analyze.draw();
	}
	void click(int x, int y, int button) {
		team.click(x, y);
		competition.click(x, y);
		exit.click(x, y);
		analyze.click(x, y);
	}
	void type(char c) {
		team.type(c);
		competition.type(c);
	}
	void analyze() {
		if(team.text.trim().matches("\\d+")) {
			average();
		} else {
			rank();
		}
	}
	void rank() {
		HashMap<SaveData, String> averages = pullAverages();
		SaveData[] sortedTeams = Arrays.copyOf(averages.keySet().toArray(), averages.size(), SaveData[].class);
		SaveData standard;
		try {
			standard = (SaveData)deserialize(sketch.loadBytes(team.text.toUpperCase() + "_" + competition.text.toUpperCase() + "_PICK.dat"));
		} catch(IOException|ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		SaveData finalStandard = standard;
		Arrays.sort(sortedTeams, (sd0, sd1)->(int)(sd0.getError(finalStandard) - sd1.getError(finalStandard)));
		String[] sortedNumbers = new String[sortedTeams.length];
		for(int i = 0; i < sortedTeams.length; i++) {
			sortedNumbers[i] = averages.get(sortedTeams[i]);
		}
		sketch.saveStrings(team.text.toUpperCase() + "_" + competition.text.toUpperCase() + "_PICKSHEET.txt", sortedNumbers);
	}
	void average() {
		double sum = 0;
		SaveData[] data = pullFiles();
		SaveData out = new SaveData("", new double[8][2][2], new double[6][2][2], new double[6][2][2], new double[][][]{{{0, 0}},{{0, 0},{0, 0}}}, new double[3], "Auto-Generated");
		for(int i = 1; i < data.length; i++) {
			if(data[i] != null) {
				out.add(data[i], Math.sqrt(i));
				sum += Math.sqrt(i);
			}
		}
		out.div(sum);
		out.sqrt();
		try {
			sketch.saveBytes(team.text.toUpperCase() + "_" + competition.text.toUpperCase() + "_AVERAGE.dat", serialize(out));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	HashMap<SaveData, String> pullAverages() {
		datName = Pattern.compile("(\\d+)_" + Pattern.quote(competition.text.toUpperCase()) + "_AVERAGE\\.dat");
		Matcher m;
		java.io.File folder = new java.io.File(sketch.sketchPath());
		String[] filenames = folder.list();
		HashMap<SaveData, String> out = new HashMap<>();
		if(filenames != null) {
			for(String filename : filenames) {
				m = datName.matcher(filename);
				if(m.matches()) {
					try {
						out.put((SaveData)deserialize(sketch.loadBytes(filename)), m.group(1));
					} catch(IOException|ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return out;
	}
	SaveData[] pullFiles() {
		datName = Pattern.compile(Pattern.quote(team.text.toUpperCase()) + "_" + Pattern.quote(competition.text.toUpperCase()) + "_" + "(\\d+)\\.dat");
		Matcher m;
		java.io.File folder = new java.io.File(sketch.sketchPath());
		String[] filenames = folder.list();
		ArrayList<String> names = new ArrayList<>();
		if(filenames != null) {
			for(String filename : filenames) {
				m = datName.matcher(filename);
				if(m.matches()) {
					int num = parseInt(m.group(1));
					if(num >= names.size()) {
						for(int i = names.size(); i <= num; i++) {
							names.add(i, "");
						}
					}
					names.set(num, m.group());
				}
			}
		}
		names.removeAll(Arrays.asList("", null));
		names.add(0, "");
		String[] finalNames = new String[names.size()];
		System.arraycopy(names.toArray(), 0, finalNames, 0, names.size());
		System.out.println(Arrays.toString(finalNames));
		SaveData[] out = new SaveData[finalNames.length];
		for(int i = 0; i < out.length; i++) {
			try {
				if(finalNames[i].equals("")) {
					out[i] = null;
				} else {
					out[i] = (SaveData)deserialize(sketch.loadBytes(finalNames[i]));
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return out;
	}
}
