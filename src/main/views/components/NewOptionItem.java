package main.views.components;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.Border;

import main.utils.Palette;

public class NewOptionItem extends JPanel implements ActionListener{
    JTextPane optionText;
    SingleOptionButton correctAnswer;

    NewOptionItem() {
        buildPanel();
        paintTextPane();
        paintMarkCorrectAnswerRadialButton();
    }

    private void buildPanel() {
        this.setBackground(Palette.instance().getOffWhite());
        this.setLayout(new GridBagLayout());
        Border border = BorderFactory.createLineBorder(Palette.instance().getLightGray());
        this.setBorder(border);
    }

    private void paintTextPane() {
        optionText = new JTextPane();
        optionText.setForeground(Palette.instance().getGray());
        optionText.setBackground(Palette.instance().getOffWhite());
        optionText.setFont(new Font("Nunito Sans", Font.PLAIN, 20));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
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
        constraints.gridx = 1;
        constraints.fill = GridBagConstraints.BOTH;

        this.add(correctAnswer, constraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        correctAnswer.paintIcon();
    }
}
