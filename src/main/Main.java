package main;

import main.utils.UserData;
import main.views.pages.Frame;
import main.views.pages.LogInView;
import main.views.pages.ExamsView;

public class Main {
    public static void main(String[] args) throws Exception {
        
        Frame.instance().setView(new LogInView());
    }
}
