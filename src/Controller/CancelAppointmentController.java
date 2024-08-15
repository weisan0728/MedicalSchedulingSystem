/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import project.Connection_Provider;

/**
 *
 * @author Cheong Wei San
 */
public class CancelAppointmentController {

    private JTextField appointmentIDField;
    private JTextField reasonField;
    private JDateChooser currentDateChooser;

    public CancelAppointmentController(JTextField appointmentIDField, JTextField reasonField, JDateChooser currentDateChooser) {
        this.appointmentIDField = appointmentIDField;
        this.reasonField = reasonField;
        this.currentDateChooser = currentDateChooser;
    }

    public void cancel() {
        try {
            java.sql.Connection conn = Connection_Provider.getCon();
            // Verify Appointment ID
            String verifySql = "SELECT Patient_ID, Appointment_ID, AppointmentDate, AppointmentTime, Clinic FROM appointment_approved WHERE Appointment_ID = ?";
            PreparedStatement verifyStmt = conn.prepareStatement(verifySql);
            verifyStmt.setString(1, appointmentIDField.getText());
            ResultSet rs = verifyStmt.executeQuery();

            if (rs.next()) {
                // Appointment ID exists, proceed to cancel
                String sql = "INSERT INTO appointment_cancelled (Patient_ID, Appointment_ID, CancelDate, AppointmentDate, AppointmentTime, Clinic, CancellationReason) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String currentdate = sdf.format(currentDateChooser.getDate());

                pstmt.setString(1, rs.getString("Patient_ID"));
                pstmt.setString(2, rs.getString("Appointment_ID"));
                pstmt.setString(3, currentdate);
                pstmt.setString(4, rs.getString("AppointmentDate"));
                pstmt.setString(5, rs.getString("AppointmentTime"));
                pstmt.setString(6, rs.getString("Clinic"));
                pstmt.setString(7, reasonField.getText());

                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Appointment cancelled successfully!");
                
                // Now delete the appointment from appointment_approved table
                String deleteSql = "DELETE FROM appointment_approved WHERE Appointment_ID = ?";
                PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
                deleteStmt.setString(1, rs.getString("Appointment_ID"));
                deleteStmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Appointment record deleted from appointment_approved.");

            } else {
                JOptionPane.showMessageDialog(null, "Invalid Appointment ID.");
            }
            conn.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public void handleSubmitButtonActionPerformed(ActionEvent evt) {
        cancel();
    }

    private void clear(JTextField appointmentIDField, JTextField reasonField, JDateChooser currentDateChooser) {
        appointmentIDField.setText("");
        reasonField.setText("");
        currentDateChooser.setDate(null);
    }
}
