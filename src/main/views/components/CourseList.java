package main.views.components;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.data.Palette;

public class CourseList extends JPanel {
    List<CourseCard> courseCard;
    GridBagConstraints constraintsLeft;
    GridBagConstraints constraintsCenter;
    GridBagConstraints constraintsRight;
    int[] index;
    
    CourseList() {
        buildCourseListPanel();
        inicializeGridConstraints();
        inicializeCourseList();
        index = new int[]{0, 1, 2};
        showCoursePanel();
    }

    private void buildCourseListPanel() {
        this.setPreferredSize(new Dimension(800,380));
        this.setBackground(Palette.instance().getWhite());
        this.setLayout(new GridBagLayout());
    }

    private void showCoursePanel() {
        this.add(courseCard.get(index[0]), constraintsLeft);
        this.add(courseCard.get(index[1]), constraintsCenter);
        this.add(courseCard.get(index[2]), constraintsRight);
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

    private void inicializeCourseList() {
        courseCard = new ArrayList<CourseCard>();
        courseCard.add(new CourseCard("Java - SE Associate Programmer"));
        courseCard.add(new CourseCard("Otro examen"));
        courseCard.add(new CourseCard("El siguiente XD"));
        courseCard.add(new CourseCard("Este examen no se debe ver hasta tocar la flecha"));
        courseCard.add(new CourseCard("Siguiente examen chikibaby"));
        courseCard.add(new CourseCard("El mejor examen del mundo y asi"));
    }

    public void moveLeft() {
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
        List<JButton> buttons = new ArrayList<JButton>();

        for (int i = 0; i < courseCard.size(); i++) {
            buttons.add(courseCard.get(i).getPresentExamButton());
        }

        return buttons;
    }
}
