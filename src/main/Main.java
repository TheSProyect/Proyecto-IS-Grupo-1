package main;

import main.views.pages.ExamsView;
import main.views.templates.Frame;

// import main.views.pages.LogInView;

public class Main {
    public static void main(String[] args) throws Exception {
        // new LogInView();
        Frame.instance().setView(ExamsView.instance());
        // Frame.instance().validate();
    }
}
