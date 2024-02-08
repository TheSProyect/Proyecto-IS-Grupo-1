package main.views.pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.utils.Palette;
import main.views.components.AdminNavBar;
import main.views.components.Button;

public class AdminExamsView extends ExamsView {
    Button editExam;
    JPanel titleButtonContainer;

    public static AdminExamsView instance() {
        if (examView == null){
			examView = new AdminExamsView();
		}
		return (AdminExamsView) examView;
    }

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
        editExam = new Button("Editar Examenes");
        editExam.setPreferredSize(new Dimension(150, 30));
        editExam.setMaximumSize(new Dimension(150, 30));
        editExam.addActionListener(this);

        titleButtonContainer.add(editExam);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editExam) {
            Frame.instance().setView(new AdminExamView());
        }
        actionEventInCourseCard(e);
    }
}
