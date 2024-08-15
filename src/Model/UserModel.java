/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import project.Connection_Provider;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import project.Select;

/**
 *
 * @author Cheong Wei San
 */
public class UserModel {
    
    //login
    public boolean checkeUser(String phoneNum, String password) throws SQLException {
        String query = "SELECT * FROM user WHERE phoneNum = ? AND password = ?";
        try (Connection con = Connection_Provider.getCon();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, phoneNum);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }
    
    public ResultSet getAllUsers() throws SQLException {
        String query = "SELECT * FROM user";
        return Select.getData(query);
    }

     public ResultSet searchUser(String idOrPhoneNum) throws SQLException {
        String query = "SELECT * FROM user WHERE id LIKE ? OR phoneNum LIKE ?";
        Connection con = Connection_Provider.getCon();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, "%" + idOrPhoneNum + "%");
        ps.setString(2, "%" + idOrPhoneNum + "%");
        return ps.executeQuery();
    }

    public boolean deleteUser(String id) throws SQLException {
        String query = "DELETE FROM user WHERE id = ?";
        try (java.sql.Connection con = project.Connection_Provider.getCon();
             java.sql.PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    //sign up
    public void registerUser(String firstName, String lastName, String ic, String phoneNo, String email, String address, String gender, String password) throws SQLException {
        String query = "INSERT INTO user (firstName, lastName, ic, gender, phoneNum, email, address, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Connection_Provider.getCon();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, ic);
            ps.setString(4, gender);
            ps.setString(5, phoneNo);
            ps.setString(6, email);
            ps.setString(7, address);
            ps.setString(8, password);
            ps.executeUpdate();
        }
    }
    
    public boolean validatePhoneNumber(String phoneNo) {
        String phonePattern = "^\\d{3}-\\d{7}$";
        Pattern pattern = Pattern.compile(phonePattern);
        Matcher matcher = pattern.matcher(phoneNo);
        return matcher.matches();
    }

     public boolean validateEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
