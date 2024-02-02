package main.views.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

import main.utils.Palette;

public class Button extends JButton{
    private int height = 48;
    private int width = 217;
    private Color background = Palette.instance().getBlue();
    
    public Button(String text){
        buildFrame();
        paintButton(text);
    }

    private void buildFrame(){
        this.setBackground(background);
        this.setPreferredSize(new Dimension(width, height));
    }

    protected void paintButton(String text){
        this.setText("<html>" + text + "</html>");
        
        this.setForeground(Palette.instance().getOffWhite());
        this.setFont(new Font("Nunito Sans", Font.BOLD, 25));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.BOTTOM);
        this.setFocusable(false);
    }
}