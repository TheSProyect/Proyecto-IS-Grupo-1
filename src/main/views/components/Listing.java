package main.views.components;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

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

import javax.swing.plaf.metal.MetalButtonUI;


import main.utils.Palette;

public class Listing extends JScrollPane{
    List<JButton> listingButtons;
    

    JPanel titlePanel;
    JLabel title;
    JButton createExam;
    JPanel titleButtonContainer;

    public Listing(List<String> elementList, String textButton) {
        listingButtons = new ArrayList<JButton>();
        System.out.print(elementList.size());
        JPanel listContainer = new JPanel();
        listContainer.setLayout(new BoxLayout(listContainer, BoxLayout.Y_AXIS));
        listContainer.setBackground(Palette.instance().getWhite());

        paintList(listContainer);
        paintListElements(elementList, textButton, listContainer);
    }

    public Listing(List<String> questionList, List<String> answerList) {
        JPanel listContainer = new JPanel();
        listContainer.setLayout(new BoxLayout(listContainer, BoxLayout.Y_AXIS));
        listContainer.setBackground(Palette.instance().getWhite());
        

        paintList(listContainer);
        paintListElements(questionList, answerList, listContainer);
    }

    
    private void paintList(JPanel elementsList) {
        
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
    
    protected void paintListElements(List<String> elementList, String textButton,JPanel listContainer) {
        
        String singleElement;
        for (int i = 0; i < elementList.size(); i++) {
            singleElement = elementList.get(0);
            
            TitleButtonContainer(singleElement, textButton, listContainer);
            
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
    private void TitleButtonContainer(String text, String textButton, JPanel listContainer) {
        JSeparator separator = paintTitleSeparator();
        
        JPanel titleButtonContainer = new JPanel();
        titleButtonContainer.setLayout(new BoxLayout(titleButtonContainer, BoxLayout.Y_AXIS));
        titleButtonContainer.setBackground(Palette.instance().getWhite());


        JPanel itemContainer = new JPanel();
        itemContainer.setLayout(new BoxLayout(itemContainer, BoxLayout.X_AXIS));
        itemContainer.setBackground(Palette.instance().getWhite());
        itemContainer.setBorder(new EmptyBorder(10,  0,  10, 0)); 
        paintText(textButton, false, 20, 0, itemContainer);

        paintCreateButton(textButton, itemContainer);
        
        titleButtonContainer.add(itemContainer);

        titleButtonContainer.add(separator);
        titleButtonContainer.setBorder(new EmptyBorder(0,  40,  0, 40)); 
        listContainer.add(titleButtonContainer);
    }

    protected JSeparator paintTitleSeparator() {
        JSeparator line = new JSeparator();
        line.setForeground(Palette.instance().getLightGray());
        line.setBackground(Palette.instance().getLightGray());
        
        return line;
    }

    

    private void paintCreateButton(String textButton,JPanel titleButtonContainer) {
        JButton button = new JButton(textButton);
        button.setFont(new Font("Nunito Sans", Font.BOLD, 15));
        button.setForeground(Palette.instance().getWhite());
        button.setBackground(Palette.instance().getBlue());
        button.setUI(new MetalButtonUI());
        button.setFocusable(false);

        Border border = BorderFactory.createLineBorder(Palette.instance().getBlue());
        button.setBorder(border);

        button.setPreferredSize(new Dimension(190, 30));
        button.setMaximumSize(new Dimension(190, 30));
    
        titleButtonContainer.add(button);
        listingButtons.add(button);
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

    public List<JButton> getListingButtons() {
        return listingButtons;
    }
}