package main.views.pages;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import main.data.Palette;
import main.views.components.ExamMenu;
import main.views.components.NavBar;
import main.views.templates.Frame;

// import main.views.pages.ExamsView;
// import main.views.components.QuestionNumber;

// import javax.swing.JLabel;
// import javax.swing.JList;
// import javax.swing.JPanel;

// import main.views.components.IconButton;
// import main.views.components.SingleOptionButton;

public class ExamView extends Frame {
    // private SingleOptionButton option;
    // private IconButton nextButton;
    // private IconButton prevButton;
    // private IconButton sendButton;
    // private JLabel questionTitle;
    // private JList questionsList;
    // private QuestionNumber questionNumber;
    // private JPanel cronometer;
    NavBar navBar;
    JPanel contentPanel;

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
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        contentPanel.setPreferredSize(new Dimension(944, 560));
        contentPanel.setBackground(Palette.instance().getLightGray());

        paintMenuPanel();

        paintExamPanel();

        this.add(contentPanel, BorderLayout.CENTER);
    }

    private void paintMenuPanel() {
        ExamMenu menuPanel = new ExamMenu();

        contentPanel.add(menuPanel);
    }

    private void paintExamPanel() {
        JPanel examPanel = new JPanel();
        examPanel.setPreferredSize(new Dimension(544, 560));
        examPanel.setBackground(Palette.instance().getWhite());

        contentPanel.add(examPanel);
    }

    public void showQuestions(){}
    public void showInstructions(){}
    public void endExam(){} 
}
