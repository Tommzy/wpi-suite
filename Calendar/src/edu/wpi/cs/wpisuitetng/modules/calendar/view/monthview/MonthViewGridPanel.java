package edu.wpi.cs.wpisuitetng.modules.calendar.view.monthview;

import java.awt.Color;

import javax.swing.*;

import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddEventTabPanel;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class MonthViewGridPanel extends JPanel {
	private JLabel headerLabel = new JLabel();
	private JTextArea textArea = new JTextArea();
	public static int mod = 1;
	public MonthViewGridPanel() {
		setBackground(Color.white);
		textArea.setEditable(false);
		setLayout(new MigLayout("insets 0 0 0 0, height :100:"));
		headerLabel.setOpaque(true);
		headerLabel.setBackground(new Color(138, 173, 209));
		add(headerLabel, "width :100%:, wrap, span");
		if (mod == 1) {
			add(textArea, "span, width :200:, height :100:");
		} else {
			add(textArea, "span, width 100%:100%:100%, height 80%:80%:80%");
		}

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
