package main.views.pages;

import main.views.components.ExamMenu;
import main.views.components.QuestionPanel;
import main.views.components.ResultsBlock;

import java.awt.event.ActionEvent;
import java.util.List;

import main.controllers.PresentExamController;

public class ExamEndedView extends ExamTemplateView {
    PresentExamController presentController;
    ResultsBlock results;

    ExamEndedView(List<QuestionPanel> questions, PresentExamController presentController) {
        this.questions = questions;
        this.presentController = presentController;
        index = 0;

        buildFrame("ExamEndedView");
        paintBorders();
        paintContentPanel();
        inicializeQuestions();

        addActionListener();
    }
    
    protected void paintMenuPanel() {
        results = new ResultsBlock();
        menuPanel = new ExamMenu(results, questions.size());
        menuPanel.setCurrentQuestion(index);

        results.paintResults(showScore(), questions.size());
        
        contentPanel.add(menuPanel, menuPanelConstraints());
    }

    protected void inicializeQuestions() {
        List<List<String>> explication = presentController.getJustification();
        
        for (int i = 0; i < questions.size(); i++) {
            questions.get(i).disableOptions();
            questions.get(i).setExplicationButtonVisible();
            questions.get(i).paintExplicationPanel(explication.get(i));
        }
    }

    private int showScore(){
        int numCorrectQuestions = 0;

        for (int i = 0; i < questions.size(); i++) {
            int selectedOption = questions.get(i).getSelectedOption();
            if (selectedOption == -1) {
                menuPanel.getQuestionListItems().get(i).setIcons("Wrong_Unselected_Icon", "Wrong_Selected_Icon");
            } else if (presentController.isCorrect(i, selectedOption)) {
                numCorrectQuestions++;
                menuPanel.getQuestionListItems().get(i).setIcons("Correct_Unselected_Icon", "Correct_Selected_Icon");
            } else {
                menuPanel.getQuestionListItems().get(i).setIcons("Wrong_Unselected_Icon", "Wrong_Selected_Icon");
            }
        }
        presentController.setResultExamC(numCorrectQuestions);
        presentController.examFinished();
        return numCorrectQuestions;
    }

    protected void actionEventInBottomLeftButton(ActionEvent e) {
        if(e.getSource() == finishExamButton) {
            Frame.instance().setView(ExamsView.instance());
            Frame.instance().setTitle("ExamsView");
        } 
    }

    public ResultsBlock getResults() {
        return results;
    }
}
