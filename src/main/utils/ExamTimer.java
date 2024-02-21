package main.utils;

import javax.swing.*;
import java.awt.event.*;

public class ExamTimer {
  private static Timer t;
  public static ExamTimer instance(){
		return t;
	}

	public void StartTimer(){
		t.start();
	}

	public void StopTimer(){
		t.stop();
	}

}
