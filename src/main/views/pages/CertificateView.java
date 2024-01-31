package main.views.pages;

import main.models.Certificate;
import main.views.components.IconButton;
import main.views.components.NavBar;
import main.views.pages.CertificatesView;
import main.views.templates.Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import main.controllers.RequestCertificateController;
import main.data.Palette;

public class CertificateView extends Frame{
    JPanel contentPanel;
    
    public CertificateView(){
        buildFrame();
        paintBorders();
        paintContentPanel();
    }
    private void buildFrame() {
        createFrame("CertificateView");
        this.setLayout(new BorderLayout());
    }
    private void paintNavBar() {
        NavBar navBar = new NavBar();
        this.add(navBar, BorderLayout.NORTH);
    }

    private void paintBorders() {
        paintNavBar();

        JPanel borderPanel = new JPanel();
        borderPanel.setPreferredSize(new Dimension(40, 560));
        borderPanel.setBackground(Palette.instance().getWhite());
        this.add(borderPanel, BorderLayout.WEST);

        borderPanel = new JPanel();
        borderPanel.setPreferredSize(new Dimension(40, 560));
        borderPanel.setBackground(Palette.instance().getWhite());
        this.add(borderPanel, BorderLayout.EAST);
    }

    private void paintContentPanel() {
        contentPanel = new JPanel();
        JLabel text;
        JPanel separator;
        IconButton downloadButton = new IconButton("Descargar", "DownloadIcon.png");
        GridBagConstraints gbc=new GridBagConstraints();
        
        // contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setPreferredSize(new Dimension(944, 560));
        contentPanel.setBackground(Palette.instance().getWhite());
        int separatorFullWidth = 300;
        
        
        text = paintText(48, "Certificado de participación");
        text.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(text, gbc);

        separator = paintTitleSeparator(Palette.instance().getLightGray(), separatorFullWidth);
        gbc.gridy = 1;
        contentPanel.add(separator, gbc);

        text = paintText(20, "Se le otorga el presente a:");
        text.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        gbc.gridy = 2;
        contentPanel.add(text, gbc);

        text = paintText(64, "Nombre Apellido");
        text.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        gbc.gridy = 3;
        contentPanel.add(text, gbc);
        
        separator = paintTitleSeparator(Palette.instance().getYellow(), separatorFullWidth);
        gbc.gridy = 4;
        contentPanel.add(separator, gbc);

        text = paintText(20, "Por su participación en el examen para aspirar a");
        text.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        gbc.gridy = 5;
        contentPanel.add(text, gbc);
        text = paintText(20, "Java SE - Java Associate Programmer");
        text.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
        gbc.gridy = 6;
        contentPanel.add(text, gbc);

        separator = paintTitleSeparator(Palette.instance().getLightGray(), separatorFullWidth);
        gbc.gridy = 7;
        contentPanel.add(separator, gbc);

        text = paintText(20, "Examen realizado por:");
        text.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        gbc.gridy = 8;
        contentPanel.add(text, gbc);
        text = paintText(20, "Profesor/a: Paula Herrero");
        text.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        gbc.gridy = 9;
        contentPanel.add(text, gbc);

        separator = paintTitleSeparator(Palette.instance().getLightGray(), separatorFullWidth);
        gbc.gridy = 10;
        contentPanel.add(separator, gbc);

        separator = paintTitleSeparator(Palette.instance().getOffWhite(), separatorFullWidth);
        
        gbc.gridy = 11;
        contentPanel.add(separator, gbc);
        gbc.gridy = 12;
        contentPanel.add(downloadButton, gbc);

        this.add(contentPanel, BorderLayout.CENTER);
    }
    private JLabel paintText(int size, String _text) {
        JLabel text = new JLabel();
        text.setText("<html>" + _text + "</html>");
        text.setFont(new Font("Nunito Sans", Font.BOLD, size));
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setVerticalAlignment(JLabel.BOTTOM);

        return text;   
    }
    private JPanel paintTitleSeparator(Color color, int width) {
        
        JPanel line = new JPanel();
        line.setForeground(color);
        line.setBackground(color);
        line.setPreferredSize(new Dimension(width, 3));
        return line;
    }
  //  private void show(Certificate)(){}
}
