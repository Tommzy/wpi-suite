package edu.wpi.cs.wpisuitetng.modules.calendar.view.monthview;

import java.awt.Color;
import java.awt.Dimension;
import java.text.DateFormatSymbols;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MonthViewController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Event;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.Updatable;

/**
 * MonthView
 * @author Hongbo
 *
 */
@SuppressWarnings("serial")
public class MonthView extends JPanel implements Updatable{
	private MonthViewPanel monthViewPanel;
	private JLabel monthTitleLabel = new JLabel();
	private String[] monthNames = new DateFormatSymbols().getMonths();
	private JButton previousButton = new JButton("<"), 
			nextButton = new JButton(">"), todayButton = new JButton("Today");
	
	public MonthView() {
		// set the layout to be MigLayout
		// command insets 0 0 0 0 means the margin for 
		// up, down, left and right is 0.
		setLayout(new MigLayout("insets 0 0 0 0"));
		
		// update method constructs the GUI component of month view.
		update();
		
		// setup month view controller.
		// the month view controller should know about
		// the month view in order to execute update method;
		// know about all the buttons to tell which is 
		// the button that the user actually pressed on the GUI panel
		MonthViewController controller = MonthViewController.getInstance();
		controller.setMonthView(this);
		controller.setBtnBefore(previousButton);
		controller.setBtnNext(nextButton);
		controller.setBtnToday(todayButton);
		
		// setup action listener to buttons
		nextButton.addActionListener(controller);
		todayButton.addActionListener(controller);
		previousButton.addActionListener(controller);
	}
	
	/**
	 * used by the update() method of month view itself.
	 * @return the first date that will show up on the upper left corner of MonthView 
	 */
	public DateController getFirstDayOfMonthView(DateController date) {
		DateController monthStartDate = new DateController(date.getYear(), date.getMonth(), 1);
		int w = monthStartDate.getDayOfWeek();
		for (int i = 0; i < w - 1; i ++) {
			monthStartDate = monthStartDate.getPrecursorDate();
		}
		return monthStartDate;
	}
	
	/**
	 * construct a monthViewPanel according to the date of DateController
	 * @return
	 */
	public void update() {
		this.removeAll();
		DateController date = MainCalendarController.getInstance().getDateController();
		/*
		 * first get the date controller from main calendar controller
		 * this date controller is the current user selected date
		 * 
		 * after that use getFirstDayOfMonthView() to calculate the first day
		 * that will be displayed on a 5 by 7 days month view
		 */
		DateController monthStartDate = getFirstDayOfMonthView(date);
		
		/*
		 * month view panel is constructed with a parameter of the first day of that month view
		 */
		monthViewPanel = new MonthViewPanel(monthStartDate.getYear(), monthStartDate.getMonth(), monthStartDate.getDayOfMonth());
		/* the label displaying something like "2013 Nov"
		 getTitle method is inside this MonthView class.
		 it looks up for the displaying name for a given month in a array list
		 but another way to do this is
		 GregorianCalendar.getInstance().getDisplayName(GregorianCalendar.FEBRUARY, GregorianCalendar.SHORT, getDefaultLocale());
		 this avoids the presenting of DatesFormatSymbol class
		 
		 GregorianCalendar.SHORT is for month name's abbreviation
		 for the complete spelling, use .LONG
		*/ 
		monthTitleLabel.setText(getTitle(date.getYear(), date.getMonth()));
		add(monthTitleLabel, "wrap");
		
		/*
		 * group the the buttons for switching among month into a panel
		 * since here we're only using the reference of these three buttons
		 * the buttons are actually already initialized in the constructor, 
		 * with the action listener added
		 */
		JPanel panel = new JPanel();
		panel.add(previousButton);
		panel.add(todayButton, "gapleft 10");
		panel.add(nextButton, "gapleft 10");
		
		add(panel, "wrap");
		add(monthViewPanel, "width :100%:");
		repaint();
	}
	// returns previousButton
	public JButton getPreviousButton() {
		return previousButton;
	}
	// returns nextButton
	public JButton getNextButton() {
		return nextButton;
	}
	// returns todayButton
	public JButton getTodayButton() {
		return todayButton;
	}
	// returns monthViewPanel
	public MonthViewPanel getMonthViewPanel() {
		return monthViewPanel;
	}
	// returns monthTitleLabel
	public JLabel getMonthTitleLabel() {
		return monthTitleLabel;
	}
	
	// This returns the display name of a given year and given month
	public String getTitle(int year, int month) {
		return "" + monthNames[month] + " " + year;
	}
	
	public void repaint() {
		super.repaint();

		double percentage = 0.9;
		
		/* 
		 * you will see this method in almost all the calendar views.
		 * 
		 * this calculates the size of month view's parent component, and set the size of month view to be 90% of 
		 * its parent's size
		 * 
		 * This is intend to get rid of the scroll bar in the calendar views.
		 * 
		 * if the size of calendar view inside the scroll pane is always small enough, the scroll bar will not show up
		 * but a better way to achieve this is actually get rid of the scroll pane and use the default resize function
		 * of MigLayout 
		 */
		if (getParent() != null && new Dimension((int)(getParent().getSize().getWidth() * percentage), (int)(getParent().getSize().getHeight() * percentage)) != this.getPreferredSize()) {
			setPreferredSize(new Dimension((int)(getParent().getSize().getWidth() * percentage), (int)(getParent().getSize().getHeight() * percentage)));
		}
	}
	
	public List<Event> getMonthViewEventList() {
		return monthViewPanel.getEventList();
	}
	
}
