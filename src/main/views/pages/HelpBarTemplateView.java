package main.views.pages;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

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

    protected void addActionListenerHelpBar() {
        helpBar.getHelpButton().addActionListener(this);
    }

    protected void actionEventInHelpBar(ActionEvent e) {
        if (e.getSource() == helpBar.getHelpButton()) {
            System.out.println ("this should open HelpView");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actionEventInHelpBar(e);
    }
}
