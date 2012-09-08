package jp.namihira.digitalclock;

import java.awt.Color;

import javax.swing.ImageIcon;

public class ComboLabel {
	private Color color;
	private String text;

	private ImageIcon imageicon;
	
	ComboLabel(Color color, String text){
		this.color = color;
		this.text = text;
	}

	public ComboLabel(String text, ImageIcon imageIcon) {
		this.imageicon = imageIcon;
		this.text = text;
	}

	public Color getColor(){
		return color;
	}
	
	public String getText(){
		return text;
	}
	
	public ImageIcon getImageIcon(){
		return imageicon;
	}
	
}
