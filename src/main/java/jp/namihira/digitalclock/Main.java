package jp.namihira.digitalclock;


public class Main {
	
	private Main(){};
	
	
	public static void main(final String[] args) {
		Clock mainframe = new Clock("DigitalClock");
		Thread thread = new Thread(mainframe);
		thread.start();	
	}
}
