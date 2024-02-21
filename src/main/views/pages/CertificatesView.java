package main.views.pages;
import main.utils.Palette;
import main.views.components.Listing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.FlowLayout;


import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JSeparator;

import main.controllers.RequestCertificateController;


public class CertificatesView extends HelpBarTemplateView {
    Listing certificateListing;
    List<String> certificates;
    List<JButton> requestCertificateButtons;
    RequestCertificateController requestCertificateController;


    public CertificatesView() {

        buildFrame("CertificatesView");
        paintBorders();
        paintContentPanel();
        
        getRequestCertificateButtons();
        addActionListener();
        
    }

    protected void paintContentPanel(){
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(944, 560));
        contentPanel.setBackground(Palette.instance().getWhite());

        paintTitlePanel(contentPanel);
        
        paintCertificatesListing(contentPanel); 

    
        this.add(contentPanel, BorderLayout.CENTER);
    }

    protected void paintTitlePanel(JPanel contentPanel) {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Palette.instance().getWhite());
        titlePanel.setPreferredSize(new Dimension(944, 60));
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        contentPanel.add(titlePanel, BorderLayout.NORTH);
        TitleContainer(titlePanel);
        paintTitleSeparator(titlePanel);        
    }

    protected void paintTitleSeparator(JPanel titlePanel) {
        JSeparator line = new JSeparator();
        line.setForeground(Palette.instance().getLightGray());
        line.setBackground(Palette.instance().getLightGray());
        titlePanel.add(line);
    }

    private void TitleContainer(JPanel titlePanel) {
        JPanel titleButtonContainer = new JPanel();
        titleButtonContainer.setMaximumSize(new Dimension(1500, 58));
        titleButtonContainer.setLayout(new BoxLayout(titleButtonContainer, BoxLayout.X_AXIS));
        titleButtonContainer.setBackground(Palette.instance().getWhite());

        paintTitleLabel(titleButtonContainer);

        
        titlePanel.add(titleButtonContainer);
    }

    protected void paintTitleLabel(JPanel titleButtonContainer) {
        JLabel title = new JLabel();
        title.setText("Mis Certificados");
        title.setFont(new Font("Nunito Sans", Font.BOLD, 25));
        title.setPreferredSize(new Dimension(944, 58));
        title.setMaximumSize(new Dimension(2048, 58));
        title.setVerticalAlignment(JLabel.BOTTOM);

        titleButtonContainer.add(title, FlowLayout.LEFT);
    }
    

    protected void paintCertificatesListing(JPanel contentPanel) {
        requestCertificateController = new RequestCertificateController();
        certificates = new ArrayList<String>();
        certificates = requestCertificateController.showCertificates();

        certificateListing = new Listing(certificates, "Solicitar Certificado");
        contentPanel.add(certificateListing);
    }

    private void getRequestCertificateButtons() {
        requestCertificateButtons = certificateListing.getListingButtons();
    }

    private void addActionListener() {
        for (int i = 0; i < requestCertificateButtons.size(); i++) {
            requestCertificateButtons.get(i).addActionListener(this);
        }
    }

    private void actionEventInCourseListing(ActionEvent e) {
        for (int i = 0; i < requestCertificateButtons.size(); i++) {
            if (e.getSource() == requestCertificateButtons.get(i)) {
                Frame.instance().setView(new CertificateView(requestCertificateController, certificates.get(i) ));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actionEventInCourseListing(e);
    }
}
