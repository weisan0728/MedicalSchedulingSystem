/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import project.Connection_Provider;

/**
 *
 * @author Cheong Wei San
 */
public class AppointmentCancelledModel {
    private JPanel panel;
    private JTextField appointmentIDTextField;
    private JTextArea reasonTextArea;
    private JDateChooser currentDateChooser;
    private String userID;

    private static final String username = "root";
    private static final String password = "murberry02";
    private static final String dataConnect = "jdbc:mysql://localhost:3306/hospital";
    
    
    public AppointmentCancelledModel(String userID) {
        this.userID = userID;
    }

    public void cancel() {
        try {
            java.sql.Connection conn = Connection_Provider.getCon();

            String verifySql = "SELECT Patient_ID, Appointment_ID, AppointmentDate, AppointmentTime, Clinic FROM appointment_approved WHERE Appointment_ID = ?";
            PreparedStatement verifyStmt = conn.prepareStatement(verifySql);
            verifyStmt.setString(1, appointmentIDTextField.getText());
            ResultSet rs = verifyStmt.executeQuery();

            if (rs.next()) {
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
                pstmt.setString(7, reasonTextArea.getText());

                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Appointment cancelled successfully!");

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
    
    public DefaultTableModel getAppointmentCancelled() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{
            "Patient_ID", "Appointment_ID", "CancelDate", "AppointmentDate", 
            "AppointmentTime", "Clinic", "CancellationReason"
        }, 0);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dataConnect, username, password);
            String sql = "SELECT Patient_ID, Appointment_ID, CancelDate, AppointmentDate, AppointmentTime, Clinic, CancellationReason FROM appointment_cancelled WHERE Patient_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userID);
            ResultSet rs = pstmt.executeQuery();

//            DefaultTableModel model = (DefaultTableModel)tbl_appointmentCancelled.getModel();
//            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("Patient_ID"),
                    rs.getString("Appointment_ID"),
                    rs.getString("CancelDate"),
                    rs.getString("AppointmentDate"),
                    rs.getString("AppointmentTime"),
                    rs.getString("Clinic"),
                    rs.getString("CancellationReason")
                });
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace(); // Logging the exception for debugging
        }
        return model;
    }
}
