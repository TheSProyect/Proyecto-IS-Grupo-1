package main.views.pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.awt.FlowLayout;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import main.controllers.PresentExamController;
import main.utils.Palette;
import main.utils.Size;
import main.views.components.PopUp;

public class ExamsViewPopUp extends PopUpTemplate{
    List<JButton> buttonList = new ArrayList<JButton>();
    String[] examId;
    PresentExamController presentExamController;

    public ExamsViewPopUp(String[] _examId, PresentExamController _presentExamController ) {
        examId = _examId;
        presentExamController = _presentExamController;
        
        buildFrame(Size.instance().getExamEndedPopUpDimension());
        paintBorders();
        paintContentPanel();
        addActionListener();

    }

    protected void paintContentPanel(){
        List<String> instructions = presentExamController.getInstructions(examId);
        JPanel contentPanel = new JPanel();
        String instructionString = "";
        contentPanel.setPreferredSize(Size.instance().getExamEndedPopUpDimension());
        contentPanel.setBackground(Palette.instance().getWhite());
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 15));
        
        paintTitlePanel(contentPanel, "Instrucciones");
        paintTextLabel(instructions.get(0), contentPanel, 25);
        paintTextLabel("Duracion: " + instructions.get(1)+ " minutos", contentPanel, 25);
        for(int i = 2 ; i< instructions.size(); i++){
            instructionString += instructions.get(i) + "\n";
        }
        scrollContent(instructionString, contentPanel);
        
        paintButtons(contentPanel);
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

    private JTextArea paintInstructions(String content, JPanel contentPanel){
        JTextArea textArea = new JTextArea(content);
        textArea.setFont(new Font("Nunito Sans", Font.PLAIN,  17));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        return textArea;
    }
    private void scrollContent(String text, JPanel container) {
        JTextArea textArea = paintInstructions(text, container);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500,  200));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
        changeScrollPaneLook(scrollPane);
        container.add(scrollPane);
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
        
        buttonList.add(button);
        
        contentPanel.add(button, gbc);
    }
    private void paintButtons(JPanel contentPanel){
        JPanel buttonsContainer = new JPanel();
        buttonsContainer.setBackground(Palette.instance().getWhite());
        buttonsContainer.setLayout(new GridBagLayout());
        
        paintButton("Cancelar", buttonsContainer);
        paintButton("Presentar exámen", buttonsContainer);
        
        
        contentPanel.add(buttonsContainer);
    }
    public JButton getButton(int index) {
        return buttonList.get(index);
    }
    private void addActionListener(){
        buttonList.get(1).addActionListener(e -> {
            Frame.instance().setView(new ExamView(presentExamController, examId ));
            PopUp.deleteInstance();
        });
        buttonList.get(0).addActionListener(e -> {
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

    private void changeScrollPaneLook(JScrollPane scrollPane) {
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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
