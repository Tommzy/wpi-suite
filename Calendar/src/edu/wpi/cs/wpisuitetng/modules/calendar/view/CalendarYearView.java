package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

/**
 * The Class CalendarYearView for displaying the year.
 */

public class CalendarYearView extends JPanel{

	private JButton nextYear;
	private JButton prevYear;
	private JButton today;
	CalendarMonth calendarMonth;
	
	/**
	 * Constructor for IterationCalendarPanel.
	 * @param parent IterationPanel
	 * @param vm ViewMode
	 * @param displayIteration Iteration
	 */
	public CalendarYearView()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel buttonPanel = new JPanel();
		
		nextYear = new JButton(">");
		nextYear.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		today = new JButton ("Today");
		today.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		prevYear = new JButton("<");
		prevYear.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		setupButtonListeners();
		
		buttonPanel.add(prevYear);
		buttonPanel.add(today);
		buttonPanel.add(nextYear);
		
		add(buttonPanel);
		 		
		calendarMonth = new CalendarMonth();
		calendarMonth.setFont(calendarMonth.getFont().deriveFont(6f));
		add(calendarMonth);
		
					
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
				next();
			}	
		});
		
		prevYear.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				previous();
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
	 * Switches the calendar to the previous year.
	 */
	public void previous()
	{
		Calendar cal = calendarMonth.getCalendar();
		cal.add(Calendar.YEAR, -1);
		calendarMonth.setFirstDisplayedDay(cal.getTime());
	}
	
	/**
	 * Switches the calendar to the current date
	 */
	public void today()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DATE, 1);
		calendarMonth.setFirstDisplayedDay(cal.getTime());
	}
	
	/**
	 * Switches the calendar to the next year.
	 */
	public void next()
	{
		Calendar cal = calendarMonth.getCalendar();
		cal.add(Calendar.YEAR, +1);
		calendarMonth.setFirstDisplayedDay(cal.getTime());
	}
	
	public void paint(Graphics g) {
		if (this.getParent() == null) {
			return;
		}
		
		calendarMonth.setFont(calendarMonth.getFont().deriveFont(
				Math.max(8f,(float) this.getParent().getSize().width / 85)));
		
		super.paint(g);
	}
	
	public void repaint() {
		if (this.getParent() == null) {
			return;
		}
		this.setPreferredSize(new Dimension
				((int)(this.getParent().getSize().getWidth() * 0.9),
				Math.max((int)(this.getParent().getSize().getWidth() * 0.9 * 0.75) ,
				(int)(this.getParent().getSize().getHeight() * 0.95)))
		);
	}
	
}
