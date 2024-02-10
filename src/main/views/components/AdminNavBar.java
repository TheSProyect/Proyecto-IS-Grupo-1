package main.views.components;

import java.awt.event.ActionEvent;

import main.views.pages.AdminExamsView;
import main.views.pages.CertificatesView;
import main.views.pages.Frame;
import main.views.pages.LogInView;
import main.views.pages.RegisterUserView;

public class AdminNavBar extends NavBar{
    NavBarButton adminButton;

    protected void paintNavBarButtons() {
        homeButton = new NavBarButton("Mis<br>Examenes", "Home_Icon.png", false);
        homeButton.addActionListener(this);
        linkNavbar.add(homeButton);
        linkNavbar.addSeparator();

        adminButton = new NavBarButton("Registrar<br>Usuario", "User_Icon.png", false);
        adminButton.addActionListener(this);
        linkNavbar.add(adminButton);
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
            Frame.instance().setView(AdminExamsView.instance());
        }else if (e.getSource() == adminButton){
            Frame.instance().setView(new RegisterUserView());
        }else if (e.getSource() == certificateButton) {
            Frame.instance().setView(new CertificatesView());
        } else if (e.getSource() == logOutButton) {
            AdminExamsView.deleteInstance();
            Frame.instance().setView(new LogInView());
        }
    }
}