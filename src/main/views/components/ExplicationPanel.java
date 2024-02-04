package main.views.components;

import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;

import main.utils.Palette;

public class ExplicationPanel extends JScrollPane {
    JTextPane explicationText;

    ExplicationPanel(List<String> text) {
        paintScroll();
        paintTextPane();
        writeText(text);
    }

    private void writeText(List<String> text) {
        // explicationText.setContentType("text/html");
        // explicationText.setText("<html>");
        System.out.println(text.size());
        for(int i = 0; i < text.size(); i ++) {
            explicationText.setText(explicationText.getText() + text.get(i) + "\n\n");
        }
        // explicationText.setText(explicationText.getText() +"</html>");
    }

    private void paintTextPane() {
        explicationText = new JTextPane();
        explicationText.setBackground(Palette.instance().getWhite());
        explicationText.setForeground(Palette.instance().getGray());
        explicationText.setFont(new Font("Nunito Sans", Font.PLAIN, 15));

        this.setViewportView(explicationText);
    }

    private void paintScroll() {
        this.setPreferredSize(new Dimension(250, 350));
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
