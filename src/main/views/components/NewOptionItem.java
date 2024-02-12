package main.views.components;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.Border;

import main.utils.Palette;

public class NewOptionItem extends JPanel implements ActionListener{
    JTextPane optionText;
    SingleOptionButton correctAnswer;
    JButton deleteButton;
    ExplicationPanel explicationPanel;

    NewOptionItem() {
        buildPanel();
        paintDeleteButton();
        paintTextPane();
        paintMarkCorrectAnswerRadialButton();
        paintExplicationPanel();
    }

    private void buildPanel() {
        this.setBackground(Palette.instance().getOffWhite());
        this.setLayout(new GridBagLayout());
        Border border = BorderFactory.createLineBorder(Palette.instance().getLightGray());
        this.setBorder(border);
    }

    private void paintDeleteButton() {
        Border border = BorderFactory.createLineBorder(Palette.instance().getOffWhite());

        deleteButton = new JButton();
        deleteButton.setBackground(Palette.instance().getOffWhite());
        deleteButton.setFocusable(false);
        deleteButton.setBorder(border);

        ImageIcon icon = new ImageIcon("src/assets/Delete_Red_Icon.png");

        deleteButton.setIcon(icon);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.insets = new Insets(0, 5, 0, 5);

        this.add(deleteButton, constraints);
    }

    private void paintTextPane() {
        optionText = new JTextPane();
        optionText.setForeground(Palette.instance().getGray());
        optionText.setBackground(Palette.instance().getOffWhite());
        optionText.setFont(new Font("Nunito Sans", Font.PLAIN, 20));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.weightx = 1.0;
        constraints.insets = new Insets(0, 5, 0, 0);
        constraints.fill = GridBagConstraints.BOTH;

        this.add(optionText, constraints);
    }

    private void paintMarkCorrectAnswerRadialButton() {
        correctAnswer = new SingleOptionButton("", null);
        correctAnswer.setBackground(Palette.instance().getOffWhite());
        correctAnswer.setPreferredSize(new Dimension(40, 40));
        correctAnswer.addActionListener(this);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.fill = GridBagConstraints.BOTH;

        this.add(correctAnswer, constraints);
    }

    private void paintExplicationPanel() {
        explicationPanel = new ExplicationPanel(null);
        explicationPanel.setBackground(Palette.instance().getOffWhite());
        explicationPanel.setPreferredSize(new Dimension(500, 70));

        Border border = BorderFactory.createLineBorder(Palette.instance().getLightGray());
        explicationPanel.setBorder(border);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;

        this.add(explicationPanel, constraints);
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public String getOptionText() {
        String text = optionText.getText();

        if (correctAnswer.isSelected()) {
            text = "v" + text;
        }

        return text;
    }

    public String getExplicationText() {
        return explicationPanel.getExplicationText();
    }

    public boolean isCorrectAnswer() {
        return correctAnswer.isSelected();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        correctAnswer.paintIcon();
    }
}
