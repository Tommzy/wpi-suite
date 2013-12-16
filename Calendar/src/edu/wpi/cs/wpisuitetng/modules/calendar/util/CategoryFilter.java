package edu.wpi.cs.wpisuitetng.modules.calendar.util;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.JButton;

import edu.wpi.cs.wpisuitetng.modules.calendar.categories.CategoriesModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.commitments.CommitmentsModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.GetCategoryController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.GetCommitmentController;
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
		
	}
	
	/**
	 * Constructor for category filter
	 * @param type Either 0 or 1; 1 stands for personal only or 0 stands for team only.
	 */
	public CategoryFilter(int type) {
		this.type = type;
	}
	
	/**
	 * Used to create a collection of commitments that begin within the start and end time
	 * 
	 * @return The collection of commitments within the time frame
	 */
	public Collection<Category> getCategoryList() {
		try {
			JButton button = new JButton("");
			button.addActionListener(new GetCategoryController());
			button.doClick();
			
			Collection<Category> list = CategoriesModel.getInstance().getAllCategory();
//			Collection<Commitment> list = FakeCommitmentModel.getInstance().getCommitmentList();
			Iterator<Category> itr = list.iterator();

			while (itr.hasNext()) {
				Category category = itr.next();
				categoryList.add(category);
			}
//			System.out.println("SUCCESS PRINT OUT cmtlist in the commitmentFileter.getCommitmentList");
			return categoryList;
		} catch (NullPointerException e) {
			System.out.println("commitment filter null pointer exception");
		}
		
		System.out.println("FAIL PRINT OUT cmtlist in the commitmentFileter.getCommitmentList");
		return new ArrayList<Category> ();
	}
	
}
