package main.views.pages;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.data.Palette;
import main.data.Size;
import main.views.components.PlaceholderTextField;

public class RegisterUserView extends LogInView{
    JButton cancelButton;
    
    PlaceholderTextField emailTextField;

    protected void buildFrame() {
        createFrame("RegisterUserView");
        
        this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.X_AXIS));
    }
    
    protected void paintLoginTitleLabel() {
        JLabel loginTitleLabel = new JLabel();
        loginTitleLabel.setText("Registrar Usuario");
        loginTitleLabel.setFont(new Font("Nunito Sans", Font.BOLD, 30));
        loginTitleLabel.setForeground(Palette.instance().getBlack());
        loginTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        loginTitleLabel.setVerticalAlignment(JLabel.BOTTOM);
        
        infoContainer.add(loginTitleLabel);
    }

    protected void paintLoginText() {
        JLabel loginTextLabel = new JLabel();
        loginTextLabel.setText("<html>Ingrese los datos para registrar a<br/>un nuevo usuario en TéchneLogic</html>");
        loginTextLabel.setFont(new Font("Nunito Sans", Font.PLAIN, 15));
        loginTextLabel.setForeground(Palette.instance().getGray());
        loginTextLabel.setHorizontalAlignment(JLabel.CENTER);

        infoContainer.add(loginTextLabel);
    }

    protected void paintTextFields() {
        userTextField = new PlaceholderTextField("Usuario", "Home_Icon.png");
        emailTextField = new PlaceholderTextField("Correo electronico", "Home_Icon.png");
        passwordTextField = new PlaceholderTextField("Contraseña", "Home_Icon.png");

        infoContainer.add(userTextField);
        infoContainer.add(emailTextField);
        infoContainer.add(passwordTextField);
    }

    protected void paintButtonContainer() {
        JPanel buttonContainer = new JPanel();
        buttonContainer.setPreferredSize(new Dimension(478,44));
        buttonContainer.setBackground(Palette.instance().getWhite());
        buttonContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));

        cancelButton = new JButton("Cancelar");
        paintButton(cancelButton, Size.instance().getSmallLoginButton());
        buttonContainer.add(cancelButton);
        cancelButton.addActionListener(this);

        loginButton = new JButton("Registrar");
        paintButton(loginButton, Size.instance().getSmallLoginButton());
        buttonContainer.add(loginButton);
        loginButton.addActionListener(this);
        
        infoContainer.add(buttonContainer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            System.out.println("This should take you to AdminExamsView");
        }
        if (e.getSource() == loginButton) {
            System.out.println("This should take you to AdminExamsView");
        }
    }
}
