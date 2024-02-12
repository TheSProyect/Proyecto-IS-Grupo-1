package main.views.components;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;

import main.utils.Palette;

public class NewQuestionPanel extends QuestionPanel {
    NewOptionsPanel newOptionsPanel;
    IconButton addImageButton;
    IconButton deleteQuestionButton;
    JTextPane questionField;
    JTextField domainField;

    public NewQuestionPanel(ActionListener listener) {
        paintQuestionField();
        paintFieldLabel("Dominio", 0);
        paintDomainField();
        paintFieldLabel("Inserta el código", 2);
        paintCodeField(null);
        paintAddImageButton();
        paintFieldLabel("Opciones", 5);
        paintNewOptionsPanel();
        paintDeleteButton(listener);
    }   

    private void paintQuestionField() {
        questionField = new JTextPane();
        questionField.setForeground(Palette.instance().getBlack());
        questionField.setFont(new Font("Nunito Sans", Font.BOLD, 20));
        questionField.setBorder(createQuestionFieldBorder());

        this.add(questionField, createQuestionConstraints());
    }

    protected GridBagConstraints createQuestionConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 1.0;
        constraints.gridwidth = 4;
        constraints.insets = new Insets(10, 10, 10, 20);
        constraints.fill = GridBagConstraints.BOTH;

        return constraints;
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
        constraints.insets = new Insets(5, 5, 5, 10);
        constraints.fill = GridBagConstraints.BOTH;

        return constraints;
    }

    private void paintDomainField() {
        domainField = new JTextField();
        domainField.setForeground(Palette.instance().getGray());
        domainField.setBackground(Palette.instance().getOffWhite());
        domainField.setFont(new Font("Nunito Sans", Font.PLAIN, 20));
        domainField.setBorder(createQuestionFieldBorder());

        questionContentPanel.add(domainField, createLabelsConstraints(1));
    }

    private void paintAddImageButton() {
        addImageButton = new IconButton("Insertar imagen", "Plus_Icon.png");
        addImageButton.setBackground(Palette.instance().getYellow());
        addImageButton.setPreferredSize(new Dimension(190, 30));
        addImageButton.setMinimumSize(new Dimension(190, 30));
        addImageButton.addActionListener(this);

        Border border = BorderFactory.createLineBorder(Palette.instance().getYellow());
        addImageButton.setBorder(border);

        questionContentPanel.add(addImageButton, createAddImageButtonConstraints());
    }

    private GridBagConstraints createAddImageButtonConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.insets = new Insets(5, 0, 5, 0);

        return constraints;
    }

    private void paintNewOptionsPanel() {
        newOptionsPanel = new NewOptionsPanel();

        newOptionsPanel.addActionListenerDeleteButtons(this);
        newOptionsPanel.getAddButton().addActionListener(this);

        questionContentPanel.add(newOptionsPanel, createOptionPanelConstraints());
    }

    protected GridBagConstraints createOptionPanelConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 6;
        constraints.gridwidth = 4;
        constraints.insets = new Insets(0, 0, 0, 10);
        constraints.fill = GridBagConstraints.BOTH;

        return constraints;
    }

    private void paintDeleteButton(ActionListener listener) {
        deleteQuestionButton = new IconButton("Eliminar", "Delete_White_Icon.png");
        deleteQuestionButton.setBackground(Palette.instance().getRed());
        deleteQuestionButton.setPreferredSize(new Dimension(190, 30));
        deleteQuestionButton.setMinimumSize(new Dimension(190, 30));
        deleteQuestionButton.addActionListener(listener);

        Border border = BorderFactory.createLineBorder(Palette.instance().getRed());
        deleteQuestionButton.setBorder(border);

        questionContentPanel.add(deleteQuestionButton, createDeleteButtonConstraints());
    }

    private GridBagConstraints createDeleteButtonConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 3;
        constraints.gridy = 9;
        constraints.insets = new Insets(20, 0, 5, 10);

        return constraints;
    }

    private void actionEventInNewOptionsPanel(ActionEvent e) {
        // final int DEFAULT_HEIGHT = 850;
        // final int WIDTH = 544;
        // int contentPanelHeight = DEFAULT_HEIGHT;

        if (e.getSource() == newOptionsPanel.getAddButton()) {
            newOptionsPanel.actionEventInAddButton(e);
            // contentPanelHeight = questionContentPanel.getHeight() + 50;

        } else if (e.getSource() != addImageButton) {
            newOptionsPanel.actionEventInDeleteButton(e);
            // contentPanelHeight = questionContentPanel.getHeight() - 50;
            
            // if (contentPanelHeight < DEFAULT_HEIGHT) {
            //     return;
            // } 
        }
        
        newOptionsPanel.addActionListenerDeleteButtons(this);
        // questionContentPanel.setPreferredSize(new Dimension(WIDTH, contentPanelHeight));
        questionContentPanel.validate();
        questionContentPanel.repaint();
    }

    private void actionEventInAddImageButton(ActionEvent e) {
        if (e.getSource() == addImageButton) {
            System.out.println("This should ask to add Image");
        }
    }

    public IconButton getDeleteButton() {
        return deleteQuestionButton;
    }

    public List<String> getOptionsText() {
        return newOptionsPanel.getOptionsText();
    }

    public String getQuestionText() {
        return questionField.getText();
    }

    public String getDomainText() {
        return domainField.getText();
    }

    public String getCode() {
        return codeField.getCode();
    }

    public String getExplication() {
        return explicationPanel.getExplicationText();
    }

    public boolean checkQuestionIsComplete() {
        if (questionField.getText() == "") {
            return false;
        } else if (domainField.getText() == "") {
            return false;
        } else if(!newOptionsPanel.checkOptionsAreComplete()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actionEventInNewOptionsPanel(e);
        actionEventInAddImageButton(e);
    }

}
