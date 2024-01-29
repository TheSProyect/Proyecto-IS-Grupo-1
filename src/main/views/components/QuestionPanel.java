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
    JButton prevButton;
    JButton nextButton;

    public QuestionPanel() {
        this.setPreferredSize(new Dimension(544, 560));
        this.setBackground(Palette.instance().getWhite());
        this.setLayout(new GridBagLayout());

        paintDomainLabel("Trabajar con tipos de datos Java");
        paintQuestionTitle("¿Cuál es el resultado de este código?");
        paintCodeField();
        paintOptions();

        paintButtonPanel();
    }

    private void paintDomainLabel(String questionDomain) {
        JPanel domainPanel = new JPanel();
        domainPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        domainPanel.setBackground(Palette.instance().getWhite());

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

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        // constraints.weighty = 0.1;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.BOTH;
        
        this.add(domainPanel, constraints);
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
        constraints.weighty = 0.5;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.BOTH;

        this.add(codeField, constraints);
    }

    private void paintOptions() {
        option = new SingleOptionButton();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.weighty = 1.3;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.BOTH;

        this.add(option, constraints);
    }

    private void paintButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        buttonPanel.setBackground(Palette.instance().getWhite());

        prevButton = new JButton("Anterior");
        paintButton(prevButton);
        buttonPanel.add(prevButton);

        nextButton = new JButton("Siguiente");
        paintButton(nextButton);
        buttonPanel.add(nextButton);


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.weighty = 0.1;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.BOTH;

        this.add(buttonPanel, constraints);
    }

    private void paintButton(JButton button) {
        button.setForeground(Palette.instance().getWhite());
        button.setBackground(Palette.instance().getBlue());
        button.setFont(new Font("Nunito Sans", Font.BOLD, 15));
        button.setPreferredSize(new Dimension(130, 30));
        button.setFocusable(false);

        Border border = BorderFactory.createLineBorder(Palette.instance().getBlue());
        button.setBorder(border);
    }
}
