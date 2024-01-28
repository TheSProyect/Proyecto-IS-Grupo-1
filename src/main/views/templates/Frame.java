package main.views.templates;

import javax.swing.JFrame;

import main.data.Size;

public class Frame extends JFrame{
    protected void createFrame(String windowName) {
        this.setVisible(true);
        this.setTitle(windowName);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(Size.instance().getDefaultFrame());
        this.setSize(Size.instance().getDefaultFrame());
    }
}
