package main.views.components;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.data.Palette;
import main.data.Size;

public class ExamMenu extends JPanel{
    JScrollPane questionsListScrollPane;
    QuestionsList questionsList;

    public ExamMenu() {
        this.setMinimumSize(new Dimension(Size.instance().getExamMenu()));
        this.setPreferredSize(new Dimension(Size.instance().getExamMenu()));
        this.setMaximumSize(new Dimension(Size.instance().getExamMenu()));
        this.setBackground(Palette.instance().getWhite());
        // this.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 50));
        this.setLayout(new GridBagLayout());

        paintTimer();

        paintQuestionsList();
    }

    public ExamMenu(int time) {
        this.setMinimumSize(new Dimension(Size.instance().getExamMenu()));
        this.setPreferredSize(new Dimension(Size.instance().getExamMenu()));
        this.setMaximumSize(new Dimension(Size.instance().getExamMenu()));
        this.setBackground(Palette.instance().getWhite());
        // this.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 50));
        this.setLayout(new GridBagLayout());

        paintTimer2(time);

        paintQuestionsList();
    }

    private void paintTimer() {
        // JPanel timerPanel = new JPanel();
        // timerPanel.setPreferredSize(new Dimension(200, 70));
        // timerPanel.setBackground(Palette.instance().getBlue());
        TimerBlock timer = new TimerBlock(4, null);

        // timerPanel.add(timer);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weighty = 0.5;

        this.add(timer, constraints);
    }

    private void paintTimer2(int time) {
        // JPanel timerPanel = new JPanel();
        // timerPanel.setPreferredSize(new Dimension(200, 70));
        // timerPanel.setBackground(Palette.instance().getBlue());
        TimerBlock timer = new TimerBlock(time, null);

        // timerPanel.add(timer);

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
