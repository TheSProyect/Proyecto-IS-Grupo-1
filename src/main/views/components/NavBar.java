package main.views.components;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import main.Palette;

public class NavBar extends JPanel{
    JLabel appName;
    JLabel linkText;
    JToolBar linkNavbar;
    NavBarButton homeButton;
    NavBarButton adminButton;
    NavBarButton certifycateButton;
    NavBarButton logOutButton;
    
    public NavBar() {
        this.setPreferredSize(new Dimension(1024, 80));
        this.setBackground(Palette.instance().getWhite());
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        drawAppName();

        drawLinkNavBar(true);

        this.add(appName);
        this.add(linkNavbar);
    }

    private void drawAppName() {
        appName = new JLabel();
        appName.setText("TéchneLogic");
        appName.setFont(new Font("Nunito Sans", Font.BOLD, 20));
        appName.setForeground(Palette.instance().getBlack());
        appName.setPreferredSize(new Dimension(200, 80));
        appName.setHorizontalAlignment(JLabel.CENTER);
    }

    private void drawLinkNavBar(boolean adminView) {
        linkNavbar = new JToolBar();
        linkNavbar.setPreferredSize(new Dimension(824, 80));
        linkNavbar.setBackground(Palette.instance().getWhite());
        linkNavbar.setFloatable(false);
        linkNavbar.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 20));

        homeButton = new NavBarButton("Mis Examenes", "assets/Home_Icon.png", false);
        linkNavbar.add(homeButton);
        linkNavbar.addSeparator();

        if (adminView) {
            adminButton = new NavBarButton("Registrar Usuario", "assets/User_Icon.png", false);
            linkNavbar.add(adminButton);
            linkNavbar.addSeparator();
        }

        certifycateButton = new NavBarButton("Solicitar Certificado", "assets/Paperclip_Icon.png", false);
        linkNavbar.add(certifycateButton);
        linkNavbar.addSeparator();

        logOutButton = new NavBarButton("Cerrar sesión", "assets/LogOut_Icon.png", true);
        linkNavbar.add(logOutButton);
        linkNavbar.addSeparator();
    }
}
