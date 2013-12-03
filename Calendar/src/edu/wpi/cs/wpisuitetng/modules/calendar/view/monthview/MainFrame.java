package edu.wpi.cs.wpisuitetng.modules.calendar.view.monthview;

import javax.swing.JFrame;

import edu.wpi.cs.wpisuitetng.modules.calendar.util.DateController;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.DatePicker;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.DatePickerPanel;
import net.miginfocom.swing.MigLayout;

/**
 * a main class for testing
 * @author Hongbo
 *
 */
public class MainFrame {
	JFrame frame = new JFrame();
	//MonthView monthView = new MonthView();
	
	public MainFrame() {
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new MigLayout("insets 0 0 0 0"));
		//frame.add(monthView);
		DateController date = new DateController(2015, 1, 1);
		//System.out.println(date.getDayOfWeek());
		frame.add(new DatePickerPanel(2015, 1, 2, null));
		frame.pack();	
	}
	
	public static void main(String[] arg) {
		new MainFrame();
	}
}
