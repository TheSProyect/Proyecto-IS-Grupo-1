package main.views.pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import main.controllers.RegisterUserController;
import main.utils.Palette;
import main.utils.Size;
import main.views.components.Button;
import main.views.components.PlaceholderTextField;
import main.views.components.PopUp;
import main.views.components.SingleOptionButton;

public class RegisterUserView extends UserTemplateView{
    PlaceholderTextField emailTextField;
    PlaceholderTextField userFirstName;
    PlaceholderTextField usertLastName;
    Button cancelButton;
    Button SignatureButton;
    String SignaturePath;
    RegisterUserPopUp popup;
    SingleOptionButton Admin;
    ButtonGroup group;

    public RegisterUserView(){
        Title = "Registrar Usuario";
        CurrentText = "Ingrese los datos para registrar a<br/>un nuevo usuario en TéchneLogic";
        ButtonText = "Registrar";

        buildFrame("RegisterUserView");
        paintTitlePanel('y', "SingIn");
        paintUserDataPanel();

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

    protected void buildUserDataPanelBorders(JPanel UserDataPanel){
        JPanel border = new JPanel();
        border.setPreferredSize(Size.instance().getRegisterTopBottomBoder());
        border.setBackground(Palette.instance().getWhite());
        UserDataPanel.add(border, BorderLayout.NORTH);

        border = new JPanel();
        border.setPreferredSize(Size.instance().getRegisterTopBottomBoder());
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
        
        ContinueButton = new Button("Registrar");
        ContinueButton.setPreferredSize(Size.instance().getSmallLoginButton());
        buttonContainer.add(ContinueButton);
        ContinueButton.addActionListener(this);
        
        infoContainer.add(buttonContainer);
    }

    private void ActionEventInRegisterButton(ActionEvent e) {
        if(e.getSource() == ContinueButton) {
            /* */
            if(userTextField.getTextField().equals("") || emailTextField.getTextField().equals("") || passwordTextField.getTextField().equals("") || userFirstName.getTextField().equals("") || usertLastName.getTextField().equals("")){
                setErrorMessage(errorLabel, "Se deben llenar todos los campos");
            } else {
            RegisterUserController RegisterControl = new RegisterUserController();

            if(Admin.getButton().isSelected() && SignaturePath == null){
                
                setErrorMessage(errorLabel, "Se debe añadir una firma");

            }else if(RegisterControl.RegisterNewUser(userTextField.getTextField(), Admin.getButton().isSelected())){
                RegisterControl.setNewUserName(userFirstName.getTextField(), usertLastName.getTextField());
                RegisterControl.setNewUserPassword(passwordTextField.getTextField(), emailTextField.getTextField());
                
                if(Admin.getButton().isSelected()){
                RegisterControl.saveSignatureImg(SignaturePath, userTextField.getTextField());
                }

                AdminExamsView.instance().paintNavBar();
                Frame.instance().setView(AdminExamsView.instance());

            popup = new RegisterUserPopUp(userTextField.getTextField());
            PopUp.instance(Size.instance().getRegisterUserPopUpDimension()).setView(popup);
            popup.getButton().addActionListener(this);
            } else {
                setErrorMessage(errorLabel, "Nombre de usuario no disponible");
            }
        }
        
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

            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
            JFileChooser imageChooser = new JFileChooser();
            imageChooser.setFileFilter(filter);

            int response = imageChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                SignaturePath = imageChooser.getSelectedFile().getAbsolutePath();
            }

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

