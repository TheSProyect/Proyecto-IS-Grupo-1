package main.views.components;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalButtonUI;

import main.utils.Palette;

public class Button extends JButton{
    private int height = 30;
    private int width = 217;
    
    public Button(String text){
        buildFrame();
        paintButton(text);
    }

    private void buildFrame(){
        this.setUI(new MetalButtonUI());
        this.setBackground(Palette.instance().getBlue());
        this.setPreferredSize(new Dimension(width, height));

        Border border = BorderFactory.createLineBorder(Palette.instance().getBlue());
        this.setBorder(border);
    }

    protected void paintButton(String text){
        this.setText(text);
        
        this.setForeground(Palette.instance().getOffWhite());
        this.setFont(new Font("Nunito Sans", Font.BOLD, 15));
        // this.setHorizontalAlignment(JLabel.CENTER);
        // this.setVerticalAlignment(JLabel.BOTTOM);
        this.setFocusable(false);
    }
}