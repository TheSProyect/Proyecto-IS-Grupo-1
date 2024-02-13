package main.views.pages;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;

import main.controllers.EditProfileController;
import main.utils.Palette;
import main.utils.Size;
import main.utils.UserData;
import main.views.components.Button;
import main.views.components.PlaceholderTextField;

public class EditProfileView extends LogInView{
    PlaceholderTextField emailTextField;
    PlaceholderTextField userFirstName;
    PlaceholderTextField usertLastName;

    protected void paintTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Palette.instance().getYellow());
        titlePanel.setPreferredSize(new Dimension(546,720));

        titlePanel.setLayout(new BorderLayout());

        paintTitleLabel(titlePanel);

        this.add(titlePanel);
    }

    protected void paintTitleLabel(JPanel titlePanel) {
        JLabel titleLabel = new JLabel();
        ImageIcon icon = new ImageIcon("src/assets/Logo_SingIn.png");
        titleLabel.setIcon(icon);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        
        titlePanel.add(titleLabel, BorderLayout.CENTER);
    }

    protected void buildLoginPanelBorders(JPanel loginPanel) {
        JPanel border = new JPanel();
        border.setPreferredSize(Size.instance().getEditProfileTopBottomBoder());
        border.setBackground(Palette.instance().getWhite());
        loginPanel.add(border, BorderLayout.NORTH);

        border = new JPanel();
        border.setPreferredSize(Size.instance().getEditProfileTopBottomBoder());
        border.setBackground(Palette.instance().getWhite());
        loginPanel.add(border, BorderLayout.SOUTH);

        border = new JPanel();
        border.setPreferredSize(Size.instance().getLogInSideBoder());
        border.setBackground(Palette.instance().getWhite());
        loginPanel.add(border, BorderLayout.WEST);

        border = new JPanel();
        border.setPreferredSize(Size.instance().getLogInSideBoder());
        border.setBackground(Palette.instance().getWhite());
        loginPanel.add(border, BorderLayout.EAST);
    }

    protected void paintLoginTitleLabel(JPanel infoContainer) {
        JLabel loginTitleLabel = new JLabel();
        loginTitleLabel.setText("Editar Perfil");
        loginTitleLabel.setFont(new Font("Nunito Sans", Font.BOLD, 30));
        loginTitleLabel.setForeground(Palette.instance().getBlack());
        loginTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        loginTitleLabel.setVerticalAlignment(JLabel.BOTTOM);
        
        infoContainer.add(loginTitleLabel);
    }

    protected void paintLoginText(JPanel infoContainer) {
        JLabel loginTextLabel = new JLabel();
        loginTextLabel.setText("<html>Edita tus datos de perfil en TéchneLogic</html>");
        loginTextLabel.setFont(new Font("Nunito Sans", Font.PLAIN, 15));
        loginTextLabel.setForeground(Palette.instance().getGray());
        loginTextLabel.setHorizontalAlignment(JLabel.CENTER);

        infoContainer.add(loginTextLabel);
    }

    protected void paintTextFields(JPanel infoContainer) {
        userTextField = new PlaceholderTextField(UserData.instance().getUsername(), "User_Login_Icon.png");
        emailTextField = new PlaceholderTextField(UserData.instance().getMail(), "Mail_Login_Icon.png");
        passwordTextField = new PlaceholderTextField(UserData.instance().getPassword(), "Unlock_Login_Icon.png");
    
        infoContainer.add(userTextField);
        infoContainer.add(emailTextField);
        infoContainer.add(passwordTextField);
        } 
        
    
    protected void paintButtonContainer(JPanel infoContainer) {
        JPanel buttonContainer = new JPanel();
        buttonContainer.setPreferredSize(new Dimension(478,44));
        buttonContainer.setBackground(Palette.instance().getWhite());
        buttonContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));

        loginButton = new Button("Editar");
        loginButton.setPreferredSize(Size.instance().getBigLoginButton());
        buttonContainer.add(loginButton);
        loginButton.addActionListener(this);
        
        infoContainer.add(buttonContainer);
    }

    protected void paintErrorLabel(JPanel infoContainer) {
        this.errorLabel = new JLabel();
        errorLabel.setFont(new Font("Nunito Sans", Font.PLAIN, 14));
        errorLabel.setForeground(Palette.instance().getRed());
        errorLabel.setHorizontalAlignment(JLabel.CENTER);
        errorLabel.setVerticalAlignment(JLabel.BOTTOM);
        errorLabel.setVisible(false);
        
        infoContainer.add(errorLabel);
    }

    public void setErrorMessage(JLabel errorLabel, String errorType){
        errorLabel.setText(errorType);
        errorLabel.setVisible(true);
    }

    @Override
     public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
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