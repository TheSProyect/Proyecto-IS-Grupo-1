package main.views.components;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

public class SingleOptionButton extends OptionButton {
    ImageIcon notSelected;
    ImageIcon selected;

    public SingleOptionButton(String option, ButtonGroup group) {
        setIcons();

        paintRadialButton();
        this.setText("<html>" + option + "</html>");
        if (group != null) {
            group.add(this);
        }
    }

    protected void setIcons() {
        notSelected = new ImageIcon("src/assets/Unselected_Option_Icon.png");
        selected = new ImageIcon("src/assets/Selected_Option_Icon.png");
    }
}
