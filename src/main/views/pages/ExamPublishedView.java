package main.views.pages;

import main.views.components.Message;

import java.awt.event.ActionEvent;

import main.controllers.CreateExamController;

public class ExamPublishedView extends ExamTemplateView{
    private Message message;
    private CreateExamController createExamController;

    ExamPublishedView(CreateExamController createExamController) {
        this.createExamController = createExamController;
        
    }

    @Override
    protected void paintMenuPanel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'paintMenuPanel'");
    }

    @Override
    protected void inicializeQuestions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inicializeQuestions'");
    }

    @Override
    protected void actionEventInBottomLeftButton(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionEventInBottomLeftButton'");
    }

}
