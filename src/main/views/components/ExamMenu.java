package main.views.components;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

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

    private void paintTimer() {
        JPanel timerPanel = new JPanel();
        timerPanel.setPreferredSize(new Dimension(200, 70));
        timerPanel.setBackground(Palette.instance().getBlue());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weighty = 0.5;

        this.add(timerPanel, constraints);
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
