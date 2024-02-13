package main.views.pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import main.utils.Palette;
import main.utils.Size;
import main.views.components.Button;
import main.views.components.PlaceholderTextField;

public abstract class UserTemplateView extends JPanel implements ActionListener {
    Button ContinueButton;
    JLabel errorLabel;

    String ButtonText;
    String CurrentText;
    String Title;
    
    PlaceholderTextField userTextField;
    PlaceholderTextField passwordTextField;

    public UserTemplateView() {
        /*buildFrame();
        paintTitlePanel();
        paintUserDataPanel();*/
    }

    protected void buildFrame(String Title) {
        Frame.instance().setTitle(Title);
        
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }
    
    protected void paintTitlePanel(char color, String Logo) {
        JPanel titlePanel = new JPanel();

        if(color == 'y'){
        titlePanel.setBackground(Palette.instance().getYellow());
        } else {
        titlePanel.setBackground(Palette.instance().getBlue());
        }

        titlePanel.setPreferredSize(new Dimension(546,720));

        titlePanel.setLayout(new BorderLayout());

        paintTitleLabel(titlePanel, Logo);

        this.add(titlePanel);
    }

    protected void paintTitleLabel(JPanel titlePanel, String Logo) {
        JLabel titleLabel = new JLabel();
        ImageIcon icon = new ImageIcon("src/assets/Logo_"+Logo+".png");
        titleLabel.setIcon(icon);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        
        titlePanel.add(titleLabel, BorderLayout.CENTER);
    }

    protected void paintUserDataPanel() {
        JPanel UserDataPanel = new JPanel();
        UserDataPanel.setPreferredSize(new Dimension(478,720));
        UserDataPanel.setLayout(new BorderLayout());
        UserDataPanel.setMinimumSize(new Dimension(478,678));

        buildUserDataPanelBorders(UserDataPanel);
        buildInfoContainer(UserDataPanel);

        this.add(UserDataPanel);
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

    private void buildInfoContainer(JPanel UserDataPanel) {
        JPanel infoContainer = new JPanel();
        infoContainer.setPreferredSize(new Dimension(478,878));
        infoContainer.setBackground(Palette.instance().getWhite());
        infoContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 1024, 15));

        paintCurrentViewTitleLabel(infoContainer);
        paintSeparator(infoContainer);
        paintCurrentText(infoContainer);
        paintTextFields(infoContainer);
        paintButtonContainer(infoContainer);
        paintErrorLabel(infoContainer);

        UserDataPanel.add(infoContainer, BorderLayout.CENTER);
    }

    protected void paintCurrentViewTitleLabel(JPanel infoContainer) {
        JLabel TitleLabel = new JLabel();
        TitleLabel.setText(this.Title);
        TitleLabel.setFont(new Font("Nunito Sans", Font.BOLD, 30));
        TitleLabel.setForeground(Palette.instance().getBlack());
        TitleLabel.setHorizontalAlignment(JLabel.CENTER);
        TitleLabel.setVerticalAlignment(JLabel.BOTTOM);
        
        infoContainer.add(TitleLabel);
    }

    protected void paintCurrentText(JPanel infoContainer) {
        JLabel loginTextLabel = new JLabel();
        loginTextLabel.setText("<html>"+CurrentText+"<html/>");
        loginTextLabel.setFont(new Font("Nunito Sans", Font.PLAIN, 15));
        loginTextLabel.setForeground(Palette.instance().getGray());
        loginTextLabel.setHorizontalAlignment(JLabel.CENTER);

        infoContainer.add(loginTextLabel);
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

        ContinueButton = new Button(this.ButtonText);
        ContinueButton.setPreferredSize(Size.instance().getBigLoginButton());
        buttonContainer.add(ContinueButton);
        ContinueButton.addActionListener(this);
        
        infoContainer.add(buttonContainer);
    }

    private void paintSeparator(JPanel infoContainer) {
        JSeparator line = new JSeparator();
        line.setPreferredSize(new Dimension(478,2));
        line.setForeground(Palette.instance().getYellow());
        line.setBackground(Palette.instance().getYellow());

        infoContainer.add(line);
    }
    
    protected void paintErrorLabel(JPanel infoContainer) {
        this.errorLabel = new JLabel();
        this.errorLabel.setFont(new Font("Nunito Sans", Font.PLAIN, 14));
        this.errorLabel.setForeground(Palette.instance().getRed());
        this.errorLabel.setHorizontalAlignment(JLabel.CENTER);
        this.errorLabel.setVerticalAlignment(JLabel.BOTTOM);
        this.errorLabel.setVisible(false);
        this.errorLabel.setText("aaa");
        this.errorLabel.setVisible(true);
        
        
        infoContainer.add(errorLabel);
    }
    protected void setErrorMessage(JLabel errorLabel, String errorType){
        this.errorLabel.setText(errorType);
        this.errorLabel.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        
    }

}