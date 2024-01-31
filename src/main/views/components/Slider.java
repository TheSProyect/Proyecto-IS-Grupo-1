package main.views.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import main.data.Palette;

public class Slider extends JPanel implements ActionListener{
    ButtonSlider rightButton;
    ButtonSlider leftButton;
    CourseList courseList;

    public Slider() {
        this.setPreferredSize(new Dimension(1024, 500));
        this.setBackground(Palette.instance().getWhite());
        this.setLayout(new BorderLayout());
        
        
        leftButton = new ButtonSlider("LeftArrow_Icon.png");
        leftButton.addActionListener(this);

        courseList = new CourseList();

        rightButton = new ButtonSlider("RightArrow_Icon.png");
        rightButton.addActionListener(this);

        this.add(leftButton, BorderLayout.WEST);
        this.add(courseList, BorderLayout.CENTER);
        this.add(rightButton, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == leftButton) {
            courseList.moveLeft();
        } 
        if (e.getSource() == rightButton) {
            courseList.moveRight();
        } 
    }
}
