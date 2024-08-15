/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.StaffModel;
import View.Login;
import View.StaffLogin;
import View.doctorHome1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author Nah Yi
 */
public class DoctorHomeController {
    private StaffModel model;
    private doctorHome1 view;
    
    public DoctorHomeController(StaffModel model, doctorHome1 view){
        this.model = model;
        this.view = view;
        initView();
    }

    public void initView() {
        view.setVisible(true);
    }
    
    public void clockInButton(){
         String doctorIdStr = view.updateID.getText();
        int doctorId;

        try {
            doctorId = Integer.parseInt(doctorIdStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Staff ID must be an integer.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "murberry02");

            int confirmClockIn = JOptionPane.showConfirmDialog(null, "Clock in right now?", "Clock In", JOptionPane.YES_NO_OPTION);

            if (confirmClockIn == JOptionPane.YES_OPTION) { 
 
                String insertQuery = "INSERT INTO clock_in (staffId, clockInDate, clockInTime) VALUES (?, CURDATE(), CURTIME())";
                PreparedStatement insertPstmt = con.prepareStatement(insertQuery);
                insertPstmt.setInt(1, doctorId);

                int rowsInserted = insertPstmt.executeUpdate();

                if (rowsInserted > 0) {

                    java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
                    java.sql.Time currentTime = new java.sql.Time(System.currentTimeMillis());

                    JOptionPane.showMessageDialog(null, "Clocked in successfully at " + currentDate + " " + currentTime);
                } else {
                    JOptionPane.showMessageDialog(null, "No doctor found with the specified ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                insertPstmt.close();
            }

            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error clocking in: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    public void clockOutButton(){
        String doctorIdStr = view.updateID.getText();
        int doctorId;

        try {
            doctorId = Integer.parseInt(doctorIdStr); 
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Doctor ID must be an integer.", "Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "murberry02");

            int confirmClockIn = JOptionPane.showConfirmDialog(null, "Clock out right now?", "Clock Out", JOptionPane.YES_NO_OPTION);

            if (confirmClockIn == JOptionPane.YES_OPTION) { 
                String insertQuery = "INSERT INTO clock_out (staffId, clockOutDate, clockOutTime) VALUES (?, CURDATE(), CURTIME())";
                PreparedStatement insertPstmt = con.prepareStatement(insertQuery);
                insertPstmt.setInt(1, doctorId); 

                int rowsInserted = insertPstmt.executeUpdate();

                if (rowsInserted > 0) {
                    java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
                    java.sql.Time currentTime = new java.sql.Time(System.currentTimeMillis());

                    JOptionPane.showMessageDialog(null, "Clocked Out successfully at " + currentDate + " " + currentTime);
                } else {
                    JOptionPane.showMessageDialog(null, "No doctor found with the specified ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                insertPstmt.close();
            }

            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error clocking out: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    public void exitButton(){
        int a = JOptionPane.showConfirmDialog(null, "Do you really want to exit?", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
        System.exit(0);
        }
    }
    
    public void logoutButton(){
        int a = JOptionPane.showConfirmDialog(null, "Do you really want to log out?", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            view.dispose();
            new StaffLogin().setVisible(true);
        }
    }  
}
