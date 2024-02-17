package main.views.components;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;


import main.utils.Palette;

public class OptionButton extends JRadioButton {
    ImageIcon notSelected;
    ImageIcon selected;

    OptionButton() {}

    public OptionButton(String option) {
        setIcons();

        paintRadialButton();
        this.setText("<html>" + option + "</html>");
    }

    protected void setIcons() {
        notSelected = new ImageIcon("src/assets/Unselected_MultiOption_Icon.png");
        selected = new ImageIcon("src/assets/Selected_MultiOption_Icon.png");
    }

    protected void paintRadialButton() {
        this.setPreferredSize(new Dimension(150, 60));
        this.setForeground(Palette.instance().getGray());
        this.setBackground(Palette.instance().getWhite());
        this.setIcon(this.notSelected);
        this.setFont(new Font("Nunito Sans", Font.PLAIN, 20));
        this.setFocusable(false);
    }

    public void paintIcon() {
        if (this.isSelected() && this.getIcon() == this.notSelected) {
            this.setIcon(this.selected);
        } else if (!this.isSelected() && this.getIcon() == this.selected) {
            this.setIcon(this.notSelected);
        }
    }

    public JRadioButton getButton() {
        return this;
    }
}
