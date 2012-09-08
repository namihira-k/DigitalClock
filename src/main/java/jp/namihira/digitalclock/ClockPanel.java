package jp.namihira.digitalclock;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Calendar;

import javax.swing.JPanel;

public class ClockPanel extends JPanel {

	private static final long serialVersionUID = 8671718620255777359L;
	private FontMetrics fontMetrics;
	private Font font;
	private Color color;
	private Clock clock;
	
//	private boolean sequenceFlag;
	
	private String time;
	
	public ClockPanel(Clock clock) {
		font = new Font("Helvetica", Font.BOLD, 50);
		fontMetrics = getFontMetrics(font);
		setSize(fontMetrics.stringWidth("00:00:00") + 10, fontMetrics.getHeight() + 10);
		color = Color.BLACK;
		this.clock = clock;
	}
	
	
	public void setSize(Font font){
		fontMetrics = getFontMetrics(font);
		setSize(fontMetrics.stringWidth("00:00:00") + 10, fontMetrics.getHeight() + 10);
	}
	
	public void setFontColor(Color color){
		this.color = color;
	}
	
	public void setFont(Font font){
		this.font = font;
	}
	
	public String getTime(){
		return time;
	}
	
	public void paintComponent(Graphics g){
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		int sec = calendar.get(Calendar.SECOND);
		String time;
		
		if(hour < 10){
			time = "0" + hour + ":";
		}else{
			time = (hour + ":");
		}
		
		if(min < 10){
			time += "0";
		}
		time += (min + ":");
		
		if(sec < 10){
			time += "0";
		}
		time += sec;
		
		g.setFont(font);
		g.setColor(color);

		this.time = time;
		g.drawString(time, 10, fontMetrics.getHeight());
		
/*
		if ((sec % 30) == 0) {
			if (sequenceFlag == false) {
				clock.getTwitter().getTimeLine();
				sequenceFlag = true;
				return;
			}
		} else {
			sequenceFlag = false;
		}
*/
		
		if ((hour == 9) && (min == 0) && (sec == 5)) {
			clock.getFrame().setBackground(Color.BLUE);
		} else if ((hour == 13) && (min == 0) && (sec == 5)) {
			clock.getFrame().setBackground(Color.GREEN);
		} else if ((hour == 17) && (min == 30) && (sec == 5)) {
			clock.getFrame().setBackground(Color.RED);
		}

	}
	
}
