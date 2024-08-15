/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.StaffModel;
import View.Login;
import View.StaffLogin;
import View.doctorHome1;
import View.nurseHome;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author Cheong Wei San
 */
public class NurseHomeController {

    private StaffModel model;
    private nurseHome view;
    private doctorHome1 docview;

    public NurseHomeController(StaffModel model, doctorHome1 docview) {
        this.model = model;
        this.docview = docview;
    }

    public NurseHomeController(StaffModel model, nurseHome view) {
        this.model = model;
        this.view = view;
        initView();
        initController();
    }

    public void initView() {
        view.setVisible(true);
    }

    public void initController() {
        view.clockInButton.addActionListener(e -> clockIn());
        view.clockOutButton.addActionListener(e -> clockOut());
        view.logout.addActionListener(e -> logout());
        view.exit.addActionListener(e -> exit());
    }

    public void clockIn() {
        String nurseIdStr = view.getUpdateID().getText();
        int nurseId;

        try {
            nurseId = Integer.parseInt(nurseIdStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Staff ID must be an integer.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmClockIn = JOptionPane.showConfirmDialog(null, "Clock in right now?", "Clock In", JOptionPane.YES_NO_OPTION);

        if (confirmClockIn == JOptionPane.YES_OPTION) {
            if (model.clockIn(nurseId)) {
                java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
                java.sql.Time currentTime = new java.sql.Time(System.currentTimeMillis());
                JOptionPane.showMessageDialog(null, "Clocked in successfully at " + currentDate + " " + currentTime);
            } else {
                JOptionPane.showMessageDialog(null, "Clock in failed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void clockOut() {
        String nurseIdStr = view.getUpdateID().getText();
        int nurseId;

        try {
            nurseId = Integer.parseInt(nurseIdStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Staff ID must be an integer.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmClockOut = JOptionPane.showConfirmDialog(null, "Clock out right now?", "Clock Out", JOptionPane.YES_NO_OPTION);

        if (confirmClockOut == JOptionPane.YES_OPTION) {
            if (model.clockOut(nurseId)) {
                java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
                java.sql.Time currentTime = new java.sql.Time(System.currentTimeMillis());
                JOptionPane.showMessageDialog(null, "Clocked Out successfully at " + currentDate + " " + currentTime);
            } else {
                JOptionPane.showMessageDialog(null, "Clock out failed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public void exit() {
        int a = JOptionPane.showConfirmDialog(null, "Do you really want to exit?", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            System.exit(0);
        }
    }

    public void logout() {
        int a = JOptionPane.showConfirmDialog(null, "Do you really want to log out?", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            view.dispose();
            new StaffLogin().setVisible(true);
        }
    }
    
}
