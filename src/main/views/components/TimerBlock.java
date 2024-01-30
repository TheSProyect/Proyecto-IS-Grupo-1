import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.awt.Color;
import javax.swing.ImageIcon;

import main.data.Palette;

public class TimerBlock extends JPanel{
	private JLabel TwoPoints, MinLabel, SecLabel, TimeRemainingLabel;
	private Timer T;
	
	public TimerBlock(int TimeLimit, JButton stopButton){
		setLayout(null);
		this.setBounds(0,0, 300, 200);
		this.setBackground(Palette.instance().getBlue());
		
		TwoPoints = new JLabel(":");
		TwoPoints.setBounds(85, 180, 20, 20);
		MinLabel = new JLabel(String.valueOf(TimeLimit));
		MinLabel.setBounds(60, 180, 20, 20);
		SecLabel = new JLabel("00");
		SecLabel.setBounds(100, 180, 20, 20);
		TimeRemainingLabel = new JLabel("Tiempo Restante");
		TimeRemainingLabel.setBounds(60, 160,80, 20);
		
		this.add(TwoPoints);
		this.add(MinLabel);
		this.add(SecLabel);
		this.add(TimeRemainingLabel);
		
		
		
		T = new Timer(1000, new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				int TimeRemaining = (Integer.parseInt(MinLabel.getText())*60 + (Integer.parseInt(SecLabel.getText())));
				
				TimeRemaining--;
				if((TimeRemaining/60)<10 && (TimeRemaining%60)<10){
					MinLabel.setText("0"+String.valueOf(TimeRemaining/60));
					SecLabel.setText("0"+String.valueOf(TimeRemaining%60));
				} else if((TimeRemaining/60)<10){
					MinLabel.setText("0"+String.valueOf(TimeRemaining/60));
					SecLabel.setText(String.valueOf(TimeRemaining%60));
				} else if((TimeRemaining%60)<10){
					MinLabel.setText(String.valueOf(TimeRemaining/60));
					SecLabel.setText("0"+String.valueOf(TimeRemaining%60));
				} else{
					MinLabel.setText(String.valueOf(TimeRemaining/60));
					SecLabel.setText(String.valueOf(TimeRemaining%60));
				}
				if(TimeRemaining<0){
				T.stop();
				stopButton.doClick();
				}
			}
			
		});
		T.start();
	}
	
	
	
	
}