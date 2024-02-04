package main.views.components;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import main.utils.Palette;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class QuestionPanel extends JPanel implements ActionListener {
    JPanel domainPanel;
    OptionsPanel optionsPanel;
    Button explicationButton;
    ExplicationPanel explicationPanel;

    public QuestionPanel() {
        this.setPreferredSize(new Dimension(544, 560));
        this.setBackground(Palette.instance().getWhite());
        this.setLayout(new GridBagLayout());
    }

    public void paintDomainPanel(String questionDomain) {
        buildDomainPanel();

        paintDomainLabel(questionDomain);
        paintExplicationButton();
        
        this.add(domainPanel, createDomainPanelConstraints());
    }

    protected void buildDomainPanel() {
        domainPanel = new JPanel();
        domainPanel.setLayout(new BoxLayout(domainPanel, BoxLayout.X_AXIS));
        domainPanel.setBackground(Palette.instance().getWhite());
        // domainPanel.setPreferredSize(new Dimension(544, 40));
    }

    protected void paintDomainLabel(String questionDomain) {
        JLabel domainLabel = new JLabel();
        domainLabel.setText("<html> <b> Dominio: </b>" + questionDomain);
        domainLabel.setFont(new Font("Nunito Sans", Font.PLAIN, 15));
        domainLabel.setForeground(Palette.instance().getGray());
        domainLabel.setVerticalAlignment(JLabel.BOTTOM);
        domainPanel.add(domainLabel);
    }

    private void paintExplicationButton() {
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
        constraints.weighty = 0.1;
        constraints.gridwidth = 4;
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

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 0.1;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.BOTH;

        this.add(questionText, constraints);
    }

    public void paintCodeField(List<String> code) {
        CodeField codeField = new CodeField(code);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weightx = 1.0;
        constraints.weighty = 0.5;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.BOTH;

        this.add(codeField, constraints);
    }

    public void paintOptionsPanel(List<String> options) {
        optionsPanel = new OptionsPanel(options);

        this.add(optionsPanel, createOptionPanelConstraints());
    }

    private GridBagConstraints createOptionPanelConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.weighty = 0.7;
        constraints.gridwidth = 4;
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
        this.add(explicationPanel, createOptionPanelConstraints());
        explicationPanel.setVisible(false);
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == explicationButton) {
            optionsPanel.setVisible(!optionsPanel.isVisible());
            explicationPanel.setVisible(!explicationPanel.isVisible());
        }
    }
}
