package main.views.pages;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;

import javax.swing.ImageIcon;

import main.utils.Size;

public class Frame extends JFrame{
    private static Frame frame;
    JPanel view;

    public static Frame instance() {
		if (frame == null){
			frame = new Frame();
		}
		return frame;
	}

    public Frame() {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(Size.instance().getDefaultFrame());
        this.setSize(Size.instance().getDefaultFrame());
        // this.setIconImage(new ImageIcon(getClass().getResource("/assets/Favicon.png")).getImage());
    }

    public void setView(JPanel view) {
        Dimension currentSize = Size.instance().getDefaultFrame();

        if (this.view != null) {
            currentSize = this.view.getSize();
            this.remove(this.view);
        }
        this.view = view;
        this.add(view);
        this.view.setPreferredSize(currentSize);

        view.repaint();
        this.pack();
    }
}
