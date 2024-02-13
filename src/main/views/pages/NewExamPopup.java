package main.views.pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.controllers.CreateExamController;
import main.utils.Palette;
import main.utils.Size;
import main.views.components.ExamInfoPanel;
import main.views.components.IconButton;
import main.views.components.PopUp;

public class NewExamPopup extends PopUpTemplate implements ActionListener{
    CreateExamController createExamController;
    IconButton returnButton;
    IconButton finishButton;
    ExamInfoPanel examInfoPanel;


    public NewExamPopup() {
        
        buildFrame(Size.instance().getNewExamPopUpDimension());
        paintBorders();
        paintContentPanel();

    }

    protected void paintContentPanel(){
        JPanel contentPanel = new JPanel();
        contentPanel.setPreferredSize(new Dimension(600, 550));
        contentPanel.setBackground(Palette.instance().getWhite());
        
        paintTitlePanel(contentPanel, "Configuración del examen");
        paintExamInfoPanel(contentPanel);
        paintButtonPanel(contentPanel);
        
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

    
    private void paintButtonPanel(JPanel contentPanel) {
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


     private void actionEventInFinishButton(ActionEvent e) {
        if (e.getSource() == finishButton) {
            if (!examInfoPanel.checkFieldsAreComplete()) {
                System.out.println("Nop");
                return;
            }
           //pass these to the controller 
                ArrayList<String> examInfo = new ArrayList<String>(); 
                examInfo.add(examInfoPanel.getExamName());
                examInfo.add(examInfoPanel.getType());
                examInfo.add(examInfoPanel.getCourse());
                examInfo.add(examInfoPanel.getDescription());

                createExamController = new CreateExamController();
                createExamController.saveExam(examInfo, ExamInfoPanel.getDuration());
                
                System.out.println(examInfo);
                System.out.println(ExamInfoPanel.getDuration());
                    


            }
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnButton ) {
            PopUp.deleteInstance();
        }
        actionEventInFinishButton(e);

    }
}