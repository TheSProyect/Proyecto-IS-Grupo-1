package main.views.components;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;

import main.data.Palette;

public class CodeField extends JScrollPane {
    JTextPane lineNumber;
    JTextPane codeField;
    JPanel codePanel;

    CodeField() {
        buildScrollPane();
        
        buildCodePanel();
        paintLineNumber();
        buildCodeField();
    }

    private void buildCodePanel() {
        codePanel = new JPanel();
        codePanel.setPreferredSize(new Dimension(600,200));
        codePanel.setLayout(new BoxLayout(codePanel, BoxLayout.X_AXIS));

        this.setViewportView(codePanel);
    }

    private void paintLineNumber() {
        lineNumber = new JTextPane();
        lineNumber.setPreferredSize(new Dimension(50,150));
        lineNumber.setMaximumSize(new Dimension(50,400));
        
        lineNumber.setForeground(Palette.instance().getGray());
        lineNumber.setFont(new Font("Cascadia Code", Font.PLAIN, 17));
        lineNumber.setBackground(Palette.instance().getBlack());
        lineNumber.setText("1 \n2 \n3 \n4 \n5 \n6 \n7 \n8 \n9 \n10");
        lineNumber.setEditable(false);

        Border border = BorderFactory.createEmptyBorder(0, 20, 0, 0);
        lineNumber.setBorder(border);

        codePanel.add(lineNumber);
    }

    private void buildCodeField() {
        codeField = new JTextPane();
        codeField.setPreferredSize(new Dimension(400, 150));
        
        codeField.setForeground(Palette.instance().getOffWhite());
        codeField.setFont(new Font("Cascadia Code", Font.PLAIN, 17));
        codeField.setBackground(Palette.instance().getBlack());
        codeField.setText("var i = 1234; \nvar s = \" \" + i; \nif (\"1234\".equals(s)) \n");
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
