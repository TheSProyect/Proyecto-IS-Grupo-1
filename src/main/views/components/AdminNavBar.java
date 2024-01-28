package main.views.components;

import java.awt.event.ActionEvent;

public class AdminNavBar extends NavBar{
    NavBarButton adminButton;

    protected void paintNavBarButtons() {
        homeButton = new NavBarButton("Mis Examenes", "Home_Icon.png", false);
        homeButton.addActionListener(this);
        linkNavbar.add(homeButton);
        linkNavbar.addSeparator();

        adminButton = new NavBarButton("Registrar Usuario", "User_Icon.png", false);
        adminButton.addActionListener(this);
        linkNavbar.add(adminButton);
        linkNavbar.addSeparator();

        certifycateButton = new NavBarButton("Solicitar Certificado", "Paperclip_Icon.png", false);
        certifycateButton.addActionListener(this);
        linkNavbar.add(certifycateButton);
        linkNavbar.addSeparator();

        logOutButton = new NavBarButton("Cerrar sesión", "LogOut_Icon.png", true);
        logOutButton.addActionListener(this);
        linkNavbar.add(logOutButton);
        linkNavbar.addSeparator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logOutButton) {
            System.out.println ("this should open LoginView");
        }
        if (e.getSource() == adminButton){
            System.out.println ("this should open RegisterUserView");
        }
        if (e.getSource() == certifycateButton) {
            System.out.println ("this should open CertifycatesView");
        }
        if (e.getSource() == homeButton) {
            System.out.println ("this should open ExamsView");
        }
    }
}