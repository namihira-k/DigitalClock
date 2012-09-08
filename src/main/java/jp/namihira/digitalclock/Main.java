package jp.namihira.digitalclock;


public class Main {
	
	private Main(){};
	
	
	public static void main(final String[] args) {
		ClockMain mainframe = new ClockMain("DigitalClock");
		Thread thread = new Thread(mainframe);
		thread.start();	
	}
}
