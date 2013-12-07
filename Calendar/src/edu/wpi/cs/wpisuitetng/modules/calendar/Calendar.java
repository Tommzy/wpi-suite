/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Team 3
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.calendar;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import edu.wpi.cs.wpisuitetng.janeway.modules.IJanewayModule;
import edu.wpi.cs.wpisuitetng.janeway.modules.JanewayTabModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.MainView;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.ToolbarView;

/**
 * A Calendar Module for WPI-Suite.
 * 
 */
public class Calendar implements IJanewayModule {

  /** The tabs used by this module. */
  private List<JanewayTabModel> tabs;

  /**
   * Construct a new Calendar Module for demonstration purposes.
   */
  public Calendar() {

    // Setup button panel
    final ToolbarView toolbar = new ToolbarView();
    // buttonPanel.setLayout(new FlowLayout());
    // buttonPanel.add(new JButton("Func A"));
    // buttonPanel.add(new JButton("Func B"));

    // Setup the main panel
    final MainView mainPanel = new MainView();
    mainPanel.setLayout(new GridLayout(1, 2));

    tabs = new ArrayList<JanewayTabModel>();
    final JanewayTabModel tab = new JanewayTabModel("Calendar", new ImageIcon(),
        toolbar, mainPanel);
    tabs.add(tab);
  }

  /**
   * Getter for name.
   * @see edu.wpi.cs.wpisuitetng.janeway.modules.IJanewayModule#getName()
   * @return A string saying it is a calendar.
   */
  @Override
  public String getName () {
    return "Calendar";
  }

  /**
   * Getter for tabs.
   * @see edu.wpi.cs.wpisuitetng.janeway.modules.IJanewayModule#getTabs()
   * @return A list of the tabs.
   */
  @Override
  public List<JanewayTabModel> getTabs() {
    return tabs;
  }
}
