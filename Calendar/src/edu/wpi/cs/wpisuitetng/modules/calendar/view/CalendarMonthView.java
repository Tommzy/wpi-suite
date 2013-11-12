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

public class CalendarMonthView extends JScrollPane {

	private JButton nextMonth;
	private JButton prevMonth;
	private JButton today;
	CalendarMonth calendarMonth;
	
	/**
	 * Constructor for IterationCalendarPanel.
	 * @param parent IterationPanel`
	 * @param vm ViewMode
	 * @param displayIteration Iteration
	 */
	public CalendarMonthView()
	{
		

		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new MigLayout());
		JPanel buttonPanel = new JPanel();
		
		nextMonth = new JButton(">>");
		nextMonth.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		today = new JButton ("Today");
		today.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		prevMonth = new JButton("<<");
		prevMonth.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		setupButtonListeners();
		
		buttonPanel.add(prevMonth);
		buttonPanel.add(today);
		buttonPanel.add(nextMonth);
		
		contentPanel.add(buttonPanel, "alignx center, dock north");
		 		
		JPanel calendarPanel = new JPanel(new BorderLayout());
		calendarMonth = new CalendarMonth(true);
		calendarPanel.add(calendarMonth, BorderLayout.CENTER);
				
		calendarPanel.add(calendarMonth, BorderLayout.CENTER);		
		contentPanel.add(calendarPanel, "alignx center, push, span");
					
		this.setViewportView(contentPanel);	
	}
	
	/**
	 * Adds action listeners to the year control buttons for the calendar view.
	 */
	private void setupButtonListeners()
	{
		nextMonth.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				nextMonth();
			}	
		});
		
		prevMonth.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				previousMonth();
			}
		});
		
		today.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				today();
			}
		});		
	}
	
	/**
	 * Switches the calendar to the previous month.
	 */
	private void previousMonth()
	{
		Calendar cal = calendarMonth.getCalendar();
		cal.add(Calendar.MONTH, -1);
		calendarMonth.setFirstDisplayedDay(cal.getTime());
	}
	
	/**
	 * Switches the calendar to the current date
	 */
	private void today()
	{
		Calendar cal = Calendar.getInstance();
		calendarMonth.setFirstDisplayedDay(cal.getTime());
	}
	
	/**
	 * Switches the calendar to the next month.
	 */
	private void nextMonth()
	{
		Calendar cal = calendarMonth.getCalendar();
		cal.add(Calendar.MONTH, +1);
		calendarMonth.setFirstDisplayedDay(cal.getTime());
	}
}
