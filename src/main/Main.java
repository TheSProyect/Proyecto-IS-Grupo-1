package main;

import main.utils.UserData;
import main.views.pages.Frame;
import main.views.pages.LogInView;

public class Main {
    public static void main(String[] args) throws Exception {
        UserData.instance().setUsername("Usuario");
        UserData.instance().setPassword("Contrasenia");

        Frame.instance().setView(new LogInView());
        // Frame.instance().setView(ExamsView.instance());
    }
}
