package main.views.pages;

import main.views.components.IconButton;
import main.views.components.ResultsBlock;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.io.File;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


import main.controllers.RequestCertificateController;
import main.utils.Directory;
import main.utils.Palette;

public class CertificateView extends HelpBarTemplateView{
    GridBagConstraints constraints = new GridBagConstraints();
    RequestCertificateController requestCertificateController;
    IconButton downloadButton; 
    String username;
    String course;
    String teacher;


    
    public CertificateView(RequestCertificateController requestCertificateController, String Course){
        this.requestCertificateController = requestCertificateController;
        this.course = Course;

        buildFrame("CertificateView");
        paintBorders();

        inicializeCertificate();
        paintContentPanel();
    }

    private void paintContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setPreferredSize(new Dimension(500, 560));
        contentPanel.setBackground(Palette.instance().getWhite());

        paintCertificateHeader(contentPanel);
        paintTitleSeparator(Palette.instance().getLightGray(), contentPanel, 1);

        paintName(contentPanel);
        paintTitleSeparator(Palette.instance().getYellow(), contentPanel, 4);

        paintCourse(contentPanel);
        paintSignature (contentPanel);
        
        paintScore(contentPanel);
        paintTeacher(contentPanel);
        paintTitleSeparator(Palette.instance().getLightGray(), contentPanel, 10);

        paintDownloadButton(contentPanel);

        this.add(contentPanel);
    }

    private void paintCertificateHeader(JPanel contentPanel) {
        JTextPane text = new JTextPane();
        text.setText("Certificado de participación");
        text.setFont(new Font("Nunito Sans", Font.BOLD, 28));
        text.setPreferredSize(new Dimension(500, 35));
        text.setEditable(false);

        centerText(text);
        setConstraints(0);

        contentPanel.add(text, constraints);
    }
    
    private void paintText( JPanel contentPanel) {
        JTextPane text = new JTextPane();
        text.setText("Se le otorga el presente a:");
        text.setPreferredSize(new Dimension(350, 22));
        text.setForeground(Palette.instance().getBlue());
        text.setFont(new Font("Nunito Sans", Font.PLAIN, 15));

        centerText(text);
        setConstraints(2);
        constraints.insets = new Insets(20, 0, 0, 0);

        contentPanel.add(text, constraints);
    }

    private void paintName( JPanel contentPanel) {

        paintText(contentPanel);

        JTextPane text = new JTextPane();
        text.setText(username);
        text.setFont(new Font("Nunito Sans", Font.BOLD, 45));
        text.setPreferredSize(new Dimension(500, 55));
        text.setEditable(false);
        text.setForeground(Palette.instance().getBlue());
        text.setLayout(new GridBagLayout());

        centerText(text);
        setConstraints(3);

        contentPanel.add(text, constraints);
    }

    private void paintTitleSeparator(Color color, JPanel contentPanel, int row) {
        
        JSeparator line = new JSeparator();
        line.setForeground(color);
        line.setBackground(color);
        line.setMinimumSize(new Dimension (10,2));

        setConstraints(row);
        constraints.insets = new Insets(0, 55, 20, 58);
        constraints.fill = GridBagConstraints.BOTH;
        contentPanel.add(line, constraints);
    }

    private void paintCourse(JPanel contentPanel) {
        JTextPane text = new JTextPane();
        text.setText("Por su participación en el examen para aspirar a \n" +  course);
        text.setPreferredSize(new Dimension(350, 45));
        text.setForeground(Palette.instance().getBlack());
        text.setFont(new Font("Nunito Sans", Font.PLAIN, 15));

        centerText(text);
        setConstraints(5);

        contentPanel.add(text, constraints);
    }

    private void paintSignature (JPanel contentPanel) {
        String imageDirectory = Directory.instance().getDirectoryTeachers() + File.separator +teacher + File.separator + "Signature.png";
        ImageIcon icon = new ImageIcon(imageDirectory);

        JLabel lbl = new JLabel();
        lbl.setIcon(icon);
        setConstraints(6);

        contentPanel.add(lbl, constraints);       
    }

    private void paintScore(JPanel contentPanel) {
        ResultsBlock results = new ResultsBlock();
        results.paintResults(requestCertificateController.getResultAnswersController(), requestCertificateController.getQuestionsExamController());
        

        setConstraints(7);
        constraints.insets = new Insets(20, 0, 10, 0);


        contentPanel.add(results, constraints);
    }

    private void paintTeacher(JPanel contentPanel) {
        JTextPane text = new JTextPane();
        text.setText("Examen realizado por \n Profesor/a: " +  teacher);
        text.setPreferredSize(new Dimension(350, 40));
        text.setForeground(Palette.instance().getBlack());
        text.setFont(new Font("Nunito Sans", Font.BOLD, 15));

        centerText(text);
        setConstraints(9);

        contentPanel.add(text, constraints);
    }

    private void inicializeCertificate() {
        requestCertificateController.searchFolderStudent(course);
        
        username = requestCertificateController.getNameStudentController();
        teacher = requestCertificateController.getNameTeacherController();
    }

     private void paintDownloadButton(JPanel contentPanel) {

        downloadButton = new IconButton("Descargar", "Download_Icon.png");
        downloadButton.addActionListener(this);

        setConstraints(11);
        constraints.fill = GridBagConstraints.VERTICAL;
        contentPanel.add(downloadButton, constraints);
    }

    private void centerText (JTextPane text) {
        StyledDocument doc = text.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }

    private void setConstraints (int row) {
        constraints.gridx = 0;
        constraints.gridy = row;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.fill = GridBagConstraints.RELATIVE;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == downloadButton) {
            requestCertificateController.createPDF();
        }

    }
}