package main.views.components;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.utils.Palette;
import main.utils.Size;

public class ExamMenu extends JPanel{
    JScrollPane questionsListScrollPane;
    QuestionsList questionsList;
    TimerBlock returnBlock;

    public ExamMenu(JPanel block, int questionAmount) {
        buildExamMenuPanel();

        paintBlock(block);

        paintQuestionsList(questionAmount);
    }

    public ExamMenu(TimerBlock block, int questionAmount) {
        buildExamMenuPanel();

        paintBlock(block);

        this.returnBlock = block;

        paintQuestionsList(questionAmount);
    }

    private void buildExamMenuPanel() {
        this.setMinimumSize(new Dimension(Size.instance().getExamMenu()));
        this.setPreferredSize(new Dimension(Size.instance().getExamMenu()));
        this.setMaximumSize(new Dimension(Size.instance().getExamMenu()));
        this.setBackground(Palette.instance().getWhite());
        this.setLayout(new GridBagLayout());
    }

    private void paintBlock(JPanel block) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weighty = 0.5;

        this.add(block, constraints);
    }

    private void paintQuestionsList(int questionAmount) {
        questionsList = new QuestionsList(questionAmount);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.weighty = 1.5;
        
        this.add(questionsList, constraints);
    }

    public void repaintQuestionsList(int questionAmount) {
        this.remove(questionsList);
        paintQuestionsList(questionAmount);
        this.repaint();
    }

    public List<QuestionListItem> getQuestionListItems() {
        return questionsList.getQuestionListItems();
    }

    public void setCurrentQuestion(int currentQuestionNumber) {
        questionsList.setCurrentQuestion(currentQuestionNumber);
    }

    /* */
    public TimerBlock getBlock(){
        return this.returnBlock;
    }
}
