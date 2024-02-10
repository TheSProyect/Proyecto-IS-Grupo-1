package main.views.components;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;

import main.utils.Palette;

public class QuestionsList extends JScrollPane{
    JPanel questionsListPanel;
    List<QuestionListItem> questionsListItems;

    QuestionsList(int questionAmount) {
        paintScroll();
        paintQuestionList();
        paintQuestionListItem(questionAmount);
    }

    private void paintQuestionListItem(int questionAmount) {
        questionsListItems = new ArrayList<QuestionListItem>();
        for (int i = 0; i < questionAmount; i++) {
            questionsListItems.add(new QuestionListItem(i+1));
            questionsListPanel.add(questionsListItems.get(i));
        }
    }

    private void paintScroll() {
        this.setPreferredSize(new Dimension(250, 320));
        this.getVerticalScrollBar().setBackground(Palette.instance().getLightGray());
        changeScrollPaneLook();

        Border border = BorderFactory.createLineBorder(Palette.instance().getWhite(), 3);
        this.setBorder(border);
    }

    private void paintQuestionList() {
        questionsListPanel = new JPanel();
        questionsListPanel.setBackground(Palette.instance().getWhite());
        questionsListPanel.setLayout(new BoxLayout(questionsListPanel, BoxLayout.Y_AXIS));
        
        this.setViewportView(questionsListPanel);
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

    public List<QuestionListItem> getQuestionListItems() {
        return questionsListItems;
    }

    public void setCurrentQuestion(int currentQuestionNumber) {
        if (questionsListItems.get(currentQuestionNumber).isCurrentQuestion()) {
            return;
        }
        
        for (int i = 0; i < questionsListItems.size(); i++) {
            if (i == currentQuestionNumber && !questionsListItems.get(i).isCurrentQuestion()) {
                questionsListItems.get(i).setCurrentQuestion(true);
            } else if (questionsListItems.get(i).isCurrentQuestion()) {
                questionsListItems.get(i).setCurrentQuestion(false);
            }
        }
    }
}
