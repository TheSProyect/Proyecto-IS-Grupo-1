package main.views.components;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

import main.utils.Palette;

public class QuestionListItem extends JButton {
    ImageIcon notAnswered;
    ImageIcon current;

    QuestionListItem(int questionNumber) {
        buildButton();
        setIcons();
        paintIcon(notAnswered);
        paintButtonText(questionNumber);
    }
    
    private void buildButton() {
        Border border = BorderFactory.createEmptyBorder();
        this.setBorder(border);
        this.setPreferredSize(new Dimension(200, 40));
        this.setMaximumSize(new Dimension(200, 40));
        this.setForeground(Palette.instance().getLightGray());
        this.setBackground(Palette.instance().getWhite());
        this.setFocusable(false);
    }

    private void setIcons() {
        notAnswered = new ImageIcon("src/assets/Unselected_Option_Icon.png");
    }

    private void paintIcon(ImageIcon icon) {
        this.setIcon(icon);
        this.setIconTextGap(25);
    }

    private void paintButtonText(int questionNumber) {
        this.setText("Pregunta " + questionNumber);
        this.setFont(new Font("Nunito Sans", Font.PLAIN, 25));
    }

    
}
