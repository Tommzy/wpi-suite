package edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

public class AddCommitmentPanelController implements ActionListener {

  JTabbedPane tabbedPane;
  JButton btnSubmit;
  public static AddCommitmentPanelController instance;

  public AddCommitmentPanelController( ) {
  }
  
  public static AddCommitmentPanelController getInstance() {
    if (instance == null) {
      instance = new AddCommitmentPanelController();
    }
    
    return instance;
  }
  
  public JTabbedPane getTabbedPane() {
    return tabbedPane;
  }

  public void setTabbedPane(JTabbedPane tabbedPane) {
    this.tabbedPane = tabbedPane;
  }

  public JButton getBtnSubmit() {
    return btnSubmit;
  }

  public void setBtnSubmit(JButton btnSubmit) {
    this.btnSubmit = btnSubmit;
  }

  
  
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnSubmit) {
      tabbedPane.removeTabAt(1);
    }

  }

}
