package main.utils;

import java.awt.Dimension;

public class Size {
    private static Size size;

    Dimension defaultFrame;
    Dimension logInSideBoder;
    Dimension loginTopBottomBoder;
    Dimension RegisterTopBottomBoder;
    Dimension editProfileTopBottomBoder;
    Dimension smallLoginButton;
    Dimension bigLoginButton;
    Dimension examMenu;
    Dimension examEndedPopUp;
    Dimension registerUserPopUp;
    Dimension newExamPopUp;
    Dimension examPublishedPopUp;
    public static Size instance() {
		if (size == null){
			size = new Size();
		}
		return size;
	}

    Size()  {
        defaultFrame = new Dimension(1024, 720);
        logInSideBoder = new Dimension(80,135);
        RegisterTopBottomBoder = new Dimension(478,60);
        loginTopBottomBoder = new Dimension(478,180);
        editProfileTopBottomBoder = new Dimension(478, 120);
        smallLoginButton = new Dimension(148, 42);
        bigLoginButton = new Dimension(314, 42);
        examMenu = new Dimension(300, 560);
        examEndedPopUp = new Dimension(500, 280);
        registerUserPopUp = new Dimension(550, 220);
        newExamPopUp = new Dimension(600, 550);
        examPublishedPopUp = new Dimension(500, 200);
    }

    public Dimension getDefaultFrame() {
        return defaultFrame;
    }
    
    public Dimension getLogInSideBoder() {
        return logInSideBoder;
    }
    
    public Dimension getLoginTopBottomBoder() {
        return loginTopBottomBoder;
    }

    public Dimension getRegisterTopBottomBoder() {
        return RegisterTopBottomBoder;
    }

    public Dimension getEditProfileTopBottomBoder() {
        return editProfileTopBottomBoder;
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

    public Dimension getExamEndedPopUpDimension() {
        return examEndedPopUp;
    }

    public Dimension getRegisterUserPopUpDimension(){
        return registerUserPopUp;
    }

    public Dimension getNewExamPopUpDimension() {
        return newExamPopUp;
    }
    
    public Dimension getExamPublishedPopUpDimension() {
        return examPublishedPopUp;
    }
}
