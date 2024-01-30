package main.views.pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import main.data.Palette;
// import main.models.Questions;
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

    public ExamView() {
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
        contentPanel.setPreferredSize(new Dimension(944, 560));
        contentPanel.setBackground(Palette.instance().getWhite());

        paintMenuPanel();

        paintExamPanel();

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

    private void paintExamPanel() {
        QuestionPanel examPanel = new QuestionPanel();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.BOTH;
        
        contentPanel.add(examPanel, constraints);
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

        Border border = BorderFactory.createLineBorder(Palette.instance().getBlue());
        button.setBorder(border);
    }

    public void showQuestions(){}
    public void showInstructions(){}
    public void endExam(){} 

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == finishExamButton) {
            System.out.println("This should show ResultView");
        }
    }

}
