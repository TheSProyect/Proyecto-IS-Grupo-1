package main.views.components;

import java.awt.Dimension;

import javax.swing.JPanel;

import main.data.Palette;

public class Slider extends JPanel {
    ButtonSlider rightButton;
    ButtonSlider leftButton;
    CourseList courseList;

    public Slider() {
        this.setPreferredSize(new Dimension(1024, 640));
        this.setBackground(Palette.instance().getWhite());
        
        rightButton = new ButtonSlider();
        courseList = new CourseList();
        leftButton = new ButtonSlider();

        this.add(rightButton);
        this.add(courseList);
        this.add(leftButton);
    }
}
