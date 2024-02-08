package main.views.pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.utils.Palette;
import main.views.components.ExamMenu;
import main.views.components.IconButton;
import main.views.components.QuestionPanel;

public abstract class ExamTemplateView extends NavBarTemplateView {
    JPanel contentPanel;
    IconButton bottomLeftButton;
    IconButton prevButton;
    IconButton nextButton;
    List<QuestionPanel> questions;
    int index;
    ExamMenu menuPanel;

    protected void paintContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setPreferredSize(new Dimension(944, 640));
        contentPanel.setBackground(Palette.instance().getWhite());

        
        paintQuestionPanel(index);

        paintBottomButtonPanel();
        
        paintMenuPanel();

        this.add(contentPanel, BorderLayout.CENTER);
    }

    private void paintQuestionPanel(int questionIndex) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.BOTH;

        for (int i = 0; i < questions.size(); i++) {
            contentPanel.add(questions.get(i), constraints);
            questions.get(i).setVisible(false);
        }
        
        questions.get(index).setVisible(true);
    }

    protected abstract void paintMenuPanel();

    protected GridBagConstraints menuPanelConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.BOTH;

        return constraints;
    }
    
    private void paintBottomButtonPanel() {
        paintFinishExamButton();
        paintQuestionButtonPanel();
    }

    private void paintFinishExamButton() {
        JPanel finishExamPanel = new JPanel();
        finishExamPanel.setBackground(Palette.instance().getWhite());
        finishExamPanel.setMaximumSize(new Dimension(300, 80));


        bottomLeftButton = new IconButton("Terminar Examen", "Exit_Exam_Icon.png");
        bottomLeftButton.setPreferredSize(new Dimension(190, 30));
        bottomLeftButton.addActionListener(this);

        finishExamPanel.add(bottomLeftButton);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.BOTH;

        contentPanel.add(finishExamPanel, constraints);
    }
    
    private void paintQuestionButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        buttonPanel.setBackground(Palette.instance().getWhite());

        prevButton = new IconButton("Anterior", "Prev_Question_Icon.png");
        paintButton(prevButton);
        buttonPanel.add(prevButton);

        nextButton = new IconButton("Siguiente", "Next_Question_Icon.png");
        paintButton(nextButton);
        nextButton.setHorizontalTextPosition(JButton.LEFT);
        buttonPanel.add(nextButton);


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.BOTH;

        contentPanel.add(buttonPanel, constraints);
    }

    private void paintButton(JButton button) {
        button.setPreferredSize(new Dimension(130, 30));
        button.addActionListener(this);
    }

    protected abstract void inicializeQuestions();

    protected void showPreviousQuestions(){
        if ((index - 1) >= 0) {
            questions.get(index).setVisible(false);
            index = index - 1;
            menuPanel.setCurrentQuestion(index);
            questions.get(index).setVisible(true);
        }
    }

    protected void showNextQuestion() {
        if ((index + 1) < questions.size()) {
            questions.get(index).setVisible(false);
            index = index + 1;
            menuPanel.setCurrentQuestion(index);
            questions.get(index).setVisible(true);
        }
    }   

    protected void showConcreteQuestion(int questionNumber) {
        questions.get(index).setVisible(false);
        index = questionNumber;
        questions.get(index).setVisible(true);
    }

    protected void addActionListener() {
        for(int i = 0; i < menuPanel.getQuestionListItems().size(); i++) {
            menuPanel.getQuestionListItems().get(i).addActionListener(this);
        }
    }

    protected void actionEventInExamMenu(ActionEvent e) {
        for(int i = 0; i < menuPanel.getQuestionListItems().size(); i++) {
            if (e.getSource() == menuPanel.getQuestionListItems().get(i)) {
                menuPanel.setCurrentQuestion(i);
                showConcreteQuestion(i);
                break;
            }
        }
    }

    protected abstract void actionEventInBottomLeftButton(ActionEvent e);

    @Override
    public void actionPerformed(ActionEvent e) {
        actionEventInBottomLeftButton(e);
        if (e.getSource() == prevButton) {
            showPreviousQuestions();

        } else if (e.getSource() == nextButton) {
            showNextQuestion();

        } else {
            actionEventInExamMenu(e);
        }
    }
}
