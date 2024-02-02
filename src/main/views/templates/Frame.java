package main.views.templates;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import main.data.Palette;
import main.data.Size;

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
    }

    public void setView(JPanel view) {
        if (this.view != null) {
            this.remove(this.view);
            System.out.println("Removed");
        }
        this.view = view;
        view.validate();
        this.add(view);

        this.validate();
        System.out.println("Here");
    }
}
