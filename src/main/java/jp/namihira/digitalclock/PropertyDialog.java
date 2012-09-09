package jp.namihira.digitalclock;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class PropertyDialog extends JDialog {
	Clock owner;
	private JLabel label = new JLabel(" ", JLabel.CENTER);
	private PropertyButtonPanel buttonPanel = new PropertyButtonPanel(this);
	private DefaultComboBoxModel model1 = new DefaultComboBoxModel();
	private DefaultComboBoxModel model2 = new DefaultComboBoxModel();
	
	private JComboBox 	familyChoice = new JComboBox(),
					styleChoice = new JComboBox(),
					sizeChoice = new JComboBox();
	
	private JComboBox colorChoice, fontColorChoice;
	
	public PropertyDialog(Clock owner) {
		this.owner = owner;
		setLocation(100, 100);
		setResizable(false);
		
		addWindowListener(new ExWindowAdapter());
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0; gbc.gridy = 0;
		gbc.gridwidth = 3; gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		label.setOpaque(true);
		add(label, gbc);
		
		gbc.fill = GridBagConstraints.CENTER;
		
		JLabel fontLabel = new JLabel("Label1");
		gbc.gridx = 0; gbc.gridy = 1;
		gbc.gridwidth = 1; gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.EAST;
		add(fontLabel, gbc);
		
		JLabel fontStyleLabel = new JLabel("Label2");
		gbc.gridx = 0; gbc.gridy = 2;
		gbc.gridwidth = 1; gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.EAST;
		add(fontStyleLabel, gbc);
		
		JLabel fontSizeLabel = new JLabel("Label3");
		gbc.gridx = 0; gbc.gridy = 3;
		gbc.gridwidth = 1; gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.EAST;
		add(fontSizeLabel, gbc);
		
		JLabel fontColorLabel = new JLabel("Label4");
		gbc.gridx = 0; gbc.gridy = 4;
		gbc.gridwidth = 1; gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.EAST;
		add(fontColorLabel, gbc);
		
		JLabel backGroudColorLabel = new JLabel("Label5");
		gbc.gridx = 0; gbc.gridy = 5;
		gbc.gridwidth = 1; gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.EAST;
		add(backGroudColorLabel, gbc);
		
		populateFonts();
		populateStyles();
		populateSizes();
		populateFontColor();
		populateColor();
		
		gbc.gridx = 1; gbc.gridy = 1;
		gbc.gridwidth = 1; gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.WEST;
		add(familyChoice, gbc);
		
		gbc.gridx = 1; gbc.gridy = 2;
		gbc.gridwidth = 1; gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.WEST;
		add(styleChoice, gbc);
		
		gbc.gridx = 1; gbc.gridy = 3;
		gbc.gridwidth = 1; gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.WEST;
		add(sizeChoice, gbc);
		
		
		ExCellRenderer renderer = new ExCellRenderer(); 
		fontColorChoice = new JComboBox(model1);
		fontColorChoice.setRenderer(renderer);
	//	fontColorChoice.setPreferredSize(new Dimension(100, 20));
		gbc.gridx = 1; gbc.gridy = 4;
		gbc.gridwidth = 1; gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.WEST;
		add(fontColorChoice, gbc);
		
		colorChoice = new JComboBox(model2);
		colorChoice.setRenderer(renderer);
		gbc.gridx = 1; gbc.gridy = 5;
		gbc.gridwidth = 1; gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.WEST;
		add(colorChoice, gbc);
		
		Listener listener = new Listener();
		
		familyChoice.addItemListener(listener);
		styleChoice.addItemListener(listener);
		sizeChoice.addItemListener(listener);
		fontColorChoice.addItemListener(listener);
		colorChoice.addItemListener(listener);
		
		gbc.gridx = 1; gbc.gridy = 6;
		gbc.gridwidth = 2; gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.EAST;
		add(buttonPanel, gbc);
		
		updateLabel(getSelectedFont(), getSelectedColor(), getSelectedFontColor());
		
		pack();
	}

	public void updateClock(Font font, Color fontColor, Color color){
		owner.updateClockPanel(font, fontColor, color);
	}
	
	public void updateLabel(Font font, Color color, Color fontColor){
		label.setText(fullNameOfFont(font));
		label.setFont(font);
		label.setForeground(fontColor);
		label.setBackground(color);
	}
	
	private String fullNameOfFont(Font font){
		String family = font.getFamily();
		String style = "";
		
		switch(font.getStyle()){
			case Font.PLAIN:	style = " Plain "; break;
			case Font.BOLD:		style = " Bold "; break;
			case Font.ITALIC:	style = " Italic "; break;
			case Font.BOLD + Font.ITALIC:
				style = " Bold Italic ";
				break;
		}
		return family + style + Integer.toString(font.getSize());
	}
	
	class ExWindowAdapter extends WindowAdapter{
		public void windowClosing(WindowEvent event){
			dispose();
		}
	}
	
	
	public Font getSelectedFont(){
		return new Font(familyChoice.getSelectedItem().toString(),
						styleChoice.getSelectedIndex(),
						Integer.parseInt(sizeChoice.getSelectedItem().toString()));
	}
	
	public Color getSelectedColor(){
		switch(colorChoice.getSelectedIndex()){
			case 0:	return Color.WHITE;
			case 1: return Color.RED;
			case 2: return Color.GREEN;
			case 3: return Color.BLUE;
			default:	return Color.WHITE;
		}
	}
	
	public Color getSelectedFontColor(){
		switch(fontColorChoice.getSelectedIndex()){
			case 0:	return Color.BLACK;
			case 1: return Color.RED;
			case 2: return Color.GREEN;
			case 3: return Color.BLUE;
			default:	return Color.WHITE;
		}
	}
	
	private void populateFonts(){
		String fontNames[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		for(int i = 0; i < fontNames.length; ++i){
			familyChoice.addItem(fontNames[i]);
		}
	}
	
	private void populateStyles(){
		styleChoice.addItem("Plain");
		styleChoice.addItem("Bold");
		styleChoice.addItem("Italic");
		styleChoice.addItem("BoldItalic");
	}
	
	private void populateSizes(){
		String sizes[] = {"50", "60", "70", "80", "90"};
		for(int i = 0; i < sizes.length; ++i){
			sizeChoice.addItem(sizes[i]);
		}
	}

	private void populateFontColor(){
		String colors[] = {"BLACK", "RED", "GREEN", "BLUE"};

		model1.addElement(new ComboLabel(colors[0], new ImageIcon("./img/black.png")));
		model1.addElement(new ComboLabel((colors[1]), new ImageIcon("./img/red.png")));
		model1.addElement(new ComboLabel((colors[2]), new ImageIcon("./img/green.png")));
		model1.addElement(new ComboLabel((colors[3]), new ImageIcon("./img/blue.png")));	
	}
	
	private void populateColor(){
		String colors[] = {"WHITE", "RED", "GREEN", "BLUE"};	
		model2.addElement(new ComboLabel(colors[0], new ImageIcon("./img/white.png")));
		model2.addElement(new ComboLabel((colors[1]), new ImageIcon("./img/red.png")));
		model2.addElement(new ComboLabel((colors[2]), new ImageIcon("./img/green.png")));
		model2.addElement(new ComboLabel((colors[3]), new ImageIcon("./img/blue.png")));
	}
	


	public class Listener implements ItemListener{
		public void itemStateChanged(ItemEvent event){
			updateLabel(getSelectedFont(), getSelectedColor(), getSelectedFontColor());
			pack();
		}
	}
}
