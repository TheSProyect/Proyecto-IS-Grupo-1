package main.views.components;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import main.Palette;

public class NavBar extends JPanel implements ActionListener {
    JLabel appName;
    JLabel linkText;
    JToolBar linkNavbar;
    NavBarButton homeButton;
    NavBarButton certifycateButton;
    NavBarButton logOutButton;
    
    public NavBar() {
        this.setPreferredSize(new Dimension(1024, 80));
        this.setBackground(Palette.instance().getWhite());
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        drawAppName();

        drawLinkNavBar();

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

    private void drawLinkNavBar() {
        linkNavbar = new JToolBar();
        linkNavbar.setPreferredSize(new Dimension(824, 80));
        linkNavbar.setBackground(Palette.instance().getWhite());
        linkNavbar.setFloatable(false);
        linkNavbar.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 20));

        this.drawNavBarButtons();
    }
    
    protected void drawNavBarButtons() {
        homeButton = new NavBarButton("Mis Examenes", "assets/Home_Icon.png", false);
        linkNavbar.add(homeButton);
        linkNavbar.addSeparator();

        certifycateButton = new NavBarButton("Solicitar Certificado", "assets/Paperclip_Icon.png", false);
        linkNavbar.add(certifycateButton);
        linkNavbar.addSeparator();

        logOutButton = new NavBarButton("Cerrar sesión", "assets/LogOut_Icon.png", true);
        logOutButton.addActionListener(this);
        linkNavbar.add(logOutButton);
        linkNavbar.addSeparator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logOutButton) {
            System.out.println ("this should open LoginView");
        }
    }
}
