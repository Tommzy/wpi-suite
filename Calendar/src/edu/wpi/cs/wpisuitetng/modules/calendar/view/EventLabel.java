/*******************************************************************************
 * Copyright (c) 2013 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Team3
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import javax.swing.Icon;
import javax.swing.JLabel;

// TODO: Auto-generated Javadoc
/**
 * This class is an extension of JLabel that is used to display event /
 * commitment on calendar day/week (potentially month) view.
 * 
 * @author Yuchen Zhang
 * 
 */
@SuppressWarnings("serial")
public class EventLabel extends JLabel {

  /** The original content. */
  private String originalContent;



  /**
   * Instantiates a new event label.
   */
  public EventLabel() {
  }



  /**
   * Sets the original content.
   * 
   * @param originalContent
   *          the originalContent to set
   */
  public final void setOriginalContent(String originalContent) {
    this.originalContent = originalContent;
  }



  /**
   * Instantiates a new event label.
   * 
   * @param text
   *          the text to display in the label
   */
  public EventLabel(String text) {
    super(text);
    originalContent = text;
  }



  /**
   * Instantiates a new event label.
   * 
   * @param image
   *          the image to display
   */
  public EventLabel(Icon image) {
    super(image);
  }



  /**
   * Instantiates a new event label.
   * 
   * @param text
   *          the text to display
   * @param horizontalAlignment
   *          the horizontal alignment for placement
   */
  public EventLabel(String text, int horizontalAlignment) {
    super(text, horizontalAlignment);
  }



  /**
   * Instantiates a new event label.
   * 
   * @param image
   *          the image to display
   * @param horizontalAlignment
   *          the horizontal alignment for placement
   */
  public EventLabel(Icon image, int horizontalAlignment) {
    super(image, horizontalAlignment);
  }



  /**
   * Instantiates a new event label.
   * 
   * @param text
   *          the text to display
   * @param icon
   *          the icon to display with it
   * @param horizontalAlignment
   *          the horizontal alignment for placement
   */
  public EventLabel(String text, Icon icon, int horizontalAlignment) {
    super(text, icon, horizontalAlignment);
  }



  /**
   * getter for originalContent.
   * 
   * @return originalContent
   */
  public final String getOriginalContent() {
    return originalContent;
  }

}
