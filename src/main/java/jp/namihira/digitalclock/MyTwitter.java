package jp.namihira.digitalclock;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class MyTwitter {
	
	private JDialog dialog;
	private JTextField textField;
	private JList list;
	private JButton button;
	private DefaultListModel model;
	private JScrollPane sp;
	private JButton tweetButton;
	private JTextField tweetText;
	
	private Twitter twitter;
	private ClockPanel clockPanel;
	
	public MyTwitter(ClockPanel clockPanel){
/*
		System.setProperty("http.proxyHost", "10.6.248.80");
	    System.setProperty("http.proxyPort", "8080");
		System.setProperty("http.proxyUser", "p000506809");
		System.setProperty("http.proxyPassword", "hswxw818");
*/
		this.clockPanel = clockPanel;
		twitter = new TwitterFactory().getInstance();
		
		dialog = new JDialog();
		dialog.setTitle("Twiiter");
		dialog.setResizable(false);
		
		textField = new JTextField(10);
		button = new JButton("button");
		model = new DefaultListModel();
		list = new JList(model);
		sp = new JScrollPane();
		tweetButton = new JButton("button");
		tweetText = new JTextField();
		
		dialog.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0; gbc.gridy = 0;
		gbc.gridwidth = 1; gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		dialog.add(textField, gbc);
		
		gbc.gridx = 1; gbc.gridy = 0;
		gbc.gridwidth = 1; gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		dialog.add(button, gbc);
		button.addActionListener(new ButtonActionListener());
		
		gbc.gridx = 0; gbc.gridy = 1;
		gbc.gridwidth = 2; gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		dialog.add(sp, gbc);
		sp.getViewport().setView(list);
		sp.setPreferredSize(new Dimension(800, 200));
		
		gbc.gridx = 0; gbc.gridy = 2;
		gbc.gridwidth = 1; gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		tweetButton.setEnabled(false);
		dialog.add(tweetButton, gbc);
		tweetButton.addActionListener(new TwitterButtonActionListener());
		
		gbc.gridx = 1; gbc.gridy = 2;
		gbc.gridwidth = 1; gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		tweetText.setEnabled(false);
		dialog.add(tweetText, gbc);
		
		dialog.pack();
	}
	
	public void setVisible(boolean flag){
		model.clear();
		dialog.setVisible(flag);
	}
	
	public void getTimeLine(){
		tweetText.setEnabled(false);
		tweetButton.setEnabled(false);
		model.clear();
		try {
			List<Status> statuses;
			String accountName = textField.getText();
			if (accountName.equals("")) {
				statuses = twitter.getPublicTimeline();
				for (Status status : statuses) {
					model.addElement("[" + status.getUser().getName() + "]:" + status.getText());
				}
			} else if (accountName.equals("namihira_k+")) {
				tweetText.setEnabled(true);
				tweetButton.setEnabled(true);
				statuses = twitter.getHomeTimeline();
				for (Status status : statuses) {
					model.addElement("[" + status.getUser().getScreenName() + "]:" + status.getText());
				}
			} else {
				if (accountName.equals("namihira_k")) {
					tweetText.setEnabled(true);
					tweetButton.setEnabled(true);
				}
				statuses = twitter.getUserTimeline(accountName);
				for (Status status : statuses) {
					model.addElement(":" + status.getText());
				}
			}
		} catch (TwitterException e) {
			int status = e.getStatusCode();
			if (status == 404) {
				model.addElement("縺昴ｓ縺ｪ繝ｦ繝ｼ繧ｶ縺�↑縺�〒縺吶�");
			} else {
				model.addElement("HTTP/1.1 " + e.getStatusCode() + " 騾壻ｿ｡繧ｨ繝ｩ繝ｼ");
			}
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		dialog.pack();
	}
	
	
	
	private class ButtonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			getTimeLine();
		}
	}
	
	private class TwitterButtonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			String tweet = tweetText.getText();
			try{
				twitter.updateStatus(tweet + " at " + clockPanel.getTime());
				Thread.sleep(1000);
			} catch (TwitterException te) {
				te.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			tweetText.setText("");
			getTimeLine();
		}
	}
}
