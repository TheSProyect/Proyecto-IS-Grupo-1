package main.data;

import java.awt.Color;

public class Palette {
    private static Palette palette;
    
    Color white;
    Color offWhite;
    Color lightGray;
    Color gray;
    Color black;
    Color blue;
    Color yellow;

    public static Palette instance() {
		if (palette == null){
			palette = new Palette();
		}
		return palette;
	}

    private Palette() {
        white = new Color(255,255,255);
        offWhite = new Color(252,252,252);
        lightGray = new Color(201,201,201);
        gray = new Color(77,77,77);
        black = new Color(31,31,31);
        blue = new Color(25, 66, 140);
        yellow = new Color(247, 167, 39);
	}

    public Color getWhite() {
        return white;
    }

    public Color getOffWhite() {
        return offWhite;
    }

    public Color getLightGray() {
        return lightGray;
    }

    public Color getGray() {
        return gray;
    }

    public Color getBlack() {
        return black;
    }

    public Color getBlue() {
        return blue;
    }

    public Color getYellow() {
        return yellow;
    }
}
