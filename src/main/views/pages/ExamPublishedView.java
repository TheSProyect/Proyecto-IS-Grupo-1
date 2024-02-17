package main.views.pages;

import main.views.components.ExamMenu;
import main.views.components.Message;
import main.views.components.PopUp;
import main.views.components.QuestionPanel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import main.controllers.CreateExamController;
import main.utils.Palette;
import main.utils.Size;

public class ExamPublishedView extends ExamTemplateView{
    private Message message;
    CreateExamController createExamController;
    ExamPublishedPopUp popup;

    public ExamPublishedView(CreateExamController _createExamController) {
        questions = new ArrayList<QuestionPanel>();
        index = 0;
        
        createExamController = _createExamController;
        inicializeQuestions();

        buildFrame("ExamView");
        paintBorders();
        paintContentPanel();

        addActionListener();
    }
    
    protected void paintMenuPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Palette.instance().getWhite());
        menuPanel = new ExamMenu(panel, questions.size());
        menuPanel.setCurrentQuestion(index);

        contentPanel.add(menuPanel, menuPanelConstraints());
    }

    protected void inicializeQuestions() {
        createExamController.showExam();
        List<String> questionsString = createExamController.getQuestionsStrings();
        List<String> domain = createExamController.getDomain();
        List<Boolean> hasCode = createExamController.getHasCode();
        List<List<String>> code = createExamController.getCode();
        List<String> image = createExamController.getDirectoryImage();
        List<List<String>> options = createExamController.getOptions();

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
            boolean isSimpleOption = createExamController.getNumCorrectAnswersController(i) == 1;
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

    protected void actionEventInBottomLeftButton(ActionEvent e) {
        if(e.getSource() == bottomLeftButton) {
            popup = new ExamPublishedPopUp();
            
            PopUp.instance(Size.instance().getExamEndedPopUpDimension()).setView(popup);

            popup.getButton().addActionListener(this);
        } 
    }

    private void actionEventInPopUp(ActionEvent e) {
        if (popup == null){
            return;
        } else if (e.getSource() == popup.getButton()) {
            PopUp.deleteInstance();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == prevButton) {
            showPreviousQuestions();

        } else if (e.getSource() == nextButton) {
            showNextQuestion();

        } else {
            actionEventInBottomLeftButton(e);
            actionEventInExamMenu(e);
            actionEventInPopUp(e);
        }
    }
}
