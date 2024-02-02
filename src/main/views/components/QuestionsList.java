package main.views.components;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;

import main.utils.Palette;

public class QuestionsList extends JScrollPane{
    JPanel questionsListPanel;

    QuestionsList() {
        paintQuestionList();
    }

    private void paintQuestionList() {

        questionsListPanel = new JPanel();
        questionsListPanel.setPreferredSize(new Dimension(200, 600));
        questionsListPanel.setBackground(Palette.instance().getWhite());
        
        this.setViewportView(questionsListPanel);
        this.setPreferredSize(new Dimension(250, 320));
        this.getVerticalScrollBar().setBackground(Palette.instance().getLightGray());
        changeScrollPaneLook();

        Border border = BorderFactory.createLineBorder(Palette.instance().getWhite(), 3);
        this.setBorder(border);
    }

    private void changeScrollPaneLook() {
        this.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
        this.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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
