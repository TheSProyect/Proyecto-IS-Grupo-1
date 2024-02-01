package main.views.pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import main.controllers.PresentExamController;
import main.data.Palette; 
import main.views.components.ExamMenu;
import main.views.components.NavBar;
import main.views.components.QuestionPanel;
import main.views.templates.Frame;

public class ExamView extends Frame implements ActionListener {
    NavBar navBar;
    JPanel contentPanel;
    JButton finishExamButton;
    JButton prevButton;
    JButton nextButton;
    List<QuestionPanel> questions;
    int index;
    PresentExamController presentController;

    public ExamView() {
        questions = new ArrayList<QuestionPanel>();
        index = 0;
        presentController= new PresentExamController();
        inicializeQuestions();

        buildFrame();
        paintBorders();
        paintContentPanel();

        this.pack();
        addActionListener();
    }

    private void buildFrame() {
        createFrame("ExamView");
        this.setLayout(new BorderLayout());
    }

    private void paintNavBar() {
        navBar = new NavBar();
        this.add(navBar, BorderLayout.NORTH);
    }

    private void paintBorders() {
        paintNavBar();

        JPanel borderPanel = new JPanel();
        borderPanel.setPreferredSize(new Dimension(40, 560));
        borderPanel.setBackground(Palette.instance().getWhite());
        this.add(borderPanel, BorderLayout.WEST);

        borderPanel = new JPanel();
        borderPanel.setPreferredSize(new Dimension(40, 560));
        borderPanel.setBackground(Palette.instance().getWhite());
        this.add(borderPanel, BorderLayout.EAST);
    }

    private void paintContentPanel() {
        contentPanel = new JPanel();
        // contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setPreferredSize(new Dimension(944, 640));
        contentPanel.setBackground(Palette.instance().getWhite());

        

        paintQuestionPanel(index);

        paintBottomButtonPanel();
        
        paintMenuPanel();

        this.add(contentPanel, BorderLayout.CENTER);
    }

    private void paintMenuPanel() {
        ExamMenu menuPanel = new ExamMenu(presentController.getDuracion(), finishExamButton);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.BOTH;

        contentPanel.add(menuPanel, constraints);
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
    
    private void paintBottomButtonPanel() {
        paintFinishExamButton();
        paintQuestionButtonPanel();
    }

    private void paintFinishExamButton() {
        JPanel finishExamPanel = new JPanel();
        finishExamPanel.setBackground(Palette.instance().getWhite());
        finishExamPanel.setMaximumSize(new Dimension(300, 80));


        finishExamButton = new JButton("Terminar Examen");
        finishExamButton.setFont(new Font("Nunito Sans", Font.BOLD, 15));
        finishExamButton.setForeground(Palette.instance().getWhite());
        finishExamButton.setBackground(Palette.instance().getBlue());
        finishExamButton.setPreferredSize(new Dimension(190, 30));
        finishExamButton.setFocusable(false);
        finishExamButton.addActionListener(this);

        Border border = BorderFactory.createLineBorder(Palette.instance().getBlue());
        finishExamButton.setBorder(border);

        finishExamPanel.add(finishExamButton);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        // constraints.weightx = 1.0;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.BOTH;

        contentPanel.add(finishExamPanel, constraints);
    }
    
    private void paintQuestionButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        buttonPanel.setBackground(Palette.instance().getWhite());
        // buttonPanel.setPreferredSize(new Dimension(1024, 80));

        prevButton = new JButton("Anterior");
        paintButton(prevButton);
        buttonPanel.add(prevButton);

        nextButton = new JButton("Siguiente");
        paintButton(nextButton);
        buttonPanel.add(nextButton);


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.BOTH;

        contentPanel.add(buttonPanel, constraints);
    }

    private void paintButton(JButton button) {
        button.setForeground(Palette.instance().getWhite());
        button.setBackground(Palette.instance().getBlue());
        button.setFont(new Font("Nunito Sans", Font.BOLD, 15));
        button.setPreferredSize(new Dimension(130, 30));
        button.setFocusable(false);
        button.addActionListener(this);

        Border border = BorderFactory.createLineBorder(Palette.instance().getBlue());
        button.setBorder(border);
    }

    private void inicializeQuestions() {
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

    public void showPreviousQuestions(){
        if ((index - 1) >= 0) {
            questions.get(index).setVisible(false);
            index = index - 1;
            questions.get(index).setVisible(true);
        }
    }

    private void showNextQuestion() {
        if ((index + 1) < questions.size()) {
            questions.get(index).setVisible(false);
            index = index + 1;
            questions.get(index).setVisible(true);
        }
    }   

    private void addActionListener() {
        navBar.getHomeButton().addActionListener(this);
        navBar.getCertificateButton().addActionListener(this);
    }

    public void showInstructions(){}
    public void endExam(){} 

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == finishExamButton) {
            this.dispose();
            ExamsView.instance().setVisible(true);

        } else if (e.getSource() == prevButton) {
            showPreviousQuestions();

        } else if (e.getSource() == nextButton) {
            showNextQuestion();

        } else if (e.getSource() == navBar.getHomeButton()) {
            this.dispose();
            ExamsView.instance().setVisible(true);

        } else if (e.getSource() == navBar.getCertificateButton()) {
            this.dispose();
            CertificatesView.instance().setVisible(true);

        }
    }

}
