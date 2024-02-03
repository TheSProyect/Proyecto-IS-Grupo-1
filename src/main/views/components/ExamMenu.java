package main.views.components;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.utils.Palette;
import main.utils.Size;

public class ExamMenu extends JPanel{
    JScrollPane questionsListScrollPane;
    QuestionsList questionsList;

    public ExamMenu(int time, JButton exitExamButton, int questionAmount) {
        buildExamMenuPanel();

        paintTimer(time, exitExamButton);

        paintQuestionsList(questionAmount);
    }

    public ExamMenu(int questionAmount) {
        buildExamMenuPanel();

        paintResults(questionAmount);

        paintQuestionsList(questionAmount);
    }

    private void buildExamMenuPanel() {
        this.setMinimumSize(new Dimension(Size.instance().getExamMenu()));
        this.setPreferredSize(new Dimension(Size.instance().getExamMenu()));
        this.setMaximumSize(new Dimension(Size.instance().getExamMenu()));
        this.setBackground(Palette.instance().getWhite());
        this.setLayout(new GridBagLayout());
    }

    private void paintTimer(int time, JButton exitExamButton) {
        TimerBlock timer = new TimerBlock(time, exitExamButton);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weighty = 0.5;

        this.add(timer, constraints);
    }

    private void paintResults(int numCorrectQuestions) {
        JPanel resultsPanel = new JPanel();
		this.setPreferredSize(new Dimension(200, 75));
		this.setMaximumSize(new Dimension(200, 75));
		this.setBackground(Palette.instance().getYellow());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;

        this.add(resultsPanel, constraints);
    }

    private void paintQuestionsList(int questionAmount) {
        questionsList = new QuestionsList(questionAmount);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.weighty = 1.5;
        
        this.add(questionsList, constraints);
    }

    public List<QuestionListItem> getQuestionListItems() {
        return questionsList.getQuestionListItems();
    }

    public void setCurrentQuestion(int currentQuestionNumber) {
        questionsList.setCurrentQuestion(currentQuestionNumber);
    }
}
