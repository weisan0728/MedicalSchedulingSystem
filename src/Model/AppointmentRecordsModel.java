/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import project.Connection_Provider;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Cheong Wei San
 */
public class AppointmentRecordsModel {
    private static final String username = "root";
    private static final String password = "murberry02";
    private static final String dataConnect = "jdbc:mysql://localhost:3306/hospital";
    private String userID;

    public AppointmentRecordsModel(String userID) {
        this.userID = userID;
    }
    
    public int updateAppointment(String doctorName, String appointmentId, String patientId) throws Exception {
        java.sql.Connection con = Connection_Provider.getCon();
        String insertQuery = "INSERT INTO appointment_records (Appointment_ID, Patient_ID, Date, Time, DoctorName, Clinic, Reason, BillingStatus) "
                + "SELECT Appointment_ID, Patient_ID, AppointmentDate, AppointmentTime, ?, Clinic, Reason, 'Completed' "
                + "FROM appointment_approved WHERE Appointment_ID=? AND Patient_ID=?";
        PreparedStatement insertPs = con.prepareStatement(insertQuery);
        insertPs.setString(1, doctorName); 
        insertPs.setString(2, appointmentId); 
        insertPs.setString(3, patientId);  
        int rowsInserted = insertPs.executeUpdate();
        
        if (rowsInserted > 0) {
            String deleteQuery = "DELETE FROM appointment_approved WHERE Appointment_ID = ?";
            PreparedStatement deletePstmt = con.prepareStatement(deleteQuery);
            deletePstmt.setString(1, appointmentId);
            deletePstmt.executeUpdate();
        }
        
        con.close();
        return rowsInserted;
    }
    
    public DefaultTableModel getAppointmentRecords() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{
            "Patient_ID", "Appointment_ID", "Date", "Time", "Reason", 
            "DoctorName", "Clinic", "BillingStatus"
        }, 0);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dataConnect, username, password);
            String sql = "SELECT Patient_ID,Appointment_ID , Date, Time, DoctorName, Clinic, Reason, BillingStatus FROM appointment_records WHERE Patient_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userID);
            ResultSet rs = pstmt.executeQuery();

//            DefaultTableModel model = (DefaultTableModel)tbl_appointmentRecords.getModel();
//            model.setRowCount(0);

            while (rs.next()) {
//                model.addRow(new Object[]{
//                    rs.getString("Patient_ID"),
//                    rs.getString("Appointment_ID"),
//                    rs.getString("Date"),
//                    rs.getString("Time"),
//                    rs.getString("Reason"),
//                    rs.getString("DoctorName"),
//                    rs.getString("Clinic"),
//                    rs.getString("BillingStatus")
                  model.addRow(new Object[]{
                rs.getString("Patient_ID"),
                rs.getString("Appointment_ID"),
                rs.getString("Date"),
                rs.getString("Time"),
                rs.getString("Reason"),
                rs.getString("DoctorName"),
                rs.getString("Clinic"),
                rs.getString("BillingStatus")
                });
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return model;
    }
}
