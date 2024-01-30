package main.views.components;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

import main.data.Palette;

public class SingleOptionButton extends JRadioButton implements ActionListener{
    ImageIcon notSelected;
    ImageIcon selected;

    SingleOptionButton(String option) {
        notSelected = new ImageIcon("assets/Home_Icon.png");
        selected = new ImageIcon("assets/Paperclip_Icon.png");

        paintRadialButton();
        this.setText(option);
        
    }

    private void paintRadialButton() {
        this.setPreferredSize(new Dimension(1024, 50));
        this.setMaximumSize(new Dimension(1024,50));
        this.setForeground(Palette.instance().getGray());
        this.setBackground(Palette.instance().getWhite());
        this.setIcon(notSelected);
        this.setFont(new Font("Nunito Sans", Font.PLAIN, 20));
        this.setFocusable(false);

        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.getIcon() == notSelected) {
            this.setIcon(selected);
        } else if (this.getIcon() == selected) {
            this.setIcon(notSelected);
        }
    }


}
