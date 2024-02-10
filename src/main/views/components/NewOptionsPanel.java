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
        this.setBackground(Palette.instance().getGray());
        paintOptionsPanel();
        paintOptionsList();
        paintAddButton();
    }

    private void buildPanel(JPanel panel) {
        panel.setLayout(new GridBagLayout());
    }

    private void paintOptionsPanel() {
        optionsPanel = new JPanel();
        buildPanel(optionsPanel);

        options.add(new NewOptionItem());
        options.add(new NewOptionItem());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;
        // constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.BOTH;

        this.add(optionsPanel, constraints);
    }

    private void paintAddButton() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));

        addButton = new JButton();
        addButton.setBackground(Palette.instance().getYellow());
        addButton.setPreferredSize(new Dimension(40,40));
        addButton.setMinimumSize(new Dimension(40,40));
        addButton.addActionListener(this);
        
        Border border = BorderFactory.createLineBorder(Palette.instance().getYellow());
        addButton.setBorder(border);

        buttonPanel.add(addButton);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        
        this.add(buttonPanel, constraints);
        
        buttonPanel.setMaximumSize(new Dimension(buttonPanel.getWidth(), 50));
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
        System.out.println(options.size());
        for (int i = 0; i < options.size(); i++) {
            optionsPanel.add(options.get(i), createNewOptionsConstraints(i));
        }
    }

    public JButton getAddButton() {
        return addButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(this.getHeight());
        options.add(new NewOptionItem());
        paintOptionsList();
        this.validate();
        this.repaint();
    }
    
}
