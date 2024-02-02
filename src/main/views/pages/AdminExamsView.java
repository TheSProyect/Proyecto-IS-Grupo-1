package main.views.pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import main.utils.Palette;
import main.views.components.AdminNavBar;

public class AdminExamsView extends ExamsView {
    JButton editExam;
    JPanel titleButtonContainer;

    protected void buildFrame() {
        Frame.instance().setTitle("AdminExamsView");
        this.setLayout(new BorderLayout());
    }

    protected void paintNavBar() {
        navBar = new AdminNavBar();
        this.add(navBar, BorderLayout.NORTH);
    }

    protected void paintTitlePanel(JPanel contentPanel) {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Palette.instance().getWhite());
        titlePanel.setPreferredSize(new Dimension(944, 60));
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        contentPanel.add(titlePanel, BorderLayout.NORTH);
        
        adminTitleButtonContainer(titlePanel);
        paintTitleSeparator(titlePanel);         
    }

    private void adminTitleButtonContainer(JPanel titlePanel) {
        titleButtonContainer = new JPanel();
        titleButtonContainer.setMaximumSize(new Dimension(1500, 58));
        titleButtonContainer.setLayout(new BoxLayout(titleButtonContainer, BoxLayout.X_AXIS));
        titleButtonContainer.setBackground(Palette.instance().getWhite());

        paintTitleLabel();

        paintEditExamsButton();
        
        titlePanel.add(titleButtonContainer);
    }

    protected void paintTitleLabel() {
        JLabel title = new JLabel();
        title.setText("Mis examenes");
        title.setFont(new Font("Nunito Sans", Font.BOLD, 25));
        title.setPreferredSize(new Dimension(944, 58));
        title.setMaximumSize(new Dimension(2048, 58));
        title.setVerticalAlignment(JLabel.BOTTOM);

        titleButtonContainer.add(title, FlowLayout.LEFT);
    }

    private void paintEditExamsButton() {
        editExam = new JButton("Editar Examenes");
        editExam.setFont(new Font("Nunito Sans", Font.BOLD, 15));
        editExam.setForeground(Palette.instance().getWhite());
        editExam.setBackground(Palette.instance().getBlue());
        editExam.setFocusable(false);

        Border border = BorderFactory.createLineBorder(Palette.instance().getBlue());
        editExam.setBorder(border);

        editExam.setPreferredSize(new Dimension(150, 30));
        editExam.setMaximumSize(new Dimension(150, 30));
    
        titleButtonContainer.add(editExam);
    }
}
