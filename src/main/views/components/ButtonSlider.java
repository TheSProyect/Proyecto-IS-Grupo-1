package main.views.components;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import main.data.Palette;

public class ButtonSlider extends JButton {
    ImageIcon icon;
    
    ButtonSlider() {
        // icon = new ImageIcon("assets/Arrow_IconPH.png");
        // this.setIcon(icon);

        this.setBackground(Palette.instance().getGray());
        this.setPreferredSize(new Dimension(30,30));
    }
}
