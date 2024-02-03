package main.views.pages;

import main.views.pages.ResultView;
import main.views.components.ExamMenu;
import main.views.pages.ExamView;

import java.awt.GridBagConstraints;

import main.controllers.PresentExamController;

public class ExamEndedView extends ExamView {
    //private void showScore(Result){}
    
    protected void paintMenuPanel() {
        menuPanel = new ExamMenu(questions.size());
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.BOTH;

        contentPanel.add(menuPanel, constraints);
    }
}
