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

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
