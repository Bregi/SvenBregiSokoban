package ch.bfh.ti.projekt1.sokoban.model;

import java.text.DecimalFormat;

import ch.bfh.ti.projekt1.sokoban.view.ClockView;

public class Clock {
	static boolean isRunning = false;
    static String seconds = "";
    static int secondsCounter = 0;
    static String minutes = "";
    static int minutesCounter = 0;
    static String hours = "";
    static int hoursCounter = 0;
    
    public Clock(){
    }
	public void startClock(){
		this.isRunning = true;
	}
	
	public void stopClock(){
		this.isRunning = false;
	}
    public void setTime() {
        secondsCounter++;
        if(secondsCounter<=59) {   
            DecimalFormat df = new DecimalFormat("00");
            seconds= df.format(secondsCounter);
        } else {
        	secondsCounter = 0;
            minutesCounter++;
        }
       
        if(minutesCounter<=59) {
            DecimalFormat df = new DecimalFormat("00");
            minutes = df.format(minutesCounter);
            } else {
                minutesCounter = 0; 
                hoursCounter++;
            }
       
        DecimalFormat df = new DecimalFormat("00");
        hours = df.format(hoursCounter);
       
       // ClockView.lblTime.setText(hours + ":" + minutes + ":" + seconds);
    }
}
