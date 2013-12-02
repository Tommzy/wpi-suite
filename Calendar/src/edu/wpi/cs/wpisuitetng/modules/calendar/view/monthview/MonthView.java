package edu.wpi.cs.wpisuitetng.modules.calendar.view.monthview;

import java.awt.Dimension;
import java.text.DateFormatSymbols;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.monthview.MonthViewController;
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

		setLayout(new MigLayout("insets 0 0 0 0"));
		
		update();
		
		MonthViewController controller = MonthViewController.getInstance();
		controller.setMonthView(this);
		controller.setBtnBefore(previousButton);
		controller.setBtnNext(nextButton);
		controller.setBtnToday(todayButton);
		
		nextButton.addActionListener(controller);
		todayButton.addActionListener(controller);
		previousButton.addActionListener(controller);
	}
	
	/**
	 * 
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
		
		DateController monthStartDate = getFirstDayOfMonthView(date);
		
		monthViewPanel = new MonthViewPanel(monthStartDate.getYear(), monthStartDate.getMonth(), monthStartDate.getDayOfMonth());
		monthTitleLabel.setText(getTitle(date.getYear(), date.getMonth()));
		add(monthTitleLabel, "wrap");
		JPanel panel = new JPanel();
		panel.add(previousButton);
		panel.add(todayButton, "gapleft 10");
		panel.add(nextButton, "gapleft 10");
		
		add(panel, "wrap");
		add(monthViewPanel);
		repaint();
	}
	
	public JButton getPreviousButton() {
		return previousButton;
	}

	public JButton getNextButton() {
		return nextButton;
	}

	public JButton getTodayButton() {
		return todayButton;
	}

	public MonthViewPanel getMonthViewPanel() {
		return monthViewPanel;
	}
	
	public JLabel getMonthTitleLabel() {
		return monthTitleLabel;
	}
	
	public String getTitle(int year, int month) {
		return "" + monthNames[month] + " " + year;
	}
	
	public void repaint() {
		super.repaint();
		if (MonthViewGridPanel.mod != 1) {
			return;
		}
		double percentage = 0.9;
		
		if (getParent() != null && new Dimension((int)(getParent().getSize().getWidth() * percentage), (int)(getParent().getSize().getHeight() * percentage)) != this.getPreferredSize()) {
			setPreferredSize(new Dimension((int)(getParent().getSize().getWidth() * percentage), (int)(getParent().getSize().getHeight() * percentage)));
		}
		
		 
		
	}
}
