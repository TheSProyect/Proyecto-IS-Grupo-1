package main.views.components;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import main.utils.Palette;
import main.views.pages.ExamsView;
import main.views.pages.Frame;
import main.views.pages.HelpView;

public class HelpBar extends JPanel implements ActionListener{
    NavBarButton helpButton;
    NavBarButton editProfileButton;

    public HelpBar() {
        buildPanel();
        paintEditProfileButton();
        paintHelpButton();
    }

    private void buildPanel() {
        this.setPreferredSize(new Dimension(1024, 80));
        this.setBackground(Palette.instance().getWhite());
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    private void paintEditProfileButton() {
        JPanel buttonContainer = new JPanel();
        buttonContainer.setBackground(Palette.instance().getWhite());
        buttonContainer.setLayout(new FlowLayout(FlowLayout.LEADING));

        editProfileButton = new NavBarButton("Editar Perfil", "Gear_Icon.png", false);
        editProfileButton.addActionListener(this);

        buttonContainer.add(editProfileButton);

        this.add(buttonContainer);
    }

    private void paintHelpButton() {
        JPanel buttonContainer = new JPanel();
        buttonContainer.setBackground(Palette.instance().getWhite());
        buttonContainer.setLayout(new FlowLayout(FlowLayout.TRAILING));

        helpButton = new NavBarButton("Ayuda", "Help_Icon.png", true);
        helpButton.addActionListener(this);

        buttonContainer.add(helpButton);

        this.add(buttonContainer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == helpButton) {

            Frame.instance().setView(HelpView.instance());
            Frame.instance().setTitle("HelpView");
        } else if (e.getSource() == editProfileButton) {
            System.out.println ("this should open EditProfileView");
        }
    }
}
