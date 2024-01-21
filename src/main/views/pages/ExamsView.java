package main.views.pages;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

import main.views.components.NavBar;

public class ExamsView extends JFrame {
    JLabel title;
    JLabel examTitle;
    NavBar navBar;

    public ExamsView() {
        this.setVisible(true);
        this.setTitle("ExamsView");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(1024, 720));
        this.setSize(1024, 720);
        this.setLayout(new BorderLayout());

        navBar = new NavBar();

        this.add(navBar, BorderLayout.NORTH);
    }
}