package main.views.components;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

import main.utils.Palette;

public class SingleOptionButton extends JRadioButton {
    ImageIcon notSelected;
    ImageIcon selected;

    SingleOptionButton(String option, ButtonGroup group) {
        notSelected = new ImageIcon("src/assets/Unselected_Option_Icon.png");
        selected = new ImageIcon("src/assets/Selected_Option_Icon.png");

        paintRadialButton();
        this.setText(option);
        group.add(this);
    }

    private void paintRadialButton() {
        this.setPreferredSize(new Dimension(1024, 50));
        this.setMaximumSize(new Dimension(1024,50));
        this.setForeground(Palette.instance().getGray());
        this.setBackground(Palette.instance().getWhite());
        this.setIcon(notSelected);
        this.setFont(new Font("Nunito Sans", Font.PLAIN, 20));
        this.setFocusable(false);
    }

    public void paintIcon() {
        if (this.isSelected() && this.getIcon() == notSelected) {
            this.setIcon(selected);
        } else if (!this.isSelected() && this.getIcon() == selected) {
            this.setIcon(notSelected);
        }
    }
}
