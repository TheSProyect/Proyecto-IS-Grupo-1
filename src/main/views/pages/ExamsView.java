package main.views.pages;
import main.views.components.Slider;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
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
    List<String> examsNames;
    List<String> courseName;

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
        paintCourseList();

        contentPanel.add(slider, BorderLayout.CENTER);
    }
    
    private void paintCourseList() {
        //BORRAR {
        examsNames = new ArrayList<String>();
        examsNames.add("Primer Examen");
        examsNames.add("Segundo Examen");
        examsNames.add("Tercer Examen");

        List<String> courseDesc = new ArrayList<String>();
        courseDesc.add("Se entiende por examen un instrumento de evaluación cuya función es proporcionar información sobre determinadas características de un candidato");
        courseDesc.add("tales como la amplitud de sus conocimientos y su grado de control lingüístico y su actuación de una forma tal que dichas características puedan medirse.");
        courseDesc.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce laoreet augue nibh, a pulvinar nisl mattis facilisis. Mauris elit velit, efficitur sed elementum et.bv");
        
        List<List<String>> examsCaracteristics = new ArrayList<List<String>>();
        examsCaracteristics.add(new ArrayList<String>());
        examsCaracteristics.get(0).add("Examen de Java");
        examsCaracteristics.get(0).add("60 minutos");
        examsCaracteristics.get(0).add("Paula Herrero");
        examsCaracteristics.get(0).add("Java");

        examsCaracteristics.add(new ArrayList<String>());
        examsCaracteristics.get(1).add("Examen de Java Swing");
        examsCaracteristics.get(1).add("30 minutos");
        examsCaracteristics.get(1).add("Paola Geneses");
        examsCaracteristics.get(1).add("Java Swing");

        examsCaracteristics.add(new ArrayList<String>());
        examsCaracteristics.get(2).add("Examen de C++");
        examsCaracteristics.get(2).add("10 minutos");
        examsCaracteristics.get(2).add("Susana Oria");
        examsCaracteristics.get(2).add("C++");
        //}

        slider.setCourseCards(examsNames, courseDesc, examsCaracteristics);
    }

    private void addActionListener() {
        addActionListenerNavbar();

        presentExamButtons = slider.getButtons();

        if (presentExamButtons == null) {
            return;
        } else {
            for (int i = 0; i < presentExamButtons.size(); i++) {
                presentExamButtons.get(i).addActionListener(this);
            }
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