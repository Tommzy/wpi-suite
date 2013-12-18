package edu.wpi.cs.wpisuitetng.modules.calendar.util;

import java.awt.Color;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;

import edu.wpi.cs.wpisuitetng.modules.calendar.categories.CategoriesModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.commitments.CommitmentsModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.GetCategoryController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.GetCommitmentController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddCommitmentPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Category;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Commitment;

/**
 * This class is used to filter a mist of commitments and return those that begin within a specified time slot
 *
 */
public class CategoryFilter {
	private int type = -1;
	private Collection<Category> categoryList = new ArrayList<Category>();
	/**
	 * Constructor for category filter
	 */
	public CategoryFilter() {
		this(-1);
	}
	
	/**
	 * Constructor for category filter
	 * @param type Either 0 or 1; 1 stands for personal only or 0 stands for team only.
	 */
	public CategoryFilter(int type) {
		this.type = type;
	}
	
	/**
	 * Used to create a collection of category that begin within the start and end time
	 * 
	 * @return The array of category within the time frame
	 */
	public Category[] getCategoryArray() {
		try {
			Category teamMeeting = new Category("Team Meeting", true, Color.cyan);
			teamMeeting.setId(1);
			teamMeeting.setIsPersonal(false);
			Category personalMeeting = new Category("Personal Meeting", true, Color.cyan);
			teamMeeting.setId(2);
			teamMeeting.setIsPersonal(true);
			Category[] categoryArray;
			//new GetCategoryController().actionPerformed(null);
			GetCategoryController getController = new GetCategoryController();
			getController.retrieveCategories();
			Collection<Category> categoryList = CategoriesModel.getInstance().getAllCategory();
			Collection<Category> list = new ArrayList<Category>();
			if (type == 0) {
				for (Category cat : categoryList) {
					if ((! cat.isPersonal()) && (cat.isActive())) {
						list.add(cat);
					}
				}
				list.add(teamMeeting);
			}
			else if (type == 1) {
				for (Category cat : categoryList) {
					if ((cat.isPersonal()) && (cat.isActive())) {
						list.add(cat);
					}
				}
				list.add(personalMeeting);
			}
			else if (type == -1) {
				for (Category cat : categoryList) {
					if (cat.isActive()) {
						list.add(cat);
					}
				}
				list.add(personalMeeting);
				list.add(teamMeeting);
			}
			categoryArray = list.toArray(new Category[list.size()]);
			System.out.println(categoryArray.length);
			return categoryArray;
		} catch (NullPointerException e) {
			System.out.println("commitment filter null pointer exception");
		}
		
		System.out.println("FAIL PRINT OUT cmtlist in the commitmentFileter.getCommitmentList");
		return new Category[0];
	}
	
	/**
	 * Used to create a collection of commitments that begin within the start and end time
	 * 
	 * @return The collection of commitments within the time frame
	 */
	public Collection<Category> getCategoryList() {
		try {
//			JButton button = new JButton("");
//			button.addActionListener(new GetCategoryController());
//			button.doClick();
			new GetCategoryController().actionPerformed(null);
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					Collection<Category> list = CategoriesModel.getInstance().getAllCategory();
					System.out.println(list.size());
					Iterator<Category> itr = list.iterator();

					while (itr.hasNext()) {
						Category category = itr.next();
						categoryList.add(category);
					}
				}
			}, 0);
			
			return categoryList;
		} catch (NullPointerException e) {
			System.out.println("commitment filter null pointer exception");
		}
		
		System.out.println("FAIL PRINT OUT cmtlist in the commitmentFileter.getCommitmentList");
		return new ArrayList<Category> ();
	}
	
}
