package edu.wpi.cs.wpisuitetng.modules.calendar.view.monthview;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class MonthViewGridPanel extends JPanel {
	private JLabel headerLabel = new JLabel();
	private JTextArea textArea = new JTextArea();
	
	public MonthViewGridPanel() {
		setLayout(new MigLayout("insets 0 0 0 0"));
		add(headerLabel, "width :100:, wrap, span");
		add(textArea, "span, width :200:, height :100:");
	}
	
	public JLabel getHeaderLabel() {
		return headerLabel;
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}
	
	public void setHeader(String s) {
		headerLabel.setText(s);
	}
	
	public void setTextArea(String s) {
		textArea.setText(s);
	}
}
