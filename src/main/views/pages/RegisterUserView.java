package main.views.pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.utils.Palette;
import main.utils.Size;
import main.views.components.Button;
import main.views.components.PlaceholderTextField;
import main.views.components.PopUp;
import main.views.components.SingleOptionButton;

public class RegisterUserView extends EditProfileView{
    Button cancelButton;
    Button SignatureButton;
    RegisterUserPopUp popup;
    SingleOptionButton Admin;
    ButtonGroup group;

    protected void buildFrame() {
        Frame.instance().setTitle("RegisterUserView");
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
    }

    protected void buildLoginPanelBorders(JPanel loginPanel) {
        JPanel border = new JPanel();
        border.setPreferredSize(Size.instance().getRegisterTopBottomBoder());
        border.setBackground(Palette.instance().getWhite());
        loginPanel.add(border, BorderLayout.NORTH);

        border = new JPanel();
        border.setPreferredSize(Size.instance().getRegisterTopBottomBoder());
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
        loginTitleLabel.setText("Registrar Usuario");
        loginTitleLabel.setFont(new Font("Nunito Sans", Font.BOLD, 30));
        loginTitleLabel.setForeground(Palette.instance().getBlack());
        loginTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        loginTitleLabel.setVerticalAlignment(JLabel.BOTTOM);
        
        infoContainer.add(loginTitleLabel);
    }

    protected void paintLoginText(JPanel infoContainer) {
        JLabel loginTextLabel = new JLabel();
        loginTextLabel.setText("<html>Ingrese los datos para registrar a<br/>un nuevo usuario en TéchneLogic</html>");
        loginTextLabel.setFont(new Font("Nunito Sans", Font.PLAIN, 15));
        loginTextLabel.setForeground(Palette.instance().getGray());
        loginTextLabel.setHorizontalAlignment(JLabel.CENTER);

        infoContainer.add(loginTextLabel);
    }

    protected void paintTextFields(JPanel infoContainer) {
        userFirstName = new PlaceholderTextField("Nombre", "Document_Icon.png");
        usertLastName = new PlaceholderTextField("Apellido", "Document_Icon.png");
        userTextField = new PlaceholderTextField("Usuario", "User_Login_Icon.png");
        emailTextField = new PlaceholderTextField("Correo electronico", "Mail_Login_Icon.png");
        passwordTextField = new PlaceholderTextField("Contraseña", "Unlock_Login_Icon.png");


        infoContainer.add(userFirstName);
        infoContainer.add(usertLastName);
        infoContainer.add(userTextField);
        infoContainer.add(emailTextField);
        infoContainer.add(passwordTextField);
        paintAdminContainer(infoContainer);
    }

    protected void paintAdminContainer(JPanel infoContainer) {
        JPanel adminContainer = new JPanel();
        adminContainer.setPreferredSize(new Dimension(478,55));
        adminContainer.setBackground(Palette.instance().getWhite());
        adminContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));

        Admin = new SingleOptionButton("Profesor", group);
        Admin.addActionListener(this);
        adminContainer.add(Admin);

        SignatureButton = new Button("Firma");
        SignatureButton.setPreferredSize(Size.instance().getSmallLoginButton());
        paintSignatureButton();

        adminContainer.add(SignatureButton);
        SignatureButton.addActionListener(this);
        
        infoContainer.add(adminContainer);
    } 

    protected void paintButtonContainer(JPanel infoContainer) {
        JPanel buttonContainer = new JPanel();
        buttonContainer.setPreferredSize(new Dimension(478,44));
        buttonContainer.setBackground(Palette.instance().getWhite());
        buttonContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));

        cancelButton = new Button("Cancelar");
        cancelButton.setPreferredSize(Size.instance().getSmallLoginButton());
        buttonContainer.add(cancelButton);
        cancelButton.addActionListener(this);
        
        loginButton = new Button("Registrar");
        loginButton.setPreferredSize(Size.instance().getSmallLoginButton());
        buttonContainer.add(loginButton);
        loginButton.addActionListener(this);
        
        infoContainer.add(buttonContainer);
    }

    private void ActionEventInRegisterButton(ActionEvent e) {
        if(e.getSource() == loginButton) {
        AdminExamsView.instance().paintNavBar();
        Frame.instance().setView(AdminExamsView.instance());

            popup = new RegisterUserPopUp("Usuario");
            PopUp.instance(Size.instance().getRegisterUserPopUpDimension()).setView(popup);
            popup.getButton().addActionListener(this);
        }         

        //this should pass controller
        if (e.getSource() == loginButton && Admin.getButton().isSelected()) {
            System.out.println("Registraste un Profesor");
        } else if (e.getSource() == loginButton && !Admin.getButton().isSelected()) {
            System.out.println("Registraste un usuario");
        }
    } 

    private void ActionEventInPopUp(ActionEvent e) {
        if (popup == null){
            return;
        } else if (e.getSource() == popup.getButton()) {
            PopUp.deleteInstance();
        }
    }

    private void paintSignatureButton () {
        if (Admin.getButton().isSelected()) {
            SignatureButton.setBackground(Palette.instance().getYellow());
            SignatureButton.setBorder(BorderFactory.createLineBorder(Palette.instance().getYellow()));
            SignatureButton.setEnabled(true);
        } else if (!Admin.getButton().isSelected()) {
            SignatureButton.setBackground(Palette.instance().getLightGray());
            SignatureButton.setBorder(BorderFactory.createLineBorder(Palette.instance().getLightGray()));
            SignatureButton.setEnabled(false);
        }

    }

    private void ActionEventInAdminButton(ActionEvent e) {
        if (e.getSource() == Admin.getButton()) {
            Admin.paintIcon();
            paintSignatureButton();
        }
    }
    
    private void ActionEventInSignatureButton(ActionEvent e) {
        if(e.getSource() == SignatureButton) {
            System.out.println("Add image");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            AdminExamsView.instance().paintNavBar();
            Frame.instance().setView(AdminExamsView.instance());
        }
        ActionEventInRegisterButton(e);
        ActionEventInAdminButton(e);
        ActionEventInPopUp(e);
        ActionEventInSignatureButton(e);
    }
}

