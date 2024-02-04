package main.views.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.utils.Palette;

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

    public List<JButton> getButtons() {
        return courseList.getButtons();
    }

    public void setCourseCards(List<List<String>> examsInfoList) {
        courseList.setCourseCards(examsInfoList);

        int maxAmountOfCardsInView = 3;
        if (examsInfoList.size() <= maxAmountOfCardsInView) {
            leftButton.setEnabled(false);
            rightButton.setEnabled(false);
        }
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
