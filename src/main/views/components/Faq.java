package main.views.components;

import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;



import main.utils.Palette;

public class Faq extends JScrollPane{
        
        public Faq(List<String> questionList, List<String> answerList) {
        JPanel FaqContainer = new JPanel();
        FaqContainer.setLayout(new BoxLayout(FaqContainer, BoxLayout.Y_AXIS));
        FaqContainer.setBackground(Palette.instance().getWhite());
        

        paintFaqList(FaqContainer);
        paintListElements(questionList, answerList, FaqContainer);
    }

        private void paintFaqList(JPanel elementsList) {
        
        this.setViewportView(elementsList);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.getVerticalScrollBar().setBackground(Palette.instance().getLightGray());
        changeScrollPaneLook();
        
        Border border = BorderFactory.createLineBorder(Palette.instance().getWhite(), 3);
        this.setBorder(border);
    }

    private void paintListElements(List<String> questionList, List<String> answerList, JPanel listContainer) {

        for (int i = 0; i < questionList.size(); i++) {
            paintContent(questionList.get(i), answerList.get(i), listContainer);
            
        }
    }   

        private void paintContent(String question, String answer, JPanel listContainer) {
        JPanel listItem = new JPanel();
        JPanel itemContainer = new JPanel();
        JSeparator separator = paintTitleSeparator();

        listItem.setBackground(Palette.instance().getWhite());
        listItem.setMaximumSize(new Dimension(860, 860));
        listItem.setLayout(new BoxLayout(listItem, BoxLayout.Y_AXIS));
        listItem.setBorder(new EmptyBorder(0,  30,  0, 0));
        

        
        itemContainer.setLayout(new BoxLayout(itemContainer, BoxLayout.Y_AXIS));
        itemContainer.setBackground(Palette.instance().getWhite());
        itemContainer.setBorder(new EmptyBorder(10,  10,  0, 5)); 

        paintText(question, true, 19, 10, itemContainer);
        
        paintText(answer, false, 17, 15, itemContainer);
        
        
        listItem.add(itemContainer);
        listItem.add(separator);
        
        listContainer.add(listItem, BorderLayout.CENTER);
        
    }
        protected void paintText(String text, boolean isBold, int textSize, int borderBottom, JPanel container) {
        int textWeight = isBold ? Font.BOLD : Font.PLAIN;

        JLabel label = new JLabel("<html>" + text + "</html>");
        label.setFont(new Font("Nunito Sans", textWeight, textSize));        
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setForeground(Palette.instance().getBlack());
        label.setBorder(new EmptyBorder(0,  10,  borderBottom, 0)); 
        
        container.add(label);
    }

    protected JSeparator paintTitleSeparator() {
        JSeparator line = new JSeparator();
        line.setForeground(Palette.instance().getLightGray());
        line.setBackground(Palette.instance().getLightGray());
        
        return line;
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
