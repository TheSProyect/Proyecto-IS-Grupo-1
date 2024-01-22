package main.views.pages;

import java.awt.BorderLayout;

import main.views.components.AdminNavBar;

public class AdminExamsView extends ExamsView {
    protected void paintNavBar() {
        navBar = new AdminNavBar();
        this.add(navBar, BorderLayout.NORTH);
    }
}
