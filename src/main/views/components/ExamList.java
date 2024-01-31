package main.views.components;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;

import main.data.Palette;

public class ExamList extends JScrollPane{
    JPanel titlePanel;
    JLabel title;
    NavBar navBar;
    Button button;
    HelpBar helpBar;
    JButton createExam;
    JPanel titleButtonContainer;
    JPanel examListPanel;
   
    public ExamList() {
    
        paintExamList();
        paintExam();

    }


    private void paintExamList() {
        examListPanel = new JPanel();
        examListPanel.setPreferredSize(new Dimension(860, 500));
        examListPanel.setBackground(Palette.instance().getWhite());

        this.setViewportView(examListPanel);
        this.setPreferredSize(new Dimension(250, 320));
        this.getVerticalScrollBar().setBackground(Palette.instance().getLightGray());
        changeScrollPaneLook();

        Border border = BorderFactory.createLineBorder(Palette.instance().getWhite(), 3);
        this.setBorder(border);

        
    }

    protected void paintExam() {
        createTitlePanel();
        TitleButtonContainer();
        paintTitleSeparator();        
    }

    protected void createTitlePanel() {
        titlePanel = new JPanel();
        titlePanel.setBackground(Palette.instance().getWhite());
        titlePanel.setPreferredSize(new Dimension(860, 60));
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        examListPanel.add(titlePanel, BorderLayout.NORTH);
    }

    protected void paintTitleSeparator() {
        JSeparator line = new JSeparator();
        line.setForeground(Palette.instance().getLightGray());
        line.setBackground(Palette.instance().getLightGray());
        titlePanel.add(line);
    }

    private void TitleButtonContainer() {
        titleButtonContainer = new JPanel();
        titleButtonContainer.setMaximumSize(new Dimension(1500, 58));
        titleButtonContainer.setLayout(new BoxLayout(titleButtonContainer, BoxLayout.X_AXIS));
        titleButtonContainer.setBackground(Palette.instance().getWhite());

        paintTitleLabel();

        paintCreateExamButton();
        
        titlePanel.add(titleButtonContainer);
    }

    protected void paintTitleLabel() {
        title = new JLabel();
        title.setText("Examen 1 Java");
        title.setFont(new Font("Nunito Sans", Font.ROMAN_BASELINE, 20));
        title.setPreferredSize(new Dimension(944, 58));
        title.setMaximumSize(new Dimension(2048, 58));
        title.setVerticalAlignment(JLabel.BOTTOM);

        titleButtonContainer.add(title, FlowLayout.LEFT);
    }

    private void paintCreateExamButton() {
        createExam = new JButton("Presentar Examen");
        createExam.setFont(new Font("Nunito Sans", Font.BOLD, 15));
        createExam.setForeground(Palette.instance().getWhite());
        createExam.setBackground(Palette.instance().getBlue());
        createExam.setFocusable(false);

        Border border = BorderFactory.createLineBorder(Palette.instance().getBlue());
        createExam.setBorder(border);

        createExam.setPreferredSize(new Dimension(150, 30));
        createExam.setMaximumSize(new Dimension(150, 30));
    
        titleButtonContainer.add(createExam);
    }
    
    private void changeScrollPaneLook() {
        this.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
        this.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override    
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton jbutton = new JButton();
                jbutton.setPreferredSize(new Dimension(0, 0));
                jbutton.setMinimumSize(new Dimension(0, 0));
                jbutton.setMaximumSize(new Dimension(0, 0));
                return jbutton;
            }

            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Palette.instance().getYellow();
            }
        });
    }


}
