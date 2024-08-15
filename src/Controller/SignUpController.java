package Controller;

import Model.UserModel;
import View.Login;
import View.SignUp;
import View.StaffRegistration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import project.Connection_Provider;


public class SignUpController {

    private UserModel model;
    private SignUp view;

    public SignUpController(UserModel model, SignUp view) {
        this.model = model;
        this.view = view;
        initView();
        initController();
    }

    public void initView() {
        view.setVisible(true);
    }

   private void initController(){
       view.signUpButton.addActionListener(e -> {
           try {
               signUpUser();
           } catch (SQLException ex) {
               Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
           }
       });
        view.toLoginButton.addActionListener(e -> toLoginButton(e));
        view.jLabel18.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toStaffRegister();
            }
        });
   }

//    public void signUpUser() {
//        String firstName = view.firstNameField.getText();
//        String lastName = view.lastNameField.getText();
//        String ic = view.icField.getText();
//        String phoneNo = view.phoneNoField.getText();
//        String email = view.emailField.getText();
//        char[] password= view.passwordField.getPassword();
//        char[] confirmPassword= view.confirmPasswordField.getPassword();
//        String address= view.addressField.getText();
//        String gender=(String) view.genderComboBox1.getSelectedItem();
//        
//        if(lastName.isEmpty()||firstName.isEmpty()||ic.isEmpty()||email.isEmpty()||phoneNo.isEmpty()||password.length == 0 || confirmPassword.length == 0||address.isEmpty()||gender.isEmpty())
//        {
//            JOptionPane.showMessageDialog(view,
//                "Please enter all field",
//                "Error Message",
//                JOptionPane.ERROR_MESSAGE);
//        }
//        //check phone num format
//        else if (!validatePhoneNumber(phoneNo)) {
//            JOptionPane.showMessageDialog(view,
//                "Invalid Phone Number",
//                "Error Message",
//                JOptionPane.ERROR_MESSAGE);
//        }
//        //check email format
//        else if (!validateEmail(email)) {
//            JOptionPane.showMessageDialog(view,
//                "Email address in the wrong format",
//                "Error Message",
//                JOptionPane.ERROR_MESSAGE);
//        }
//        //check password
//        else if(!Arrays.equals(password,confirmPassword)){
//            JOptionPane.showMessageDialog(view,
//                "Password does not match",
//                "Error Message",
//                JOptionPane.ERROR_MESSAGE);
//        }else{
//            String passwordStr = new String(password);
//            String insertQuery = "INSERT INTO user (firstName, lastName, ic, gender, phoneNum, email, address, password) " +
//            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//            try (PreparedStatement ps = Connection_Provider.getCon().prepareStatement(insertQuery)) {
//                ps.setString(1, firstName);
//                ps.setString(2, lastName);
//                ps.setString(3, ic);
//                ps.setString(4, gender);
//                ps.setString(5, phoneNo);
//                ps.setString(6, email);
//                ps.setString(7, address);
//                ps.setString(8, passwordStr);
//
//                int rowsInserted = ps.executeUpdate();
//                if (rowsInserted > 0) {
//                    JOptionPane.showMessageDialog(view,
//                        "Registered successfully",
//                        "Success",
//                        JOptionPane.INFORMATION_MESSAGE);
//                    view.dispose();
//                    Login LoginFrame=new Login();
//                    LoginFrame.setVisible(true);
//                    LoginFrame.pack();
//                    LoginFrame.setLocationRelativeTo(null);
//                    LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                } else {
//                    JOptionPane.showMessageDialog(view,
//                        "Registration failed",
//                        "Error",
//                        JOptionPane.ERROR_MESSAGE);
//                }
//            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(view,
//                    "Error: " + ex.getMessage(),
//                    "SQL Error",
//                    JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }
//
//    private boolean validatePhoneNumber(String phoneNo) {
//        String phonePattern = "^\\d{3}-\\d{7}$";
//        Pattern pattern = Pattern.compile(phonePattern);
//        Matcher matcher = pattern.matcher(phoneNo);
//        return matcher.matches();
//    }
//
//    private boolean validateEmail(String email) {
//        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
//        Pattern pattern = Pattern.compile(emailPattern);
//        Matcher matcher = pattern.matcher(email);
//        return matcher.matches();
//    }
//    
//     public void toStaffRegister() {
//        StaffRegistration staffRegistrationFrame = new StaffRegistration();
//        staffRegistrationFrame.setVisible(true);
//        staffRegistrationFrame.pack();
//        staffRegistrationFrame.setLocationRelativeTo(null);
//        staffRegistrationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        view.dispose();
//    }
//    
//    public void toLoginButton(ActionEvent evt){
//        Login LoginFrame=new Login();
//        LoginFrame.setVisible(true);
//        LoginFrame.pack();
//        LoginFrame.setLocationRelativeTo(null);
//        LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        view.dispose();
//    }
   private void signUpUser() throws SQLException {
        String firstName = view.firstNameField.getText();
        String lastName = view.lastNameField.getText();
        String ic = view.icField.getText();
        String phoneNo = view.phoneNoField.getText();
        String email = view.emailField.getText();
        char[] password = view.passwordField.getPassword();
        char[] confirmPassword = view.confirmPasswordField.getPassword();
        String address = view.addressField.getText();
        String gender = (String) view.genderComboBox1.getSelectedItem();

        if (lastName.isEmpty() || firstName.isEmpty() || ic.isEmpty() || email.isEmpty() || phoneNo.isEmpty() || password.length == 0 || confirmPassword.length == 0 || address.isEmpty() || gender.isEmpty()) {
            showError("Please enter all fields");
        } else if (!model.validatePhoneNumber(phoneNo)) {
            showError("Invalid Phone Number");
        } else if (!model.validateEmail(email)) {
            showError("Email address in the wrong format");
        } else if (!java.util.Arrays.equals(password, confirmPassword)) {
            showError("Password does not match");
        } else {
            model.registerUser(firstName, lastName, ic, phoneNo, email, address, gender, lastName);
            JOptionPane.showMessageDialog(view, "Registered successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            view.dispose();
            toLogin();
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(view, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void toLogin() {
        Login LoginFrame = new Login();
        LoginFrame.setVisible(true);
        LoginFrame.pack();
        LoginFrame.setLocationRelativeTo(null);
        LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.dispose();
    }

    private void toStaffRegister() {
        StaffRegistration staffRegistrationFrame = new StaffRegistration();
        staffRegistrationFrame.setVisible(true);
        staffRegistrationFrame.pack();
        staffRegistrationFrame.setLocationRelativeTo(null);
        staffRegistrationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.dispose();
    }

    private void toLoginButton(ActionEvent evt) {
        toLogin();
    }
}
