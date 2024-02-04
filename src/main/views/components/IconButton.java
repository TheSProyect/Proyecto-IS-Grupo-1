package main.views.components;

import javax.swing.ImageIcon;
import javax.swing.plaf.metal.MetalButtonUI;

public class IconButton extends Button {

    public IconButton(String text, String pathIcon){
        super(text);
        paintIcon(pathIcon);
    }
    
    private void paintIcon(String pathIcon){
        ImageIcon icon = new ImageIcon("src/assets/"+pathIcon);

        this.setIcon(icon);
    }
}