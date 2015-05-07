package com.example.modernartui;

import android.graphics.Color;
import java.util.Random;

public class ColorTube {
	private int r1,g1,b1,r2,g2,b2;
	
	public static ColorTube create() {
		return new ColorTube();
	}
	
	private ColorTube() {
		Random r = new Random();
		r1 = r.nextInt(256);
		g1 = r.nextInt(256);
		b1 = r.nextInt(256);
		r2 = 256 - r1;
		g2 = 256 - g1;
		b2 = 256 - b1;
	}
	
	/*
	 * Level should be between 0 and 100
	 */
	public int at(int level) {
		int r = (int) (level / 100.0 * r1 + 
				(100 - level) / 100.0 * r2);
		int g = (int) (level / 100.0 * g1 + 
				(100 - level) / 100.0 * g2);
		int b = (int) (level / 100.0 * b1 + 
				(100 - level) / 100.0 * b2);
		int k = Color.BLACK;
		return k + Color.rgb(r, g, b);
	}
}
