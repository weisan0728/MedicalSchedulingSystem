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
public class AppointmentRejectedModel {
    private static final String username = "root";
    private static final String password = "murberry02";
    private static final String dataConnect = "jdbc:mysql://localhost:3306/hospital";

    private String userID;

    public AppointmentRejectedModel(String userID) {
        this.userID = userID;
    }

    public DefaultTableModel getAppointmentRejected() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{
            "Patient_ID", "Request_ID", "RejectionDate", "AppointmentRequestedDate", 
            "AppointmentRequestedTime", "Clinic", "RejectionReason"
        }, 0);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dataConnect, username, password);
            String sql = "SELECT Patient_ID, Request_ID, RejectionDate, AppointmentRequestedDate, AppointmentRequestedTime, Clinic, RejectionReason FROM appointment_rejected WHERE Patient_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userID);
            ResultSet rs = pstmt.executeQuery();


//            DefaultTableModel model = (DefaultTableModel)tbl_appointmentRejected.getModel();
//            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("Patient_ID"),
                    rs.getString("Request_ID"),
                    rs.getString("RejectionDate"),
                    rs.getString("AppointmentRequestedDate"),
                    rs.getString("AppointmentRequestedTime"),
                    rs.getString("Clinic"),
                    rs.getString("RejectionReason")
                });
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
}
