package edu.wpi.cs.wpisuitetng.modules.calendar.view.monthview;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

/**
 * MonthView
 * @author Hongbo
 *
 */
@SuppressWarnings("serial")
public class MonthView extends JPanel {
	private MonthViewPanel monthViewPanel;
	private JLabel monthTitleLabel = new JLabel();
	private MonthName monthName = new MonthName();
	private Component superComponent;
	
	public MonthView(Component comp) {
		superComponent = comp;
		System.out.println(comp);
		setLayout(new MigLayout("insets 0 0 0 0"));
		monthViewPanel = new MonthViewPanel(2013, 9, 27);
		monthTitleLabel.setText(getTitle(2013, 10));
		add(monthTitleLabel, "wrap");
		add(monthViewPanel);
	}
	
	public MonthViewPanel getMonthViewPanel() {
		return monthViewPanel;
	}
	
	public JLabel getMonthTitleLabel() {
		return monthTitleLabel;
	}
	
	public String getTitle(int year, int month) {
		return "" + monthName.getMonthName(month) + " " + year;
	}
	
	public void repaint() {
		super.repaint();
		double percentage = 0.9;
		if (superComponent != null && new Dimension((int)(superComponent.getSize().getWidth() * percentage), (int)(superComponent.getSize().getHeight() * percentage)) != this.getPreferredSize()) {
			setPreferredSize(new Dimension((int)(superComponent.getSize().getWidth() * percentage), (int)(superComponent.getSize().getHeight() * percentage)));
			//System.out.println(superComponent.getSize());
		}
		
	}
}
