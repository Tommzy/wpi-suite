package edu.wpi.cs.wpisuitetng.modules.calendar.view.monthview;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.*;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddEventTabPanel;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class MonthViewGridPanel extends JPanel {
	private JLabel headerLabel = new JLabel();
	private JTextArea textArea = new JTextArea();
	public static int mod = 1;
	public DateController date;
	
	public MonthViewGridPanel(DateController date) {
		this.date = date.clone();
		setBackground(Color.white);
		textArea.setEditable(false);
		textArea.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				setToThisDate();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		setLayout(new MigLayout("insets 0 0 0 0, height :100:"));
		headerLabel.setOpaque(true);
		headerLabel.setBackground(new Color(138, 173, 209));
		add(headerLabel, "width :100%:, wrap, span");
		if (mod == 1) {
			add(textArea, "span, width :200:, height :100:");
		} else {
			add(textArea, "span, width 100%:100%:100%, height 80%:80%:80%");
		}

	}
	
	public JLabel getHeaderLabel() {
		return headerLabel;
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}
	
	public void setHeader(String s) {
		headerLabel.setText(s);
	}
	
	public void setTextArea(String s) {
		textArea.setText(s);
	}
	
	public DateController getDateContrller() {
		return date;
	}
	
	public void repaint() {
		super.repaint();
		if (MainCalendarController.getInstance().getDateController().equals(date)) {
			this.setBackground(Color.orange);
		} else {
			this.setBackground(new Color(138, 173, 209));
		}
	}
	
	public void setToThisDate() {
		DateController date = MainCalendarController.getInstance().getDateController();
		
		date.set(Calendar.YEAR, this.date.getYear());
		date.set(Calendar.MONTH, this.date.getMonth());
		date.set(Calendar.DATE, this.date.getDayOfMonth());
		
		MainCalendarController.getInstance().getMonthView().getMonthViewPanel().repaintAll();
	}

	public void filtCommitment(Collection<Commitment> commitment) {
		Iterator<Commitment> itr = commitment.iterator();
		while (itr.hasNext()) {
			Commitment cmt = itr.next();
			Calendar calStartTime = cmt.getStartTime();
			if (calStartTime.get(GregorianCalendar.YEAR) == date.getYear()
				&& calStartTime.get(GregorianCalendar.MONTH) == date.getMonth()
				&& calStartTime.get(GregorianCalendar.DATE) == date.getDayOfMonth()) {
				addCommitmentToTable(cmt);
			}
		}
		
	}
	
	public void addCommitmentToTable(Commitment cmt) {
		textArea.append(cmt.getName() + "\n");
	}
	
	
}
