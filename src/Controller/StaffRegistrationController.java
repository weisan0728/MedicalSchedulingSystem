/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.StaffModel;
import View.Login;
import View.SignUp;
import View.StaffLogin;
import View.StaffRegistration;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import project.Connection_Provider;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 *
 * @author Cheong Wei San
 */
public class StaffRegistrationController {

    private StaffModel model;
    private StaffRegistration view;
    
    public StaffRegistrationController(StaffModel model, StaffRegistration view){
        this.model = model;
        this.view = view;
        initView();
    }

    public void initView() {
        view.setVisible(true);
    }

   public void staffRegister(){
   String staffName = view.staffNameField.getText();
    String staffIc = view.staffIcField.getText();
    char[] staffPsw = view.staffPswField.getPassword();
    char[] staffConfirmPsw = view.staffConfirmPswField.getPassword();
    String staffEmail = view.staffEmailField.getText();
    String staffphoneNum = view.StaffphoneNumField.getText();
    String staffAddress = view.staffAddressField.getText();
    String position = (String) view.positionComboBox.getSelectedItem();

    if (staffName.isEmpty() || staffIc.isEmpty() || staffEmail.isEmpty() || staffphoneNum.isEmpty() || staffPsw.length == 0 || staffConfirmPsw.length == 0 || staffAddress.isEmpty()) {
        JOptionPane.showMessageDialog(view, "Please enter all fields.", "Error Message", JOptionPane.ERROR_MESSAGE);
        return;
    } else if (!validatePhoneNumber(staffphoneNum)) {
        JOptionPane.showMessageDialog(view, "Invalid Phone Number", "Error Message", JOptionPane.ERROR_MESSAGE);
        return;
    } else if (!validateEmail(staffEmail)) {
        JOptionPane.showMessageDialog(view, "Email address in the wrong format", "Error Message", JOptionPane.ERROR_MESSAGE);
        return;
    } else if (!Arrays.equals(staffPsw, staffConfirmPsw)) {
        JOptionPane.showMessageDialog(view, "Passwords do not match.", "Error Message", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String staffPswStr = new String(staffPsw);
    String insertQuery = null;
    String idColumn = null;

    if (position.equals("Nurse")) {
        insertQuery = "INSERT INTO nurse (nurseName, nurseIc, nurseEmail, nursePhoneNum, nurseAddress, nursePsw, position) VALUES (?, ?, ?, ?, ?, ?, ?)";
        idColumn = "nurseId";
    } else if (position.equals("Doctor")) {
        insertQuery = "INSERT INTO doc (docName, docIc, docEmail, docPhoneNum, docAddress, docPsw, position) VALUES (?, ?, ?, ?, ?, ?, ?)";
        idColumn = "docId";
    } else {
        JOptionPane.showMessageDialog(view, "Invalid position selected.", "Error Message", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try (PreparedStatement ps = Connection_Provider.getCon().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
        ps.setString(1, staffName);
        ps.setString(2, staffIc);
        ps.setString(3, staffEmail);
        ps.setString(4, staffphoneNum);
        ps.setString(5, staffAddress);
        ps.setString(6, staffPswStr);
        ps.setString(7, position);

        int rowsInserted = ps.executeUpdate();
        if (rowsInserted > 0) {
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    JOptionPane.showMessageDialog(view, "Registered successfully. Your ID is " + generatedId, "Success", JOptionPane.INFORMATION_MESSAGE);
                    view.dispose();
                    new StaffLogin().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(view, "Registration successful, but failed to retrieve the generated ID.", "Success", JOptionPane.WARNING_MESSAGE);
                    new StaffLogin().setVisible(true);
                }
            }
        } else {
            JOptionPane.showMessageDialog(view, "Registration failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
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
    
   public void toStaffLogin(){
        StaffLogin StaffLoginFrame= new StaffLogin();
        StaffLoginFrame.setVisible(true);
        StaffLoginFrame.pack();
        StaffLoginFrame.setLocationRelativeTo(null);
        StaffLoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.dispose();
   }
   
  public void toSignUp(){
        SignUp SignUpFrame= new SignUp();
        SignUpFrame.setVisible(true);
        SignUpFrame.pack();
        SignUpFrame.setLocationRelativeTo(null);
        SignUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.dispose();
    }
   
}
