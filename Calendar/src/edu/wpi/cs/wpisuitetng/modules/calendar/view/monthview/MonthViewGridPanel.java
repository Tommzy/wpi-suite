package edu.wpi.cs.wpisuitetng.modules.calendar.view.monthview;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.*;

import edu.wpi.cs.wpisuitetng.modules.calendar.controller.MainCalendarController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.CalendarTimePeriod;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddEventTabPanel;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")

/*Note: Anywhere comments read "-Gravy" means that Andrew Aveyard edited there*/

public class MonthViewGridPanel extends JPanel {
	private JLabel headerLabel = new JLabel(); // Date for day
	private JLabel firstItemLabel = new JLabel(); //1st displayed item in day
	private JLabel secondItemLabel = new JLabel(); //2nd
	private JLabel thirdItemLabel = new JLabel(); //3rd
	private JLabel fourthItemLabel = new JLabel(); //4th
	private JLabel additionalItemsLabel = new JLabel(); //only display when more than 4 items for day
	private JTextArea textArea = new JTextArea(); //No longer stores information, only around to enforce
												  //month view size restrictions and provide easier date
												  //selection -Gravy
	public static int mod = 1;

	public DateController date;
	
	public MonthViewGridPanel(DateController date) {
		this.date = date.clone();
		
		
		setBackground(Color.white);
		textArea.setEditable(false); //Disallow manual editing
		textArea.setVisible(false); //Text area does not need to be seen, only be there to keep grid spaced
									//nicely
		
		/*
		 * I added the mouse listener to 
		 * both textArea and MonthViewGridPanel
		 * because it seems that if I only add
		 * listener to the panel, the activated area
		 * for clicking is the gap between the header label
		 * and the text area within the grid panel
		 */
		// Left textArea listener in to give a bigger selection area
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

		// Add mouse listeners for individual items (commitments or events)
		// Clicking sets date to current date -Gravy
		firstItemLabel.addMouseListener(new MouseListener(){

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
		secondItemLabel.addMouseListener(new MouseListener(){

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
		thirdItemLabel.addMouseListener(new MouseListener(){

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
		fourthItemLabel.addMouseListener(new MouseListener(){

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
		additionalItemsLabel.addMouseListener(new MouseListener(){

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
		
		
		// Add day labels (numbering the days of the month) to grid
		setLayout(new MigLayout("insets 0 0 0 0, height :100:"));
		headerLabel.setOpaque(true);
		headerLabel.setBackground(new Color(138, 173, 209));
		add(headerLabel, "width :100%:, wrap, span");
		
		//Adds the labels that will have item data to grid
		//Replaces the previous functionality of textArea of displaying item data -Gravy
		firstItemLabel.setOpaque(true);
		firstItemLabel.setBackground(new Color(138, 173, 209));
		add(firstItemLabel, "width :100%:, wrap, span");
		
		secondItemLabel.setOpaque(true);
		secondItemLabel.setBackground(new Color(138, 173, 209));
		add(secondItemLabel, "width :100%:, wrap, span");
		
		thirdItemLabel.setOpaque(true);
		thirdItemLabel.setBackground(new Color(138, 173, 209));
		add(thirdItemLabel, "width :100%:, wrap, span");
		
		fourthItemLabel.setOpaque(true);
		fourthItemLabel.setBackground(new Color(138, 173, 209));
		add(fourthItemLabel, "width :100%:, wrap, span");
		
		additionalItemsLabel.setOpaque(true);
		additionalItemsLabel.setBackground(new Color(138, 173, 209));
		add(additionalItemsLabel, "width :100%:, wrap, span");
		
		
		// previous setting. will be removed for upcoming refactoring.
		// mod defaultly set to 1, representing auto resize with the window
		// if set to any number other than 1, the month view will have the scroll bar
		if (mod == 1) {
			add(textArea, "span, width :200:, height :100:");
		} else {
			add(textArea, "span, width 100%:100%:100%, height 80%:80%:80%");
		}

	}
	
	public JLabel getHeaderLabel() {
		return headerLabel;
	}

	
//UNUSED
//	public JTextArea getTextArea() {
//		return textArea;
//	}
	
	public void setHeader(String s) {
		headerLabel.setText(s);
	}

//UNUSED
//	public void setTextArea(String s) {
//		textArea.setText(s);
//	}

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
				firstItemLabel.setBackground(Color.cyan);
				secondItemLabel.setBackground(Color.cyan);
				thirdItemLabel.setBackground(Color.cyan);
				fourthItemLabel.setBackground(Color.cyan);
				additionalItemsLabel.setBackground(Color.cyan);
	
	
	
	
			} else {
				this.setBackground(new Color(138, 173, 209));
				firstItemLabel.setBackground(new Color(138, 173, 209));
				secondItemLabel.setBackground(new Color(138, 173, 209));
				thirdItemLabel.setBackground(new Color(138, 173, 209));
				fourthItemLabel.setBackground(new Color(138, 173, 209));
				additionalItemsLabel.setBackground(new Color(138, 173, 209));
			}
		} catch (NullPointerException e) {
			
		}
	}
	
	public void setToThisDate() {
		DateController date = MainCalendarController.getInstance().getDateController();
		
		date.set(Calendar.YEAR, this.date.getYear());
		date.set(Calendar.MONTH, this.date.getMonth());
		date.set(Calendar.DATE, this.date.getDayOfMonth());
		
		//System.out.println(selectedDate);
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
	
	public void addCommitmentToTable(Commitment cmt) {
		
		GregorianCalendar commitmentStartTime = cmt.getStartTime();
		int hour = commitmentStartTime.get(Calendar.HOUR_OF_DAY);
		int minute = commitmentStartTime.get(Calendar.MINUTE);
		
		String AmOrPm = ""; //Set to either Am or Pm
		if(commitmentStartTime.getTime().getHours() < 12){
			AmOrPm = "Am";
		}
		
		else{
			AmOrPm = "Pm";
		}
		//Used for tooltip ONLY 
		String startToolTipClockTime = "" + hour + ":" + (minute < 10 ? "0" + minute : minute) + AmOrPm;
		
		
		//Used for label text ONLY
		String startLabelClockTime = "" + hour + AmOrPm;
		
		
		
		//Check to see what label is being added then add correct label.
		//If a day has more than 4 items then a label indicating as much will appear
		//NOTE: LABELS WILL HAVE TO BE CHANGED WHEN ITEMS CAN BE DELETED
		//CURRENTLY WILL NOT CHANGE THE LABEL TEXT-Gravy
		if(firstItemLabel.getText() == ""){
			firstItemLabel.setText(startLabelClockTime + " " + cmt.getName());
			firstItemLabel.setToolTipText("<html>Name: " + cmt.getName() + "<br />" + 
										"Description: " + cmt.getDescription() + "<br />" +
										"Due: " + startToolTipClockTime + "<br /></html>");
		}
		else if(secondItemLabel.getText() == ""){
			secondItemLabel.setText(startLabelClockTime + " " + cmt.getName());
			secondItemLabel.setToolTipText("<html>Name: " + cmt.getName() + "<br />" + 
										"Description: " + cmt.getDescription() + "<br />" +
										"Due: " + startToolTipClockTime + "<br /></html>");
		}
		else if(thirdItemLabel.getText() == ""){
			thirdItemLabel.setText(startLabelClockTime + " " + cmt.getName());
			thirdItemLabel.setToolTipText("<html>Name: " + cmt.getName() + "<br />" + 
										"Description: " + cmt.getDescription() + "<br />" +
										"Due: " + startToolTipClockTime + "<br /></html>");
		}
		else if(fourthItemLabel.getText() == ""){
			fourthItemLabel.setText(startLabelClockTime + " " + cmt.getName());
			fourthItemLabel.setToolTipText("<html>Name: " + cmt.getName() + "<br />" + 
										"Description: " + cmt.getDescription() + "<br />" +
										"Due: " + startToolTipClockTime + "<br /></html>");
		}
		else{
			additionalItemsLabel.setText("See More Items");
		}
	
	
	}
}
