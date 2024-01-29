package main.data;

import java.awt.Dimension;

public class Size {
    private static Size size;

    Dimension defaultFrame;
    Dimension logInSideBoder;
    Dimension logInTopBottomBoder;
    Dimension smallLoginButton;
    Dimension bigLoginButton;
    Dimension examMenu;
    
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
        smallLoginButton = new Dimension(148, 42);
        bigLoginButton = new Dimension(314, 42);
        examMenu = new Dimension(300, 640);
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

    public Dimension getSmallLoginButton() {
        return smallLoginButton;
    }

    public Dimension getBigLoginButton() {
        return bigLoginButton;
    }

    public Dimension getExamMenu() {
        return examMenu;
    }
}
