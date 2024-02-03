package main.views.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import main.utils.Palette;

public class OptionsPanel extends JPanel implements ActionListener{
    List<SingleOptionButton> options;
    ButtonGroup group;
    boolean answered;

    OptionsPanel(List<String> optionsString) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Palette.instance().getWhite());
        options = new ArrayList<SingleOptionButton>();
        group = new ButtonGroup();
        answered = false;

        createOptionsButtons(optionsString);
        paintOption();
    }

    private void paintOption() {
        for(int i = 0; i < options.size(); i++) {
            this.add(options.get(i));
        }
    }

    private void createOptionsButtons(List<String> optionsString) {
        for (int i = 0; i < optionsString.size(); i++) {
            options.add(new SingleOptionButton(optionsString.get(i), group));
            options.get(i).addActionListener(this);
        }
    }

    public boolean isAnswered() {
        return answered;
    }

    public int getSelectedOption() {
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).isSelected()) {
                return i;
            }
        }
        return -1;
    }

    public void disableOptions() {
        for(int i = 0; i < options.size(); i++) {
            options.get(i).setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        answered = true;

        for (int i = 0; i < options.size(); i++) {
            options.get(i).paintIcon();
        }
    }
}
