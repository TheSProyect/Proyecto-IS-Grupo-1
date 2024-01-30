package main.views.components;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import main.data.Palette;

public class OptionsPanel extends JPanel {
    List<SingleOptionButton> options;

    OptionsPanel() {
        // this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Palette.instance().getWhite());
        options = new ArrayList<SingleOptionButton>();

        setPlaceHolderQuestions();
        paintOption();
    }

    private void paintOption() {
        for(int i = 0; i < options.size(); i++) {
            this.add(options.get(i));
        }
    }

    private void setPlaceHolderQuestions() {
        for (int i = 0; i < 6; i++) {
            options.add(new SingleOptionButton("Hola"));
        }
    }

    private void getQuestionOptions() {
        // This should comunicate with the controller
    }
}
