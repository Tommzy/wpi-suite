package edu.wpi.cs.wpisuitetng.modules.calendar.view.monthview;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Event;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.CalendarTimePeriod;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddEventTabPanel;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")

/*Note: Anywhere comments read "-Gravy" means that Andrew Aveyard edited there*/

public class MonthViewGridPanel extends JPanel {
	private JLabel headerLabel = new JLabel(); // Date for day

	private DateController date;
	
	JList list = new JList();
	
	DefaultListModel model = new DefaultListModel();
	
	public MonthViewGridPanel(DateController date) {
		this.date = date.clone();
		
		setBackground(Color.white);
	
		setLayout(new MigLayout("insets 0 0 0 0, height :100:"));
		
		headerLabel.setOpaque(true);
		headerLabel.setBackground(new Color(138, 173, 209));
		add(headerLabel, "width :100%:, wrap, span");
		
		list.setModel(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(2);
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setToThisDate();
			}
		});
		JScrollPane listScroller = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		listScroller.setHorizontalScrollBar(null);
		add(listScroller, "span, width :100%:, height :80%:");
		
	}
	
	public JLabel getHeaderLabel() {
		return headerLabel;
	}
	
	public void setHeader(String s) {
		headerLabel.setText(s);
	}

	public DateController getDateContrller() {
		return date;
	}
	
	public void update() {
		new MonthViewGridPanel(this.date);
	}
	
	public void repaint() {
		super.repaint();
		try {
			/*
			 * if the current selected date from main calendar controller 
			 * matches the date controller of this grid panel,
			 * label this panel with cyan
			 * 
			 * other wise use the default color blue to blend with the upper label of header label
			 */
			if (MainCalendarController.getInstance().getDateController().equals(date)) {
				this.setBackground(Color.cyan); //Changed to cyan to keep consistent with day and week view
												//-Gravy
	
			} else {
				this.setBackground(new Color(138, 173, 209));
				list.clearSelection();
			}
		} catch (NullPointerException e) {
			
		}
	
	}
	
	public void setToThisDate() {
		DateController date = MainCalendarController.getInstance().getDateController();
		
		date.set(Calendar.YEAR, this.date.getYear());
		date.set(Calendar.MONTH, this.date.getMonth());
		date.set(Calendar.DATE, this.date.getDayOfMonth());
		
		if (MainCalendarController.getInstance().isSelectedDate(date.getDayOfMonth(), date.getMonth(), System.currentTimeMillis())) {
			//TODO: switch to day view here	
			JToggleButton btn = new JToggleButton();
			btn.setText("Day");
			MainCalendarController.getInstance().timePeriodChanged(btn);

		}
		else {
			MainCalendarController.getInstance().setSelectedDate(date.getDayOfMonth(), date.getMonth());
		}
		
		MainCalendarController.getInstance().getMonthView().getMonthViewPanel().repaintAll();
	}

	public void filtCommitment(Collection<Commitment> commitment) {
		if (date == null) {
			return;
		}
		
		// filter out the commitment for this date
		Iterator<Commitment> itr = commitment.iterator();
		while (itr.hasNext()) {
			Commitment cmt = itr.next();
			GregorianCalendar calStartTime = cmt.getStartTime();
			if (calStartTime.get(GregorianCalendar.YEAR) == date.getYear()
				&& calStartTime.get(GregorianCalendar.MONTH) == date.getMonth()
				&& calStartTime.get(GregorianCalendar.DATE) == date.getDayOfMonth()) {
				addCommitmentToTable(cmt);
			}
		}
		
	}
	
	public void filtEvent(List<Event> event) {
		if (date == null) {
			return;
		}
		
		// filter out the commitment for this date
		Iterator<Event> itr = event.iterator();
		while (itr.hasNext()) {
			Event eve = itr.next();
			GregorianCalendar calStartTime = eve.getStartTime();
			if (calStartTime.get(GregorianCalendar.YEAR) == date.getYear()
				&& calStartTime.get(GregorianCalendar.MONTH) == date.getMonth()
				&& calStartTime.get(GregorianCalendar.DATE) == date.getDayOfMonth()) {
				addEventToTalble(eve);
			}
		}
		
	}
	public void addEventToTalble(Event event) {
		String element = event.getName();
		model.addElement(element);
	}
	public void addCommitmentToTable(Commitment cmt) {
		String element = cmt.getName();
		model.addElement(element);
		
	}
}
