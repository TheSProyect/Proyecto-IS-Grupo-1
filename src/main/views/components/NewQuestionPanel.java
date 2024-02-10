package main.views.components;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.border.Border;

import main.utils.Palette;

public class NewQuestionPanel extends QuestionPanel {
    NewOptionsPanel newOptionsPanel;

    public NewQuestionPanel() {
        paintQuestionField();
        paintCodeFieldLabel();
        paintCodeField(null);
        paintOptionsLabel();
        paintNewOptionsPanel();
        paintExplicationLabel();
        paintExplicationPanel();
    }   

    private void paintQuestionField() {
        JTextPane questionField = new JTextPane();
        questionField.setForeground(Palette.instance().getBlack());
        questionField.setFont(new Font("Nunito Sans", Font.BOLD, 20));
        questionField.setBorder(createQuestionFieldBorder());


        this.add(questionField, createQuestionConstraints());
    }

    private Border createQuestionFieldBorder() {
        Border outside = BorderFactory.createLineBorder(Palette.instance().getLightGray());
        Border inside = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border border = BorderFactory.createCompoundBorder(outside, inside);

        return border;
    }

    private void paintCodeFieldLabel() {
        JLabel codeFieldLabel = new JLabel("Inserta el código");
        codeFieldLabel.setForeground(Palette.instance().getGray());
        codeFieldLabel.setFont(new Font("Nunito Sans", Font.BOLD, 17));

        questionContentPanel.add(codeFieldLabel, createLabelsConstraints(0));
    }

    private void paintOptionsLabel() {
        JLabel codeFieldLabel = new JLabel("Opciones");
        codeFieldLabel.setForeground(Palette.instance().getGray());
        codeFieldLabel.setFont(new Font("Nunito Sans", Font.BOLD, 17));

        questionContentPanel.add(codeFieldLabel, createLabelsConstraints(2));
    }

    private void paintExplicationLabel() {
        JLabel codeFieldLabel = new JLabel("Explicación");
        codeFieldLabel.setForeground(Palette.instance().getGray());
        codeFieldLabel.setFont(new Font("Nunito Sans", Font.BOLD, 17));

        questionContentPanel.add(codeFieldLabel, createLabelsConstraints(4));
    }

    private GridBagConstraints createLabelsConstraints(int gridy) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = gridy;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.BOTH;

        return constraints;
    }

    private void paintNewOptionsPanel() {
        newOptionsPanel = new NewOptionsPanel();
        newOptionsPanel.getAddButton().addActionListener(this);

        questionContentPanel.add(newOptionsPanel, createOptionPanelConstraints());
    }

    private void paintExplicationPanel() {
        explicationPanel = new ExplicationPanel(null);
        explicationPanel.setBackground(Palette.instance().getOffWhite());

        Border border = BorderFactory.createLineBorder(Palette.instance().getLightGray());
        explicationPanel.setBorder(border);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.weighty = 1.0;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        questionContentPanel.add(explicationPanel, constraints);
        questionContentPanel.setPreferredSize(new Dimension(544, questionContentPanel.getHeight()));
    }

    protected GridBagConstraints createOptionPanelConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 3;
        // constraints.weightx = 1.0;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.BOTH;

        return constraints;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newOptionsPanel.getAddButton()) {
            int contentPanelHeight = questionContentPanel.getHeight() + 50;
            questionContentPanel.setPreferredSize(new Dimension(544, contentPanelHeight));
            questionContentPanel.validate();
            questionContentPanel.repaint();
        }
    }

}
