package main.views.components;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.Border;

import main.utils.Palette;

public class NewOptionItem extends JPanel {
    JTextPane optionText;

    NewOptionItem() {
        buildPanel();
        paintTextPane();
    }

    private void buildPanel() {
        this.setBackground(Palette.instance().getWhite());
        Border border = BorderFactory.createLineBorder(Palette.instance().getLightGray());
        this.setBorder(border);
    }

    private void paintTextPane() {
        optionText = new JTextPane();
        optionText.setForeground(Palette.instance().getGray());


        this.add(optionText);
    }
}
