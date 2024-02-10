package main.views.components;

import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import main.utils.Palette;

public class ExplicationPanel extends JScrollPane {
    JTextPane explicationText;

    ExplicationPanel(List<String> text) {
        paintScroll();
        paintTextPane(determineNumLines(text) == 0);
        writeText(text);
    }

    private int determineNumLines(List<String> text) {
        if (text == null) {
            return 0;
        } else {
            return text.size();
        }
    }

    private HTMLDocument createHTMLDocument() {
        HTMLDocument doc = new HTMLDocument();
        Font font = new Font("Nunito Sans", Font.PLAIN, 15);
        int r = Palette.instance().getGray().getRed();
        int g = Palette.instance().getGray().getGreen();
        int b = Palette.instance().getGray().getBlue();
        String hex = String.format("#%02x%02x%02x", r, g, b);
        
        doc.getStyleSheet().addRule("body { font-family: " + font.getFamily() + "; font-size: " + font.getSize()
                + "pt" + "; color: " + hex + "; }");
        return doc;
    }

    private void writeText(List<String> text) {
        HTMLEditorKit kit = new HTMLEditorKit();
        HTMLDocument doc = createHTMLDocument();
        explicationText.setEditorKit(kit);
        explicationText.setDocument(doc);

        for(int i = 0; i < determineNumLines(text); i ++) {
            try {
                kit.insertHTML(doc, doc.getLength(), text.get(i), 0, 0, null);
                kit.insertHTML(doc, doc.getLength(), "<br>", 0, 0, null);
            } catch (BadLocationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void paintTextPane(boolean isEmpty) {
        explicationText = new JTextPane();
        explicationText.setBackground(Palette.instance().getWhite());
        explicationText.setEditable(isEmpty);

        this.setViewportView(explicationText);
    }

    private void paintScroll() {
        this.setPreferredSize(new Dimension(250, 350));
        this.getVerticalScrollBar().setBackground(Palette.instance().getLightGray());
        changeScrollPaneLook();

        Border border = BorderFactory.createLineBorder(Palette.instance().getWhite(), 3);
        this.setBorder(border);
    }

    private void changeScrollPaneLook() {
        this.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
        this.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override    
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton jbutton = new JButton();
                jbutton.setPreferredSize(new Dimension(0, 0));
                jbutton.setMinimumSize(new Dimension(0, 0));
                jbutton.setMaximumSize(new Dimension(0, 0));
                return jbutton;
            }

            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Palette.instance().getYellow();
            }
        });
    }

}
