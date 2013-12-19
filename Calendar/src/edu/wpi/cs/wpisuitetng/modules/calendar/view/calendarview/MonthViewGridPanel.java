/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team3
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.view.calendarview;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addcontroller.AddCommitmentPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addcontroller.AddEventPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Event;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.editpanel.AddCommitmentPanel;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.editpanel.AddEventPanel;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")

/*Note: Anywhere comments read "-Gravy" means that Andrew Aveyard edited there*/

public class MonthViewGridPanel extends JPanel {
	private JLabel headerLabel = new JLabel(); // Date for day

	private DateController date;
	
	JList list = new JList() {
		@Override
        public int locationToIndex(Point location) {
            int index = super.locationToIndex(location);
            if (index != -1 && !getCellBounds(index, index).contains(location)) {
                return -1;
            }
            else {
                return index;
            }
        }
	};
	List calendarItemList = new ArrayList();
	
	DefaultListModel model = new DefaultListModel();
	
	public MonthViewGridPanel(DateController date) {
		this.date = date.clone();
		
		setBackground(Color.white);
	
		setLayout(new MigLayout("insets 0 0 0 0, height :100:"));
		
		headerLabel.setOpaque(true);
		headerLabel.setBackground(new Color(138, 173, 209));
		add(headerLabel, "width :100%:, wrap, span");
		headerLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					switchView();
				}
			}
		});
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					switchView();
				}
			}
		});
		
		list.setModel(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(2);
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setToThisDate();
				JList list = (JList)e.getSource();
				if (e.getClickCount() == 1) {
					int index = list.locationToIndex(e.getPoint());
					System.out.println(index);
					list.clearSelection();
				} else if (e.getClickCount() == 2) {
		            int index = list.locationToIndex(e.getPoint());
		            Object item;
		            try {
		            	item = calendarItemList.get(index);
		            } catch (Exception ex) {
		            	switchView();
		            	return;
		            }
		            if (item instanceof Commitment) {
		    			AddCommitmentPanel newCommitmentPanel = new AddCommitmentPanel(new MigLayout());
		    			newCommitmentPanel.populateCommitment((Commitment) item);
		    			AddEventPanelController.getInstance().getTabbedPane().add(newCommitmentPanel);
		    			AddEventPanelController.getInstance().getTabbedPane().setTitleAt(AddEventPanelController.getInstance().getTabbedPane().getTabCount() - 1, "Edit Commitment");
		    			AddCommitmentPanelController.getInstance().getTabbedPane().setSelectedIndex(AddEventPanelController.getInstance().getTabbedPane().getTabCount() - 1);
		    	        newCommitmentPanel.initiateFocus();
		            } else if (item instanceof Event) {
		            	AddEventPanel newEventPanel = new AddEventPanel(new MigLayout());
		    			newEventPanel.populateEvent((Event) item);
		    			AddEventPanelController.getInstance().getTabbedPane().add(newEventPanel);
		    			AddEventPanelController.getInstance().getTabbedPane().setTitleAt(AddEventPanelController.getInstance().getTabbedPane().getTabCount() - 1, "Edit Event");
		    			AddCommitmentPanelController.getInstance().getTabbedPane().setSelectedIndex(AddEventPanelController.getInstance().getTabbedPane().getTabCount() - 1);
		    			newEventPanel.initiateFocus();
		            }
		    	
		        }
			}
		});
		list.addMouseMotionListener(new MouseMotionAdapter() {
	        @Override
	        public void mouseMoved(MouseEvent e) {
	            JList l = (JList)e.getSource();
	            ListModel m = l.getModel();
	            int index = l.locationToIndex(e.getPoint());
	            if(index > -1) {
	            	String displayString = "<html>";
	            	Object item = calendarItemList.get(index);
	            	if (item instanceof Commitment) {
	            		Commitment cmtItem = (Commitment) item;
	            		displayString += cmtItem.getName() + "<br>";
	            		if (cmtItem.getDescription().length() != 0) {
	            			displayString += cmtItem.getDescription() + "<br>";
	            		}
	            		displayString += cmtItem.getStartTime().get(GregorianCalendar.HOUR_OF_DAY) + ":" + 
	            		cmtItem.getStartTime().get(GregorianCalendar.MINUTE) + "</html>";
	            	}
	            	
	            	if (item instanceof Event) {
	            		Event eventItem = (Event) item;
	            		displayString += eventItem.getName() + " " + eventItem.getDescription() + " "
	            		+ eventItem.getStartTime().get(GregorianCalendar.HOUR_OF_DAY) + ":" + 
	            		eventItem.getStartTime().get(GregorianCalendar.MINUTE);
	            	}
	                l.setToolTipText(displayString);
	            }
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
		new MonthViewGridPanel(date);
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
			System.out.println(e);
		}
	
	}
	
	public void setToThisDate() {
		DateController date = MainCalendarController.getInstance().getDateController();
		
		date.set(Calendar.YEAR, this.date.getYear());
		date.set(Calendar.MONTH, this.date.getMonth());
		date.set(Calendar.DATE, this.date.getDayOfMonth());
		
		MainCalendarController.getInstance().getMonthView().getMonthViewPanel().repaintAll();
	}

	private void switchView() {
		System.out.println("view switched");
		JToggleButton btn = new JToggleButton();
		btn.setText("Day");
		MainCalendarController.getInstance().timePeriodChanged(btn);
//		MainCalendarController.getInstance().timePeriodChanged(btn);
//		System.out.println("view switched");
//		if (MainCalendarController.getInstance().isSelectedDate(date.getDayOfMonth(), date.getMonth(), System.currentTimeMillis())) {
//			//TODO: switch to day view here	
//			JToggleButton btn = new JToggleButton();
//			btn.setText("Day");
//			MainCalendarController.getInstance().timePeriodChanged(btn);
//
//		}
//		else {
//			MainCalendarController.getInstance().setSelectedDate(date.getDayOfMonth(), date.getMonth());
//		}
//		
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
		calendarItemList.add(event);
		String element = event.getName();
		model.addElement(element);
	}
	public void addCommitmentToTable(Commitment cmt) {
		calendarItemList.add(cmt);
		String element = cmt.getName();
		model.addElement(element);
		
	}
}
