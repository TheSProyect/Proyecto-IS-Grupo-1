package main.views.pages;

import main.views.components.IconButton;
import main.views.components.NavBar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import main.controllers.RequestCertificateController;
import main.utils.Palette;

public class CertificateView extends JPanel implements ActionListener{
    JPanel contentPanel;
    
    NavBar navBar;
    
    public CertificateView(){
        buildFrame();
        paintNavBar();
        paintContentPanel();

        addActionListener();
    }

    private void buildFrame() {
        Frame.instance().setTitle("CertificateView");
        this.setLayout(new BorderLayout());
    }

    private void paintNavBar() {
        navBar = new NavBar();
        this.add(navBar, BorderLayout.NORTH);
    }

    private void paintContentPanel() {
        IconButton downloadButton = new IconButton("Descargar", "Download_Icon.png");
        GridBagConstraints gbc = new GridBagConstraints();
        int separatorFullWidth = 668;
        int separatorMediumWidth = 460;
        Color textColor = Palette.instance().getBlack();
        Color textBlueColor = Palette.instance().getBlue();

        buildContentPanel();
        
        paintText(48, "Certificado de participación", textColor, 0, 10, 0);

        paintSeparator(Palette.instance().getLightGray(), separatorFullWidth, 1);

        paintText(20, "Se le otorga el presente a:",textBlueColor, 2, 0, 40);
        paintText(64, "Nombre Apellido", textBlueColor, 3, 10, 0);
        
        paintSeparator(Palette.instance().getYellow(), separatorFullWidth, 4);

        paintText(20, "Por su participación en el examen para aspirar a", textColor, 5, 0, 40);

        paintText(20,  "Java SE - Java Associate Programmer", textColor, 6, 40, 0);

        paintSeparator(Palette.instance().getLightGray(), separatorMediumWidth, 7);

        paintNote();
        
        paintText(20,  "Examen realizado por:", textColor, 10, 0, 20);
        
        paintText(20,  "Profesor/a: Paula Herrero", textColor, 11, 20, 0);

        paintSeparator(Palette.instance().getLightGray(), separatorMediumWidth, 12);

        
        gbc.gridy = 13;
        gbc.insets = new Insets(20, 0, 0, 0); 
        contentPanel.add(downloadButton, gbc);

        paintScrollPanel();
    }
    private void paintScrollPanel(){
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);

        this.add(scrollPane, BorderLayout.CENTER);
    }
    private void paintNote(){
        JPanel container = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        ImageIcon icon = new ImageIcon("assets/folderIcon.png");
        JLabel text = new JLabel(icon);
        Color textBlueColor = Palette.instance().getBlue();

        container.setLayout(new GridBagLayout());
        container.setBackground(Palette.instance().getOffWhite());
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        container.add(text, gbc);

        gbc.gridx = 1;
        gbc.gridheight = 1;
        text = setText(15, "Nota final", textBlueColor);
        container.add(text, gbc);

        gbc.gridy = 1;
        text = setText(32, "15 / 20", textBlueColor);
        container.add(text, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2; 
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(container, gbc);
    }
   
    private void buildContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setPreferredSize(new Dimension(944, 560));
        contentPanel.setBackground(Palette.instance().getWhite());
    }

    private JLabel setText(int size, String _text, Color color) {
        JLabel text = new JLabel();
        text.setText("<html>" + _text + "</html>");
        text.setFont(new Font("Nunito Sans", Font.BOLD, size));
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setVerticalAlignment(JLabel.BOTTOM);
        text.setForeground(color);

        return text;   
    }
    private void paintText(int size, String _text, Color color, int row, int borderBottom, int borderTop){
        JLabel text = setText(size, _text, color);
        GridBagConstraints gbc = new GridBagConstraints();

        text.setBorder(BorderFactory.createEmptyBorder(borderTop, 0, borderBottom, 0));
        gbc.gridx = 0;
        gbc.gridy = row;
        contentPanel.add(text, gbc);
    }

    private void paintSeparator(Color color, int width, int row) {
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel separator = new JPanel();
        separator.setForeground(color);
        separator.setBackground(color);
        separator.setPreferredSize(new Dimension(width, 3));
        separator.setMaximumSize(new Dimension(width, 3));

        gbc.gridy = row;
        contentPanel.add(separator, gbc);
    }
  //  private void show(Certificate)(){}
    private void addActionListener() {
        navBar.getHomeButton().addActionListener(this);
        navBar.getCertificateButton().addActionListener(this);
        navBar.getLogOutButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == navBar.getHomeButton()) {
            Frame.instance().setView(ExamsView.instance());
            Frame.instance().setTitle("ExamsView");
        } else if (e.getSource() == navBar.getCertificateButton()) {
            Frame.instance().setView(CertificatesView.instance());
            Frame.instance().setTitle("CertificatesView");
        } else if (e.getSource() == navBar.getLogOutButton()) {
            CertificatesView.deleteInstance();
            ExamsView.deleteInstance();
            Frame.instance().setView(new LogInView());
        }
    }
}