package main.views.pages;
import main.views.components.Slider;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import main.utils.Palette;

public class ExamsView extends HelpBarTemplateView {
    private static ExamsView examView;
    
    Slider slider;
    List<JButton> presentExamButtons;

    public static ExamsView instance() {
		if (examView == null){
			examView = new ExamsView();
		}
		return examView;
	}

    public static void deleteInstance() {
        examView = null;
    }

    public ExamsView() {
        buildFrame("ExamsView");
        
        paintBorders();

        paintContentPanel();
        
        addActionListener();
        this.validate();
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
        addActionListenerNavbar();

        presentExamButtons = slider.getButtons();

        for (int i = 0; i < presentExamButtons.size(); i++) {
            presentExamButtons.get(i).addActionListener(this);
        }
    }

    private void actionEventInCourseCard(ActionEvent e) {
        for (int i = 0; i < presentExamButtons.size(); i++) {
            if (e.getSource() == presentExamButtons.get(i)) {
                Frame.instance().setView(new ExamView());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actionEventInNavBar(e);
        actionEventInCourseCard(e);
    }
}