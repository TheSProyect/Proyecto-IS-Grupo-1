package main.views.pages;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import main.controllers.LoginController;
import main.utils.Palette;
import main.utils.Size;
import main.views.components.Button;
import main.views.components.PlaceholderTextField;

public class LogInView extends UserTemplateView {
    Button loginButton;
    JLabel errorLabel;
    
    PlaceholderTextField userTextField;
    PlaceholderTextField passwordTextField;

    public LogInView() {
        Title = "Inicia Sesión";
        CurrentText = "Estás a un paso de tus evaluaciones";
        ButtonText = "Entrar";
        

        buildFrame("LoginView");
        paintTitlePanel('b', "Login");
        paintUserDataPanel();
    }

    protected void buildUserDataPanelBorders(JPanel UserDataPanel){
        JPanel border = new JPanel();
        border.setPreferredSize(Size.instance().getLoginTopBottomBoder());
        border.setBackground(Palette.instance().getWhite());
        UserDataPanel.add(border, BorderLayout.NORTH);

        border = new JPanel();
        border.setPreferredSize(Size.instance().getLoginTopBottomBoder());
        border.setBackground(Palette.instance().getWhite());
        UserDataPanel.add(border, BorderLayout.SOUTH);

        border = new JPanel();
        border.setPreferredSize(Size.instance().getLogInSideBoder());
        border.setBackground(Palette.instance().getWhite());
        UserDataPanel.add(border, BorderLayout.WEST);

        border = new JPanel();
        border.setPreferredSize(Size.instance().getLogInSideBoder());
        border.setBackground(Palette.instance().getWhite());
        UserDataPanel.add(border, BorderLayout.EAST);
    }

    protected void paintTextFields(JPanel infoContainer) {
        userTextField = new PlaceholderTextField("Usuario", "User_Login_Icon.png");
        passwordTextField = new PlaceholderTextField("Contraseña", "Unlock_Login_Icon.png");

        infoContainer.add(userTextField);
        infoContainer.add(passwordTextField);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ContinueButton) {
            LoginController LoginControl = new LoginController();
            
            if(userTextField.getTextField().equals("") || passwordTextField.getTextField().equals("")){
                
                setErrorMessage(errorLabel, "Se deben llenar todos los campos");
            
            } else if(LoginControl.searchUser(userTextField.getTextField())){

                if(LoginControl.verifyPassWord(passwordTextField.getTextField())){

                    if (LoginControl.isAdmin()) {
                        Frame.instance().setView(AdminExamsView.instance());
                    } else {
                        Frame.instance().setView(ExamsView.instance());
                    }

                } else {
                    setErrorMessage(errorLabel, "Contraseña incorrecta");
                
                }

            } else {
                setErrorMessage(errorLabel, "Usuario no existe");

            }
    
        }
    }


}
