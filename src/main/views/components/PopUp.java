package main.views.components;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.utils.Size;
import main.views.pages.Frame;
import main.views.pages.NewExamPopup;


public class PopUp extends JFrame {
    private static PopUp popup;
    JPanel view;
   
    public static PopUp instance(Dimension Size) {
		if (popup == null){
			popup = new PopUp(Size);
            popup.setLocationRelativeTo(Frame.instance());
		}
		return popup;
	}

    public static void deleteInstance() {
        popup.setVisible(false);
        popup = null;
    }

    public PopUp(Dimension Size) {
        this.setUndecorated(true);
        this.setVisible(true);
        this.setSize(Size);

    }


    public void setView(JPanel view) {
        if (this.view != null) {
            this.remove(this.view);
        }
        this.view = view;
        this.add(view);
        
        view.repaint();
        this.setAlwaysOnTop(true);
        this.pack();
    }

}
