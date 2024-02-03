package main.views.components;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.Timer;

import main.utils.Palette;

import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.Font;

public class TimerBlock extends JPanel{
	private JLabel TwoPoints, MinLabel, SecLabel, TimeRemainingLabel, imgLabel;
	private Timer T;
	private ImageIcon ClockImg;

	public TimerBlock(int TimeLimit, JButton stopButton){
		this.paintPanel();
		
		JLabel TwoPoints = new JLabel(":");
		this.paintTimeLabel(TwoPoints, 115);
		
		JLabel MinLabel = new JLabel(TimerFormat(String.valueOf(TimeLimit)));
		this.paintTimeLabel(MinLabel, 85);
		
		JLabel SecLabel = new JLabel("00");
		this.paintTimeLabel(SecLabel, 125);
		
		TimeRemainingLabel = new JLabel("Tiempo restante");
		TimeRemainingLabel.setBounds(70, 10,200, 25);
		TimeRemainingLabel.setFont(new Font("Nunito Sans", Font.BOLD, 13));
		TimeRemainingLabel.setForeground(Palette.instance().getOffWhite());
		
		this.add(TimeRemainingLabel);

		this.paintImageIcon(ClockImg, imgLabel);
		
		T = new Timer(1000, setActionListener(stopButton, MinLabel, SecLabel));
		T.start();

	}

	public void paintTimeLabel(JLabel label, int width){
		label.setBounds(width, 30, 30, 25);
		label.setForeground(Palette.instance().getWhite());
		label.setFont(new Font("Nunito Sans", Font.BOLD, 25));
		this.add(label);
	}
	
	public void paintPanel(){
		this.setLayout(null);
		this.setPreferredSize(new Dimension(200, 75));
		this.setBackground(Palette.instance().getBlue());
	}

	public void paintImageIcon(ImageIcon Img, JLabel imgLabel){
		Img = new ImageIcon("assets/Timer_Icon.png");
		imgLabel = new JLabel("");
		imgLabel.setBounds(10,10, 55, 55);
		imgLabel.setIcon(Img);
		this.add(imgLabel);
	}

	public ActionListener setActionListener(JButton stopButton, JLabel MinLabel, JLabel SecLabel){
		ActionListener taskPerformer = new ActionListener() {
			int TimeRemaining = 10;
			public void actionPerformed(ActionEvent e){
				TimeRemaining = (Integer.parseInt(MinLabel.getText())*60 + (Integer.parseInt(SecLabel.getText())));
				
				TimeRemaining--;
				MinLabel.setText(TimerFormat(String.valueOf(TimeRemaining/60)));
				SecLabel.setText(TimerFormat(String.valueOf(TimeRemaining%60)));
				
				if(TimeRemaining<0){
				T.stop();
				stopButton.doClick();
				}
			}
		};
		return taskPerformer;
	}

	public String TimerFormat(String Time){
		if(Integer.parseInt(String.valueOf(Time))<10){
			Time = "0"+Time;
		}
		return Time;
	}
	
}
