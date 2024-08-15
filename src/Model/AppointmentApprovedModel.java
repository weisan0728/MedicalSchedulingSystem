/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import project.Connection_Provider;

/**
 *
 * @author Cheong Wei San
 */
public class AppointmentApprovedModel {

    private static final String username = "root";
    private static final String password = "murberry02";
    private static final String dataConnect = "jdbc:mysql://localhost:3306/hospital";

    private String userID;

    public AppointmentApprovedModel(String userID) {
        this.userID = userID;
    }

    public DefaultTableModel getAppointmentApproved() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{
            "Patient_ID", "Appointment_ID", "ApproveDate", "AppointmentDate", "AppointmentTime", "Clinic"
        }, 0);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dataConnect, username, password);
            String sql = "SELECT Patient_ID, Appointment_ID, ApproveDate, AppointmentDate, AppointmentTime, Clinic FROM appointment_approved WHERE Patient_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userID);
            ResultSet rs = pstmt.executeQuery();
            
//            DefaultTableModel model = (DefaultTableModel)tbl_appointmentApproved.getModel();
//            model.setRowCount(0);
            
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("Patient_ID"),
                    rs.getString("Appointment_ID"),
                    rs.getString("ApproveDate"),
                    rs.getString("AppointmentDate"),
                    rs.getString("AppointmentTime"),
                    rs.getString("Clinic")
                });
            }
            conn.close();
            
            
        } catch (Exception e) {
            e.printStackTrace(); // Logging the exception for debugging
        }
        return model;
    }
}
