package edu.wpi.cs.wpisuitetng.modules.calendar.view.monthview;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.CommitmentFilter;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;
import net.miginfocom.swing.MigLayout;

/**
 * MontViewPanel is a sub-component of MonthView
 * It displays MonthViewGridPanel
 * @author Hongbo
 *
 */
@SuppressWarnings("serial")
public class MonthViewPanel extends JPanel {
	// a list of day grid
	private List<MonthViewGridPanel> monthViewList = new ArrayList<MonthViewGridPanel>();
	private int column = 4;
	private Calendar cal = GregorianCalendar.getInstance();
	private String[] monthNamesAbbr = new DateFormatSymbols().getShortMonths();
	private String[] weekdayNamesAbbr = new DateFormatSymbols().getShortWeekdays();
	
	/*
	 * month view maintains a list of commitment
	 * this list is for the commitment panel on the right of our application layout
	 * 
	 * when a commitment execute the update() method, it will ask the MainCalendarController
	 * for the currently displaying calendarView.
	 * If the currently displaying calendar view is MonthView, the month view panel will pass
	 * all the commitment to the commitment panel. Those commitment will be displayed on the 
	 * commitment panel until the next time user switch to another month or another calendar view.
	 * 
	 * this commitment list only store the commitment for current month
	 */
	private Collection<Commitment> cmtList;
	/**
	 * given the beginning day of the month view, the day that will appear on the upper-left corner
	 * @param year
	 * @param month
	 * @param dayOfMonth
	 */
	public MonthViewPanel(int year, int month, int dayOfMonth) {
		// set the panel with the first day of that calendar page.
		// this day is not necessary on the same month of the 
		// user selected date. If the user selected day is not 
		// Sunday, then the day of the constructor's three parameter 
		// will be the last Sunday before the user selected date.
		setMonth(year, month, dayOfMonth);
		this.setBackground(Color.white);
		
		// set miglayout and remove the panel margin
		this.setLayout(new MigLayout("insets 0 0 0 0"));
		
		// add the label of "Sunday, Monday, etc" to the first row of month view panel
		for (int i = 1; i < weekdayNamesAbbr.length; i++) {
			JLabel weekday = new JLabel(weekdayNamesAbbr[i]);
			weekday.setOpaque(true);
			weekday.setBackground(new Color(138, 173, 209));
			if (i != weekdayNamesAbbr.length - 1) 
				add(weekday, "width :14%:");
			else
				// when finished adding the first row of components, 
				// use "wrap" mig command to tell the layout controller
				// you want to put the next component to the next row
				add(weekday, "width :14%:, wrap");
			
			/*
			 * also, width 14% means the weekday label will will be 14% of the width of the container
			 */
		}
		
		/*
		 * retrieve the commitments from commitmentFilter with a start time and an end time
		 */
		GregorianCalendar calendarStart = new GregorianCalendar(year, month, dayOfMonth, 0, 0);
		GregorianCalendar calendarEnd = new GregorianCalendar(year, month, dayOfMonth, 0, 0);
		// a calendar month page have 35 days. retrieve the commitment for those 35 days
		calendarEnd.add(Calendar.DATE, 35);
		CommitmentFilter cmtFilter = new CommitmentFilter(calendarStart, calendarEnd);
		Collection<Commitment> cmtList = cmtFilter.getCommitmentList();
		this.cmtList = cmtList;
		
		for (int i = 0; i < 5; i ++) {
			for (int j = 0; j < 7; j ++) {
				final MonthViewGridPanel panel = new MonthViewGridPanel(new DateController(cal));
				monthViewList.add(panel);
				panel.filtCommitment(cmtList);
				/*
				 * panel means a panel for a day grid
				 * a MonthViewGridPanel has the ability to filter out the commitment of that day
				 * using filtCommitment() method
				 */
				
				String s;
				
				// setup a miglayout command for the day grids to change to next row
				if (j == 6) {
					s = ", wrap";
				} else {
					s = "";
				}
				add(panel, "gapleft 0, gaptop 0" + s);

				panel.setHeader(getHeaderLabelText(cal.get(GregorianCalendar.YEAR),
						cal.get(GregorianCalendar.MONTH), cal.get(GregorianCalendar.DATE)));
				cal.add(GregorianCalendar.DATE, 1);
				panel.addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
						/* 
						 * when user click on this day grid panel
						 * change the current date controller (in the main calendar controller) to the day controller
						 * that stored inside that day grid panel
						 * 
						 * after changing the date controller, at the next repaint,
						 * if the date controller from main calendar controller matches
						 * one of the day grid panel, the day grid panel will be labeled
						 * as orange
						 */
						panel.setToThisDate();
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
				});
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
			return "" + monthNamesAbbr[month] + " " + date; 
		} else {
			return "" + date;
		}
	}
	
	public void repaintAll() {
		Iterator<MonthViewGridPanel> itr = monthViewList.iterator();
		while (itr.hasNext()) {
			itr.next().repaint();
		}
	}
	
	public Collection<Commitment> getMonthCommitmentList() {
		return cmtList;
	}
	
}
