package main.views.pages;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import main.utils.Palette;
import main.utils.Size;
import main.views.components.ResultsBlock;

public class ExamEndedPopup extends PopUpTemplate{
    JButton Button;
    
    public ExamEndedPopup(float correctAnswer,  int amountOfQuestion) {
        
        buildFrame(Size.instance().getExamEndedPopUpDimension());
        paintBorders();
        paintContentPanel(correctAnswer, amountOfQuestion);

    }

    protected void paintContentPanel(float correctAnswer,  int amountOfQuestion){
        JPanel contentPanel = new JPanel();
        contentPanel.setPreferredSize(Size.instance().getExamEndedPopUpDimension());
        contentPanel.setBackground(Palette.instance().getWhite());
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 15));
        
        paintTitlePanel(contentPanel, "Examen Finalizado");
        
        paintTextLabel(contentPanel);
        paintScore(contentPanel, correctAnswer, amountOfQuestion);
        paintButton(contentPanel);
        
        this.add(contentPanel, FlowLayout.CENTER);    
    }
    

    protected void paintTextLabel(JPanel contentPanel) {
        JTextPane text = new JTextPane();
        text.setText("Haz finalizado el examen con una nota de:");
        text.setFont(new Font("Nunito Sans", Font.PLAIN, 20));
        text.setPreferredSize(new Dimension(500, 25));
        text.setEditable(false);
        text.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        StyledDocument doc = text.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        contentPanel.add(text);
    }

    private void paintScore(JPanel contentPanel, float correctAnswer,  int amountOfQuestion) {
        ResultsBlock results = new ResultsBlock();
        results.paintResults(correctAnswer, amountOfQuestion);
        contentPanel.add(results);
    }
    
    private void paintButton(JPanel contentPanel) {
        Button = new JButton("Ver Respuestas");
        Button.setFont(new Font("Nunito Sans", Font.BOLD, 15));
        Button.setUI(new MetalButtonUI());
        Button.setForeground(Palette.instance().getWhite());
        Button.setBackground(Palette.instance().getBlue());
        Button.setPreferredSize(new Dimension(200, 30));
        Button.setFocusable(false);
        
        Border border = BorderFactory.createLineBorder(Palette.instance().getBlue());
        Button.setBorder(border);

        contentPanel.add(Button);
    }

    public JButton getButton() {
        return Button;
    }


}
