/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.*;
import java.util.Arrays;
import project.Connection_Provider;



/**
 *
 * @author Cheong Wei San
 */
public class StaffModel {
 
    public boolean registerStaff(String staffName, String staffIc, char[] staffPassword, char[] staffConfirmPassword, String staffEmail, String staffPhoneNum, String staffAddress, String position) {
        if (staffName.isEmpty() || staffIc.isEmpty() || staffEmail.isEmpty() || staffPhoneNum.isEmpty() ||
            staffPassword.length == 0 || staffConfirmPassword.length == 0 || staffAddress.isEmpty()) {
            return false; 
        } else if (!Arrays.equals(staffPassword, staffConfirmPassword)) {
            return false;
        }

        String staffPswStr = new String(staffPassword);
        String insertQuery;

        if (position.equals("Nurse")) {
            insertQuery = "INSERT INTO nurse (nurseName, nurseIc, nurseEmail, nursePhoneNum, nurseAddress, nursePsw, position) VALUES (?, ?, ?, ?, ?, ?, ?)";
        } else if (position.equals("Doctor")) {
            insertQuery = "INSERT INTO doc (docName, docIc, docEmail, docPhoneNum, docAddress, docPsw, position) VALUES (?, ?, ?, ?, ?, ?, ?)";
        } else {
            return false; 
        }

        try (Connection con = Connection_Provider.getCon();
             PreparedStatement ps = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, staffName);
            ps.setString(2, staffIc);
            ps.setString(3, staffEmail);
            ps.setString(4, staffPhoneNum);
            ps.setString(5, staffAddress);
            ps.setString(6, staffPswStr);
            ps.setString(7, position);

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        return true; 
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false; 
    }

    public String validateStaffLogin(String staffId, String staffPassword) {
        String userType = null;

        try (Connection con = Connection_Provider.getCon()) {
            // Check if the user is a nurse
            PreparedStatement nurseStmt = con.prepareStatement("SELECT * FROM nurse WHERE nurseId = ? AND nursePsw = ?");
            nurseStmt.setString(1, staffId);
            nurseStmt.setString(2, staffPassword);
            ResultSet nurseRs = nurseStmt.executeQuery();
            if (nurseRs.next()) {
                userType = "Nurse";
            }

            // Check if the user is a doctor
            if (userType == null) {
                PreparedStatement doctorStmt = con.prepareStatement("SELECT * FROM doc WHERE docId = ? AND docPsw = ?");
                doctorStmt.setString(1, staffId);
                doctorStmt.setString(2, staffPassword);
                ResultSet doctorRs = doctorStmt.executeQuery();
                if (doctorRs.next()) {
                    userType = "Doctor";
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return userType;
    }
    
    public boolean clockIn(int staffId) {
        String insertQuery = "INSERT INTO clock_in (staffId, clockInDate, clockInTime) VALUES (?, CURDATE(), CURTIME())";

        try (Connection con = Connection_Provider.getCon();
             PreparedStatement ps = con.prepareStatement(insertQuery)) {
            ps.setInt(1, staffId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean clockOut(int staffId) {
        String insertQuery = "INSERT INTO clock_out (staffId, clockOutDate, clockOutTime) VALUES (?, CURDATE(), CURTIME())";

        try (Connection con = Connection_Provider.getCon();
             PreparedStatement ps = con.prepareStatement(insertQuery)) {
            ps.setInt(1, staffId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
