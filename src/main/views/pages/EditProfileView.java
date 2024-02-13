package main.views.pages;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;

import main.utils.Palette;
import main.utils.Size;
import main.views.components.Button;
import main.views.components.PlaceholderTextField;

public class EditProfileView extends RegisterUserView{
   
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
        userFirstName = new PlaceholderTextField("Nombre", "Document_Icon.png");
        usertLastName = new PlaceholderTextField("Apellido", "Document_Icon.png");
        emailTextField = new PlaceholderTextField("Correo electronico", "Mail_Login_Icon.png");
        passwordTextField = new PlaceholderTextField("Contraseña", "Unlock_Login_Icon.png");
    
        infoContainer.add(userFirstName);
        infoContainer.add(usertLastName);
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

    @Override
     public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            System.out.println("This should Edit Profile info");
            Frame.instance().setTitle("ExamsView");
            Frame.instance().setView(ExamsView.instance());
        }
    }
}