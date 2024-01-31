package main.views.components;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import main.data.Palette;

public class CourseCard extends JPanel {
    JButton presentExamButton;

    CourseCard(String examName) {
        Border border = BorderFactory.createLineBorder(Palette.instance().getYellow(), 2);
        this.setBackground(Palette.instance().getWhite());
        this.setBorder(border);
        this.setPreferredSize(new Dimension(270, 400));
        this.setMinimumSize(new Dimension(220, 355));
        
        paintSpacer();
        paintExamName(examName);
        paintLine();
        paintCourseDescription();
        paintSpacer();
        paintPresentExamButton();
        paintExamCaracteristics();
    }
    
    private void paintSpacer() {
        JLabel spacer = new JLabel();
        spacer.setPreferredSize(new Dimension(240, 20));
        this.add(spacer);
    }

    private void paintExamName(String str) {
        JTextPane examName = new JTextPane();
        examName.setPreferredSize(new Dimension(210, 70));
        examName.setText(str);
        examName.setEditable(false);
        examName.setForeground(Palette.instance().getGray());
        examName.setFont(new Font("Nunito Sans", Font.BOLD, 17));

        Border border = BorderFactory.createEmptyBorder(20, 15, 20, 15);
        examName.setBorder(border);

        StyledDocument doc = examName.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        this.add(examName);
    }

    private void paintLine() {
        JSeparator line = new JSeparator();
        line.setPreferredSize(new Dimension(200,1));
        line.setForeground(Palette.instance().getLightGray());
        line.setBackground(Palette.instance().getLightGray());

        this.add(line);
    }

    private void paintCourseDescription() {
        JTextPane courseDescription = new JTextPane();
        courseDescription.setPreferredSize(new Dimension(210, 100));
        courseDescription.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
        courseDescription.setEditable(false);
        courseDescription.setForeground(Palette.instance().getGray());
        courseDescription.setFont(new Font("Nunito Sans", Font.PLAIN, 12));

        Border border = BorderFactory.createEmptyBorder(20, 15, 20, 15);
        courseDescription.setBorder(border);

        StyledDocument doc = courseDescription.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        this.add(courseDescription);
    }

    private void paintPresentExamButton() {
        presentExamButton = new JButton("Presentar Examen");
        presentExamButton.setFont(new Font("Nunito Sans", Font.BOLD, 15));
        presentExamButton.setForeground(Palette.instance().getWhite());
        presentExamButton.setBackground(Palette.instance().getBlue());
        presentExamButton.setPreferredSize(new Dimension(190, 30));
        presentExamButton.setFocusable(false);

        Border border = BorderFactory.createLineBorder(Palette.instance().getBlue());
        presentExamButton.setBorder(border);

        this.add(presentExamButton);
    }

    private void paintExamCaracteristics() {
        paintExamCaracteristicPanel("Tipo:", "Examen de Java");
        
        paintExamCaracteristicPanel("Duración:", "60 minutos");
        
        paintExamCaracteristicPanel("Profesor:", "Paula Herrero");
    }

    private void paintExamCaracteristicPanel(String category, String caracteristic) {
        JPanel caracteristicPanel = new JPanel();
        caracteristicPanel.setPreferredSize(new Dimension(210, 20));
        caracteristicPanel.setBackground(Palette.instance().getWhite());

        JLabel categoryLabel = new JLabel(category);
        categoryLabel.setForeground(Palette.instance().getGray());
        categoryLabel.setFont(new Font("Nunito Sans", Font.BOLD, 12));

        JLabel caracteristicLabel = new JLabel(caracteristic);
        caracteristicLabel.setForeground(Palette.instance().getGray());
        caracteristicLabel.setFont(new Font("Nunito Sans", Font.PLAIN, 12));

        caracteristicPanel.add(categoryLabel);
        caracteristicPanel.add(caracteristicLabel);

        this.add(caracteristicPanel);
    }
}
