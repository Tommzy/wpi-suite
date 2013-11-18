package edu.wpi.cs.wpisuitetng.modules.calendar.view.monthview;

import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;

public class MainFrame {
	JFrame frame = new JFrame();
	MonthView monthView = new MonthView();
	
	public MainFrame() {
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new MigLayout("insets 0 0 0 0"));
		frame.add(monthView);
		
		frame.pack();	
	}
	
	public static void main(String[] arg) {
		new MainFrame();
	}
}
