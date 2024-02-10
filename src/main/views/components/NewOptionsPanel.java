package main.views.components;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import main.utils.Palette;

public class NewOptionsPanel extends JPanel implements ActionListener{
    List<NewOptionItem> options;
    JPanel optionsPanel;
    JButton addButton;
    
    NewOptionsPanel() {
        options = new ArrayList<NewOptionItem>();
        buildPanel(this);
        paintOptionsPanel();
        paintOptionsList();
        paintAddButtonPanel();
    }

    private void buildPanel(JPanel panel) {
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Palette.instance().getWhite());
    }

    private void paintOptionsPanel() {
        optionsPanel = new JPanel();
        buildPanel(optionsPanel);

        options.add(new NewOptionItem());
        options.add(new NewOptionItem());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;

        this.add(optionsPanel, constraints);
    }

    private void paintAddButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        buttonPanel.setBackground(Palette.instance().getWhite());

        paintAddButton();
        paintAddButtonIcon();

        buttonPanel.add(addButton);

        this.add(buttonPanel, createAddButtonConstraints());
        
        buttonPanel.setMaximumSize(new Dimension(buttonPanel.getWidth(), 50));
    }

    private void paintAddButton() {
        addButton = new JButton();
        addButton.setBackground(Palette.instance().getYellow());
        addButton.setPreferredSize(new Dimension(40,40));
        addButton.setMinimumSize(new Dimension(40,40));
        addButton.addActionListener(this);
        addButton.setFocusable(false);
        
        Border border = BorderFactory.createLineBorder(Palette.instance().getYellow());
        addButton.setBorder(border);
    }

    private void paintAddButtonIcon() {
        ImageIcon icon = new ImageIcon("src/assets/Plus_Icon.png");
        addButton.setIconTextGap(15);

        addButton.setIcon(icon);
    }

    private GridBagConstraints createAddButtonConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        return constraints;
    }

    private GridBagConstraints createNewOptionsConstraints(int gridy) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = gridy;
        constraints.weightx = 1.0;
        constraints.insets = new Insets(5, 0, 5, 0);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        return constraints;
    }

    private void paintOptionsList() {
        for (int i = 0; i < options.size(); i++) {
            optionsPanel.add(options.get(i), createNewOptionsConstraints(i));
            options.get(i).getDeleteButton().addActionListener(this);
        }
    }

    public JButton getAddButton() {
        return addButton;
    }

    private void actionEventInAddButton(ActionEvent e) {
        if (e.getSource() == addButton) {
            options.add(new NewOptionItem());
            paintOptionsList();
            this.validate();
            this.repaint();
        }
    }

    private void actionEventInDeleteButton(ActionEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actionEventInAddButton(e);
    }
    
}
