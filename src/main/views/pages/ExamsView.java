package main.views.pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.data.Palette;
import main.views.components.HelpBar;
import main.views.components.NavBar;
import main.views.components.NavBarButton;
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

        paintNavBar();
        paintContentPanel();
    }

    protected void paintNavBar() {
        navBar = new NavBar();
        this.add(navBar, BorderLayout.NORTH);
    }

    private void paintContentPanel(){
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(1024, 640));
        contentPanel.setBackground(Palette.instance().getWhite());

        JPanel borderPanel = new JPanel();
        borderPanel.setPreferredSize(new Dimension(40, 640));
        borderPanel.setBackground(Palette.instance().getWhite());
        this.add(borderPanel, BorderLayout.WEST);

        borderPanel = new JPanel();
        borderPanel.setPreferredSize(new Dimension(40, 640));
        borderPanel.setBackground(Palette.instance().getWhite());
        this.add(borderPanel, BorderLayout.EAST);

        helpBar = new HelpBar();
        this.add(helpBar, BorderLayout.SOUTH);

        paintTitlePanel();
        paintSlider();

        

        this.add(contentPanel, BorderLayout.CENTER);

    }
    
    private void paintTitlePanel() {
        titlePanel = new JPanel();
        titlePanel.setBackground(Palette.instance().getWhite());
        titlePanel.setPreferredSize(new Dimension(984, 60));
        
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        title = new JLabel();
        title.setText("Mis examenes");
        title.setFont(new Font("Nunito Sans", Font.BOLD, 25));
        title.setPreferredSize(new Dimension(1024, 58));
        title.setHorizontalAlignment(JLabel.LEFT);
        title.setVerticalAlignment(JLabel.BOTTOM);
        // title.setBackground(Palette.instance().getGray());
        // title.setOpaque(true);

        

        JPanel line = new JPanel();
        line.setBackground(Palette.instance().getLightGray());
        line.setPreferredSize(new Dimension(1024, 2));

        titlePanel.add(title);
        titlePanel.add(line);

        contentPanel.add(titlePanel, BorderLayout.NORTH);
    }

    private void paintSlider() {
        slider = new Slider();
        contentPanel.add(slider, BorderLayout.CENTER);
    }
}