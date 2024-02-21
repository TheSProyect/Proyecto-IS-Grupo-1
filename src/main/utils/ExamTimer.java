package main.utils;

import javax.swing.*;
import java.awt.event.*;

public class ExamTimer {
  private static ExamTimer t;
  public Timer clock;
  public static Timer instance(){
		return clock;
	}
public void StartTimer(){
	clock.start();
}
public void StopTimer(){
	clock.stop();
}
	
}

