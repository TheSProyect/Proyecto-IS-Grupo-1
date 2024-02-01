package main.views.components;

import javax.swing.JLabel;

import java.net.URL;

import javax.swing.ImageIcon;

public class IconButton extends Button {

    public IconButton(String text, String pathIcon){
        super(text);
        paintIcon(pathIcon);
    }
    private void paintIcon(String pathIcon){
        ImageIcon icon = new ImageIcon("assets/"+pathIcon);

        this.setIcon(icon);
    }
}