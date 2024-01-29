package main.views.components;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import main.data.Palette;
import main.data.Size;

public class ExamMenu extends JPanel {
    JScrollPane questionsListScrollPane;
    JButton finishExamButton;
    QuestionsList questionsList;

    public ExamMenu() {
        this.setMinimumSize(new Dimension(Size.instance().getExamMenu()));
        this.setPreferredSize(new Dimension(Size.instance().getExamMenu()));
        this.setMaximumSize(new Dimension(Size.instance().getExamMenu()));
        this.setBackground(Palette.instance().getWhite());
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 50));

        paintTimer();

        questionsList = new QuestionsList();
        this.add(questionsList);

        paintFinishExamButton();
    }

    private void paintTimer() {
        JPanel timerPanel = new JPanel();
        timerPanel.setPreferredSize(new Dimension(200, 70));
        timerPanel.setBackground(Palette.instance().getBlue());

        this.add(timerPanel);
    }

    private void paintFinishExamButton() {
        finishExamButton = new JButton("Terminar Examen");
        finishExamButton.setFont(new Font("Nunito Sans", Font.BOLD, 15));
        finishExamButton.setForeground(Palette.instance().getWhite());
        finishExamButton.setBackground(Palette.instance().getBlue());
        finishExamButton.setPreferredSize(new Dimension(190, 30));
        finishExamButton.setFocusable(false);

        Border border = BorderFactory.createLineBorder(Palette.instance().getBlue());
        finishExamButton.setBorder(border);

        this.add(finishExamButton);
    }
}
