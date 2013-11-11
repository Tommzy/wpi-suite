package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

public class CalendarYearView extends JScrollPane {

	private JButton nextYear;
	private JButton prevYear;
	private JButton nextMonth;
	private JButton prevMonth;
	private JButton today;
	CalendarYearMonthView calendarYearMonthView;
	
	/**
	 * Constructor for IterationCalendarPanel.
	 * @param parent IterationPanel
	 * @param vm ViewMode
	 * @param displayIteration Iteration
	 */
	public CalendarYearView()
	{
		

		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new MigLayout());
		
		JPanel buttonPanel = new JPanel();
		
		nextYear = new JButton(">>");
		nextYear.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		nextMonth = new JButton(">");
		nextMonth.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		today = new JButton ("Today");
		today.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		prevMonth = new JButton("<");
		prevMonth.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		prevYear = new JButton("<<");
		prevYear.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		setupButtonListeners();
		
		buttonPanel.add(prevYear);
		buttonPanel.add(prevMonth);
		buttonPanel.add(today);
		buttonPanel.add(nextMonth);
		buttonPanel.add(nextYear);
		
		contentPanel.add(buttonPanel, "alignx center, dock north");
		 		
		JPanel calendarPanel = new JPanel(new BorderLayout());
		calendarYearMonthView = new CalendarYearMonthView();
		calendarPanel.add(calendarYearMonthView, BorderLayout.CENTER);
				
		calendarPanel.add(calendarYearMonthView, BorderLayout.CENTER);		
		contentPanel.add(calendarPanel, "alignx center, push, span");
					
		this.setViewportView(contentPanel);	
	}
	
	/**
	 * Adds action listeners to the year control buttons for the calendar view.
	 */
	private void setupButtonListeners()
	{
		nextYear.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				nextYear();
			}	
		});
		
		prevYear.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				previousYear();
			}
		});
		
		today.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				today();
			}
		});
		
		prevMonth.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				previousMonth();
			}
		});
		
		nextMonth.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				nextMonth();
			}
		});
		
	}
	
	/**
	 * Switches the calendar to the previous year.
	 */
	private void previousYear()
	{
		Calendar cal = calendarYearMonthView.getCalendar();
		cal.add(Calendar.YEAR, -1);
		calendarYearMonthView.setFirstDisplayedDay(cal.getTime());
	}
	
	/**
	 * Switches the calendar to the current date
	 */
	private void today()
	{
		Calendar cal = Calendar.getInstance();
		calendarYearMonthView.setFirstDisplayedDay(cal.getTime());
	}
	
	/**
	 * Switches the calendar to the next month.
	 */
	private void nextMonth()
	{
		Calendar cal = calendarYearMonthView.getCalendar();
		cal.add(Calendar.MONTH, 1);
		calendarYearMonthView.setFirstDisplayedDay(cal.getTime());
	}
	
	/**
	 * Switches the calendar to the previous month.
	 */
	private void previousMonth()
	{
		Calendar cal = calendarYearMonthView.getCalendar();
		cal.add(Calendar.MONTH, -1);
		calendarYearMonthView.setFirstDisplayedDay(cal.getTime());
	}
	
	/**
	 * Switches the calendar to the next year.
	 */
	private void nextYear()
	{
		Calendar cal = calendarYearMonthView.getCalendar();
		cal.add(Calendar.YEAR, +1);
		calendarYearMonthView.setFirstDisplayedDay(cal.getTime());
	}
}