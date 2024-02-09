package main.views.components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import main.utils.Palette;

public class NewOptionsPanel extends JPanel implements ActionListener{
    List<NewOptionItem> options;
    JPanel optionsPanel;
    JButton addButton;
    
    NewOptionsPanel() {
        options = new ArrayList<NewOptionItem>();
        buildPanel(this);
        paintOptionsPanel();

        paintAddButton();
    }

    private void buildPanel(JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }

    private void paintOptionsPanel() {
        optionsPanel = new JPanel();
        buildPanel(optionsPanel);

        options.add(new NewOptionItem());
        

        this.add(optionsPanel);
    }

    private void paintAddButton() {
        addButton = new JButton();
        addButton.setBackground(Palette.instance().getYellow());
        addButton.setPreferredSize(new Dimension(40,40));
        addButton.addActionListener(this);

        this.add(addButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("YIPPEEE");
    }
    
}
