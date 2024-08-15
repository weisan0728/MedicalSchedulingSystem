package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

public class MakeAppointmentModel {
    private static final String username = "root";
    private static final String password = "murberry02";
    private static final String dataConnect = "jdbc:mysql://localhost:3306/hospital";
    
    private static final String PHONE_NUMBER_REGEX = "^\\d{3}-\\d{7}$";

    private String userID;
    private String appointmentName;
    private String appointmentPhoneNum;
    private String appointmentDate;
    private String appointmentTime;
    private String appointmentClinic;
    private String appointmentReason;
    private String appointmentSpecialNeeds;

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    // Setters for appointment details
    public void setAppointmentDetails(String name, String phoneNum, String date, String time, String clinic, String reason, String specialNeeds) {
        this.appointmentName = name;
        this.appointmentPhoneNum = phoneNum;
        this.appointmentDate = date;
        this.appointmentTime = time;
        this.appointmentClinic = clinic;
        this.appointmentReason = reason;
        this.appointmentSpecialNeeds = specialNeeds;
    }

    

    // Method to insert appointment data into the database
    public boolean makeAppointment() {
        if (userID == null || userID.isEmpty()) {
            JOptionPane.showMessageDialog(null, "User ID is not set.");
            return false;
        }

        if (!Pattern.matches(PHONE_NUMBER_REGEX, appointmentPhoneNum)) {
            JOptionPane.showMessageDialog(null, "Please enter a valid phone number.");
            return false;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(dataConnect, username, password)) {
                String sql = "INSERT INTO make_appointment (Patient_ID, Name, PhoneNum, AppointmentDate, AppointmentTime, Clinic, Reason, SpecialNeeds) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, userID);
                    pstmt.setString(2, appointmentName);
                    pstmt.setString(3, appointmentPhoneNum);
                    pstmt.setString(4, appointmentDate);
                    pstmt.setString(5, appointmentTime);
                    pstmt.setString(6, appointmentClinic);
                    pstmt.setString(7, appointmentReason);
                    pstmt.setString(8, appointmentSpecialNeeds);
                    
                    int result = pstmt.executeUpdate();
                    return result > 0;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error making appointment: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
}
