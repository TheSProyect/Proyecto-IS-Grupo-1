package main.views.components;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import main.utils.Palette;

public class HelpBar extends JPanel{
    NavBarButton helpButton;

    public HelpBar() {
        this.setPreferredSize(new Dimension(1024, 80));
        this.setBackground(Palette.instance().getWhite());
        this.setLayout(new FlowLayout(FlowLayout.TRAILING));

        helpButton = new NavBarButton("Ayuda", "Help_Icon.png", true);

        this.add(helpButton);
    }

    public NavBarButton getHelpButton() {
        return helpButton;
    }
}
