package main.views.pages;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import main.views.components.ExamMenu;
import main.views.components.IconButton;
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

        JPanel upperPanel = new JPanel();
        upperPanel.setBackground(Palette.instance().getWhite());
        upperPanel.add(publishButton);

        menuPanel = new ExamMenu(upperPanel, questions.size());
        menuPanel.setCurrentQuestion(index);

        contentPanel.add(menuPanel, menuPanelConstraints());
    }

    @Override
    protected void inicializeQuestions() {
        NewQuestionPanel question = new NewQuestionPanel();

        questions.add(question);
    }

    @Override
    protected void actionEventInBottomLeftButton(ActionEvent e) {
        if (e.getSource() == bottomLeftButton) {  
            questions.add(new NewQuestionPanel());
            menuPanel.repaintQuestionsList(questions.size());
            menuPanel.setCurrentQuestion(index);
            paintQuestionPanel(index);
            addActionListener();
            this.validate();
        }
    }
}
