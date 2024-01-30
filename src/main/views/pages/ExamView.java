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

import main.data.Palette; 
import main.views.components.ExamMenu;
import main.views.components.NavBar;
import main.views.components.QuestionPanel;
import main.views.templates.Frame;

public class ExamView extends Frame implements ActionListener {
    JPanel contentPanel;
    JButton finishExamButton;
    JButton prevButton;
    JButton nextButton;
    List<QuestionPanel> questions;
    int index;

    public ExamView() {
        questions = new ArrayList<QuestionPanel>();
        index = 0;
<<<<<<< HEAD
        inicializateQuestions();
=======
        inicializeQuestions();
>>>>>>> main

        buildFrame();
        paintBorders();
        paintContentPanel();

        this.pack();
    }

    private void buildFrame() {
        createFrame("ExamView");
        this.setLayout(new BorderLayout());
    }

    private void paintNavBar() {
        NavBar navBar = new NavBar();
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
        contentPanel.setPreferredSize(new Dimension(944, 560));
        contentPanel.setBackground(Palette.instance().getWhite());

        paintMenuPanel();

        paintQuestionPanel(index);

        paintBottomButtonPanel();

        this.add(contentPanel, BorderLayout.CENTER);
    }

    private void paintMenuPanel() {
        ExamMenu menuPanel = new ExamMenu();
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
        paintButtonPanel();
    }

    private void paintFinishExamButton() {
        finishExamButton = new JButton("Terminar Examen");
        finishExamButton.setFont(new Font("Nunito Sans", Font.BOLD, 15));
        finishExamButton.setForeground(Palette.instance().getWhite());
        finishExamButton.setBackground(Palette.instance().getBlue());
        finishExamButton.setPreferredSize(new Dimension(190, 30));
        finishExamButton.setFocusable(false);
        finishExamButton.addActionListener(this);

        Border border = BorderFactory.createLineBorder(Palette.instance().getBlue());
        finishExamButton.setBorder(border);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        // constraints.weighty = 0.5;

        contentPanel.add(finishExamButton, constraints);
    }
    
    private void paintButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        buttonPanel.setBackground(Palette.instance().getWhite());
        // buttonPanel.setPreferredSize(new Dimension(1024, 200));

        prevButton = new JButton("Anterior");
        paintButton(prevButton);
        buttonPanel.add(prevButton);

        nextButton = new JButton("Siguiente");
        paintButton(nextButton);
        buttonPanel.add(nextButton);


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weighty = 0;
        // constraints.gridwidth = 4;
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

<<<<<<< HEAD
    private void inicializateQuestions() {
        questions.add(new QuestionPanel("¿Cuál es el resultado de este código?"));
        questions.add(new QuestionPanel("¿Cuál no es el resultado de este código?"));
        questions.add(new QuestionPanel("¿Cuál tu cara?"));
        questions.add(new QuestionPanel("¿Quien te preguntó?"));
=======
    private void inicializeQuestions() {
        // este metodo es de prueba. Terrible lo se
        // lo que esté entre comentarios no va btw


        List<String> questionsString = new ArrayList<String>();
        // prueba {
        questionsString.add("¿Cuál es el resultado de este código?");
        questionsString.add("¿Cuál no es el resultado de este código?");
        questionsString.add("¿Cuál tu cara?");
        questionsString.add("¿Quien te preguntó?");
        // } prueba

        List<String> domain = new ArrayList<String>();
        // prueba {
        domain.add("Trabajar con tipos de datos Java");
        domain.add("Sufrir Java Swing");
        domain.add("Por favor funciona");
        domain.add("Me voy a pegar un tiro");
        // } prueba

        List<Boolean> hasCode = new ArrayList<Boolean>();
        // prueba {
        hasCode.add(true);
        hasCode.add(true);
        hasCode.add(true);
        hasCode.add(false);
        // } prueba

        List<List<String>> code = new ArrayList<List<String>>();
        // prueba {
        code.add(new ArrayList<String>());
        code.get(0).add("var i = 1234;");
        code.get(0).add("var i = 1234;");
        code.get(0).add("var i = 1234;");

        code.add(new ArrayList<String>());
        code.get(1).add("var s = \" \" + i; ");
        code.get(1).add("var p = \" \" + i; ");
        code.get(1).add("var j = \" \" + i; ");
        code.get(1).add("var k = \" \" + i; ");

        code.add(new ArrayList<String>());
        code.get(2).add("if (\"1234\".equals(s)) ");
        // } prueba

        List<List<String>> options = new ArrayList<List<String>>();
        // prueba {
        options.add(new ArrayList<String>());
        options.get(0).add("No compila");
        options.get(0).add("Se ve feo");
        options.get(0).add("El bit dirty");
        options.get(0).add("Que?");
        options.get(0).add("so");

        
        options.add(new ArrayList<String>());
        options.get(1).add("When I was");
        options.get(1).add("A young boy");
        options.get(1).add("My father");
        options.get(1).add("Took me into the city");
        options.get(1).add("To see a marching band");

        
        options.add(new ArrayList<String>());
        options.get(2).add("One thing");
        options.get(2).add("I don't know why");
        options.get(2).add("It doesn't even matter how hard you try");
        options.get(2).add("Keep that in mind");
        
        options.add(new ArrayList<String>());
        options.get(3).add("Connection terminated");
        options.get(3).add("I'm sorry to interrupt you, Elizabeth");
        options.get(3).add("If you still even remember that name");
        options.get(3).add("But I'm afraid you've been misinformed.");
        options.get(3).add("You are not here to receive a gift,");
        options.get(3).add("nor have you been called here by the individual you assume,");
        options.get(3).add("although, you have indeed been called.");
        // } prueba

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
>>>>>>> main
    }

    public void showPreviousQuestions(){
        if ((index - 1) >= 0) {
            questions.get(index).setVisible(false);
            index = index - 1;
<<<<<<< HEAD
            // paintQuestionPanel(index);
=======
>>>>>>> main
            questions.get(index).setVisible(true);
        }
    }

    private void showNextQuestion() {
        if ((index + 1) < questions.size()) {
            questions.get(index).setVisible(false);
            index = index + 1;
<<<<<<< HEAD
            // paintQuestionPanel(index);
=======
>>>>>>> main
            questions.get(index).setVisible(true);
        }
    }   

    public void showInstructions(){}
    public void endExam(){} 

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == finishExamButton) {
            System.out.println("This should show ResultView");

        } else if (e.getSource() == prevButton) {
<<<<<<< HEAD
            System.out.println("This should change question");
            showPreviousQuestions();

        } else if (e.getSource() == nextButton) {
            System.out.println("This should change question");
            showNextQuestion();
            // paintQuestionPanel(index, "Next Question");
=======
            System.out.println("This should xhow next question");
            showPreviousQuestions();

        } else if (e.getSource() == nextButton) {
            System.out.println("This should show next question");
            showNextQuestion();
>>>>>>> main
        }
    }

}
