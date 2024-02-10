package main.views.components;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;

import main.utils.Palette;

public class NewQuestionPanel extends QuestionPanel {
    NewOptionsPanel newOptionsPanel;
    IconButton addImageButton;

    public NewQuestionPanel() {
        paintQuestionField();
        paintFieldLabel("Dominio", 0);
        paintDomainField();
        paintFieldLabel("Inserta el código", 2);
        paintCodeField(null);
        paintAddImageButton();
        paintFieldLabel("Opciones", 5);
        paintNewOptionsPanel();
        paintFieldLabel("Explicación", 7);
        paintExplicationPanel();
    }   

    private void paintQuestionField() {
        JTextPane questionField = new JTextPane();
        questionField.setForeground(Palette.instance().getBlack());
        questionField.setFont(new Font("Nunito Sans", Font.BOLD, 20));
        questionField.setBorder(createQuestionFieldBorder());

        this.add(questionField, createQuestionConstraints());
    }

    private Border createQuestionFieldBorder() {
        Border outside = BorderFactory.createLineBorder(Palette.instance().getLightGray());
        Border inside = BorderFactory.createEmptyBorder(5, 10, 5, 10);
        Border border = BorderFactory.createCompoundBorder(outside, inside);

        return border;
    }

    private void paintFieldLabel(String label, int gridy) {
        JLabel domainLabel =  new JLabel(label);
        domainLabel.setForeground(Palette.instance().getGray());
        domainLabel.setFont(new Font("Nunito Sans", Font.BOLD, 17));

        questionContentPanel.add(domainLabel, createLabelsConstraints(gridy));
    }

    private GridBagConstraints createLabelsConstraints(int gridy) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = gridy;
        constraints.gridwidth = 4;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.BOTH;

        return constraints;
    }

    private void paintDomainField() {
        JTextField domainField = new JTextField();
        domainField.setForeground(Palette.instance().getGray());
        domainField.setBackground(Palette.instance().getOffWhite());
        domainField.setFont(new Font("Nunito Sans", Font.PLAIN, 20));
        domainField.setBorder(createQuestionFieldBorder());

        questionContentPanel.add(domainField, createLabelsConstraints(1));
    }

    private void paintAddImageButton() {
        addImageButton = new IconButton("Insertar imagen", "Plus_Icon.png");
        addImageButton.setBackground(Palette.instance().getYellow());
        addImageButton.setPreferredSize(new Dimension(250, 50));
        addImageButton.setMinimumSize(new Dimension(190, 30));
        addImageButton.addActionListener(this);

        Border border = BorderFactory.createLineBorder(Palette.instance().getYellow());
        addImageButton.setBorder(border);

        questionContentPanel.add(addImageButton, createAddImageButtonConstraints());
    }

    private GridBagConstraints createAddImageButtonConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 4;
        constraints.insets = new Insets(5, 0, 5, 0);
        constraints.fill = GridBagConstraints.BOTH;

        return constraints;
    }

    private void paintNewOptionsPanel() {
        newOptionsPanel = new NewOptionsPanel();

        newOptionsPanel.addActionListenerDeleteButtons(this);
        newOptionsPanel.getAddButton().addActionListener(this);

        questionContentPanel.add(newOptionsPanel, createOptionPanelConstraints());
    }

    private void paintExplicationPanel() {
        explicationPanel = new ExplicationPanel(null);
        explicationPanel.setBackground(Palette.instance().getOffWhite());

        Border border = BorderFactory.createLineBorder(Palette.instance().getLightGray());
        explicationPanel.setBorder(border);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.weighty = 1.0;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.BOTH;

        questionContentPanel.add(explicationPanel, constraints);

        final int DEFAULT_HEIGHT = 700;
        final int WIDTH = 544;
        questionContentPanel.setPreferredSize(new Dimension(WIDTH, DEFAULT_HEIGHT));
    }

    protected GridBagConstraints createOptionPanelConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 6;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.BOTH;

        return constraints;
    }

    private void actionEventInNewOptionsPanel(ActionEvent e) {
        final int DEFAULT_HEIGHT = 700;
        final int WIDTH = 544;
        int contentPanelHeight = DEFAULT_HEIGHT;

        if (e.getSource() == newOptionsPanel.getAddButton()) {
            newOptionsPanel.actionEventInAddButton(e);
            contentPanelHeight = questionContentPanel.getHeight() + 50;

        } else if (e.getSource() != addImageButton) {
            newOptionsPanel.actionEventInDeleteButton(e);
            contentPanelHeight = questionContentPanel.getHeight() - 50;
            
            if (contentPanelHeight < DEFAULT_HEIGHT) {
                return;
            } 
        }
        
        newOptionsPanel.addActionListenerDeleteButtons(this);
        questionContentPanel.setPreferredSize(new Dimension(WIDTH, contentPanelHeight));
        questionContentPanel.validate();
        questionContentPanel.repaint();
    }

    private void actionEventInAddImageButton(ActionEvent e) {
        if (e.getSource() == addImageButton) {
            System.out.println("This should ask to add Image");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actionEventInNewOptionsPanel(e);
        actionEventInAddImageButton(e);
    }

}
