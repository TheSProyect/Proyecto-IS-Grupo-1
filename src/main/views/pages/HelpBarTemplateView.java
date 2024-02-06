package main.views.pages;

import java.awt.BorderLayout;

import main.views.components.HelpBar;

public class HelpBarTemplateView extends NavBarTemplateView {
    HelpBar helpBar;

    private void paintHelpBar() {
        helpBar = new HelpBar();
        this.add(helpBar, BorderLayout.SOUTH);
    }

    protected void paintBorders() {
        paintNavBar();
        paintSideBorders();
        paintHelpBar();
    }
}
