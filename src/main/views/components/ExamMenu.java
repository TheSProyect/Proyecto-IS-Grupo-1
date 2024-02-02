package main.views.components;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.utils.Palette;
import main.utils.Size;

public class ExamMenu extends JPanel{
    JScrollPane questionsListScrollPane;
    QuestionsList questionsList;

    public ExamMenu(int time, JButton exitExamButton) {
        this.setMinimumSize(new Dimension(Size.instance().getExamMenu()));
        this.setPreferredSize(new Dimension(Size.instance().getExamMenu()));
        this.setMaximumSize(new Dimension(Size.instance().getExamMenu()));
        this.setBackground(Palette.instance().getWhite());
        this.setLayout(new GridBagLayout());

        paintTimer(time, exitExamButton);

        paintQuestionsList();
    }

    private void paintTimer(int time, JButton exitExamButton) {
        TimerBlock timer = new TimerBlock(time, exitExamButton);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weighty = 0.5;

        this.add(timer, constraints);
    }

    private void paintQuestionsList() {
        questionsList = new QuestionsList();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.weighty = 1.5;
        
        this.add(questionsList, constraints);
    }
}
