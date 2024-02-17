package main;

import main.views.pages.ExamsView;
import main.views.pages.Frame;
import main.views.pages.LogInView;
import main.utils.UserData;
import main.views.pages.ExamsView;


public class Main {
    public static void main(String[] args) throws Exception {
        UserData.instance().setUsername("Usuario");
        UserData.instance().setPassword("Contrasenia");
        //Frame.instance().setView(new LogInView());
        Frame.instance().setView(new ExamsView());
    }
}
