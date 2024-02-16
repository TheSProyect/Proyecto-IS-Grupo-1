package main.views.components;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import main.utils.Palette;

public class NewDomainTextField extends JPanel{
    JTextField textField;
    JButton deleteButton;

    NewDomainTextField () {
        paintPanel();
        paintDeleteButton();
        paintTextField();
    }

    private void paintPanel() {
        this.setLayout(new GridBagLayout());
        this.setBackground(Palette.instance().getOffWhite());
        this.setBorder(createBorder());
    }

    private void paintTextField() {
        Border inside = BorderFactory.createEmptyBorder(0, 0, 0, 0);

        textField = new JTextField();
        textField.setForeground(Palette.instance().getGray());
        textField.setBackground(Palette.instance().getOffWhite());
        textField.setFont(new Font("Nunito Sans", Font.PLAIN, 20));
        textField.setBorder(inside);

        this.add(textField, createTextFieldConstraints());
    }

    private GridBagConstraints createTextFieldConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.weightx = 1.0;
        constraints.insets = new Insets(0, 5, 0, 0);
        constraints.fill = GridBagConstraints.BOTH;

        return constraints;
    }

    private Border createBorder() {
        Border outside = BorderFactory.createLineBorder(Palette.instance().getLightGray());
        Border inside = BorderFactory.createEmptyBorder(5, 10, 5, 10);
        Border border = BorderFactory.createCompoundBorder(outside, inside);

        return border;
    }

    private void paintDeleteButton() {
        Border border = BorderFactory.createLineBorder(Palette.instance().getOffWhite());

        deleteButton = new JButton();
        deleteButton.setBackground(Palette.instance().getOffWhite());
        deleteButton.setFocusable(false);
        deleteButton.setBorder(border);

        ImageIcon icon = new ImageIcon("src/assets/Delete_Red_Icon.png");

        deleteButton.setIcon(icon);

        this.add(deleteButton, createDeleteButtoConstraints());
    }

    private GridBagConstraints createDeleteButtoConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.insets = new Insets(0, 5, 0, 5);

        return constraints;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public String getDomainText() {
        return textField.getText();
    }
}
