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

        results.paintResults(showScore(), questions.size());
        
        contentPanel.add(menuPanel, menuPanelConstraints());
    }

    protected void inicializeQuestions() {
        List<List<String>> explication = presentController.getJustification();
        
        for (int i = 0; i < questions.size(); i++) {
            questions.get(i).setExplicationButtonVisible();
            questions.get(i).paintExplicationPanel(explication.get(i));
        }
    }

    private float showScore(){
        float numCorrectQuestions = 0;

        for (int i = 0; i < questions.size(); i++) {
            List<Boolean> selectedOptions = questions.get(i).getSelectedOption();
            for (int j = 0; j < selectedOptions.size(); j++) {
                if (selectedOptions.get(j) && presentController.isCorrect(i, j)) {
                    // numCorrectQuestions++;
                    menuPanel.getQuestionListItems().get(i).setIcons("Correct_Unselected_Icon", "Correct_Selected_Icon");
                } else {
                    menuPanel.getQuestionListItems().get(i).setIcons("Wrong_Unselected_Icon", "Wrong_Selected_Icon");
                }
            }
            // numCorrectQuestions = numCorrectQuestions+ presentController.computeResultQuestion(i,numCorrectQuestions);
        }
        presentController.setResultExamC(numCorrectQuestions);
        presentController.examFinished();
        return numCorrectQuestions;
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
