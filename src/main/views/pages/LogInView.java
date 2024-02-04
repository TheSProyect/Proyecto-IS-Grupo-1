package main.views.pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.plaf.metal.MetalButtonUI;

import main.controllers.LoginController;
import main.utils.Palette;
import main.utils.Size;
import main.views.components.PlaceholderTextField;

public class LogInView extends JPanel implements ActionListener {
    JPanel infoContainer;
    JButton loginButton;
    
    PlaceholderTextField userTextField;
    PlaceholderTextField passwordTextField;

    public LogInView() {
        buildFrame();
        paintTitlePanel();
        paintLoginPanel();
    }

    protected void buildFrame() {
        Frame.instance().setTitle("LogInView");
        
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    private void paintTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Palette.instance().getBlue());
        titlePanel.setPreferredSize(new Dimension(546,720));

        titlePanel.setLayout(new BorderLayout());

        paintTitleLabel(titlePanel);

        this.add(titlePanel);
    }

    private void paintTitleLabel(JPanel titlePanel) {
        JLabel titleLabel = new JLabel();
        ImageIcon icon = new ImageIcon("src/assets/Logo_Login.png");
        titleLabel.setIcon(icon);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        
        titlePanel.add(titleLabel, BorderLayout.CENTER);
    }

    private void paintLoginPanel() {
        JPanel loginPanel = new JPanel();
        loginPanel.setPreferredSize(new Dimension(478,720));
        loginPanel.setLayout(new BorderLayout());
        loginPanel.setMinimumSize(new Dimension(478,678));

        buildLoginPanelBorders(loginPanel);
        buildInfoContainer(loginPanel);

        this.add(loginPanel);
    }

    private void buildLoginPanelBorders(JPanel loginPanel) {
        JPanel border = new JPanel();
        border.setPreferredSize(Size.instance().getLogInTopBottomBoder());
        border.setBackground(Palette.instance().getWhite());
        loginPanel.add(border, BorderLayout.NORTH);

        border = new JPanel();
        border.setPreferredSize(Size.instance().getLogInTopBottomBoder());
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

    private void buildInfoContainer(JPanel loginPanel) {
        JPanel infoContainer = new JPanel();
        infoContainer.setPreferredSize(new Dimension(478,478));
        infoContainer.setBackground(Palette.instance().getWhite());
        infoContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 1024, 15));

        paintLoginTitleLabel(infoContainer);
        paintLoginText(infoContainer);
        paintLoginSeparator(infoContainer);
        paintTextFields(infoContainer);
        paintButtonContainer(infoContainer);

        loginPanel.add(infoContainer, BorderLayout.CENTER);
    }

    protected void paintLoginTitleLabel(JPanel infoContainer) {
        JLabel loginTitleLabel = new JLabel();
        loginTitleLabel.setText("Inicia Sesión");
        loginTitleLabel.setFont(new Font("Nunito Sans", Font.BOLD, 30));
        loginTitleLabel.setForeground(Palette.instance().getBlack());
        loginTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        loginTitleLabel.setVerticalAlignment(JLabel.BOTTOM);
        
        infoContainer.add(loginTitleLabel);
    }

    protected void paintLoginText(JPanel infoContainer) {
        JLabel loginTextLabel = new JLabel();
        loginTextLabel.setText("Estás a un paso de tus evaluaciones");
        loginTextLabel.setFont(new Font("Nunito Sans", Font.PLAIN, 15));
        loginTextLabel.setForeground(Palette.instance().getGray());
        loginTextLabel.setHorizontalAlignment(JLabel.CENTER);

        infoContainer.add(loginTextLabel);
    }

    private void paintLoginSeparator(JPanel infoContainer) {
        JSeparator line = new JSeparator();
        line.setPreferredSize(new Dimension(478,2));
        line.setForeground(Palette.instance().getYellow());
        line.setBackground(Palette.instance().getYellow());

        infoContainer.add(line);
    }

    protected void paintTextFields(JPanel infoContainer) {
        userTextField = new PlaceholderTextField("Usuario", "User_Login_Icon.png");
        passwordTextField = new PlaceholderTextField("Contraseña", "Unlock_Login_Icon.png");

        infoContainer.add(userTextField);
        infoContainer.add(passwordTextField);
    }

    protected void paintButtonContainer(JPanel infoContainer) {
        JPanel buttonContainer = new JPanel();
        buttonContainer.setPreferredSize(new Dimension(478,44));
        buttonContainer.setBackground(Palette.instance().getWhite());
        buttonContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));

        loginButton = new JButton("Entrar");
        paintButton(loginButton, Size.instance().getBigLoginButton());
        buttonContainer.add(loginButton);
        loginButton.addActionListener(this);
        
        infoContainer.add(buttonContainer);
    }

    protected void paintButton(JButton button, Dimension buttonSize) {
        button.setPreferredSize(buttonSize);
        button.setFont(new Font("Nunito Sans", Font.PLAIN, 17));
        button.setUI(new MetalButtonUI());
        button.setBackground(Palette.instance().getBlue());
        button.setForeground(Palette.instance().getWhite());
        button.setFocusable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            LoginController LoginControl = new LoginController();
            
            //reemplazar los System.out con sus respectivos Popups

            if(userTextField.getTextField().equals("") || passwordTextField.getTextField().equals("")){
                
                System.out.println("Se deben llenar todos los campos");
            
            } else if(LoginControl.searchUser(userTextField.getTextField())){

                if(LoginControl.verifyPassWord(passwordTextField.getTextField())){
                    
                    Frame.instance().setView(ExamsView.instance());
                
                } else {
                    
                    System.out.println("Contraseña incorrecta");
                
                }

            } else {
                
                System.out.println("Usuario no existe");

            }
            
            //Frame.instance().setView(ExamsView.instance());
        }
    }


}
