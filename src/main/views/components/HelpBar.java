package main.views.components;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import main.utils.Palette;
import main.views.pages.ExamsView;
import main.views.pages.Frame;
import main.views.pages.HelpView;

public class HelpBar extends JPanel implements ActionListener{
    NavBarButton helpButton;

    public HelpBar() {
        this.setPreferredSize(new Dimension(1024, 80));
        this.setBackground(Palette.instance().getWhite());
        this.setLayout(new FlowLayout(FlowLayout.TRAILING));

        helpButton = new NavBarButton("Ayuda", "Help_Icon.png", true);
        helpButton.addActionListener(this);

        this.add(helpButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == helpButton) {
            Frame.instance().setView(HelpView.instance());
            Frame.instance().setTitle("HelpView");
        }
    }
}
