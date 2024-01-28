package main.views.pages;

import main.views.pages.ExamsView;
import main.views.components.QuestionNumber;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import main.views.components.IconButton;
import main.views.components.SingleOptionButton;

public class ExamView {
    private SingleOptionButton option;
    private IconButton nextButton;
    private IconButton prevButton;
    private IconButton sendButton;
    private JLabel questionTitle;
    private JList questionsList;
    private QuestionNumber questionNumber;
    private JPanel cronometer;

    public void showQuestions(){}
    public void showInstructions(){}
    public void endExam(){} 
}
