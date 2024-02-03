package main.views.pages;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.utils.Palette;
import main.views.components.NavBar;

public class NavBarTemplateView extends JPanel implements ActionListener{
    NavBar navBar;

    protected void buildFrame(String viewName) {
        Frame.instance().setTitle(viewName);
        this.setLayout(new BorderLayout());
    }

    protected void paintNavBar() {
        navBar = new NavBar();
        this.add(navBar, BorderLayout.NORTH);
    }

    protected void paintSideBorders() {
        JPanel borderPanel = new JPanel();
        borderPanel.setPreferredSize(new Dimension(40, 560));
        borderPanel.setBackground(Palette.instance().getWhite());
        this.add(borderPanel, BorderLayout.WEST);

        borderPanel = new JPanel();
        borderPanel.setPreferredSize(new Dimension(40, 560));
        borderPanel.setBackground(Palette.instance().getWhite());
        this.add(borderPanel, BorderLayout.EAST);
    }

    protected void paintBorders() {
        paintNavBar();
        paintSideBorders();
    }

    protected void addActionListenerNavbar() {
        navBar.getHomeButton().addActionListener(this);
        navBar.getCertificateButton().addActionListener(this);
        navBar.getLogOutButton().addActionListener(this);
    }

    protected void actionEventInNavBar(ActionEvent e) {
        if (e.getSource() == navBar.getHomeButton()) {
            Frame.instance().setView(ExamsView.instance());
            Frame.instance().setTitle("ExamsView");

        } else if (e.getSource() == navBar.getCertificateButton()) {
            Frame.instance().setView(CertificatesView.instance());
            Frame.instance().setTitle("CertificatesView");

        } else if (e.getSource() == navBar.getLogOutButton()) {
            CertificatesView.deleteInstance();
            ExamsView.deleteInstance();
            Frame.instance().setView(new LogInView());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actionEventInNavBar(e);
    }
}
