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
    boolean isEmpty = true;

    public PlaceholderTextField(String str, String iconFilename) {
        paintContainer();

        paintIcon(iconFilename);

        this.str = str;
        
        paintTextField();
    }

    private void paintContainer() {
        Border outside = BorderFactory.createLineBorder(Palette.instance().getLightGray(), 2);
        this.setBorder(outside);

        this.setPreferredSize(new Dimension(314,48));
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
        textField.setPreferredSize(new Dimension(235,35));
        textField.setText(this.str);
        textField.setFont(new Font("Nunito Sans", Font.PLAIN, 17));
        textField.setForeground(Palette.instance().getLightGray());
        textField.setBackground(Palette.instance().getOffWhite());
        textField.addFocusListener(this);

        Border inside = BorderFactory.createEmptyBorder(0, 0, 0, 0);
        textField.setBorder(inside);
        
        this.add(textField);
    }

    public String getTextField() {
        if(isEmpty){
            return "";
        }
        return textField.getText();
    }

    @Override
    public void focusGained(FocusEvent e) {
        isEmpty = false;
        if (textField.getText().equals(str)) {
            textField.setText("");
            textField.setForeground(Palette.instance().getBlack());
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        
        if (textField.getText().isEmpty()) {
            textField.setForeground(Palette.instance().getLightGray());
            textField.setText(str);
            isEmpty = true;
        }
    }
}
