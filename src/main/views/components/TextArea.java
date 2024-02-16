package main.views.components;

import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;

import main.utils.Palette;

public class TextArea extends JTextArea implements FocusListener {
    String placeHolder;

    public TextArea(String placeHolder) {
        this.placeHolder = placeHolder;

        this.setText(placeHolder);
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.setForeground(Palette.instance().getLightGray());
        this.setBackground(Palette.instance().getOffWhite());
        this.setFont(new Font("Nunito Sans", Font.BOLD, 20));
        this.addFocusListener(this);
    }

    public boolean isEmpty() {
        return  this.getText().equals(placeHolder);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (isEmpty()) {
            this.setText("");
            this.setForeground(Palette.instance().getBlack());
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (placeHolder == null) {
            return;
        }
        if (this.getText().isEmpty()) {
            this.setText(placeHolder);
            this.setForeground(Palette.instance().getLightGray());
        }
    }
    
}
