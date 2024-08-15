/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.StaffModel;
import View.Login;
import View.StaffLogin;
import View.StaffRegistration;
import View.doctorHome1;
import View.nurseHome;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Cheong Wei San
 */
public class StaffLoginController {

    private StaffModel model;
    private StaffLogin view;

    public StaffLoginController(StaffModel model, StaffLogin view) {
        this.model = model;
        this.view = view;
        initView();
        initController();
    }

    public void initView() {
        view.setVisible(true);
    }

    public void initController() {
        view.staffLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    staffLogin();
                } catch (ParseException ex) {
                    Logger.getLogger(StaffLoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        view.staffLoginToRegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toStaffRegister();
            }
        });

        view.jLabel5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toLogin();
            }
        });
    }

    public void staffLogin() throws ParseException {
        String staffId = view.getStaffIdField().getText();
        String staffPassword = new String(view.getStaffPasswordField().getPassword());

        if (staffId.equals("") || staffPassword.equals("")) {
        JOptionPane.showMessageDialog(view, "Every field is required", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

        String userType = model.validateStaffLogin(staffId, staffPassword);

        if (userType == null) {
            JOptionPane.showMessageDialog(view,"Incorrect username or password.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            switch (userType) {
                case "Nurse":
                    new nurseHome(staffId).setVisible(true);
                    break;
                case "Doctor":
                    new doctorHome1(staffId).setVisible(true);
                    break;
            }
            view.dispose();
        }
    }

    public void toStaffRegister() {
        StaffRegistration StaffRegistrationFrame= new StaffRegistration();
        StaffRegistrationFrame.setVisible(true);
        StaffRegistrationFrame.pack();
        StaffRegistrationFrame.setLocationRelativeTo(null);
        StaffRegistrationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.dispose();
    }

    public void toLogin() {
//        new Login().setVisible(true);
//        view.dispose();
        Login LoginFrame= new Login();
        LoginFrame.setVisible(true);
        LoginFrame.pack();
        LoginFrame.setLocationRelativeTo(null);
        LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.dispose();
    }
    
}
