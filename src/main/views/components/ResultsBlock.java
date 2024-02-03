package main.views.components;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.utils.Palette;

public class ResultsBlock extends JPanel {
    public ResultsBlock() {
        paintPanel();
        paintIcon();
        paintBlockText();
    }

    private void paintPanel() {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(200, 75));
		this.setBackground(Palette.instance().getYellow());
    }

    private void paintIcon() {
        ImageIcon folderIcon = new ImageIcon("src/assets/Folder_Icon.png");
		JLabel imgLabel = new JLabel();
		imgLabel.setBounds(25,10, 55, 55);
		imgLabel.setIcon(folderIcon);
		this.add(imgLabel);
    }

    private void paintBlockText() {
        JLabel blockText = new JLabel("Nota Final");
        blockText.setFont(new Font("Nunito Sans", Font.BOLD, 13));
        blockText.setForeground(Palette.instance().getOffWhite());
        blockText.setBounds(85, 10, 200, 25);
        this.add(blockText);
    }

    public void paintResults(int numCorrectQuestions, int numTotalQuestions) {
        JLabel results = new JLabel(numCorrectQuestions + " / " + numTotalQuestions);
        results.setFont(new Font("Nunito Sans", Font.BOLD, 25));
        results.setForeground(Palette.instance().getWhite());
        results.setBounds(85, 30, 200, 25);
        this.add(results);
    }
}
