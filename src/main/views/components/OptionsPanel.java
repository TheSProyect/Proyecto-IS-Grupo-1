package main.views.components;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import main.data.Palette;

public class OptionsPanel extends JPanel {
    List<SingleOptionButton> options;

    OptionsPanel(List<String> optionsString) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Palette.instance().getWhite());
        options = new ArrayList<SingleOptionButton>();

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
            options.add(new SingleOptionButton(optionsString.get(i)));
        }
    }
}
