package main.views.components;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.utils.Palette;

public class CourseList extends JPanel {
    List<CourseCard> courseCard;
    GridBagConstraints constraintsLeft;
    GridBagConstraints constraintsCenter;
    GridBagConstraints constraintsRight;
    int[] index;
    
    CourseList() {
        buildCourseListPanel();
        inicializeGridConstraints();
    }

    private void buildCourseListPanel() {
        this.setPreferredSize(new Dimension(800,380));
        this.setBackground(Palette.instance().getWhite());
        this.setLayout(new GridBagLayout());
    }

    private void showCoursePanel() {
        int leftCard = 1;
        int centerCard = 2;
        int rightCard = 3;

        if (index.length >= leftCard) {
            this.add(courseCard.get(index[0]), constraintsLeft);
        }
        if (index.length >= centerCard) {
            this.add(courseCard.get(index[1]), constraintsCenter);
        }
        if (index.length == rightCard) {
            this.add(courseCard.get(index[2]), constraintsRight);
        }
    }

    private void inicializeGridConstraints() {
        constraintsLeft = new GridBagConstraints();
        constraintsLeft.gridx = 0;
        constraintsLeft.weightx = 1.0;

        constraintsCenter = new GridBagConstraints();
        constraintsCenter.gridx = 1;
        constraintsCenter.weightx = 1.0;

        constraintsRight = new GridBagConstraints();
        constraintsRight.gridx = 2;
        constraintsRight.weightx = 1.0;
    }

    private void inicializeCarrousel() {
        int maxAmountOfCourseCardsInView = 3;
        if (courseCard.size() >= maxAmountOfCourseCardsInView) {
            index = new int[]{0, 1, 2};
        } else {
            index = new int[courseCard.size()];
            for (int i = 0; i < courseCard.size(); i++) {
                index[i] = i;
            }
        }

        showCoursePanel();
    }

    public void setCourseCards(List<List<String>> examsInfoList) {
        courseCard = new ArrayList<CourseCard>();
        for (int i = 0; i < examsInfoList.size(); i++) {
            courseCard.add(new CourseCard(examsInfoList.get(i).get(0)));
            courseCard.get(i).paintCourseDescription(examsInfoList.get(i).get(1));
            courseCard.get(i).paintExamCaracteristics(examsInfoList.get(i));
        }

        inicializeCarrousel();
    }

    private boolean cannotMove() {
        if (index == null) {
            return true;
        } else if (index.length == courseCard.size()) {
            return true;
        } else {
            return false;
        }
    }

    public void moveLeft() {
        if (cannotMove()) {
            return;
        }
       
        this.remove(courseCard.get(index[2]));
        this.remove(courseCard.get(index[1]));
        this.remove(courseCard.get(index[0]));

        this.add(courseCard.get(index[1]), constraintsRight);
        this.add(courseCard.get(index[0]), constraintsCenter);

        if (index[0] - 1 < 0) {
            index[2] = index[1];
            index[1] = index[0];
            index[0] = courseCard.size() - 1;
        } else {
            index[2] = index[1];
            index[1] = index[0];
            index[0] = index[0] - 1;
        }

        this.add(courseCard.get(index[0]), constraintsLeft);
        this.revalidate();
        this.repaint();
    }

    public void moveRight() {
        if (cannotMove()) {
            return;
        }

        this.remove(courseCard.get(index[2]));
        this.remove(courseCard.get(index[1]));
        this.remove(courseCard.get(index[0]));

        this.add(courseCard.get(index[2]), constraintsCenter);
        this.add(courseCard.get(index[1]), constraintsLeft);

        if (index[2] + 1 == courseCard.size()) {
            index[0] = index[1];
            index[1] = index[2];
            index[2] = 0;
        } else {
            index[0] = index[1];
            index[1] = index[2];
            index[2] = index[2] + 1;
        }
        
        this.add(courseCard.get(index[2]), constraintsRight);
        this.revalidate();
        this.repaint();
    }

    public List<JButton> getButtons() {
        if (courseCard == null) {
            return null;

        } else {
            List<JButton> buttons = new ArrayList<JButton>();

            for (int i = 0; i < courseCard.size(); i++) {
                buttons.add(courseCard.get(i).getPresentExamButton());
            }

            return buttons;
        }
    }
}
