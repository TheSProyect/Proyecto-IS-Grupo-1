package main.views.pages;


import main.utils.Palette;
import main.views.components.AdminNavBar;
import main.views.components.Listing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.FlowLayout;


import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;


public class AdminExamView extends CertificatesView {

    protected void paintTitleLabel(JPanel titleButtonContainer) {
        JLabel title = new JLabel();
        title.setText("Administrador de Examenes");
        title.setFont(new Font("Nunito Sans", Font.BOLD, 25));
        title.setPreferredSize(new Dimension(944, 58));
        title.setMaximumSize(new Dimension(2048, 58));
        title.setVerticalAlignment(JLabel.BOTTOM);

        titleButtonContainer.add(title, FlowLayout.LEFT);
        paintCreateButton(titleButtonContainer);
    }

    private void paintCreateButton(JPanel titleButtonContainer) {
        JButton button = new JButton("Crear Examen");
        button.setFont(new Font("Nunito Sans", Font.BOLD, 15));
        button.setForeground(Palette.instance().getWhite());
        button.setBackground(Palette.instance().getBlue());
        button.setFocusable(false);

        Border border = BorderFactory.createLineBorder(Palette.instance().getBlue());
        button.setBorder(border);

        button.setPreferredSize(new Dimension(150, 30));
        button.setMaximumSize(new Dimension(150, 30));
    
        titleButtonContainer.add(button);
    }

    protected void paintCertificatesListing(JPanel contentPanel) {
        certificateListing = new Listing(certificates, "Editar Examen");
        contentPanel.add(certificateListing);
    }

}
