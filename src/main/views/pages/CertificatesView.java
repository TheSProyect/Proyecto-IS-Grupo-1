package main.views.pages;
import main.views.templates.Frame;
import main.views.components.NavBar;
import main.views.components.AdminNavBar;
import main.views.components.Button;
import main.views.components.HelpBar;
import main.views.components.Listing;


import main.data.Palette;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.FlowLayout;


import javax.swing.JLabel;
import javax.swing.JPanel;
    import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JSeparator;


public class CertificatesView extends Frame implements ActionListener {
    JPanel titlePanel;
    JPanel contentPanel;
    JLabel title;
    NavBar navBar;
    Button button;
    HelpBar helpBar;
    JButton createExam;
    JPanel titleButtonContainer;
    Listing certificateListing;
    List<String> certificates;
    List<JButton> requestCertificateButtons;

    public CertificatesView() {
        inicializeCertificates();

        buildFrame();
        paintBorders();
        paintContentPanel();

        this.pack();
        
        getRequestCertificateButtons();
        addActionListener();
    }

    protected void buildFrame() {
        createFrame("CertificatesView");
        this.setLayout(new BorderLayout());
    }

    protected void paintNavBar() {
        navBar = new NavBar();
        this.add(navBar, BorderLayout.NORTH);
    }

    protected void paintBorders() {
        paintNavBar();
        
        helpBar = new HelpBar();
        this.add(helpBar, BorderLayout.SOUTH);

        JPanel borderPanel = new JPanel();
        borderPanel.setPreferredSize(new Dimension(40, 560));
        borderPanel.setBackground(Palette.instance().getWhite());
        this.add(borderPanel, BorderLayout.WEST);

        borderPanel = new JPanel();
        borderPanel.setPreferredSize(new Dimension(40, 560));
        borderPanel.setBackground(Palette.instance().getWhite());
        this.add(borderPanel, BorderLayout.EAST);

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
        navBar.getHomeButton().addActionListener(this);

        for (int i = 0; i < requestCertificateButtons.size(); i++) {
            requestCertificateButtons.get(i).addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == navBar.getHomeButton()) {
            this.dispose();
            ExamsView.instance().setVisible(true);
        } else {
            this.dispose();
            new CertificateView();
        }
    }
}
