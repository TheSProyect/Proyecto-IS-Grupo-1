package main.views.components;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.border.Border;

import main.utils.Palette;
import main.views.components.ExamInfoPanel;


public class ExamInfoPanel extends JPanel{
    TextArea examNameField;
    TextArea typeField;
    TextArea courseField;
    TextArea descriptionField;
    static JSpinner timeSpinner;
    JPanel examInfoPanel;

    public ExamInfoPanel(JPanel contentPanel) {
        examInfoPanel = new JPanel();
        examInfoPanel.setPreferredSize(new Dimension(600, 400 ));
        examInfoPanel.setBackground(Palette.instance().getWhite());
        examInfoPanel.setLayout(new GridBagLayout());

        paintFieldLabel("Nombre del examen", 0, 0);
        paintExamNameField();
        paintFieldLabel("Tipo del examen", 2, 0);
        paintTypeField();
        paintFieldLabel("Curso", 2, 3);
        paintCourseField(); 
        paintFieldLabel("Duración", 4 , 0 );
        paintDurationField();
        paintFieldLabel("Descripción", 4, 1);
        paintDescriptionField();
        
        contentPanel.add(examInfoPanel);
    }
           
    private void paintFieldLabel(String label, int gridy, int gridx) {
        JLabel FieldLabel =  new JLabel(label);
        FieldLabel.setForeground(Palette.instance().getGray());
        FieldLabel.setFont(new Font("Nunito Sans", Font.BOLD, 17));

        examInfoPanel.add(FieldLabel, createLabelsConstraints(gridy, gridx));
    }

    private void paintExamNameField() {
        examNameField = new TextArea(null);
        examNameField.setForeground(Palette.instance().getBlack());
        examNameField.setFont(new Font("Nunito Sans", Font.BOLD, 20));
        examNameField.setBorder(createBorder());

        examInfoPanel.add(examNameField, createNameConstraints());
    }
    
    private void paintTypeField() {
        typeField = new TextArea(null);
        typeField.setForeground(Palette.instance().getBlack());
        typeField.setFont(new Font("Nunito Sans", Font.BOLD, 20));
        typeField.setBorder(createBorder());

        examInfoPanel.add(typeField, createTypeConstraints());
    }

    private void paintCourseField() {
        courseField = new TextArea(null);
        courseField.setForeground(Palette.instance().getBlack());
        courseField.setFont(new Font("Nunito Sans", Font.BOLD, 20));
        courseField.setBorder(createBorder());

        examInfoPanel.add(courseField, createCourseConstraints());
    }

    private SpinnerDateModel inicializeDuration() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);   
        Date initialDate = calendar.getTime();
            
        SpinnerDateModel model = new SpinnerDateModel();
        model.setValue(initialDate);
        model.setCalendarField(Calendar.MINUTE);

        return model;
    }

     private void paintDurationField () {
        timeSpinner= new JSpinner(inicializeDuration());
        timeSpinner.setForeground(Palette.instance().getBlack());
        timeSpinner.setBackground(Palette.instance().getWhite());
        timeSpinner.setFont(new Font("Nunito Sans", Font.BOLD, 20));
        timeSpinner.setBorder(createBorder());

        JSpinner.DateEditor editor = new JSpinner.DateEditor(timeSpinner, "HH:mm:00");
        timeSpinner.setEditor(editor);

        examInfoPanel.add(timeSpinner, createDurationConstraints());
        }

    private void paintDescriptionField() {
        descriptionField = new TextArea(null);
        descriptionField.setForeground(Palette.instance().getBlack());
        descriptionField.setFont(new Font("Nunito Sans", Font.PLAIN, 15));
        descriptionField.setBorder(createBorder());
    
        examInfoPanel.add(descriptionField, createDescriptionConstraints());
        }

    private Border createBorder() {
        Border outside = BorderFactory.createLineBorder(Palette.instance().getLightGray());
        Border inside = BorderFactory.createEmptyBorder(5, 10, 5, 10);
        Border border = BorderFactory.createCompoundBorder(outside, inside);

        return border;
    }

    protected GridBagConstraints createNameConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 1.0;
        constraints.gridwidth = 6;
        constraints.insets = new Insets(10, 10, 10, 20);
        constraints.fill = GridBagConstraints.BOTH;

        return constraints;
    }

    
    protected GridBagConstraints createTypeConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth=3;
        constraints.weightx = 0.4;
        constraints.insets = new Insets(10, 10, 10, 20);
        constraints.fill = GridBagConstraints.BOTH;

        return constraints;
    }

    protected GridBagConstraints createCourseConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 3;
        constraints.gridwidth=3;
        constraints.weightx = 0.4;
        constraints.insets = new Insets(10, 10, 10, 20);
        constraints.fill = GridBagConstraints.BOTH;

        return constraints;
    }

    protected GridBagConstraints createDurationConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth=1;
        constraints.gridheight = 1;
        constraints.weighty = 0;
        constraints.insets = new Insets(10, 10, 10, 20);
        constraints.fill = GridBagConstraints.BOTH;

        return constraints;
    }

    
    protected GridBagConstraints createDescriptionConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 5;
        constraints.gridwidth = 5;
        constraints.gridheight = 3;
        constraints.weighty = 1.0;
        constraints.insets = new Insets(10, 10, 10, 20);
        constraints.fill = GridBagConstraints.BOTH;

        return constraints;
    }

    private GridBagConstraints createLabelsConstraints(int gridy, int gridx) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = gridy;
        constraints.gridx = gridx;
        constraints.gridwidth = 5;
        constraints.insets = new Insets(5, 5, 5, 10);
        constraints.fill = GridBagConstraints.BOTH;

        return constraints;
    }

    public String getExamName() {
        return examNameField.getText();
    }

    public String getType() {
        return typeField.getText();
    }

    public String getCourse() {
        return courseField.getText();
    }

    public static int getDuration() {
        Date date = (Date) timeSpinner.getValue();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int minutes = calendar.get(Calendar.MINUTE) + calendar.get(Calendar.HOUR) * 60;

        return minutes;

    }

    public ArrayList<String> getDescription() {
        String description = descriptionField.getText();
        ArrayList<String> descriptionList = new ArrayList<>(Arrays.asList(description.split("\n")));
        return descriptionList;
    }

    
    public boolean checkFieldsAreComplete() {
        if (examNameField.isEmpty()) {
            return false;
    } else if (typeField.isEmpty()) {
            return false;
    } else if (courseField.isEmpty()) {
            return false;
    }  else if(descriptionField.isEmpty()) {
            return false;
    }  else if (getDuration() == 0) {
            return false;
    }  
    return true;
    }
}
