/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Cheong Wei San
 */
public class PatientProfileModel {
    private static final String username = "root";
    private static final String password = "murberry02";
    private static final String dataConnect = "jdbc:mysql://localhost:3306/hospital";

    private String userPhoneNumber;
    private String userID;
    private String fname;
    private String lname;
    private String gender;
    private String icOrPassport;
    private String phoneNum;
    private String email;
    private String address;

    public void setUserPhoneNumber(String phoneNumber) {
        this.userPhoneNumber = phoneNumber;
    }

    public String getUserID() {
        return userID;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getGender() {
        return gender;
    }

    public String getICorPassport() {
        return icOrPassport;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void fetchUserID() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dataConnect, username, password);
            
            
            String sql = "SELECT id FROM user WHERE phoneNum = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userPhoneNumber);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                userID = rs.getString("id");
            } else {
                JOptionPane.showMessageDialog(null, "No patient found. Please check your ID again.");
            }
            
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public boolean profile() {
    try {
        System.out.println("User Phone Number: " + userPhoneNumber);
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dataConnect, username, password);
        
        String sql = "SELECT firstName, lastName, id, gender, ic, phoneNum, email, address FROM user WHERE phoneNum = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, userPhoneNumber);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            fname = rs.getString("firstName");
            lname = rs.getString("lastName");
            userID = rs.getString("id");
            gender = rs.getString("gender");
            icOrPassport = rs.getString("ic");
            phoneNum = rs.getString("phoneNum");
            email = rs.getString("email");
            address = rs.getString("address");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "No patient found with the given phone number.");
            return false;
        }
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        return false;
    }
}

}
