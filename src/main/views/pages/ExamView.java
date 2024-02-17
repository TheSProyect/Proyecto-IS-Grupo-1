package main.views.pages;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import main.controllers.PresentExamController;
import main.utils.Size;
import main.views.components.ExamMenu;
import main.views.components.PopUp;
import main.views.components.QuestionPanel;
import main.views.components.TimerBlock;

public class ExamView extends ExamTemplateView {
    PresentExamController presentController;
    String[] examID;
    ExamEndedPopup popup;
    boolean actionEnable;

    public ExamView(PresentExamController presentExamController, String[] examID) {
        questions = new ArrayList<QuestionPanel>();
        index = 0;
        this.presentController = presentExamController;
        this.examID = examID;
        actionEnable = true;

        inicializeQuestions();

        buildFrame("ExamView");
        paintBorders();
        paintContentPanel();

        addActionListener();
    }

    protected void paintMenuPanel() {
        TimerBlock timer = new TimerBlock(presentController.getDuracion(), bottomLeftButton);
        menuPanel = new ExamMenu(timer, questions.size());
        menuPanel.setCurrentQuestion(index);

        contentPanel.add(menuPanel, menuPanelConstraints());
    }

    protected void inicializeQuestions() {
        presentController.searchFolder(examID);
        List<String> questionsString= presentController.getQuestionsStrings();
        List<String> domain = presentController.getDomain();
        List<Boolean> hasCode = presentController.getHasCode();
        List<List<String>> code = presentController.getCode();
        List<String> image = presentController.directoryImage();
        List<List<String>> options = presentController.getOptions();

        QuestionPanel question;

        for (int i = 0; i < questionsString.size(); i++){
            question = new QuestionPanel();
            question.paintDomainPanel(domain.get(i));
            question.paintQuestion(questionsString.get(i));
            if (hasCode.get(i)) {
                question.paintCodeField(code.get(i));
            }

            if (image.get(i) != null) {
                question.paintImage(image.get(i));
            }
            boolean isSimpleOption = presentController.getNumCorrectAnswersController(i) == 1;
            question.paintOptionsPanel(options.get(i), isSimpleOption);
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

    private List<List<Boolean>> getSelectedOptions() {
        List<List<Boolean>> selectedOptions = new ArrayList<List<Boolean>>();

        for (QuestionPanel question : questions) {
            selectedOptions.add(question.getSelectedOption());
        }

        return selectedOptions;
    }

    private void disableEvents() {
        actionEnable = false;
        for(QuestionPanel question : questions) {
            question.disableOptions();
        }
    }

    protected void actionEventInBottomLeftButton(ActionEvent e) {
        if(e.getSource() == bottomLeftButton) {
            disableEvents();
            
            this.menuPanel.getBlock().StopTimer();

            float numCorrectQuestions = presentController.caculateResult(getSelectedOptions());
            popup = new ExamEndedPopup(numCorrectQuestions, questions.size());
            PopUp.instance(Size.instance().getExamEndedPopUpDimension()).setView(popup);

            popup.getButton().addActionListener(this);
        } 
    } 

    private void actionEventInPopUp(ActionEvent e) {
        if (popup == null){
            return;
        } else if (e.getSource() == popup.getButton()) {
            PopUp.deleteInstance();
            Frame.instance().setView(new ExamEndedView(questions, presentController));
            Frame.instance().setTitle("ExamEndedView");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!actionEnable) {
            actionEventInPopUp(e);

        } else {
            defaultActionEvents(e);
        }
    }
}
