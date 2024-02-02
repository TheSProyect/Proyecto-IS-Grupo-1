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
    JPanel titlePanel;
    JLabel title;
    Button button;
    JButton createExam;
    JPanel titleButtonContainer;
    JPanel listingPanel;
    List<JButton> listingButtons;

    public Listing(List<String> elements, String textButton) {
        listingButtons = new ArrayList<JButton>();
        paintListingPanel();
        paintListElements(elements, textButton);
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


    protected void paintListElements(List<String> elements, String textButton) {
        String singleElement;
        for (int i = 0; i < elements.size(); i++) {
            singleElement = elements.get(i);
            createTitlePanel();
            TitleButtonContainer(singleElement, textButton);
            paintTitleSeparator();
        }       
    }

    protected void createTitlePanel() {
        titlePanel = new JPanel();
        titlePanel.setBackground(Palette.instance().getWhite());
        titlePanel.setPreferredSize(new Dimension(860, 60));
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        listingPanel.add(titlePanel, BorderLayout.NORTH);
    }

    protected void paintTitleSeparator() {
        JSeparator line = new JSeparator();
        line.setForeground(Palette.instance().getLightGray());
        line.setBackground(Palette.instance().getLightGray());
        titlePanel.add(line);
    }

    private void TitleButtonContainer(String singleElement, String textButton) {
        titleButtonContainer = new JPanel();
        titleButtonContainer.setMaximumSize(new Dimension(1500, 58));
        titleButtonContainer.setLayout(new BoxLayout(titleButtonContainer, BoxLayout.X_AXIS));
        titleButtonContainer.setBackground(Palette.instance().getWhite());

        paintTitleLabel(singleElement);

        paintCreateExamButton(textButton);
        
        titlePanel.add(titleButtonContainer);
    }

    protected void paintTitleLabel(String singleElement) {
        title = new JLabel();
        title.setText(singleElement);
        title.setFont(new Font("Nunito Sans", Font.ROMAN_BASELINE, 20));
        title.setPreferredSize(new Dimension(944, 58));
        title.setMaximumSize(new Dimension(2048, 58));
        title.setVerticalAlignment(JLabel.BOTTOM);

        titleButtonContainer.add(title, FlowLayout.LEFT);
    }

    private void paintCreateExamButton(String textButton) {
        createExam = new JButton(textButton);
        createExam.setFont(new Font("Nunito Sans", Font.BOLD, 15));
        createExam.setForeground(Palette.instance().getWhite());
        createExam.setBackground(Palette.instance().getBlue());
        createExam.setFocusable(false);

        Border border = BorderFactory.createLineBorder(Palette.instance().getBlue());
        createExam.setBorder(border);

        createExam.setPreferredSize(new Dimension(150, 30));
        createExam.setMaximumSize(new Dimension(150, 30));
    
        titleButtonContainer.add(createExam);
        listingButtons.add(createExam);
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