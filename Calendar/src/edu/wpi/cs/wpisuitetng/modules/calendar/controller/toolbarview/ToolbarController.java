package edu.wpi.cs.wpisuitetng.modules.calendar.controller.toolbarview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import net.miginfocom.swing.MigLayout;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddCommitmentPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.controller.addeventpanel.AddEventPanelController;
import edu.wpi.cs.wpisuitetng.modules.calendar.model.AddEventModel;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddEventPanel;
import edu.wpi.cs.wpisuitetng.modules.calendar.view.AddCommitmentPanel;

public class ToolbarController implements ActionListener {

   private static ToolbarController instance;
   private JButton addEventButton;
   private JButton addCommitmentButton;


  public ToolbarController() {

  }

  public JButton getAddEventButton() {
    return addEventButton;
  }

  public JButton getAddCommitmentButton() {
    return addCommitmentButton;
  }

  public void setAddEventButton(JButton addEventButton) {
    this.addEventButton = addEventButton;
  }

  public void setAddCommitmentButton(JButton addCommitmentButton) {
    this.addCommitmentButton = addCommitmentButton;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == addEventButton) {
      //if (AddEventPanelController.getInstance().getTabbedPane().getTabCount() == 1) {
    	AddEventPanel newEventPanel = new AddEventPanel(new MigLayout(),  AddEventPanelController.getInstance().getTabbedPane());
        AddEventPanelController.getInstance().getTabbedPane().add(newEventPanel);
        AddEventPanelController.getInstance().getTabbedPane().setTitleAt(AddEventPanelController.getInstance().getTabbedPane().getTabCount() - 1, "Add Event");
        
//        JButton closeButton = new JButton("x");
//        closeButton.addActionListener(new ActionListener() {
//
//          @Override
//          public void actionPerformed(ActionEvent e) {
//            AddEventPanelController.getInstance().getBtnSubmit().doClick();
//
//          }
//
//        });

        AddEventPanelController.getInstance().getTabbedPane().setSelectedIndex(AddEventPanelController.getInstance().getTabbedPane().getTabCount() - 1);

      //}
    }

    if (e.getSource() == addCommitmentButton) {
      JTabbedPane pane = AddCommitmentPanelController.getInstance().getTabbedPane();
      //if (AddCommitmentPanelController.getInstance().getTabbedPane().getTabCount() == 1) {
      AddCommitmentPanel newCommitmentPanel = new AddCommitmentPanel(new MigLayout());
        AddEventPanelController.getInstance().getTabbedPane().add(newCommitmentPanel);
        AddEventPanelController.getInstance().getTabbedPane().setTitleAt(AddEventPanelController.getInstance().getTabbedPane().getTabCount() - 1, "Add Commitment");
//        JButton closeButton = new JButton("x");
//        closeButton.addActionListener(new ActionListener() {
//
//          @Override
//          public void actionPerformed(ActionEvent e) {
//            AddCommitmentPanelController.getInstance().getBtnSubmit().doClick();
//           }
//
//        });

        AddCommitmentPanelController.getInstance().getTabbedPane().setSelectedIndex(AddEventPanelController.getInstance().getTabbedPane().getTabCount() - 1);
        newCommitmentPanel.initiateFocus();
      }
    //}

  }

  public static ToolbarController getInstance() {
    if (instance == null) {
      instance = new ToolbarController();
    }

    return instance;
  }
}
