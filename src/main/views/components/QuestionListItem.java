package main.views.components;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

import main.utils.Palette;

public class QuestionListItem extends JButton {
    ImageIcon notCurrentIcon;
    ImageIcon currentIcon;
    boolean currentQuestion;
    boolean answered;

    QuestionListItem(int questionNumber) {
        answered = false;
        buildButton();
        setIcons("Unanswered_Icon", "Unanswered_Icon");
        paintIcon(notCurrentIcon);
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

    public void setIcons(String unselectedIconFile, String selectedIconFile) {
        boolean isCurrentIcon = this.getIcon() == currentIcon;

        notCurrentIcon = new ImageIcon("src/assets/"+ unselectedIconFile + ".png");
        currentIcon = new ImageIcon("src/assets/" + selectedIconFile + ".png");

        if (isCurrentIcon) {
            paintIcon(currentIcon);
        } else {
            paintIcon(notCurrentIcon);
        }
    }

    private void paintIcon(ImageIcon icon) {
        this.setIcon(icon);
        this.setIconTextGap(25);
    }

    private void paintButtonText(int questionNumber) {
        this.setText("Pregunta " + questionNumber);
        this.setFont(new Font("Nunito Sans", Font.PLAIN, 25));
    }

    public void setCurrentQuestion(boolean currentQuestion) {
        this.currentQuestion = currentQuestion;

        if (this.getIcon() == notCurrentIcon && currentQuestion) {
            this.setIcon(currentIcon);
            this.setFont(new Font("Nunito Sans", Font.BOLD, 25));
            this.setForeground(Palette.instance().getBlack());
        } else if (this.getIcon() == currentIcon && !currentQuestion) {
            this.setIcon(notCurrentIcon);
            this.setFont(new Font("Nunito Sans", Font.PLAIN, 25));
            this.setForeground(Palette.instance().getLightGray());
        }
    }

    public boolean isCurrentQuestion() {
        return currentQuestion;
    }
}
