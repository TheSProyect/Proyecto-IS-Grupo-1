package main.views.components;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import main.utils.Palette;

public class PlaceholderTextField extends JPanel implements FocusListener {
    String str;
    JLabel icon;
    JTextField textField;
    Border inside;
    boolean isFocused;

    public PlaceholderTextField(String str, String iconFilename) {
        paintContainer();

        paintIcon(iconFilename);

        this.str = str;
        
        paintTextField();
    }

    private void paintContainer() {
        inside = BorderFactory.createEmptyBorder(0, 0, 0, 0);
        Border outside = BorderFactory.createLineBorder(Palette.instance().getLightGray(), 2);
        Border border = BorderFactory.createCompoundBorder(outside, inside);

        this.setPreferredSize(new Dimension(314,48));
        this.setBorder(border);
        this.setBackground(Palette.instance().getOffWhite());
    }

    private void paintIcon(String iconFilename) {
        ImageIcon imageIcon = new ImageIcon("src/assets/" + iconFilename);;
        icon = new JLabel();
        icon.setPreferredSize(new Dimension(35,35));
        icon.setIcon(imageIcon);
        
        this.add(icon);
    }

    private void paintTextField() {
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(265,35));
        textField.setText(this.str);
        textField.setFont(new Font("Nunito Sans", Font.PLAIN, 17));
        textField.setForeground(Palette.instance().getLightGray());
        textField.setBackground(Palette.instance().getOffWhite());
        textField.addFocusListener(this);
        textField.setBorder(inside);

        

        
        this.add(textField);
    }

    public String getTextField() {
        if(!isFocused){
            return "";
        }
        return textField.getText();
    }

    @Override
    public void focusGained(FocusEvent e) {
        isFocused = true;
        if (textField.getText().equals(str)) {
            textField.setText("");
            textField.setForeground(Palette.instance().getBlack());
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        isFocused = false;
        if (textField.getText().isEmpty()) {
            textField.setForeground(Palette.instance().getLightGray());
            textField.setText(str);
        }
    }
}
