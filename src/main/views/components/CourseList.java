package main.views.components;

import java.awt.Dimension;

import javax.swing.JPanel;

import main.data.Palette;

public class CourseList extends JPanel {
    CourseCard courseCard;
    
    CourseList() {
        // this.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        // this.setVisibleRowCount(3);
        this.setPreferredSize(new Dimension(800,600));
        this.setBackground(Palette.instance().getLightGray());

        courseCard = new CourseCard();
        this.add(courseCard);

        courseCard = new CourseCard();
        this.add(courseCard);

        courseCard = new CourseCard();
        this.add(courseCard);
    }
}
