package edu.wpi.cs.wpisuitetng.modules.calendar.view.monthview;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

/**
 * MontViewPanel is a sub-component of MonthView
 * It displays MonthViewGridPanel
 * @author Hongbo
 *
 */
@SuppressWarnings("serial")
public class MonthViewPanel extends JPanel {
	
	private List<MonthViewGridPanel> monthViewList = new ArrayList<MonthViewGridPanel>();
	private int row = 3, column = 4;
	private Calendar cal = GregorianCalendar.getInstance();
	private MonthName monthName = new MonthName();
	
	/**
	 * given the beginning day of the month view, the day that will appear on the upper-left corner
	 * @param year
	 * @param month
	 * @param dayOfMonth
	 */
	public MonthViewPanel(int year, int month, int dayOfMonth) {
		setMonth(year, month, dayOfMonth);
		this.setBackground(Color.white);
		this.setLayout(new MigLayout("insets 0 0 0 0"));
		add(new JLabel("SUN"));
		add(new JLabel("MON"));
		add(new JLabel("TUE"));
		add(new JLabel("WED"));
		add(new JLabel("THU"));
		add(new JLabel("FRI"));
		add(new JLabel("SAT"), "wrap");
		
		for (int i = 0; i < 5; i ++) {
			for (int j = 0; j < 7; j ++) {
				MonthViewGridPanel panel = new MonthViewGridPanel();
				monthViewList.add(panel);
				
				String s;
				if (j == 6) {
					s = ", wrap";
				} else {
					s = "";
				}
				add(panel, "gapleft 0, gaptop 0" + s);

				panel.setHeader(getHeaderLabelText(cal.get(GregorianCalendar.YEAR),
						cal.get(GregorianCalendar.MONTH), cal.get(GregorianCalendar.DATE)));
				cal.add(GregorianCalendar.DATE, 1);
			}
		}
	}
	
	public MonthViewGridPanel getPanel(int row, int column) {
		return monthViewList.get(row * this.column + column);
	}
	
	/**
	 * Month view panel will switch to the given month
	 * this method will not check the correctness of parameter
	 * @param year a four digit integer.
	 * @param month an integer between 0 and 11
	 * @param date an integer between 1 and 31
	 */
	public void setMonth(int year, int month, int date) { 
		cal.set(year, month, date);
	}
	
	public String getHeaderLabelText(int year, int month, int date) {
		if (date == 1) {
			return "" + monthName.getMonthNameAbbr(month) + " " + date; 
		} else {
			return "" + date;
		}
	}
	
	
	
}
