package main;

import main.views.pages.LogInView;
import main.views.templates.Frame;

public class Main {
    public static void main(String[] args) throws Exception {
        Frame.instance().setView(new LogInView());
    }
}
