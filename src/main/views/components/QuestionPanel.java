package main.views.components;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import main.data.Palette;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class QuestionPanel extends JPanel {
    SingleOptionButton option;
    JPanel domainPanel;
    OptionsPanel optionsPanel;

    public QuestionPanel() {
        this.setPreferredSize(new Dimension(544, 560));
        this.setBackground(Palette.instance().getWhite());
        this.setLayout(new GridBagLayout());

        paintDomainPanel("Trabajar con tipos de datos Java");
        paintQuestionTitle("¿Cuál es el resultado de este código?");
        paintCodeField();
        paintOptionsPanel();

        // paintButtonPanel();
    }

    private void paintDomainPanel(String questionDomain) {
        domainPanel = new JPanel();
        domainPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        domainPanel.setBackground(Palette.instance().getWhite());

        paintDomainLabel(questionDomain);
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        // constraints.weighty = 0.1;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.BOTH;
        
        this.add(domainPanel, constraints);
    }

    private void paintDomainLabel(String questionDomain) {
        JLabel domainLabel = new JLabel();
        domainLabel.setText("Dominio:");
        domainLabel.setFont(new Font("Nunito Sans", Font.BOLD, 15));
        domainLabel.setForeground(Palette.instance().getGray());
        domainLabel.setVerticalAlignment(JLabel.BOTTOM);

        domainPanel.add(domainLabel);

        domainLabel = new JLabel();
        domainLabel.setText(questionDomain);
        domainLabel.setFont(new Font("Nunito Sans", Font.PLAIN, 15));
        domainLabel.setForeground(Palette.instance().getGray());
        domainLabel.setVerticalAlignment(JLabel.BOTTOM);
        domainPanel.add(domainLabel);
    }

    private void paintQuestionTitle(String questionTitleString) {
        JLabel questionTitle = new JLabel(questionTitleString);
        questionTitle.setFont(new Font("Nunito Sans", Font.BOLD, 20));
        questionTitle.setForeground(Palette.instance().getBlack());
        questionTitle.setPreferredSize(new Dimension(2048,30));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weighty = 0.1;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.BOTH;

        this.add(questionTitle, constraints);
    }

    private void paintCodeField() {
        CodeField codeField = new CodeField();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.BOTH;

        this.add(codeField, constraints);
    }

    private void paintOptionsPanel() {
        optionsPanel = new OptionsPanel();

        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.weighty = 0.5;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.BOTH;

        this.add(optionsPanel, constraints);
    }

    
}
