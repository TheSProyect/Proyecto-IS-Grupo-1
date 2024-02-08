package main.views.components;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.Border;

import main.utils.Palette;
import main.views.pages.CertificatesView;
import main.views.pages.ExamsView;
import main.views.pages.Frame;
import main.views.pages.LogInView;

public class NavBar extends JPanel implements ActionListener {
    JLabel appName;
    JLabel linkText;
    JToolBar linkNavbar;
    NavBarButton homeButton;
    NavBarButton certificateButton;
    NavBarButton logOutButton;
    
    public NavBar() {
        this.setPreferredSize(new Dimension(1024, 80));
        this.setBackground(Palette.instance().getWhite());
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        paintAppName();

        paintLinkNavBar();

        this.add(appName);
        this.add(linkNavbar);
    }

    private void paintAppName() {
        appName = new JLabel();
        appName.setPreferredSize(new Dimension(220, 80));
        
        ImageIcon icon = new ImageIcon ("src/assets/Logo_Header.png");
        appName.setIcon(icon);
        
        appName.setHorizontalAlignment(JLabel.CENTER);

    }

    private void paintLinkNavBar() {
        linkNavbar = new JToolBar();
        linkNavbar.setPreferredSize(new Dimension(744, 80));
        linkNavbar.setBackground(Palette.instance().getWhite());
        linkNavbar.setFloatable(false);
        linkNavbar.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 20));

        Border border = BorderFactory.createLineBorder(Palette.instance().getWhite());
        linkNavbar.setBorder(border);

        this.paintNavBarButtons();
    }
    
    protected void paintNavBarButtons() {
        homeButton = new NavBarButton("Mis<br>Examenes", "Home_Icon.png", false);
        homeButton.addActionListener(this);
        linkNavbar.add(homeButton);
        linkNavbar.addSeparator();

        certificateButton = new NavBarButton("Solicitar<br>Certificado", "Paperclip_Icon.png", false);
        certificateButton.addActionListener(this);
        linkNavbar.add(certificateButton);
        linkNavbar.addSeparator();

        logOutButton = new NavBarButton("Cerrar<br>sesión", "LogOut_Icon.png", true);
        logOutButton.addActionListener(this);
        linkNavbar.add(logOutButton);
        linkNavbar.addSeparator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homeButton) {
            Frame.instance().setView(ExamsView.instance());
            Frame.instance().setTitle("ExamsView");

        } else if (e.getSource() == certificateButton) {
            Frame.instance().setView(new CertificatesView());
            Frame.instance().setTitle("CertificatesView");

        } else if (e.getSource() == logOutButton) {
            ExamsView.deleteInstance();
            Frame.instance().setView(new LogInView());
        }
    }
}
