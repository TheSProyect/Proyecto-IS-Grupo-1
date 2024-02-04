package main.views.components;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;

import com.itextpdf.awt.geom.Dimension;

import main.utils.Palette;

public class ExplicationPanel extends JScrollPane {
    ExplicationPanel() {

    }
    private void paintScroll() {
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
