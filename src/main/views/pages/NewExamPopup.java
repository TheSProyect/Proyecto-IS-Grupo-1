package main.views.pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.utils.Palette;
import main.views.components.ExamInfoPanel;
import main.views.components.IconButton;
import main.views.components.PopUp;

public class NewExamPopup extends PopUpTemplate implements ActionListener{
    IconButton returnButton;
    IconButton finishButton;
    ExamInfoPanel examInfoPanel;

    public NewExamPopup() {
        
        buildFrame();
        paintBorders();
        paintContentPanel();

    }

    protected void paintContentPanel(){
        JPanel contentPanel = new JPanel();
        contentPanel.setPreferredSize(new Dimension(600, 550));
        contentPanel.setBackground(Palette.instance().getWhite());
        
        paintTitlePanel(contentPanel, "Configuración del examen");
        paintExamInfoPanel(contentPanel);
        paintQuestionButtonPanel(contentPanel);
        
        this.add(contentPanel);    
    }

    private void paintExamInfoPanel (JPanel contentPanel) {
        examInfoPanel = new ExamInfoPanel(contentPanel);

        contentPanel.add(examInfoPanel, BorderLayout.NORTH);
    }
    
    private void paintButton(JButton button) {
        button.setPreferredSize(new Dimension(180, 30));
        button.addActionListener(this);
    }

    
    private void paintQuestionButtonPanel(JPanel contentPanel) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        buttonPanel.setBackground(Palette.instance().getWhite());
        buttonPanel.setPreferredSize(new Dimension(550, 50));

        returnButton = new IconButton("Editar Preguntas", "Prev_Question_Icon.png");
        paintButton(returnButton);
        buttonPanel.add(returnButton);

        finishButton = new IconButton("Publicar Examen", "WhiteLogOut_Icon.png");
        paintButton(finishButton);
        buttonPanel.add(finishButton);

        contentPanel.add(buttonPanel);
    }


     private void actionEventInPublishButton(ActionEvent e) {
        if (e.getSource() == finishButton) {
            if (!examInfoPanel.checkFieldsAreComplete()) {
                System.out.println("Nop");
                return;
            }
                //pass these to the controller 
                examInfoPanel.getExamName();
                examInfoPanel.getType();
                examInfoPanel.getCourse();
                ExamInfoPanel.getDuration();
                examInfoPanel.getDescription();
            }
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnButton ) {
            PopUp.deleteInstance();
        }
        actionEventInPublishButton(e);

    }
}