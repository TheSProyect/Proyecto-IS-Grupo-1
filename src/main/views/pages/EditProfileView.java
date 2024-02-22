package main.views.pages;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import main.controllers.EditProfileController;
import main.models.UserData;
import main.utils.Palette;
import main.utils.Size;
import main.views.components.PlaceholderTextField;

public class EditProfileView extends UserTemplateView{
    PlaceholderTextField emailTextField;
    PlaceholderTextField userFirstName;
    PlaceholderTextField usertLastName;

    public EditProfileView(){
        Title = "Editar Perfil";
        CurrentText = "Edita tus datos de Perfil en Téchenelogic";
        ButtonText = "Editar";

        buildFrame("EditProfileView");
        paintTitlePanel('y', "SingIn");
        paintUserDataPanel();

    }

    protected void buildUserDataPanelBorders(JPanel UserDataPanel){
        JPanel border = new JPanel();
        border.setPreferredSize(Size.instance().getEditProfileTopBottomBoder());
        border.setBackground(Palette.instance().getWhite());
        UserDataPanel.add(border, BorderLayout.NORTH);

        border = new JPanel();
        border.setPreferredSize(Size.instance().getEditProfileTopBottomBoder());
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
        userTextField = new PlaceholderTextField(UserData.instance().getUsername(), "User_Login_Icon.png");
        emailTextField = new PlaceholderTextField(UserData.instance().getMail(), "Mail_Login_Icon.png");
        passwordTextField = new PlaceholderTextField(UserData.instance().getPassword(), "Unlock_Login_Icon.png");
    
        infoContainer.add(userTextField);
        infoContainer.add(emailTextField);
        infoContainer.add(passwordTextField);
        } 
        
    


    @Override
     public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ContinueButton) {
            EditProfileController EditController = new EditProfileController();
            

            if(EditController.setNewUsername(userTextField.getTextField())){
                EditController.setNewUserInfo(emailTextField.getTextField(), passwordTextField.getTextField());
                
                Frame.instance().setView(ExamsView.instance());
                Frame.instance().setTitle("ExamsView");
            } else {
                setErrorMessage(errorLabel, "Nombre de Usuario no disponible");
            }
            
           


        }
    }
}