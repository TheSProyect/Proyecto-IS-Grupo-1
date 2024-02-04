package main.views.pages;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import main.utils.Palette;
import main.views.components.PopUp;

public class PopUpTemplate extends JPanel {
    
    protected void buildFrame() {
        PopUp.instance();
        Border border = BorderFactory.createLineBorder(Palette.instance().getLightGray(), 2);
        this.setBorder(border);
        this.setLayout(new BorderLayout());
    }

    protected void paintBorders() {
        JPanel borderPanel = new JPanel();
        borderPanel.setPreferredSize(new Dimension(40, 300));
        borderPanel.setBackground(Palette.instance().getWhite());
        this.add(borderPanel, BorderLayout.WEST);

        borderPanel = new JPanel();
        borderPanel.setPreferredSize(new Dimension(40, 300));
        borderPanel.setBackground(Palette.instance().getWhite());
        this.add(borderPanel, BorderLayout.EAST);
    }

  protected void paintTitlePanel(JPanel contentPanel, String str) {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Palette.instance().getWhite());
        titlePanel.setPreferredSize(new Dimension(500, 60));
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        
        paintTitleLabel(titlePanel, str);
        paintTitleSeparator(titlePanel); 
        contentPanel.add(titlePanel);

    }

    protected void paintTitleLabel(JPanel titlePanel, String str) {
        JTextPane title = new JTextPane();
        title.setText(str);
        title.setFont(new Font("Nunito Sans", Font.BOLD, 30));
        title.setPreferredSize(new Dimension(500, 50));
        title.setEditable(false);

        StyledDocument doc = title.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        titlePanel.add(title);
    }

    protected void paintTitleSeparator(JPanel titlePanel) {
        JSeparator line = new JSeparator();
        line.setForeground(Palette.instance().getYellow());
        line.setBackground(Palette.instance().getYellow());
        titlePanel.add(line);
    }

    

    
}
