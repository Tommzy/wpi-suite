package edu.wpi.cs.wpisuitetng.modules.calendar.controller.monthview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.JButton;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.monthview.MonthView;

public class MonthViewController implements ActionListener {
	private static MonthViewController instance;
	private MonthView monthView;
	private JButton btnNext, btnToday, btnBefore;
	
	public JButton getBtnNext() {
		return btnNext;
	}

	public void setBtnNext(JButton btnNext) {
		this.btnNext = btnNext;
	}

	public JButton getBtnToday() {
		return btnToday;
	}

	public void setBtnToday(JButton btnToday) {
		this.btnToday = btnToday;
	}

	public JButton getBtnBefore() {
		return btnBefore;
	}

	public void setBtnBefore(JButton btnBefore) {
		this.btnBefore = btnBefore;
	}

	public MonthView getMonthView() {
		return monthView;
	}

	public void setMonthView(MonthView monthView) {
		this.monthView = monthView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DateController date = MainCalendarController.getInstance().getDateController();
		if (e.getSource() == btnNext) {
			date.setToNextMonth();
		} else if (e.getSource() == btnBefore) {
			date.setToPreviousMonth();
		} else if (e.getSource() == btnToday) {
			date.setToToday();
		}
		monthView.updateMonthView();
		MainCalendarController.getInstance().getMainView().getMainTabPane().getCommitmentTable().update();
	}
	
	public static MonthViewController getInstance() {
		if (instance == null) {
			instance = new MonthViewController();
		}

		return instance;
	}

}
