package main.views.components;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

import main.utils.Palette;

public class NavBarButton extends JButton {
    public NavBarButton(String buttonName, String iconFileName, boolean leftAling) {
        buildButton();
        
        setButtonText(buttonName);
        
        setButtonIcon(iconFileName, leftAling);
    }

    private void buildButton() {
        Border border = BorderFactory.createEmptyBorder();
        this.setBorder(border);
        this.setPreferredSize(new Dimension(150, 40));
        this.setForeground(Palette.instance().getLightGray());
        this.setBackground(Palette.instance().getWhite());
        this.setFocusable(false);
    }

    private void setButtonIcon(String iconFileName, boolean leftAling) {
        ImageIcon icon = new ImageIcon("src/assets/" + iconFileName);
        this.setIcon(icon);
        this.setIconTextGap(15);

        if (leftAling) {
            this.setHorizontalTextPosition(JButton.LEFT);
        }
    }

    private void setButtonText(String buttonName) {
        this.setText("<html>" + buttonName + "</html>");
        this.setFont(new Font("Nunito Sans", Font.PLAIN, 15));
    }
}
