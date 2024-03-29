package main.views.pages;

import main.utils.Palette;
import main.views.components.Faq;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JSeparator;

public class HelpView extends HelpBarTemplateView {
    private static HelpView faqView;

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
        buildFrame("HelpView");
        paintBorders();
        paintContentPanel();
        
    }

    protected void paintContentPanel(){
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(944, 560));
        contentPanel.setBackground(Palette.instance().getWhite());

        paintTitlePanel(contentPanel);
        
        paintFaqList(contentPanel); 

    
        this.add(contentPanel, BorderLayout.CENTER);
    }

    protected void paintTitlePanel(JPanel contentPanel) {
        JPanel titlePanel;
        titlePanel = createTitlePanel(contentPanel);
        TitleContainer(titlePanel);
        paintTitleSeparator(2, titlePanel);        
    }

    protected JPanel createTitlePanel(JPanel contentPanel) {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Palette.instance().getWhite());
        titlePanel.setPreferredSize(new Dimension(944, 60));
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        contentPanel.add(titlePanel, BorderLayout.NORTH);
        return titlePanel;
    }

    protected void paintTitleSeparator(int numberColor, JPanel titlePanel) {
        Color color = numberColor == 1 ? Palette.instance().getLightGray() : Palette.instance().getYellow();
        JSeparator line = new JSeparator();
        line.setForeground(color);
        line.setBackground(color);
        titlePanel.add(line);
    }

    private void TitleContainer(JPanel titlePanel) {
        JPanel titleButtonContainer = new JPanel();
        titleButtonContainer.setMaximumSize(new Dimension(1500, 58));
        titleButtonContainer.setLayout(new BoxLayout(titleButtonContainer, BoxLayout.X_AXIS));
        titleButtonContainer.setBackground(Palette.instance().getWhite());

        paintTitleLabel(titleButtonContainer);

        
        titlePanel.add(titleButtonContainer);
    }

    protected void paintTitleLabel(JPanel titleButtonContainer) {
        JLabel title = new JLabel();
        title.setText("Preguntas Frecuentes");
        title.setFont(new Font("Nunito Sans", Font.BOLD, 25));
        title.setPreferredSize(new Dimension(944, 58));
        title.setMaximumSize(new Dimension(2048, 58));
        title.setVerticalAlignment(JLabel.BOTTOM);

        titleButtonContainer.add(title, FlowLayout.LEFT);
    }

    private void paintFaqList(JPanel contentPanel) {
        Faq FaqList = new Faq(questionList, answerList);
        contentPanel.add(FaqList);
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
