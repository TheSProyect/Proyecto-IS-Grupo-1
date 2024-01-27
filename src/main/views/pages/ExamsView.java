package main.views.pages;
import main.views.components.CourseCard;
import main.views.components.NavBar;
import main.views.components.Slider;
import main.views.pages.AdminExamView;
import main.views.pages.ExamView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import main.data.Palette;
import main.views.components.HelpBar;
import main.views.components.NavBar;
import main.views.components.Slider;

public class ExamsView extends JFrame {
    JPanel contentPanel;
    JPanel titlePanel;
    JLabel title;
    JLabel examTitle;
    NavBar navBar;
    Slider slider;
    HelpBar helpBar;

    public ExamsView() {
        this.setVisible(true);
        this.setTitle("ExamsView");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(1024, 720));
        this.setSize(1024, 720);
        this.setLayout(new BorderLayout());

        paintBorders();

        paintContentPanel();
    }

    protected void paintNavBar() {
        navBar = new NavBar();
        this.add(navBar, BorderLayout.NORTH);
    }

    private void paintBorders() {
        paintNavBar();
        
        helpBar = new HelpBar();
        this.add(helpBar, BorderLayout.SOUTH);

        JPanel borderPanel = new JPanel();
        borderPanel.setPreferredSize(new Dimension(40, 560));
        borderPanel.setBackground(Palette.instance().getWhite());
        this.add(borderPanel, BorderLayout.WEST);

        borderPanel = new JPanel();
        borderPanel.setPreferredSize(new Dimension(40, 560));
        borderPanel.setBackground(Palette.instance().getWhite());
        this.add(borderPanel, BorderLayout.EAST);
    }

    private void paintContentPanel(){
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(944, 560));
        contentPanel.setBackground(Palette.instance().getWhite());

        paintTitlePanel();
        paintSlider();

        this.add(contentPanel, BorderLayout.CENTER);

    }
    
    private void paintTitlePanel() {
        titlePanel = new JPanel();
        titlePanel.setBackground(Palette.instance().getWhite());
        titlePanel.setPreferredSize(new Dimension(944, 60));
        
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        title = new JLabel();
        title.setText("Mis examenes");
        title.setFont(new Font("Nunito Sans", Font.BOLD, 25));
        title.setPreferredSize(new Dimension(944, 58));
        title.setHorizontalAlignment(JLabel.LEFT);
        title.setVerticalAlignment(JLabel.BOTTOM);

        JSeparator line = new JSeparator();
        line.setForeground(Palette.instance().getLightGray());
        line.setBackground(Palette.instance().getLightGray());

        titlePanel.add(title);
        titlePanel.add(line);

        contentPanel.add(titlePanel, BorderLayout.NORTH);
    }

    private void paintSlider() {
        slider = new Slider();
        contentPanel.add(slider, BorderLayout.CENTER);
    }
}