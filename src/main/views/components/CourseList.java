package main.views.components;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import main.data.Palette;

public class CourseList extends JPanel {
    CourseCard courseCardLeft;
    CourseCard courseCardCenter;
    CourseCard courseCardRight;
    
    CourseList() {
        this.setPreferredSize(new Dimension(800,380));
        this.setBackground(Palette.instance().getWhite());
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        courseCardLeft = new CourseCard();
        this.add(courseCardLeft);

        courseCardCenter = new CourseCard();
        this.add(courseCardCenter);

        courseCardRight = new CourseCard();
        this.add(courseCardRight);
    }
}
