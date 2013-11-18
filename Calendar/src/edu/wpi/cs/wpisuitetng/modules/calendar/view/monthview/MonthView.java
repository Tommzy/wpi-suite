package edu.wpi.cs.wpisuitetng.modules.calendar.view.monthview;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.*;

import edu.wpi.cs.wpisuitetng.modules.calendar.model.fakeModel.FakeDate;
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
	private FakeDate date;
	
	public MonthView(Component comp, FakeDate fakeDate) {
		date = fakeDate;
		superComponent = comp;
		System.out.println(comp);
		setLayout(new MigLayout("insets 0 0 0 0"));
		FakeDate monthStartDate = new FakeDate(date.getYear(), date.getMonth(), 1);
		System.out.println(monthStartDate);
		int w = monthStartDate.getDayOfWeek();
		for (int i = 0; i < w - 1; i ++) {
			monthStartDate = monthStartDate.getPrecursorDate();
		}
		System.out.println(monthStartDate);
		monthViewPanel = new MonthViewPanel(monthStartDate.getYear(), monthStartDate.getMonth(), monthStartDate.getDayOfMonth());
		monthTitleLabel.setText(getTitle(date.getYear(), date.getMonth()));
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
