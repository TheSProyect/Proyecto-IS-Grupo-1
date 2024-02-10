package main.views.components;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;

import main.utils.Palette;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class QuestionPanel extends JPanel implements ActionListener {
    JPanel questionContentPanel;
    OptionsPanel optionsPanel;
    Button explicationButton;
    ExplicationPanel explicationPanel;

    public QuestionPanel() {
        this.setPreferredSize(new Dimension(544, 560));
        this.setBackground(Palette.instance().getWhite());
        this.setLayout(new GridBagLayout());

        builQuestionContentPane();
    }

    private void builQuestionContentPane() {
        questionContentPanel = new JPanel();
        questionContentPanel.setBackground(Palette.instance().getWhite());
        questionContentPanel.setLayout(new GridBagLayout());

        JScrollPane questionContentScroll = new JScrollPane();
        paintScroll(questionContentScroll);
        questionContentScroll.setViewportView(questionContentPanel);
    }

    private void paintScroll(JScrollPane questionContentScroll) {
        questionContentScroll.getVerticalScrollBar().setBackground(Palette.instance().getLightGray());
        changeScrollPaneLook(questionContentScroll);

        Border border = BorderFactory.createLineBorder(Palette.instance().getWhite(), 3);
        questionContentScroll.setBorder(border);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.BOTH;
        this.add(questionContentScroll, constraints);
    }

    private void changeScrollPaneLook(JScrollPane questionContentScroll) {
        questionContentScroll.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
        questionContentScroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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

    public void paintDomainPanel(String questionDomain) {
        JPanel domainPanel = buildDomainPanel();

        paintDomainLabel(questionDomain, domainPanel);
        paintExplicationButton(domainPanel);
        
        this.add(domainPanel, createDomainPanelConstraints());
    }

    protected JPanel buildDomainPanel() {
        JPanel domainPanel = new JPanel();
        domainPanel.setLayout(new BoxLayout(domainPanel, BoxLayout.X_AXIS));
        domainPanel.setBackground(Palette.instance().getWhite());
        return domainPanel;
    }

    protected void paintDomainLabel(String questionDomain, JPanel domainPanel) {
        JLabel domainLabel = new JLabel();
        domainLabel.setText("<html> <b> Dominio: </b>" + questionDomain);
        domainLabel.setFont(new Font("Nunito Sans", Font.PLAIN, 15));
        // domainLabel.setPreferredSize(new Dimension(544, 40));
        domainLabel.setForeground(Palette.instance().getGray());
        domainLabel.setVerticalAlignment(JLabel.BOTTOM);
        domainPanel.add(domainLabel);
    }

    private void paintExplicationButton(JPanel domainPanel) {
        explicationButton = new Button("Explicación");
        explicationButton.setPreferredSize(new Dimension(130, 30));
        explicationButton.setMaximumSize(new Dimension(130, 30));
        explicationButton.setVisible(false);
        explicationButton.addActionListener(this);

        domainPanel.add(explicationButton);
    }

    protected GridBagConstraints createDomainPanelConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 0.05;
        constraints.gridwidth = 4;
        constraints.insets = new Insets(10, 10, 0, 10);
        constraints.fill = GridBagConstraints.BOTH;
        return constraints;
    }

    public void paintQuestion(String questionString) {
        JTextPane questionText = new JTextPane();
        questionText.setText(questionString);
        questionText.setEditable(false);
        questionText.setFont(new Font("Nunito Sans", Font.BOLD, 20));
        questionText.setForeground(Palette.instance().getBlack());
        questionText.setPreferredSize(new Dimension(1024,30));

        this.add(questionText, createQuestionConstraints());
    }

    protected GridBagConstraints createQuestionConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 1.0;
        constraints.gridwidth = 4;
        constraints.insets = new Insets(0, 5, 0, 10);
        constraints.fill = GridBagConstraints.BOTH;

        return constraints;
    }

    public void paintCodeField(List<String> code) {
        CodeField codeField = new CodeField(code);

        questionContentPanel.add(codeField, createCodeFieldConstraints());
    }

    protected GridBagConstraints createCodeFieldConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.weightx = 1.0;
        // constraints.weighty = 1.0;
        constraints.gridwidth = 4;
        constraints.insets = new Insets(0, 0, 0, 10);
        constraints.fill = GridBagConstraints.BOTH;

        return constraints;
    }

    public void paintOptionsPanel(List<String> options) {
        optionsPanel = new OptionsPanel(options);

        questionContentPanel.add(optionsPanel, createOptionPanelConstraints());
        questionContentPanel.setPreferredSize(new Dimension(544, questionContentPanel.getHeight()));
    }

    protected GridBagConstraints createOptionPanelConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.weighty = 1.0;
        constraints.gridwidth = 4;
        constraints.insets = new Insets(0, 0, 0, 10);
        constraints.fill = GridBagConstraints.BOTH;

        return constraints;
    }

    public boolean isAnswered() {
        return optionsPanel.isAnswered();
    }

    public int getSelectedOption() {
        return optionsPanel.getSelectedOption();
    }

    public void disableOptions() {
        optionsPanel.disableOptions();
    }

    public void setExplicationButtonVisible() {
        explicationButton.setVisible(true);
        this.repaint();
    }

    public void paintExplicationPanel(List<String> text) {
        explicationPanel = new ExplicationPanel(text);
        questionContentPanel.add(explicationPanel, createOptionPanelConstraints());
        explicationPanel.setVisible(false);
        questionContentPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == explicationButton) {
            optionsPanel.setVisible(!optionsPanel.isVisible());
            explicationPanel.setVisible(!explicationPanel.isVisible());
            
            if (optionsPanel.isVisible()) {
                explicationButton.setText("Explicación");
            } else {
                explicationButton.setText("Pregunta");
            }
        } 
    }
}