/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Andrew Aveyard
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.calendar.categories.CategoriesModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.AddCommitmentController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.AddFilterController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddCommitmentPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.ManageFiltersPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.filters.FiltersModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Category;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.Filter;
import edu.wpi.cs.wpisuitetng.modules.calendar.util.CategoryFilter;

// TODO: Auto-generated Javadoc
/**
 * The Class AddFilterPanel.
 */
@SuppressWarnings("serial")
public class AddFilterPanel extends JPanel {

  /** The btn submit. */
  JButton    btnSubmit, btnUpdate, btnCancel;

  /** The name label. */
  JLabel     nameLabel;
  
  /** The error msg box for name. */
  JErrorMessageLabel nameErrMsg;
  
  /** The error msg box for categories */
  JErrorMessageLabel catErrMsg;

  /** The name text field. */
  JTextField nameTextField;
  
  /** The team categories label. */
  JLabel     teamCategoriesLabel;
  
  /** The personal categories label */
  JLabel personCategoriesLabel;
  
  /** ScrollPane Container for team categories */
  JScrollPane teamScroll;
  
  /** ScrollPane Container for personal categories */
  JScrollPane personalScroll;
  
  /** The team categories. */
  JList  teamJList;
  
  /** The personal categories. */
  JList  personalJList;
  
  /** The currently selected categories */
  List selectedCats = new ArrayList();
  
  /** ArrayList of SelectedCategories 
   *  Updates when packInfo() is called **/
  ArrayList<Category> selCategories = new ArrayList<Category>();
  
  /** The Id field */
  JLabel IDText; 

  /** Model for Team JList **/
  DefaultListModel teamdlm = new DefaultListModel();

  /** Model for Personal JList **/
  DefaultListModel persdlm = new DefaultListModel();

  int textAreaSelection = 0; // 0 for team cate
  // 1 for personal cate
  
  List<Category> teamCategory = new ArrayList<Category>();
  List<Category> personalCategory = new ArrayList<Category>();
  
  /**
   * Instantiates a new filter panel.
   * 
   * @param miglayout
   *          the MigLayout
   */
  public AddFilterPanel(MigLayout miglayout) {
    JPanel contentPanel = new JPanel(miglayout);
    nameLabel = new JLabel("Name:");

    nameTextField = new JTextField(10);
    
    nameErrMsg = new JErrorMessageLabel();
    
    catErrMsg = new JErrorMessageLabel();
    
    teamCategoriesLabel = new JLabel("Team Categories");
    personCategoriesLabel = new JLabel("Personal Categories");
    
    teamJList = new JList() {
        protected void paintBorder(Graphics g) {
        	if (textAreaSelection == 0) {
        		 g.setColor(Color.cyan);
                 g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        	}
       }
    };
    personalJList = new JList() {
        protected void paintBorder(Graphics g) {
        	if (textAreaSelection == 1) {
       		 g.setColor(Color.cyan);
                g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
       	}
       }
    };

    teamJList.addMouseListener(new MouseAdapter() {
    	public void mouseMoved(MouseEvent e) {
    		verify();
    	}
    	public void mouseReleased(MouseEvent e) {
    		verify();
    	}
       	public void mouseClicked(MouseEvent e) {
    		textAreaSelection = 0;
    		e.getComponent().revalidate();
    		e.getComponent().repaint();
    		personalJList.revalidate();
    		personalJList.repaint();
    	}
    });
    personalJList.addMouseListener(new MouseAdapter() {
    	public void mouseMoved(MouseEvent e) {
    		verify();
    		
    	}
    	public void mouseReleased(MouseEvent e) {
    		verify();
    		
    	}
    	
    	public void mouseClicked(MouseEvent e) {
    		textAreaSelection = 1;
    		e.getComponent().revalidate();
    		e.getComponent().repaint();
    		teamJList.revalidate();
    		teamJList.repaint();
    	}
    });
    nameTextField.addMouseListener(new MouseAdapter() {
    	public void mouseMoved(MouseEvent e) {
    		verify();
    	}
    	public void mouseReleased(MouseEvent e) {
    		verify();
    	}
 
    });
    
    nameTextField.addKeyListener(new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			verify();
			
		}
    	
    });
   
    teamScroll = new JScrollPane(teamJList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    teamScroll.setPreferredSize(new Dimension(400, 100));
    
    personalScroll = new JScrollPane(personalJList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    personalScroll.setPreferredSize(new Dimension(400, 100));
    
    btnSubmit = new JButton("Submit");
    btnUpdate = new JButton("Update");
    btnCancel = new JButton ("Cancel");
    
//    btnSubmit.addActionListener(new ActionListener() {
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			AddFilterController c = new AddFilterController(FiltersModel.getInstance(), packInfo());
//			c.actionPerformed(event);
//			System.out.println("Filtermodel size: " + FiltersModel.getInstance().getFilters().size());
//		}
//		
//		
//    	
//    });
    
    //btnSubmit.addActionListener(new AddFilterController(FiltersModel.getInstance(),packInfo()));
    JLabel IDText = new JLabel(); 
    
    final FiltersModel model = FiltersModel.getInstance();

    // Set up properties and values
	nameTextField.setInputVerifier(new TextVerifier(nameErrMsg, btnSubmit));
	JListVerifier categorySelectVerifier = new JListVerifier(catErrMsg, btnSubmit);
	teamJList.setInputVerifier(categorySelectVerifier);
	personalJList.setInputVerifier(categorySelectVerifier);

    contentPanel.add(nameLabel);
    contentPanel.add(nameTextField, "span 3, wrap");
    contentPanel.add(nameErrMsg, "wrap, span");
    contentPanel.add(teamCategoriesLabel, "span 3");
    contentPanel.add(personCategoriesLabel, "wrap, span 4");    
    contentPanel.add(teamScroll, "span 3, width 150");
    contentPanel.add(personalScroll, "wrap, span 4, width 150");
    contentPanel.add(catErrMsg, "wrap, span");
    contentPanel.add(btnSubmit, "cell 1 7");
    contentPanel.add(btnUpdate, "cell 2 7");
    contentPanel.add(btnCancel, "cell 3 7");
    
    // Set up listeners and properties. 
    if (IDText.getText().equals("")) {
    	btnUpdate.setVisible(false);
    	btnSubmit.setVisible(true);
    }
    else {
    	btnUpdate.setVisible(true);
    	btnSubmit.setVisible(false);
    }
    btnSubmit.addActionListener(ManageFiltersPanelController.getInstance());
    btnSubmit.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			AddFilterController f = new AddFilterController(FiltersModel.getInstance() , packInfo());
			f.addFilterToDatabase(packInfo());
			f.addFilterToModel(packInfo());
			System.out.println("filtermodelsize: " + FiltersModel.getInstance().getFilters().size());
		}
    	
    });
    btnCancel.addActionListener(ManageFiltersPanelController.getInstance());
    btnUpdate.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			((JButton)e.getSource()).addActionListener(new AddFilterController(model , packInfo()));
			((JButton)e.getSource()).addActionListener(ManageFiltersPanelController.getInstance());
			((JButton)e.getSource()).removeActionListener(this);
			((JButton)e.getSource()).doClick();
		}
    	
    });
    
    teamJList.setModel(teamdlm);
    teamJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    teamJList.setLayoutOrientation(JList.VERTICAL);
    teamJList.setVisibleRowCount(2);
    
    
    personalJList.setModel(persdlm);
    personalJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    personalJList.setLayoutOrientation(JList.VERTICAL);
    personalJList.setVisibleRowCount(2);
    
    this.add(contentPanel);
    
    
    //Testing for JLists
//    teamdlm.addElement("Hello");
//    teamdlm.addElement("Hiya");
//    teamdlm.addElement("Hey");
//    persdlm.addElement("Goodbye");
   
    
    updateList();
    
    verify();
  }


  public void initiateFocus() {
	  nameTextField.requestFocusInWindow();
  }


  public void updateList() {
	  CategoryFilter personalfilter = new CategoryFilter(1);
	  Category[] c = personalfilter.getCategoryArray();
	  personalCategory.clear();
	  persdlm.removeAllElements();
	  System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	  for (int i = 0; i < c.length; i ++) {
		  personalCategory.add(c[i]);
		  persdlm.addElement(c[i].getName());
		  System.out.println(c[i].getId());
	  }
	  CategoryFilter teamfilter = new CategoryFilter(0);
	  Category[] p = teamfilter.getCategoryArray();
	  teamCategory.clear();
	  teamdlm.removeAllElements();
	  System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	  for (int i = 0; i < p.length; i ++) {
		  teamCategory.add(p[i]);
		  teamdlm.addElement(p[i].getName());
		  System.out.println(p[i].getId());
	  }
	  
  }
  
  private Filter packInfo() {
	  String name = nameTextField.getText();
	  printCategoryList();
	  ArrayList<Category> c = new ArrayList<Category>();
	  int[] is = teamJList.getSelectedIndices();
	  for (int i = 0; i < is.length; i ++) {
		  c.add((Category)teamCategory.get(is[i]));
	  }
	  is = personalJList.getSelectedIndices();
	  for (int i = 0; i < is.length; i ++) {
		  c.add((Category)personalCategory.get(is[i]));
	  }
	  Filter f = new Filter(name, c);
	  return f;
  }
//  private Filter packInfo() {
//	  // ID 
//	  int id;
//	  if (IDText.getText().equals("")) {
//		 id = -1;
//	  }
//	  else {
//		  id = Integer.parseInt(IDText.getText()); 
//	  }
//	  // Name
//	  String name = nameTextField.getText();
//	  
//	  //Pack selected categories into selCategories for the filter
//	  
//	  List teamCats = teamJList.getSelectedValuesList();
//	  List personCats = personalJList.getSelectedValuesList();	  
//	  
//	  Iterator<List> iTeam = teamCats.iterator();
//	  if(iTeam.hasNext()){
//		  do{
//			  selCategories.add((Category) iTeam.next());
//		  }
//		  while(iTeam.hasNext());
//	  }
//	  
//	  Iterator<List> iPers = personCats.iterator();
//	  if(iPers.hasNext()){
//		  do{
//			  selCategories.add((Category) iPers.next());
//			}
//		  while(iPers.hasNext());
//	  }
//	  
//	  // Pack into a filter
//	  Filter filter = new Filter(name, selCategories);
//
//
//	  filter.setId(id);
//	  return filter;
//  }

  public void printCategoryList() {
	  System.out.println("TEam categoreis");
	  for (Category c : teamCategory) {
		  System.out.println(c.getName());
	  }
	  System.out.println("Personal categoreis");
	  for (Category c : personalCategory) {
		  System.out.println(c.getName());
	  }
  }
  private boolean checkContent() {
	  boolean flag = false;
	  String s = nameTextField.getText();
	  for (int i = 0; i < s.length(); i ++) {
		  if (s.charAt(i) != ' ') {
			  flag = true;
		  }
	  }
	  if (!flag) {
		  nameTextField.setText("");
		  return false;
	  } 
	  
	  if (nameErrMsg.getContentText().equals("") && (!teamJList.getSelectedValuesList().isEmpty() || !personalJList.getSelectedValuesList().isEmpty())) {
		  nameErrMsg.setText("");
		  catErrMsg.setText("");
		  return true;
	  }
	  else 
		  return false;
  }
	  

	private class TextVerifier extends InputVerifier {
		JLabel errMsg; 
		JButton btnSubmit;
		
		public TextVerifier(JComponent errMsg, JButton btnSubmit) {
			this.errMsg = (JLabel) errMsg;
			this.btnSubmit = btnSubmit;
		}
		
		@Override
		public boolean verify(JComponent input) {
			JTextField tf = (JTextField) input;
			if (tf.getText().equals("")) {
				errMsg.setText("Name can not be empty! ");
				btnSubmit.setEnabled(checkContent());
				btnUpdate.setEnabled(checkContent());
			}
			else if (tf.getText().trim().equals("")) {
				errMsg.setText("Invalid Name! ");
				btnSubmit.setEnabled(checkContent());
				btnUpdate.setEnabled(checkContent());
			}
			else {
				errMsg.setText("");
				btnSubmit.setEnabled(checkContent());
				btnUpdate.setEnabled(checkContent());
			}
			return (! tf.getText().trim().equals(""));
		}
	}

	public void verify() {
		btnSubmit.setEnabled(checkContent());
		btnUpdate.setEnabled(checkContent());
		revalidate();
		repaint();
	}
	private class JListVerifier extends InputVerifier {
		JLabel errMsg; 
		JButton btnSubmit;
		
		public JListVerifier(JComponent errMsg, JButton btnSubmit) {
			this.errMsg = (JLabel) errMsg;
			this.btnSubmit = btnSubmit;
		}
		
		@Override
		public boolean verify(JComponent input) {
			if (teamJList.getSelectedValuesList().isEmpty() && personalJList.getSelectedValuesList().isEmpty()) {
				errMsg.setText("Need at least one category selected! ");
				btnSubmit.setEnabled(checkContent());
				btnUpdate.setEnabled(checkContent());
			}
			else {
				errMsg.setText("");
				btnSubmit.setEnabled(checkContent());
				btnUpdate.setEnabled(checkContent());
			}
			return (! (teamJList.getSelectedValuesList().isEmpty() && personalJList.getSelectedValuesList().isEmpty()));
		}
	}

}

