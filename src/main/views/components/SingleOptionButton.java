package main.views.components;

import java.awt.Font;

import javax.swing.JRadioButton;

import main.data.Palette;

public class SingleOptionButton extends JRadioButton {
    SingleOptionButton() {
        this.setForeground(Palette.instance().getGray());
        this.setBackground(Palette.instance().getWhite());
        this.setText("No compila");
        this.setFont(new Font("Nunito Sans", Font.PLAIN, 20));
        this.setFocusable(false);
    }
}
