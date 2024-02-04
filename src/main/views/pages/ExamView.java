package main.views.pages;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import main.controllers.PresentExamController;
import main.views.components.ExamMenu;
import main.views.components.QuestionPanel;
import main.views.components.TimerBlock;

public class ExamView extends ExamTemplateView {
    PresentExamController presentController;
    String[] examID;

    public ExamView(PresentExamController presentExamController, List<String> examID) {
        questions = new ArrayList<QuestionPanel>();
        index = 0;
        this.presentController = presentExamController;
        this.examID = new String[]{examID.get(0), examID.get(1)};
        inicializeQuestions();

        buildFrame("ExamView");
        paintBorders();
        paintContentPanel();

        addActionListener();
    }

    protected void paintMenuPanel() {
        TimerBlock timer = new TimerBlock(presentController.getDuracion(), finishExamButton);
        menuPanel = new ExamMenu(timer, questions.size());
        menuPanel.setCurrentQuestion(index);

        contentPanel.add(menuPanel, menuPanelConstraints());
    }

    protected void inicializeQuestions() {
        List<String> questionsString= presentController.getQuestionsStrings();
        List<String> domain = presentController.getDomain();
        List<Boolean> hasCode = presentController.getHasCode();
        List<List<String>> code = presentController.getCode();
        List<List<String>> options = presentController.getOptions();

        QuestionPanel question;

        for (int i = 0; i < questionsString.size(); i++){
            question = new QuestionPanel();
            question.paintDomainPanel(domain.get(i));
            question.paintQuestion(questionsString.get(i));
            if (hasCode.get(i)) {
                question.paintCodeField(code.get(i));
            }
            question.paintOptionsPanel(options.get(i));
            questions.add(question);
        }
    }

    private void setAnsweredQuestion() {
        ImageIcon unansweredIcon = new ImageIcon("src/assets/Unanswered_Icon.png");
        boolean unanswered = !(menuPanel.getQuestionListItems().get(index).getIcon() == unansweredIcon);
        
        if (questions.get(index).isAnswered() && unanswered) {
            menuPanel.getQuestionListItems().get(index).setIcons("Answered_Unselected_Icon", "Answered_Selected_Icon");
        }
    }

    protected void actionEventInExamMenu(ActionEvent e) {
        setAnsweredQuestion();

        for(int i = 0; i < menuPanel.getQuestionListItems().size(); i++) {
            if (e.getSource() == menuPanel.getQuestionListItems().get(i)) {
                menuPanel.setCurrentQuestion(i);
                showConcreteQuestion(i);
                break;
            }
        }
    }
    
    protected void showPreviousQuestions(){
        if ((index - 1) >= 0) {
            setAnsweredQuestion();
            questions.get(index).setVisible(false);
            index = index - 1;
            menuPanel.setCurrentQuestion(index);
            questions.get(index).setVisible(true);
        }
    }

    protected void showNextQuestion() {
        if ((index + 1) < questions.size()) {
            setAnsweredQuestion();
            questions.get(index).setVisible(false);
            index = index + 1;
            menuPanel.setCurrentQuestion(index);
            questions.get(index).setVisible(true);
        }
    }  
    protected void actionEventInBottomLeftButton(ActionEvent e) {
        if(e.getSource() == finishExamButton) {
            Frame.instance().setView(new ExamEndedView(questions, presentController));
            Frame.instance().setTitle("ExamEndedView");
        } 
    } 
  
    public void showInstructions(){}
    public void endExam(){}
}
