package com.HiItsMe;

import java.io.Serializable;

class SaveData implements Serializable {
	String notes, scout; double[][][] cs, r0, r1, climb; private static final long serialVersionUID = -3322781230343957262L;
	SaveData(String notes, double[][][] cs, double[][][] r0, double[][][] r1, double[][][] climb, String scout) {
		this.notes = notes; this.cs = cs; this.r0 = r0; this.r1 = r1; this.climb = climb; this.scout = scout;
	}
	void add(SaveData s, double mult) {
		if(!s.notes.trim().equals("")) {
			notes += s.notes.trim() + " ";
		}
		add1(cs, s.cs, mult);
		add1(r0, s.r0, mult);
		add1(r1, s.r1, mult);
		add1(climb, s.climb, mult);
	}
	void div(double d) {
		div1(d, cs);
		div1(d, r0);
		div1(d, r1);
		div1(d, climb);
	}
	void sqrt() {
		sqrt1(cs);
		sqrt1(r0);
		sqrt1(r1);
		sqrt1(climb);
	}
	private void add1(double[][][] arr, double[][][] arr1, double m) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				for(int k = 0; k < arr[i][j].length; k++) {
					arr[i][j][k] += arr1[i][j][k] * m;
				}
			}
		}
	}
	private void sqrt1(double[][][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				for(int k = 0; k < arr[i][j].length; k++) {
					arr[i][j][k] = Math.sqrt(arr[i][j][k]);
				}
			}
		}
	}
	private void div1(double d, double[][][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				for(int k = 0; k < arr[i][j].length; k++) {
					arr[i][j][k] /= d;
				}
			}
		}
	}
	double getError(SaveData compare) {
		return get1Error(cs, compare.cs) + get1Error(r0, compare.r0) + get1Error(r1, compare.r1) + get1Error(climb, compare.climb);
	}
	double get1Error(double[][][] arr, double[][][] compare) {
		double sum = 0;
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				for(int k = 0; k < arr[i][j].length; k++) {
					if(compare[i][j][k] > arr[i][j][k]) {
						sum += compare[i][j][k] - arr[i][j][k];
					}
				}
			}
		}
		return sum;
	}
	double[][][][] get() {
		return new double[][][][]{cs, r0, r1, climb};
	}
}