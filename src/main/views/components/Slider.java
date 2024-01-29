package main.views.components;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import main.data.Palette;

public class Slider extends JPanel {
    ButtonSlider rightButton;
    ButtonSlider leftButton;
    CourseList courseList;

    public Slider() {
        this.setPreferredSize(new Dimension(1024, 500));
        this.setBackground(Palette.instance().getWhite());
        
        
        leftButton = new ButtonSlider("LeftArrow_Icon.png");
        courseList = new CourseList();
        rightButton = new ButtonSlider("RightArrow_Icon.png");

        JLabel spacer = new JLabel();
        spacer.setPreferredSize(new Dimension(2048, 70));

        this.add(spacer);
        this.add(leftButton);
        this.add(courseList);
        this.add(rightButton);
    }
}
