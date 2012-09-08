package jp.namihira.digitalclock;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class Confirm extends JDialog {
	
	private static final long serialVersionUID = -6822297999607508055L;

	private GridBagConstraints gbc = new GridBagConstraints();
	
	private String file = "img/ricoh.jpg";
	private String[] otufile = {"img/otu1.jpg", "img/otu2.jpg"};
	private ImageIcon imageIcon = new ImageIcon(file);
	private JLabel label = new JLabel();
	
	private JButton kaeruButton = new JButton("残業してません。");
	private JButton zangyoButton = new JButton("残業しました。");
	
	private int limitHour = 8;
	private int limitMinute = 30;
	
	public Confirm() {
//		setSize(200, 200);
		setLocation(200, 200);
		setResizable(false);
		setLayout(new GridBagLayout());
		
		label.setIcon(imageIcon);
		gbc.gridx = 0; gbc.gridy = 0;
		gbc.gridwidth = 2; gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		add(label, gbc);
		
		gbc.gridx = 0; gbc.gridy = 1;
		gbc.gridwidth = 1; gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		kaeruButton.setOpaque(true);
		kaeruButton.addActionListener(new KaeruActionListener());
		add(kaeruButton, gbc);
		
		gbc.gridx = 1; gbc.gridy = 1;
		gbc.gridwidth = 1; gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		zangyoButton.setOpaque(true);
		zangyoButton.addActionListener(new ZangyoActionListener());
		add(zangyoButton, gbc);
		
		setTitle("残業申請");
		toFront();
		setVisible(true);
		pack();
	}
	
	class KaeruActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			new Log().save();
			int i = Math.abs(new Random().nextInt()) % 2;
			System.out.println(i);
			imageIcon = new ImageIcon(otufile[i]);
			label.setIcon(imageIcon);
			kaeruButton.setEnabled(false);
			zangyoButton.setEnabled(false);
			
			Calendar now = Calendar.getInstance();
			int diffHour = now.get(Calendar.HOUR_OF_DAY) - limitHour;
	        int diffMinute = now.get(Calendar.MINUTE) - limitMinute;
	        if (diffMinute < 0) {
	        	diffMinute = 0;
	        }
			
	        if (diffMinute < 10) {
	        	setTitle("残業時間[{��̎" + diffHour + ":0" + diffMinute + "]");
	        } else {
	        	setTitle("残業時間[{��̎" + diffHour + ":" + diffMinute + "]");
	        }
			pack();
		}
	}
	
	class ZangyoActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			setVisible(false);
		}
	}
}
