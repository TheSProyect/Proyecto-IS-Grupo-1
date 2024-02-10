package main.views.pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.utils.Palette;
import main.utils.Size;
import main.views.components.Button;
import main.views.components.PlaceholderTextField;

public class RegisterUserView extends LogInView{
    Button cancelButton;
    
    PlaceholderTextField emailTextField;
    PlaceholderTextField userFirstName;
    PlaceholderTextField usertLastName;

    protected void buildFrame() {
        Frame.instance().setTitle("RegisterUserView");
        
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
    }
    
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            System.out.println("This should take you to AdminExamsView");
        } else if (e.getSource() == loginButton) {
            System.out.println("This should call controller");
        }
        Frame.instance().setView(AdminExamsView.instance());
    }
}
