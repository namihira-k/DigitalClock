package jp.namihira.digitalclock;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class Graph extends JFrame {

	public Graph() {
		JTabbedPane tab = new JTabbedPane();
		
		tab.addTab("日毎", new GraphDay());
		tab.addTab("月毎", new GraphMonth());
		
		getContentPane().add(tab, BorderLayout.CENTER);
		
		setTitle("残業グラフ");
		
		setLocation(100, 100);
		pack();
		setResizable(false);
		setVisible(true);
	}
	
	
}
