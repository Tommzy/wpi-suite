package edu.wpi.cs.wpisuitetng.modules.calendar.view.monthview;

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
	
	public MonthView() {
		
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
}
