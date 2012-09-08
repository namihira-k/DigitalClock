package jp.namihira.digitalclock;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ExCellRenderer extends JLabel implements ListCellRenderer {
	
	ExCellRenderer(){
	      setOpaque(true);
	}
	

	public Component getListCellRendererComponent(JList arg0, Object arg1,
			int arg2, boolean arg3, boolean arg4) {
		
		ComboLabel data = (ComboLabel)arg1;

		setIcon(data.getImageIcon());
		setText(data.getText());
	    
	    if (arg3){
	    	setForeground(Color.white);
	    	setBackground(Color.black);
	    }else{
	    	setForeground(Color.black);	
	        setBackground(Color.white);
	    }
	    
	    return this;	
	}

}
