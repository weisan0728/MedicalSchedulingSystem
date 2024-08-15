/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.UserModel;
import View.Login;
import View.SignUp;
import View.StaffLogin;
import View.PatientProfile;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import project.Connection_Provider;

/**
 *
 * @author Cheong Wei San
 */
public class LoginController {

    private UserModel model;
    private Login view;

    public LoginController(UserModel model, Login view) {
        this.model = model;
        this.view = view;
        initView();
        initController();
    }

    private void initView() {
        view.setVisible(true);
    }

    private void initController() {
        view.login.addActionListener(e -> {
            try {
                login();
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        view.jButton1.addActionListener(e -> toSignUp(e));
        view.toStaffLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toStaffLogin();
            }
        });
    }

    private void login() throws SQLException {
        String phoneNum = view.phoneNumField.getText();
        String password = new String(view.passwordField.getPassword());

        if (phoneNum.isEmpty() || password.isEmpty()) {
            showError("Every field is required");
            return;
        }

        if (!model.validatePhoneNumber(phoneNum)) {
            showError("Invalid Phone Number");
            return;
        }

        if (model.checkeUser(phoneNum, password)) {
            openPatientProfile(phoneNum);
        } else {
            showError("Invalid phone number or password");
        }
    }

    private void openPatientProfile(String phoneNum) {
        try {
            PatientProfile patientProfileFrame = new PatientProfile(phoneNum);
            patientProfileFrame.setVisible(true);
            patientProfileFrame.pack();
            patientProfileFrame.setLocationRelativeTo(null);
            patientProfileFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            view.dispose();
        } catch (Exception ex) {
            showError("Error creating patient profile: " + ex.getMessage());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(view, message, "Error", JOptionPane.ERROR_MESSAGE);
    }


    private void toStaffLogin() {
        StaffLogin staffLoginFrame = new StaffLogin();
        staffLoginFrame.setVisible(true);
        staffLoginFrame.pack();
        staffLoginFrame.setLocationRelativeTo(null);
        staffLoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.dispose();
    }

    private void toSignUp(ActionEvent evt) {
        SignUp signUpFrame = new SignUp();
        signUpFrame.setVisible(true);
        signUpFrame.pack();
        signUpFrame.setLocationRelativeTo(null);
        signUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.dispose();
    }
    
}
