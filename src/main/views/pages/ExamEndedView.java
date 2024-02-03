package main.views.pages;

import main.views.components.ExamMenu;
import main.views.components.ResultsBlock;

import java.awt.GridBagConstraints;

import main.controllers.PresentExamController;

public class ExamEndedView extends ExamView {
    //private void showScore(Result){}
    
    protected void paintMenuPanel() {
        int numCorrectQuestions =  15;
        ResultsBlock results = new ResultsBlock(numCorrectQuestions, questions.size());
        menuPanel = new ExamMenu(results, questions.size());
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.BOTH;

        contentPanel.add(menuPanel, constraints);
    }
}
