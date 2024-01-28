package main.data;

import java.awt.Dimension;

public class Size {
    private static Size size;

    Dimension defaultFrame;
    Dimension logInSideBoder;
    Dimension logInTopBottomBoder;
    
    public static Size instance() {
		if (size == null){
			size = new Size();
		}
		return size;
	}

    Size()  {
        defaultFrame = new Dimension(1024, 720);
        logInSideBoder = new Dimension(80,135);
        logInTopBottomBoder = new Dimension(478,135);
    }

    public Dimension getDefaultFrame() {
        return defaultFrame;
    }
    
    public Dimension getLogInSideBoder() {
        return logInSideBoder;
    }
    
    public Dimension getLogInTopBottomBoder() {
        return logInTopBottomBoder;
    }
}
