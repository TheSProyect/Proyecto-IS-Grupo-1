package main.views.components;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

import main.utils.Palette;

import java.awt.Dimension;
import java.awt.Font;

public class TimerBlock extends JPanel{
	private JLabel TimeRemainingLabel, imgLabel;
	private Timer T;
	private ImageIcon ClockImg;

	public TimerBlock(int TimeLimit, JButton stopButton){
		this.paintPanel();
		
		JLabel TimeLabel = new JLabel(TimerFormat(String.valueOf(TimeLimit))+":00");
		this.paintTimeLabel(TimeLabel);
		
		
		TimeRemainingLabel = new JLabel("Tiempo restante");
		TimeRemainingLabel.setBounds(70, 10,200, 25);
		TimeRemainingLabel.setFont(new Font("Nunito Sans", Font.BOLD, 13));
		TimeRemainingLabel.setForeground(Palette.instance().getOffWhite());
		
		this.add(TimeRemainingLabel);

		this.paintImageIcon(ClockImg, imgLabel);
		
		T = new Timer(1000, setActionListener(stopButton, TimeLabel));
		T.start();

	}

	public void paintTimeLabel(JLabel label){
		label.setBounds(88, 30, 80, 25);
		label.setForeground(Palette.instance().getWhite());
		label.setFont(new Font("Nunito Sans", Font.BOLD, 24));
		this.add(label);
	}
	
	public void paintPanel(){
		this.setLayout(null);
		this.setPreferredSize(new Dimension(200, 75));
		this.setBackground(Palette.instance().getBlue());
	}

	public void paintImageIcon(ImageIcon Img, JLabel imgLabel){
		String directory = System.getProperty("user.dir");
        directory = directory+File.separator+"src"+File.separator+"assets"+File.separator;
		directory = directory+"Timer_Icon.png";
		Img = new ImageIcon(directory);
		imgLabel = new JLabel("");
		imgLabel.setBounds(10,10, 55, 55);
		imgLabel.setIcon(Img);
		this.add(imgLabel);
	}

	public ActionListener setActionListener(JButton stopButton, JLabel TimeLabel){
		ActionListener taskPerformer = new ActionListener() {
			int TimeRemaining;
			public void actionPerformed(ActionEvent e){
				String[] parts = TimeLabel.getText().split(":");
				
				TimeRemaining = (Integer.parseInt(parts[0])*60) + (Integer.parseInt(parts[1]));
				
				TimeRemaining--;

				if(TimeRemaining<0){
					T.stop();
					stopButton.doClick();
				} else {

				TimeLabel.setText(TimerFormat(String.valueOf(TimeRemaining/60))+":"+TimerFormat(String.valueOf(TimeRemaining%60)));
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

	public void StopTimer(){
		T.stop();
	}
	
}
