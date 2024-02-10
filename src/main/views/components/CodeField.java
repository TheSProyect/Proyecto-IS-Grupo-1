package main.views.components;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

public class CodeField extends JScrollPane implements KeyListener {
    JTextPane lineNumber;
    JTextPane codeField;
    JPanel codePanel;

    CodeField(List<String> codeLines) {
        buildScrollPane();
        
        buildCodePanel();
        paintLineNumber(determineNumLines(codeLines));
        paintCodeField(codeLines);
    }

    private int determineNumLines(List<String> codeLines) {
        if (codeLines == null) {
            return 0;
        } else {
            return codeLines.size();
        }
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

        writeLineNumber(numerOfLines);

        Border border = BorderFactory.createEmptyBorder(0, 20, 0, 0);
        lineNumber.setBorder(border);
        
        lineNumber.setPreferredSize(new Dimension(50,lineNumber.getHeight()));
        lineNumber.setMaximumSize(new Dimension(50,9999));

        codePanel.add(lineNumber);
    }

    private void writeLineNumber (int numerOfLines) {
        lineNumber.setText("");
        for (int i = 1; i <= numerOfLines; i++) {
            lineNumber.setText(lineNumber.getText() + i + "\n");;
        }
        lineNumber.setEditable(false);
    }

    private void buildCodeField() {
        codeField = new JTextPane();
        
        codeField.setForeground(Palette.instance().getOffWhite());
        codeField.setFont(new Font("Cascadia Code", Font.PLAIN, 17));
        codeField.setBackground(Palette.instance().getBlack());

        Border border = BorderFactory.createEmptyBorder(0, 15, 0, 0);
        codeField.setBorder(border);
    }

    private void paintCodeField(List<String> codelines) {
        buildCodeField();

        for (int i = 0; i < determineNumLines(codelines); i++) {
            codeField.setText(codeField.getText() + codelines.get(i) + "\n");;
        }

        if (determineNumLines(codelines) > 0) {
            codeField.setEditable(false);
        } else {
            codeField.addKeyListener(this);
        }

        codePanel.add(codeField);
    }

    private void buildScrollPane(){
        this.setPreferredSize(new Dimension(635, 200));
        this.setMinimumSize(new Dimension(544, 150));

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

    private int countNumberOfLines() {
        char newLine = '\n';
        int numerOfLines = 1;

        for(int i = 0; i < codeField.getText().length(); i++) {
            if (codeField.getText().charAt(i) == newLine) {
                numerOfLines++;
            }
        }

        return numerOfLines;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        writeLineNumber(countNumberOfLines());
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
