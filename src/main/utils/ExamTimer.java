package main.utils;

import javax.swing.*;
import java.awt.event.*;

public class ExamTimer {
  private static Timer t;
  public static ExamTimer instance(){
		if(t == null){
			t = new Timer();
		}
		return t;
	}
}
