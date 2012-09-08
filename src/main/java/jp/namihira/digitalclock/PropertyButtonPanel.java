package jp.namihira.digitalclock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PropertyButtonPanel extends JPanel {
	private PropertyDialog dialog;
	private JButton setButton = new JButton("OK");
	private JButton cancelButton = new JButton("Button");
	
	public PropertyButtonPanel(PropertyDialog dialog){
		this.dialog = dialog;
		setButton.addActionListener(new SetActionListener());
		cancelButton.addActionListener(new CancelActionListener());
		add(setButton);
		add(cancelButton);
	}
	
	class SetActionListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			dialog.updateClock(dialog.getSelectedFont(), dialog.getSelectedFontColor(), dialog.getSelectedColor());
			dialog.dispose();
		}
	}
	
	class CancelActionListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			dialog.dispose();
		}
	}
 }
