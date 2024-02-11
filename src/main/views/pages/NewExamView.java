package main.views.pages;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import main.views.components.ExamMenu;
import main.views.components.IconButton;
import main.views.components.NewOptionsPanel;
import main.views.components.NewQuestionPanel;
import main.views.components.QuestionPanel;
import main.utils.Palette;


public class NewExamView extends ExamTemplateView {
    private IconButton publishButton;

    public NewExamView() {
        questions = new ArrayList<QuestionPanel>();
        index = 0;

        inicializeQuestions();

        buildFrame("ExamView");
        paintBorders();
        paintContentPanel();
        bottomLeftButton.setText("Añadir Pregunta");
        bottomLeftButton.paintIcon("Plus_Icon.png");

        addActionListener();
    }

    @Override
    protected void paintMenuPanel() {
        publishButton = new IconButton("Publicar Examen", "Exit_Exam_Icon.png");
        publishButton.setPreferredSize(new DimensionUIResource(190, 30));
        publishButton.addActionListener(this);

        JPanel upperPanel = new JPanel();
        upperPanel.setBackground(Palette.instance().getWhite());
        upperPanel.add(publishButton);

        menuPanel = new ExamMenu(upperPanel, questions.size());
        menuPanel.setCurrentQuestion(index);

        contentPanel.add(menuPanel, menuPanelConstraints());
    }

    @Override
    protected void inicializeQuestions() {
        NewQuestionPanel question = new NewQuestionPanel(this);

        questions.add(question);
    }

    @Override
    protected void actionEventInBottomLeftButton(ActionEvent e) {
        if (e.getSource() == bottomLeftButton) {  
            questions.add(new NewQuestionPanel(this));
            menuPanel.repaintQuestionsList(questions.size());
            menuPanel.setCurrentQuestion(index);
            paintQuestionPanel(index);
            addActionListener();
            this.validate();
        }
    }

    private void actionEventInDeleteButton(ActionEvent e) {
        NewQuestionPanel currentQuestionPanel = (NewQuestionPanel)questions.get(index);
        final int MINIMUM_NUM_QUESTIONS = 1;

        if (e.getSource() == currentQuestionPanel.getDeleteButton()) {
            boolean onlyQuestion = questions.size() <= MINIMUM_NUM_QUESTIONS;

            currentQuestionPanel.setVisible(false);
            this.remove(currentQuestionPanel);
            this.revalidate();

            questions.remove(index);

            if (onlyQuestion) {
                questions.add(new NewQuestionPanel(this));
            } else {
                if (index > 0) {
                    index = index - 1;
                }
                menuPanel.repaintQuestionsList(questions.size());
                menuPanel.setCurrentQuestion(index);
                addActionListener();
            }

            paintQuestionPanel(index);
            this.validate();
            this.repaint();
        }
    }

    private boolean checkQuestionsAreComplete() {
        for(QuestionPanel question : questions) {
            NewQuestionPanel newQuestion = (NewQuestionPanel)question;
            if (!newQuestion.checkQuestionIsComplete()) {
                return false;
            }
        }
        return true;
    }

    private void actionEventInPublishButton(ActionEvent e) {
        if (e.getSource() == publishButton) {
            if (!checkQuestionsAreComplete()) {
                System.out.println("Nop");
                return;
            }
            for(QuestionPanel question : questions) {
                NewQuestionPanel newQuestion = (NewQuestionPanel)question;
                //pass these to the controller 
                newQuestion.getQuestionText();
                newQuestion.getDomainText();
                newQuestion.getCode();
                newQuestion.getOptionsText();
                newQuestion.getExplication();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actionEventInBottomLeftButton(e);
        actionEventInDeleteButton(e);
        if (e.getSource() == prevButton) {
            showPreviousQuestions();

        } else if (e.getSource() == nextButton) {
            showNextQuestion();

        } else {
            actionEventInExamMenu(e);
            actionEventInPublishButton(e);
        }
    }
}
