package main.views.pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
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
import main.views.components.PopUp;

public class ExamPublishedPopUp extends PopUpTemplate{
    JButton button;
    String[] examId;
    

    public ExamPublishedPopUp() {
        
        buildFrame(Size.instance().getExamEndedPopUpDimension());
        paintBorders();
        paintContentPanel();
        addActionListener();

    }

    protected void paintContentPanel(){
        JPanel contentPanel = new JPanel();
        contentPanel.setPreferredSize(Size.instance().getExamEndedPopUpDimension());
        contentPanel.setBackground(Palette.instance().getWhite());
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 15));
        
        paintTitlePanel(contentPanel, "Felicidades!");
        paintTextLabel("Tu examen ha sido publicado exitosamente", contentPanel, 25);
        
        paintButton("continuar", contentPanel);
        this.add(contentPanel, FlowLayout.CENTER);    
    }
    

    protected void paintTextLabel( String string, JPanel contentPanel, int height) {
        JTextPane text = new JTextPane();
        text.setText(string);
        text.setFont(new Font("Nunito Sans", Font.PLAIN, 17));
        text.setPreferredSize(new Dimension(500, height));
        
        text.setEditable(false);
        text.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        StyledDocument doc = text.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        contentPanel.add(text);
    }

    private void paintButton(String textButton, JPanel contentPanel) {
        JButton button = new JButton(textButton);
        button.setFont(new Font("Nunito Sans", Font.BOLD, 15));
        button.setUI(new MetalButtonUI());
        button.setForeground(Palette.instance().getWhite());
        button.setBackground(Palette.instance().getBlue());
        button.setPreferredSize(new Dimension(200, 30));
        button.setFocusable(false);
        button.addActionListener(null);

        Border border = BorderFactory.createLineBorder(Palette.instance().getBlue());
        button.setBorder(border);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,  10,  0,  10);
        
        button.add(button);
        
        contentPanel.add(button, gbc);
    }
    public JButton getButton() {
        return button;
    }
    private void addActionListener(){
        button.addActionListener(e -> {
            PopUp.deleteInstance();
        });
    }

    

    @Override
    protected void paintBorders() {
        JPanel borderPanel = new JPanel();
        borderPanel.setPreferredSize(new Dimension(40, 450));
        borderPanel.setBackground(Palette.instance().getWhite());
        this.add(borderPanel, BorderLayout.WEST);

        borderPanel = new JPanel();
        borderPanel.setPreferredSize(new Dimension(40, 450));
        borderPanel.setBackground(Palette.instance().getWhite());
        this.add(borderPanel, BorderLayout.EAST);
    }

}
