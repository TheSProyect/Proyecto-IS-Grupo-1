package main.views.pages;
import main.utils.Palette;
import main.views.components.Button;
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


public class CertificatesView extends HelpBarTemplateView {
    private static CertificatesView certificatesView;

    JPanel titlePanel;
    JPanel contentPanel;
    JLabel title;
    Button button;
    JButton createExam;
    JPanel titleButtonContainer;
    Listing certificateListing;
    List<String> certificates;
    List<JButton> requestCertificateButtons;

    public static CertificatesView instance() {
		if (certificatesView == null){
			certificatesView = new CertificatesView();
		}
		return certificatesView;
	}

    public static void deleteInstance() {
        certificatesView = null;
    }

    public CertificatesView() {
        inicializeCertificates();

        buildFrame("CertificatesView");
        paintBorders();
        paintContentPanel();
        
        getRequestCertificateButtons();
        addActionListener();
    }

    protected void paintContentPanel(){
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(944, 560));
        contentPanel.setBackground(Palette.instance().getWhite());

        paintTitlePanel();
        
        paintCertificatesListing(); 

    
        this.add(contentPanel, BorderLayout.CENTER);
    }

    protected void paintTitlePanel() {
        createTitlePanel();
        TitleContainer();
        paintTitleSeparator();        
    }

    protected void createTitlePanel() {
        titlePanel = new JPanel();
        titlePanel.setBackground(Palette.instance().getWhite());
        titlePanel.setPreferredSize(new Dimension(944, 60));
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        contentPanel.add(titlePanel, BorderLayout.NORTH);
    }

    protected void paintTitleSeparator() {
        JSeparator line = new JSeparator();
        line.setForeground(Palette.instance().getLightGray());
        line.setBackground(Palette.instance().getLightGray());
        titlePanel.add(line);
    }

    private void TitleContainer() {
        titleButtonContainer = new JPanel();
        titleButtonContainer.setMaximumSize(new Dimension(1500, 58));
        titleButtonContainer.setLayout(new BoxLayout(titleButtonContainer, BoxLayout.X_AXIS));
        titleButtonContainer.setBackground(Palette.instance().getWhite());

        paintTitleLabel();

        
        titlePanel.add(titleButtonContainer);
    }

    protected void paintTitleLabel() {
        title = new JLabel();
        title.setText("Mis Certificados");
        title.setFont(new Font("Nunito Sans", Font.BOLD, 25));
        title.setPreferredSize(new Dimension(944, 58));
        title.setMaximumSize(new Dimension(2048, 58));
        title.setVerticalAlignment(JLabel.BOTTOM);

        titleButtonContainer.add(title, FlowLayout.LEFT);
    }

    private void paintCertificatesListing() {
        certificateListing = new Listing(certificates, "Solicitar Certificado");
        contentPanel.add(certificateListing);
    }

    private void inicializeCertificates() {
        // este metodo es de prueba. Terrible lo se
        // lo que esté entre comentarios no va btw

        certificates = new ArrayList<String>();
        // prueba {
        certificates.add("Curso 1");
        certificates.add("Curso 2");
        certificates.add("Curso 3");
        certificates.add("Curso 4");
        //prueba    
    }

    private void getRequestCertificateButtons() {
        requestCertificateButtons = certificateListing.getListingButtons();
    }

    private void addActionListener() {
        addActionListenerNavbar();

        for (int i = 0; i < requestCertificateButtons.size(); i++) {
            requestCertificateButtons.get(i).addActionListener(this);
        }
    }

    private void actionEventInCourseListing(ActionEvent e) {
        for (int i = 0; i < requestCertificateButtons.size(); i++) {
            if (e.getSource() == requestCertificateButtons.get(i)) {
                Frame.instance().setView(new CertificateView());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actionEventInNavBar(e);
        actionEventInCourseListing(e);
    }
}
