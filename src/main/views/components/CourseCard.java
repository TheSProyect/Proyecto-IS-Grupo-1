package main.views.components;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.data.Palette;

public class CourseCard extends JPanel {
    JLabel courseName;
    JPanel lineDivider;
    JLabel courseDescription;
    JButton presentExamButton;

    CourseCard() {
        this.setBackground(Palette.instance().getWhite());
        this.setPreferredSize(new Dimension(200, 500));
    }
}
