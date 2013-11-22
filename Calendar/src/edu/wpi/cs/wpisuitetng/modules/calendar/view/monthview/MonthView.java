package edu.wpi.cs.wpisuitetng.modules.calendar.view.monthview;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.text.DateFormatSymbols;

import javax.swing.*;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.fakeModel.FakeDate;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.fakeModel.FakeModel;
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
	private String[] monthNames = new DateFormatSymbols().getMonths();
	private FakeDate date;
	private JButton previousButton = new JButton("<"), 
			nextButton = new JButton(">"), todayButton = new JButton("Today");
	
	public MonthView() {
		date = FakeModel.getInstance().getCurrentDate();

		setLayout(new MigLayout("insets 0 0 0 0"));
		
		FakeDate monthStartDate = getFirstDayOfMonthView(date);
		
		monthViewPanel = new MonthViewPanel(monthStartDate.getYear(), monthStartDate.getMonth(), monthStartDate.getDayOfMonth());
		monthTitleLabel.setText(getTitle(date.getYear(), date.getMonth()));
		add(monthTitleLabel, "wrap");
		JPanel panel = new JPanel();
		panel.add(previousButton);
		panel.add(todayButton, "gapleft 10");
		panel.add(nextButton, "gapleft 10");
		add(panel, "wrap");
		add(monthViewPanel);
	}
	
	/**
	 * 
	 * @return the first date that will show up on the upper left corner of MonthView 
	 */
	public FakeDate getFirstDayOfMonthView(FakeDate date) {
		FakeDate monthStartDate = new FakeDate(date.getYear(), date.getMonth(), 1);
		int w = monthStartDate.getDayOfWeek();
		for (int i = 0; i < w - 1; i ++) {
			monthStartDate = monthStartDate.getPrecursorDate();
		}
		
		return monthStartDate;
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
			//System.out.println(superComponent.getSize());
		}
		
	}
}
