package main.views.components;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

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

import main.utils.Palette;

public class CourseCard extends JPanel {
    Button presentExamButton;

    CourseCard(String examName) {
        buildPanel();
        paintExamName(examName);
        paintLine();
        // paintCourseDescription();
        paintPresentExamButton();
        // paintExamCaracteristics();
    }

    private void buildPanel() {
        Border border = BorderFactory.createLineBorder(Palette.instance().getYellow(), 2);
        this.setBackground(Palette.instance().getWhite());
        this.setBorder(border);
        this.setPreferredSize(new Dimension(270, 400));
        this.setMinimumSize(new Dimension(220, 355));
        this.setLayout(new GridBagLayout());
    }

    public void paintExamName(String examTitle) {
        JTextPane examName = new JTextPane();
        examName.setPreferredSize(new Dimension(210, 70));
        examName.setText(examTitle);
        examName.setEditable(false);
        examName.setForeground(Palette.instance().getGray());
        examName.setFont(new Font("Nunito Sans", Font.BOLD, 17));

        Border border = BorderFactory.createEmptyBorder(20, 15, 20, 15);
        examName.setBorder(border);

        StyledDocument doc = examName.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;

        this.add(examName, constraints);
    }

    private void paintLine() {
        JSeparator line = new JSeparator();
        line.setPreferredSize(new Dimension(200,10));
        line.setForeground(Palette.instance().getLightGray());
        line.setBackground(Palette.instance().getLightGray());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 1;

        this.add(line, constraints);
    }

    public void paintCourseDescription(String courseDesc) {
        JTextPane courseDescription = new JTextPane();
        courseDescription.setPreferredSize(new Dimension(210, 115));
        courseDescription.setText(courseDesc);
        courseDescription.setEditable(false);
        courseDescription.setForeground(Palette.instance().getGray());
        courseDescription.setFont(new Font("Nunito Sans", Font.PLAIN, 12));

        Border border = BorderFactory.createEmptyBorder(20, 15, 20, 15);
        courseDescription.setBorder(border);

        StyledDocument doc = courseDescription.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 2;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        this.add(courseDescription, constraints);
    }

    private void paintPresentExamButton() {
        presentExamButton = new Button("Presentar Examen");
        presentExamButton.setPreferredSize(new Dimension(190, 30));

        JPanel buttonContainer = new JPanel();
        buttonContainer.setBackground(Palette.instance().getWhite());
        buttonContainer.add(presentExamButton);


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 4;

        this.add(buttonContainer, constraints);
    }

    public void paintExamCaracteristics(List<String> caracteristics) {
        paintExamCaracteristicPanel("<b>Tipo: </b>" + caracteristics.get(0), 5);
        
        paintExamCaracteristicPanel("<b>Duración: </b>" + caracteristics.get(1), 6);
        
        paintExamCaracteristicPanel("<b>Profesor: </b>" + caracteristics.get(2), 7);

        paintExamCaracteristicPanel("<b>Curso: </b>" + caracteristics.get(3), 8);
    }

    private void paintExamCaracteristicPanel(String caracteristic, int gridy) {
        JPanel caracteristicPanel = new JPanel();
        caracteristicPanel.setPreferredSize(new Dimension(210, 25));
        caracteristicPanel.setBackground(Palette.instance().getWhite());

        JLabel caracteristicLabel = new JLabel("<html>" + caracteristic + "</html>");
        caracteristicLabel.setForeground(Palette.instance().getGray());
        caracteristicLabel.setFont(new Font("Nunito Sans", Font.PLAIN, 12));

        caracteristicPanel.add(caracteristicLabel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = gridy;
        constraints.fill = GridBagConstraints.BOTH;

        this.add(caracteristicPanel, constraints);
    }

    public JButton getPresentExamButton() {
        return presentExamButton;
    }

}
