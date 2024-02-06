package main.views.pages;

import main.views.components.NavBar;
import main.utils.Palette;
import main.views.components.Button;
import main.views.components.HelpBar;
import main.views.components.Listing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.FlowLayout;


import javax.swing.JLabel;
import javax.swing.JPanel;
    import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JSeparator;


public class HelpView extends JPanel {
    private static HelpView faqView;

    JPanel titlePanel;
    JPanel contentPanel;
    JLabel title;
    NavBar navBar;
    Button button;
    HelpBar helpBar;
    JButton createExam;
    JPanel titleButtonContainer;
    Listing certificateListing;
    List<String> certificates;
    List<JButton> requestCertificateButtons;

    List<String> questionList = new ArrayList<String>();
    List<String> answerList = new ArrayList<String>();

    public static HelpView instance() {
		if (faqView == null){
			faqView = new HelpView();
		}
		return faqView;
	}

    public static void deleteInstance() {
        faqView = null;
    }

    public HelpView() {
        init();

        buildFrame();
        paintBorders();
        paintContentPanel();
        
    }

    protected void buildFrame() {
        Frame.instance().setTitle("FaqView");
        this.setLayout(new BorderLayout());
    }

    protected void paintNavBar() {
        navBar = new NavBar();
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
        
        paintCertificatesListing(); 

    
        this.add(contentPanel, BorderLayout.CENTER);
    }

    protected void paintTitlePanel() {
        createTitlePanel();
        TitleContainer();
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

    private void TitleContainer() {
        titleButtonContainer = new JPanel();
        titleButtonContainer.setMaximumSize(new Dimension(1500, 58));
        titleButtonContainer.setLayout(new BoxLayout(titleButtonContainer, BoxLayout.X_AXIS));
        titleButtonContainer.setBackground(Palette.instance().getWhite());

        paintTitleLabel();

        
        titlePanel.add(titleButtonContainer);
    }

    protected void paintTitleLabel() {
        title = new JLabel();
        title.setText("Preguntas Frecuentes");
        title.setFont(new Font("Nunito Sans", Font.BOLD, 25));
        title.setPreferredSize(new Dimension(944, 58));
        title.setMaximumSize(new Dimension(2048, 58));
        title.setVerticalAlignment(JLabel.BOTTOM);

        titleButtonContainer.add(title, FlowLayout.LEFT);
    }

    private void paintCertificatesListing() {
        certificateListing = new Listing(questionList, answerList);
        contentPanel.add(certificateListing);
    }



    private void init(){
        questionList.add("¿Para que sirve Technelogic?");
        questionList.add("¿Quienes pueden presentar exámenes con esta aplicación?");
        questionList.add("¿Que tipos de tecnologías se pueden certificar?");
        questionList.add("¿Cómo puedo generar un certificado?");
        questionList.add("¿Puedo modificar mi contraseña? ");
        answerList.add("Technologic es una herramienta de certificación de cursos en la que podrás presentar exámenes y así demostrar tu conocimiento en diversas tecnologías ");
        answerList.add("Cualquier usuario que haya sido previamente registrado por un administrador podrá utilizar la aplicación para presentar todo los tipos de exámenes que se ofrecen.");
        answerList.add("Cualquier tipo de tecnología que los profesores ofrezcan como curso en la aplicación podrá ser certificada. Desde lenguajes de programación como Java o Python, lenguajes de desarrollo web como HTML o Javascript, e incluso arquitectura y diseño del computador."); 
        answerList.add("Luego de presentar un examen, debes dirigirte a la pestaña \"Generar Certificado\", dónde encontrarás una lista de certificados de todos los cursos en lo que has participado. Una vez hayas elegido alguno, podrás visualizar un resumen de tus resultados, así como la tener opción de descargarlo en formato PDF."); 
        answerList.add("Para modificar tu contraseña, debes dirigirte a la esquina inferior izquierda y presionar el botón \"Editar Perfil\", allí podrás modificar la información de tu perfil, tanto tú contraseña como tu nombre de usuario y correo electrónico."); 
    }

}
