package main.views.components;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

import main.utils.Palette;

public class ButtonSlider extends JButton {
    ImageIcon icon;
    
    ButtonSlider(String iconName) {
        icon = new ImageIcon("assets/" + iconName);
        this.setIcon(icon);

        Border border = BorderFactory.createLineBorder(Palette.instance().getWhite());
        this.setBorder(border);

        this.setBackground(Palette.instance().getWhite());
        this.setPreferredSize(new Dimension(40,40));
        this.setFocusable(false);

    }
}
