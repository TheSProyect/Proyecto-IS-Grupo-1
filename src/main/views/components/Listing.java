package main.views.components;

import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.plaf.basic.BasicScrollBarUI;

import main.utils.Palette;

public class Listing extends JScrollPane{
    JPanel listingPanel;
    List<JButton> listingButtons;
    String singleElement;
    String textButton;


    public Listing(List<String> elements, String TextButton) {
        listingButtons = new ArrayList<JButton>();
        textButton = TextButton;
       
        paintListingPanel();
        paintListElements(elements);
    }

    
    private void paintListingPanel() {
        listingPanel = new JPanel();
        listingPanel.setPreferredSize(new Dimension(860, 500));
        listingPanel.setBackground(Palette.instance().getWhite());

        this.setViewportView(listingPanel);
        this.setPreferredSize(new Dimension(250, 320));
        this.getVerticalScrollBar().setBackground(Palette.instance().getLightGray());
        changeScrollPaneLook();

        Border border = BorderFactory.createLineBorder(Palette.instance().getWhite(), 3);
        this.setBorder(border);

    }

    protected void paintListElements(List<String> elements) {
        for (int i = 0; i < elements.size(); i++) {
            singleElement = elements.get(i);
            
            JPanel titlePanel = new JPanel();
            titlePanel.setBackground(Palette.instance().getWhite());
            titlePanel.setPreferredSize(new Dimension(860, 60));
            titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
            
            listingPanel.add(titlePanel, BorderLayout.NORTH);
            
            TitleButtonContainer(titlePanel);
            paintTitleSeparator(titlePanel);
        }       
    }

    protected void paintTitleSeparator(JPanel titlePanel) {
        JSeparator line = new JSeparator();
        line.setForeground(Palette.instance().getLightGray());
        line.setBackground(Palette.instance().getLightGray());
        titlePanel.add(line);
    }

    private void TitleButtonContainer(JPanel titlePanel) {
        JPanel titleButtonContainer = new JPanel();
        titleButtonContainer.setMaximumSize(new Dimension(1500, 58));
        titleButtonContainer.setLayout(new BoxLayout(titleButtonContainer, BoxLayout.X_AXIS));
        titleButtonContainer.setBackground(Palette.instance().getWhite());

        paintTitleLabel(titleButtonContainer);

        paintCreateButton(titleButtonContainer);
        
        titlePanel.add(titleButtonContainer);
    }

    protected void paintTitleLabel(JPanel titleButtonContainer) {
        JLabel title = new JLabel();
        title.setText(singleElement);
        title.setFont(new Font("Nunito Sans", Font.ROMAN_BASELINE, 20));
        title.setPreferredSize(new Dimension(944, 58));
        title.setMaximumSize(new Dimension(2048, 58));
        title.setVerticalAlignment(JLabel.BOTTOM);

        titleButtonContainer.add(title, FlowLayout.LEFT);
    }

    private void paintCreateButton(JPanel titleButtonContainer) {
        JButton button = new JButton(textButton);
        button.setFont(new Font("Nunito Sans", Font.BOLD, 15));
        button.setForeground(Palette.instance().getWhite());
        button.setBackground(Palette.instance().getBlue());
        button.setFocusable(false);

        Border border = BorderFactory.createLineBorder(Palette.instance().getBlue());
        button.setBorder(border);

        button.setPreferredSize(new Dimension(150, 30));
        button.setMaximumSize(new Dimension(150, 30));
    
        titleButtonContainer.add(button);
        listingButtons.add(button);
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