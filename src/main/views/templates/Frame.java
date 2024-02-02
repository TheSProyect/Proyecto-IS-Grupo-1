package main.views.templates;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
        }
        this.view = view;
        this.add(view);
        
        view.repaint();
        this.pack();
    }
}
