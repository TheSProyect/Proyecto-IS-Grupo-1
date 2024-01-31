package main.views.components;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.Font;

import main.data.Palette;

public class TimerBlock extends JPanel{
	private JLabel TwoPoints, MinLabel, SecLabel, TimeRemainingLabel, imgLabel;
	private Timer T;
	private ImageIcon ClockImg;
	
	public TimerBlock(int TimeLimit, JButton stopButton){
		this.setLayout(null);
		this.setPreferredSize(new Dimension(200, 75));
		this.setBackground(Palette.instance().getBlue());
		
		TwoPoints = new JLabel(":");
		TwoPoints.setBounds(115, 30, 20, 25);
		TwoPoints.setFont(new Font("Nunito Sans", Font.BOLD, 25));
		TwoPoints.setForeground(Palette.instance().getWhite());
		
		MinLabel = new JLabel(String.valueOf(TimeLimit));
		MinLabel.setBounds(85, 30, 30, 25);
		MinLabel.setFont(new Font("Nunito Sans", Font.BOLD, 25));
		MinLabel.setForeground(Palette.instance().getWhite());
		
		
		SecLabel = new JLabel("00");
		SecLabel.setBounds(125, 30, 30, 25);
		SecLabel.setFont(new Font("Nunito Sans", Font.BOLD, 25));
		SecLabel.setForeground(Palette.instance().getWhite());
		
		TimeRemainingLabel = new JLabel("Tiempo restante");
		TimeRemainingLabel.setBounds(70, 10,200, 25);
		TimeRemainingLabel.setFont(new Font("Nunito Sans", Font.BOLD, 13));
		TimeRemainingLabel.setForeground(Palette.instance().getOffWhite());
		
		ClockImg = new ImageIcon("assets/Timer_Icon.png");
		imgLabel = new JLabel("");
		imgLabel.setBounds(10,10, 55, 55);
		imgLabel.setIcon(ClockImg);
		this.add(imgLabel);
		
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
				// stopButton.doClick();
				}
			}
			
		});
		T.start();

		// this.validate();
	}
	
	
	
	
}
