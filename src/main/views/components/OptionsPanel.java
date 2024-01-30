package main.views.components;

<<<<<<< HEAD
import java.awt.FlowLayout;
=======
>>>>>>> main
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
<<<<<<< HEAD
import javax.swing.ButtonGroup;
=======
>>>>>>> main
import javax.swing.JPanel;

import main.data.Palette;

public class OptionsPanel extends JPanel {
    List<SingleOptionButton> options;

<<<<<<< HEAD
    OptionsPanel() {
        // this.setLayout(new FlowLayout(FlowLayout.LEADING));
=======
    OptionsPanel(List<String> optionsString) {
>>>>>>> main
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Palette.instance().getWhite());
        options = new ArrayList<SingleOptionButton>();

<<<<<<< HEAD
        setPlaceHolderQuestions();
=======
        createOptionsButtons(optionsString);
>>>>>>> main
        paintOption();
    }

    private void paintOption() {
        for(int i = 0; i < options.size(); i++) {
            this.add(options.get(i));
        }
    }

<<<<<<< HEAD
    private void setPlaceHolderQuestions() {
        for (int i = 0; i < 6; i++) {
            options.add(new SingleOptionButton("Hola"));
        }
    }

    private void getQuestionOptions() {
        // This should comunicate with the controller
    }
=======
    private void createOptionsButtons(List<String> optionsString) {
        for (int i = 0; i < optionsString.size(); i++) {
            options.add(new SingleOptionButton(optionsString.get(i)));
        }
    }
>>>>>>> main
}
