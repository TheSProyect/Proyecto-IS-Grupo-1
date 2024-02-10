package main.views.components;

import javax.swing.ImageIcon;

public class IconButton extends Button {

    public IconButton(String text, String pathIcon){
        super(text);
        paintIcon(pathIcon);
    }
    
    public void paintIcon(String pathIcon){
        ImageIcon icon = new ImageIcon("src/assets/"+pathIcon);
        this.setIconTextGap(15);

        this.setIcon(icon);
    }
}