package main.views.pages;


import main.views.templates.Frame;
import main.views.components.NavBar;
import main.views.components.AdminNavBar;
import main.views.components.Button;
import main.views.components.HelpBar;
import main.views.components.Listing;


import main.data.Palette;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.awt.FlowLayout;


import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.border.Border;
import javax.swing.text.html.parser.Element;


public class AdminExamView extends Frame {
    JPanel titlePanel;
    JPanel contentPanel;
    JLabel title;
    NavBar navBar;
    Button button;
    HelpBar helpBar;
    JButton createExam;
    JPanel titleButtonContainer;
    Listing examListing;
    List<String> exams;


    public AdminExamView() {
        inicializeExams();

        buildFrame();
        paintBorders();
        paintContentPanel();


        this.pack();
    }

    protected void buildFrame() {
        createFrame("ExamsView");
        this.setLayout(new BorderLayout());
    }

    protected void paintNavBar() {
        navBar = new AdminNavBar();
        this.add(navBar, BorderLayout.NORTH);
    }

    protected void paintBorders() {
        paintNavBar();
        
        helpBar = new HelpBar();
        this.add(helpBar, BorderLayout.SOUTH);

        JPanel borderPanel = new JPanel();
        borderPanel.setPreferredSize(new Dimension(40, 560));
        borderPanel.setBackground(Palette.instance().getWhite());
        this.add(borderPanel, BorderLayout.WEST);

        borderPanel = new JPanel();
        borderPanel.setPreferredSize(new Dimension(40, 560));
        borderPanel.setBackground(Palette.instance().getWhite());
        this.add(borderPanel, BorderLayout.EAST);

    }

    protected void paintContentPanel(){
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(944, 560));
        contentPanel.setBackground(Palette.instance().getWhite());

        paintTitlePanel();
        
        paintExamListing(); 

    
        this.add(contentPanel, BorderLayout.CENTER);
        
    }

    protected void paintTitlePanel() {
        createTitlePanel();
        TitleButtonContainer();
        paintTitleSeparator();        
    }

    protected void createTitlePanel() {
        titlePanel = new JPanel();
        titlePanel.setBackground(Palette.instance().getWhite());
        titlePanel.setPreferredSize(new Dimension(944, 60));
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        contentPanel.add(titlePanel, BorderLayout.NORTH);
    }

    protected void paintTitleSeparator() {
        JSeparator line = new JSeparator();
        line.setForeground(Palette.instance().getLightGray());
        line.setBackground(Palette.instance().getLightGray());
        titlePanel.add(line);
    }

    private void TitleButtonContainer() {
        titleButtonContainer = new JPanel();
        titleButtonContainer.setMaximumSize(new Dimension(1500, 58));
        titleButtonContainer.setLayout(new BoxLayout(titleButtonContainer, BoxLayout.X_AXIS));
        titleButtonContainer.setBackground(Palette.instance().getWhite());

        paintTitleLabel();

        paintCreateExamButton();
        
        titlePanel.add(titleButtonContainer);
    }

    protected void paintTitleLabel() {
        title = new JLabel();
        title.setText("Administrador de Examenes");
        title.setFont(new Font("Nunito Sans", Font.BOLD, 25));
        title.setPreferredSize(new Dimension(944, 58));
        title.setMaximumSize(new Dimension(2048, 58));
        title.setVerticalAlignment(JLabel.BOTTOM);

        titleButtonContainer.add(title, FlowLayout.LEFT);
    }

    private void paintCreateExamButton() {
        createExam = new JButton("Crear Examen");
        createExam.setFont(new Font("Nunito Sans", Font.BOLD, 15));
        createExam.setForeground(Palette.instance().getWhite());
        createExam.setBackground(Palette.instance().getBlue());
        createExam.setFocusable(false);

        Border border = BorderFactory.createLineBorder(Palette.instance().getBlue());
        createExam.setBorder(border);

        createExam.setPreferredSize(new Dimension(150, 30));
        createExam.setMaximumSize(new Dimension(150, 30));
    
        titleButtonContainer.add(createExam);
    }

    private void paintExamListing() {
        examListing = new Listing(exams, "Presentar Examen");
        contentPanel.add(examListing);
        
    }


    private void inicializeExams() {
        // este metodo es de prueba. Terrible lo se
        // lo que esté entre comentarios no va btw


        exams = new ArrayList<String>();
        // prueba {
        exams.add("¿Cuál es el resultado de este código?");
        exams.add("¿Cuál no es el resultado de este código?");
        exams.add("¿Cuál tu cara?");
        exams.add("¿Quien te preguntó?");
        //prueba    





    }




}
