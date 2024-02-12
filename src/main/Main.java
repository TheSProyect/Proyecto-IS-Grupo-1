package main;

import javax.swing.Popup;

import main.utils.UserData;
import main.views.components.PopUp;
import main.views.pages.Frame;
import main.views.pages.LogInView;
import main.views.pages.NewExamPopup;
import main.views.pages.NewExamView;

public class Main {
    public static void main(String[] args) throws Exception {
        UserData.instance().setUsername("Usuario");
        UserData.instance().setPassword("Contrasenia");

        // Frame.instance().setView(new LogInView());
        Frame.instance().setView(new NewExamView());
        PopUp.instance().setView(new NewExamPopup());
    }
}
