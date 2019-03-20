package com.HiItsMe;

class Defense {
	private TextBox[] t = new TextBox[3];
	private TextBox[] in = new TextBox[3];
	Defense(int x, int y) {
		for(int i = 0; i < 3; i++) {
			t[i] = new TextBox(x - 50, y - 50 + i*50, 100, 50, new String[]{"Blocks:", "Pin Count:", "Penalties:"}[i]);
			in[i] = new TextBox(x + 50, y - 50 + i*50, 100, 50, "0");
		}
	}
	void draw() {
		for(int i = 0; i < 3; i++) {
			t[i].draw();
			in[i].draw();
		}
	}
	void click(int x, int y) {
		for(TextBox i : in) {
			i.click(x, y);
		}
	}
	void type(char key) {
		for(TextBox i : in) {
			i.type(key);
		}
	}
	void set(double[] num) {
		for(int i = 0; i < 3; i++) {
			in[i].text = ""+num[i];
		}
	}
	double[] get() {
		double[] out = new double[3];
		for(int i = 0; i < 3; i++) {
			try {
				out[i] = Double.parseDouble(in[i].text.trim());
			} catch(Exception e) {
				out[i] = in[i].text.trim().length();
			}
		}
		return out;
	}
}
