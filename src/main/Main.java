package main;

import main.views.pages.Frame;
import main.views.pages.LogInView;
import main.views.pages.RegisterUserView;


public class Main {
    public static void main(String[] args) throws Exception {
        // Frame.instance().setView(new LogInView());
        Frame.instance().setView(new RegisterUserView());
    }
}
