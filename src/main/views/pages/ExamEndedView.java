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
        this.bottomLeftButton.setText("Salir de Examen");
        inicializeQuestions();

        addActionListener();
    }
    
    protected void paintMenuPanel() {
        results = new ResultsBlock();
        menuPanel = new ExamMenu(results, questions.size());
        menuPanel.setCurrentQuestion(index);

        showScore();
        float numCorrectQuestions = presentController.caculateResult(getSelectedOptions());
        results.paintResults(numCorrectQuestions, questions.size());
        
        presentController.setResultExamC(numCorrectQuestions);
        presentController.examFinished();

        contentPanel.add(menuPanel, menuPanelConstraints());
    }

    protected void inicializeQuestions() {
        List<List<String>> explication = presentController.getJustification();
        
        for (int i = 0; i < questions.size(); i++) {
            questions.get(i).setExplicationButtonVisible();
            questions.get(i).paintExplicationPanel(explication.get(i));
        }
    }

    private void showScore(){
        for (int i = 0; i < questions.size(); i++) {
            List<Boolean> selectedOptions = questions.get(i).getSelectedOption();
            int numCorrectAnswers = 0;
            int numIncorrectAnswers = 0;
            boolean allCorrectSelected = true;
            boolean oneCorrectSelected = false;
            boolean oneWrongSelected = false;
            boolean allSelecteds = true;
            
            for (int j = 0; j < selectedOptions.size(); j++) {
                if (selectedOptions.get(j)) {
                    if (presentController.isCorrect(i, j)) {
                        oneCorrectSelected = true;
                    } else {
                        oneWrongSelected = true;
                    }
                } else {
                    allSelecteds = false;
                    if (presentController.isCorrect(i, j)) {
                        allCorrectSelected = false;
                    }
                }
            }

            if (allSelecteds || !oneCorrectSelected || numCorrectAnswers < numIncorrectAnswers) {
                menuPanel.getQuestionListItems().get(i).setIcons("Wrong_Unselected_Icon", "Wrong_Selected_Icon");
            } else if (allCorrectSelected && !oneWrongSelected) {
                menuPanel.getQuestionListItems().get(i).setIcons("Correct_Unselected_Icon", "Correct_Selected_Icon");
            }
        }
    }

    protected void actionEventInBottomLeftButton(ActionEvent e) {
        if(e.getSource() == bottomLeftButton) {
            ExamsView.instance().paintNavBar();
            Frame.instance().setView(ExamsView.instance());
            Frame.instance().setTitle("ExamsView");
        } 
    }

    public ResultsBlock getResults() {
        return results;
    }
}
