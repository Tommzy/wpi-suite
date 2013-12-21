/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team3
 ******************************************************************************/

package edu.wpi.cs.wpisuitetng.modules.calendar.view;

import javax.swing.Icon;
import javax.swing.JLabel;

/**
 * The Class JErrorMessageLabel to display on panels
 * when invalid input has been given.
 * 
 * @author Yuchen Zhang
 */
public class JErrorMessageLabel extends JLabel {

  /**
   * Instantiates a new j error message label.
   */
  public JErrorMessageLabel() {
  }



  /**
   * Instantiates a new j error message label.
   * 
   * @param text
   *          the text that should be shown.
   */
  public JErrorMessageLabel(String text) {
    super("<html><font color='red'>" + text + "</font></html>");
  }



  /**
   * Instantiates a new j error message label.
   * 
   * @param image
   *          the image to show.
   */
  public JErrorMessageLabel(Icon image) {
    super(image);
  }



  /**
   * Instantiates a new j error message label.
   * 
   * @param text
   *          the text to show.
   * @param horizontalAlignment
   *          the horizontal alignment of the label for displaying.
   */
  public JErrorMessageLabel(String text, int horizontalAlignment) {
    super(text, horizontalAlignment);
  }



  /**
   * Instantiates a new j error message label.
   * 
   * @param image
   *          the image to display.
   * @param horizontalAlignment
   *          the horizontal alignment of the label.
   */
  public JErrorMessageLabel(Icon image, int horizontalAlignment) {
    super(image, horizontalAlignment);
  }



  /**
   * Instantiates a new j error message label.
   * 
   * @param text
   *          the text to display.
   * @param icon
   *          the icon to display.
   * @param horizontalAlignment
   *          the horizontal alignment of the label.
   */
  public JErrorMessageLabel(String text, Icon icon, int horizontalAlignment) {
    super(("<html><font color='red'>" + text + "</font></html>"), icon,
        horizontalAlignment);
  }



  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.JLabel#setText(java.lang.String)
   */
  @Override
  public final void setText(String text) {
    super.setText("<html><font color='red'>" + text + "</font></html>");
  }



  /**
   * Gets the content text.
   * 
   * @return the content text
   */
  public final String getContentText() {
    String all = super.getText();
    String content = all.split("<font color='red'>")[1].split("</font>")[0];
    return content;
  }

}
