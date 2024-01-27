package main.data;

import java.awt.Color;

public class Palette {
    private static Palette palette;
    
    Color white;
    Color offWhite;
    Color lightGray;
    Color gray;
    Color black;

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

}
