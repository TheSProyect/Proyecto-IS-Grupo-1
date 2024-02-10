package main.views.pages;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import main.utils.Palette;
import main.utils.Size;
import main.views.components.PopUp;

public class UserCreatedPopUp extends PopUpTemplate{
    JButton Button;

    public UserCreatedPopUp (String User) {
        buildFrame();
        paintBorders();
        paintContentPanel(User);
    }

    protected void paintContentPanel(String User){
        JPanel contentPanel = new JPanel();
        contentPanel.setPreferredSize(new Dimension(500, 220));
        contentPanel.setBackground(Palette.instance().getWhite());
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 15));
        
        paintTitlePanel(contentPanel, "Usuario creado exitosamente");
        
        paintTextLabel(contentPanel, User);
        paintButton(contentPanel);
        
        this.add(contentPanel, FlowLayout.CENTER);
    }

    protected void paintTextLabel(JPanel contentPanel, String User) {
        JTextPane text = new JTextPane();
        text.setText("Ahora el usuario " + User + "\n ha sido registrado con exito");
        text.setFont(new Font("Nunito Sans", Font.PLAIN, 20));
        text.setPreferredSize(new Dimension(500, 60 ));
        text.setEditable(false);
        text.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        StyledDocument doc = text.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        contentPanel.add(text);
    }

    private void paintButton(JPanel contentPanel) {
        Button = new JButton("Continuar");
        Button.setFont(new Font("Nunito Sans", Font.BOLD, 15));
        Button.setUI(new MetalButtonUI());
        Button.setForeground(Palette.instance().getWhite());
        Button.setBackground(Palette.instance().getBlue());
        Button.setPreferredSize(new Dimension(200, 30));
        Button.setFocusable(false);
        Button.addActionListener(null);

        Border border = BorderFactory.createLineBorder(Palette.instance().getBlue());
        Button.setBorder(border);

        contentPanel.add(Button);
    }

    public JButton getButton() {
        return Button;
    }

}