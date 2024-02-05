package main.views.components;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.utils.Size;
import main.views.pages.Frame;


public class PopUp extends JFrame {
    private static PopUp popup;
    JPanel view;
   
    public static PopUp instance() {
		if (popup == null){
			popup = new PopUp();
            popup.setLocationRelativeTo(Frame.instance());
		}
		return popup;
	}

    public static void deleteInstance() {
        popup.setVisible(false);
        popup = null;
    }

    public PopUp() {
        this.setUndecorated(true);
        this.setVisible(true);
        this.setMinimumSize(Size.instance().getPopupDimension());
        this.setSize(Size.instance().getPopupDimension());
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
