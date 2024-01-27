package main.views.components;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

import main.data.Palette;

public class NavBarButton extends JButton {
    ImageIcon icon;
    Border border;
    
    public NavBarButton(String buttonName, String iconFileName, boolean leftAling) {
        border = BorderFactory.createEmptyBorder();
        this.setBorder(border);
        this.setPreferredSize(new Dimension(150, 40));

        icon = new ImageIcon("assets/" + iconFileName);
        this.setIcon(icon);

        if(isTwoWords(buttonName)) {
            String[] separated = buttonName.split(" ");
            this.setText("<html>" + separated[0] + "<br/>" + separated[1] + "</html>");
        } else {
            this.setText(buttonName);
        }
        
        this.setFont(new Font("Nunito Sans", Font.PLAIN, 15));
        this.setIconTextGap(15);

        if (leftAling) {
            this.setHorizontalTextPosition(JButton.LEFT);
        }

        this.setForeground(Palette.instance().getLightGray());
        this.setBackground(Palette.instance().getWhite());
        this.setFocusable(false);
    }

    private boolean isTwoWords(String str) {
        if (str.indexOf(" ") == -1) {
            return false;
        }
        return true;
    }
}
