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

import main.utils.Palette;
import main.utils.Size;
import main.views.components.PlaceholderTextField;
import main.controllers.LoginController;

public class LogInView extends JPanel implements ActionListener {
    JPanel titlePanel;
    JPanel loginPanel;
    JPanel infoContainer;
    JButton loginButton;
    
    PlaceholderTextField userTextField;
    PlaceholderTextField passwordTextField;

    public LogInView() {
        buildFrame();
        paintTitlePanel();
        paintLoginPanel();
        LoginController loginController = new LoginController();
    }

    protected void buildFrame() {
        Frame.instance().setTitle("LogInView");
        
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    private void paintTitlePanel() {
        titlePanel = new JPanel();
        titlePanel.setBackground(Palette.instance().getBlue());
        titlePanel.setPreferredSize(new Dimension(546,720));
        // titlePanel.setMaximumSize(new Dimension(546,720));

        titlePanel.setLayout(new BorderLayout());

        paintTitleLabel();

        this.add(titlePanel);
    }

    private void paintTitleLabel() {
        JLabel titleLabel = new JLabel();
        ImageIcon icon = new ImageIcon("src/assets/Logo_Login.png");
        titleLabel.setIcon(icon);
        // titleLabel.setText("TéchneLogic");
        // titleLabel.setFont(new Font("Nunito Sans", Font.BOLD, 50));
        // titleLabel.setForeground(Palette.instance().getWhite());
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        
        titlePanel.add(titleLabel, BorderLayout.CENTER);
    }

    private void paintLoginPanel() {
        loginPanel = new JPanel();
        loginPanel.setPreferredSize(new Dimension(478,720));
        loginPanel.setLayout(new BorderLayout());
        loginPanel.setMinimumSize(new Dimension(478,678));
        // titlePanel.setMaximumSize(new Dimension(1024,720));

        buildLoginPanelBorders();
        buildInfoContainer();

        this.add(loginPanel);
    }

    private void buildLoginPanelBorders() {
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

    private void buildInfoContainer() {
        infoContainer = new JPanel();
        infoContainer.setPreferredSize(new Dimension(478,478));
        infoContainer.setBackground(Palette.instance().getWhite());
        infoContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 1024, 15));

        paintLoginTitleLabel();
        paintLoginText();
        paintLoginSeparator();
        paintTextFields();
        paintButtonContainer();

        loginPanel.add(infoContainer, BorderLayout.CENTER);
    }

    protected void paintLoginTitleLabel() {
        JLabel loginTitleLabel = new JLabel();
        loginTitleLabel.setText("Inicia Sesión");
        loginTitleLabel.setFont(new Font("Nunito Sans", Font.BOLD, 30));
        loginTitleLabel.setForeground(Palette.instance().getBlack());
        loginTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        loginTitleLabel.setVerticalAlignment(JLabel.BOTTOM);
        
        infoContainer.add(loginTitleLabel);
    }

    protected void paintLoginText() {
        JLabel loginTextLabel = new JLabel();
        loginTextLabel.setText("Estás a un paso de tus evaluaciones");
        loginTextLabel.setFont(new Font("Nunito Sans", Font.PLAIN, 15));
        loginTextLabel.setForeground(Palette.instance().getGray());
        loginTextLabel.setHorizontalAlignment(JLabel.CENTER);

        infoContainer.add(loginTextLabel);
    }

    private void paintLoginSeparator() {
        JSeparator line = new JSeparator();
        line.setPreferredSize(new Dimension(478,2));
        line.setForeground(Palette.instance().getYellow());
        line.setBackground(Palette.instance().getYellow());

        infoContainer.add(line);
    }

    protected void paintTextFields() {
        userTextField = new PlaceholderTextField("Usuario", "User_Icon.png");
        passwordTextField = new PlaceholderTextField("Contraseña", "User_Icon.png");

        infoContainer.add(userTextField);
        infoContainer.add(passwordTextField);
    }

    protected void paintButtonContainer() {
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
            Frame.instance().setView(ExamsView.instance());
        }
    }


}
