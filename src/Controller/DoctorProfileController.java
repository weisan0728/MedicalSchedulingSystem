/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.StaffModel;
import View.doctorHome1;
import View.nurseHome;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import project.Connection_Provider;

/**
 *
 * @author Nah Yi
 */
public class DoctorProfileController {
    private StaffModel model;
    private doctorHome1 view;
    
    public DoctorProfileController(StaffModel model, doctorHome1 view){
        this.model = model;
        this.view = view;
        initView();
    }

    public void initView() {
        view.setVisible(true);
    }
    
    public void updateButton(){
        String id = view.updateID.getText();
        String name = view.updateNameField.getText();
        String ic = view.updateIcField.getText();
        String email = view.updateEmailField.getText();
        String phoneNum = view.updatePhoneNumField.getText();
        String address = view.updateAddressField.getText();

        if (name.equals("") || ic.equals("") || email.equals("") || phoneNum.equals("") || address.equals("")) {
            JOptionPane.showMessageDialog(null, "All fields are required.");
        } else if (!validatePhoneNumber(phoneNum)) {
        JOptionPane.showMessageDialog(view, "Invalid Phone Number", "Error Message", JOptionPane.ERROR_MESSAGE);
        return;
    } else if (!validateEmail(email)) {
        JOptionPane.showMessageDialog(view, "Email address in the wrong format", "Error Message", JOptionPane.ERROR_MESSAGE);
        return;
    }else {
            try {
                Connection con = Connection_Provider.getCon();
                PreparedStatement ps = con.prepareStatement("UPDATE doc SET docName=?, docIc=?, docEmail=?, docPhoneNum=?, docAddress=? WHERE docId=?");
                ps.setString(1, name);
                ps.setString(2, ic);
                ps.setString(3, email);
                ps.setString(4, phoneNum);
                ps.setString(5, address);
                ps.setString(6, id);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Profile Successfully Updated!");
                new doctorHome1(view.doctorId).setVisible(true);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error updating profile: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }   catch (ParseException ex) {
                Logger.getLogger(nurseHome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private boolean validatePhoneNumber(String phoneNo) {
        String phonePattern = "^\\d{3}-\\d{7}$";
        Pattern pattern = Pattern.compile(phonePattern);
        Matcher matcher = pattern.matcher(phoneNo);
        return matcher.matches();
    }

    private boolean validateEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}