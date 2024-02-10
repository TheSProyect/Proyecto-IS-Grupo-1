package main.views.pages;

import main.views.components.TextField;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import com.itextpdf.awt.geom.Dimension;

import main.views.components.AdminNavBar;
import main.views.components.CodeField;
import main.views.components.ExamMenu;
import main.views.components.QuestionsList;
import main.views.components.IconButton;
import main.views.components.NewQuestionPanel;
import main.views.components.QuestionPanel;
import main.views.pages.AdminExamView;
import main.views.pages.ExamPublishedView;
import main.controllers.CreateExamController;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionEventInBottomLeftButton'");
    }
    // private JLabel labels;
    // private TextField textField;
    // private CodeField codeField;
    // private QuestionsList questionNumber;
    // private JList questionsList;
    // private IconButton nexButton;
    // private IconButton prevButton;
    // private IconButton addQuestionButton;

    // private void setName(String Name){}
    // private void setInstructions(String Instructions){}
    
}
