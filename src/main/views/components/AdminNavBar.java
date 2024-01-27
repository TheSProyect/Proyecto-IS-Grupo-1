package main.views.components;

public class AdminNavBar extends NavBar{
    NavBarButton adminButton;

    protected void drawNavBarButtons() {
        homeButton = new NavBarButton("Mis Examenes", "Home_Icon.png", false);
        linkNavbar.add(homeButton);
        linkNavbar.addSeparator();

        adminButton = new NavBarButton("Registrar Usuario", "User_Icon.png", false);
        linkNavbar.add(adminButton);
        linkNavbar.addSeparator();

        certifycateButton = new NavBarButton("Solicitar Certificado", "Paperclip_Icon.png", false);
        linkNavbar.add(certifycateButton);
        linkNavbar.addSeparator();

        logOutButton = new NavBarButton("Cerrar sesión", "LogOut_Icon.png", true);
        linkNavbar.add(logOutButton);
        linkNavbar.addSeparator();
    }
}