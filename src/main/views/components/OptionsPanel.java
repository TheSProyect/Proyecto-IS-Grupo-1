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
    List<OptionButton> options;
    boolean answered;

    OptionsPanel(List<String> optionsString, boolean isSimpleOption) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Palette.instance().getWhite());
        answered = false;
        
        options = new ArrayList<OptionButton>();
        if (isSimpleOption) {
            createSimpleOptionsButtons(optionsString);
        } else {
            createMultiOptionsButtons(optionsString);
        }
        
        paintOption();
    }

    private int determineNumLines(List<String> optionsString) {
        if (optionsString == null) {
            return 0;
        } else {
            return optionsString.size();
        }
    }

    private void paintOption() {
        for(int i = 0; i < options.size(); i++) {
            this.add(options.get(i));
        }
    }

    private void createSimpleOptionsButtons(List<String> optionsString) {
        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i < determineNumLines(optionsString); i++) {
            options.add(new SingleOptionButton(optionsString.get(i), group));
            options.get(i).addActionListener(this);
        }
    }

    private void createMultiOptionsButtons(List<String> optionsString) {
        for (int i = 0; i < determineNumLines(optionsString); i++) {
            options.add(new OptionButton(optionsString.get(i)));
            options.get(i).addActionListener(this);
        }
    }

    public boolean isAnswered() {
        return answered;
    }

    public List<Boolean> getSelectedOption() {
        List<Boolean> selectedQuestions = new ArrayList<Boolean>();
        for (OptionButton option : options) {
            selectedQuestions.add(option.isSelected());
        }
        return selectedQuestions;
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
