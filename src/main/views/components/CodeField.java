package main.views.components;

import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;

import main.utils.Palette;

public class CodeField extends JScrollPane {
    JTextPane lineNumber;
    JTextPane codeField;
    JPanel codePanel;

    CodeField(List<String> codeLines) {
        buildScrollPane();
        
        buildCodePanel();
        paintLineNumber(codeLines.size());
        buildCodeField(codeLines);
    }

    private void buildCodePanel() {
        codePanel = new JPanel();
        codePanel.setLayout(new BoxLayout(codePanel, BoxLayout.X_AXIS));

        this.setViewportView(codePanel);
    }

    private void paintLineNumber(int numerOfLines) {
        lineNumber = new JTextPane();
        
        lineNumber.setForeground(Palette.instance().getGray());
        lineNumber.setFont(new Font("Cascadia Code", Font.PLAIN, 17));
        lineNumber.setBackground(Palette.instance().getBlack());

        for (int i = 1; i <= numerOfLines; i++) {
            lineNumber.setText(lineNumber.getText() + i + "\n");
        }
        lineNumber.setEditable(false);

        Border border = BorderFactory.createEmptyBorder(0, 20, 0, 0);
        lineNumber.setBorder(border);
        
        lineNumber.setPreferredSize(new Dimension(50,lineNumber.getHeight()));
        lineNumber.setMaximumSize(new Dimension(50,9999));

        codePanel.add(lineNumber);
    }

    private void buildCodeField(List<String> codelines) {
        codeField = new JTextPane();
        
        codeField.setForeground(Palette.instance().getOffWhite());
        codeField.setFont(new Font("Cascadia Code", Font.PLAIN, 17));
        codeField.setBackground(Palette.instance().getBlack());

        for (int i = 0; i < codelines.size(); i++) {
            codeField.setText(codeField.getText() + codelines.get(i) + "\n");
        }
        codeField.setEditable(false);

        Border border = BorderFactory.createEmptyBorder(0, 15, 0, 0);
        codeField.setBorder(border);

        codePanel.add(codeField);
        
    }

    private void buildScrollPane(){
        this.setPreferredSize(new Dimension(645, 200));

        this.getVerticalScrollBar().setBackground(Palette.instance().getGray());
        this.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
        changeVerticalScrollBarLook();

        this.getHorizontalScrollBar().setBackground(Palette.instance().getGray());
        this.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 8));
        changeHorizontalScrollBarLook();

        Border border = BorderFactory.createLineBorder(Palette.instance().getWhite(), 3);
        this.setBorder(border);
    }

    private void changeVerticalScrollBarLook() {
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
                this.thumbColor = Palette.instance().getLightGray();
            }
        });
    }

    private void changeHorizontalScrollBarLook() {
        this.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
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
                this.thumbColor = Palette.instance().getLightGray();
            }
        });
    }

}
