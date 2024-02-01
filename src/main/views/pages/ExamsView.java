package main.views.pages;
import main.views.components.NavBar;
import main.views.components.Slider;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import main.data.Palette;
import main.views.components.HelpBar;
import main.views.templates.Frame;

public class ExamsView extends Frame implements ActionListener{
    NavBar navBar;
    Slider slider;
    HelpBar helpBar;
    List<JButton> presentExamButtons;

    public ExamsView() {
        buildFrame();
        
        paintBorders();

        paintContentPanel();
        
        this.pack();
        addActionListener();
    }

    protected void buildFrame() {
        createFrame("ExamsView");
        this.setLayout(new BorderLayout());
    }

    protected void paintNavBar() {
        navBar = new NavBar();
        this.add(navBar, BorderLayout.NORTH);
    }

    protected void paintBorders() {
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

    protected void paintContentPanel(){
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(944, 560));
        contentPanel.setBackground(Palette.instance().getWhite());

        paintTitlePanel(contentPanel);
        paintSlider(contentPanel);

        this.add(contentPanel, BorderLayout.CENTER);

    }

    protected void paintTitlePanel(JPanel contentPanel) {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Palette.instance().getWhite());
        titlePanel.setPreferredSize(new Dimension(944, 60));
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        contentPanel.add(titlePanel, BorderLayout.NORTH);
        
        paintTitleLabel(titlePanel);
        paintTitleSeparator(titlePanel);  
    }

    protected void paintTitleLabel(JPanel titlePanel) {
        JLabel title = new JLabel();
        title.setText("Mis examenes");
        title.setFont(new Font("Nunito Sans", Font.BOLD, 25));
        title.setPreferredSize(new Dimension(944, 58));
        title.setHorizontalAlignment(JLabel.LEFT);
        title.setVerticalAlignment(JLabel.BOTTOM);

        titlePanel.add(title);
    }

    protected void paintTitleSeparator(JPanel titlePanel) {
        JSeparator line = new JSeparator();
        line.setForeground(Palette.instance().getLightGray());
        line.setBackground(Palette.instance().getLightGray());
        titlePanel.add(line);
    }

    private void paintSlider(JPanel contentPanel) {
        slider = new Slider();
        contentPanel.add(slider, BorderLayout.CENTER);
    }
    
    private void addActionListener() {
        navBar.getCertifycateButton().addActionListener(this);

        presentExamButtons = slider.getButtons();

        for (int i = 0; i < presentExamButtons.size(); i++) {
            presentExamButtons.get(i).addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(false);
        if (e.getSource() == navBar.getCertifycateButton()) {
            new CertificateView(this);
        } else {
            new ExamView(this);
        }
    }
}