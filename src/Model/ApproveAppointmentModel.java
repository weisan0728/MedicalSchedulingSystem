/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import project.Connection_Provider;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import project.Select;


/**
 *
 * @author Cheong Wei San
 */
public class ApproveAppointmentModel {
    public boolean approveAppointment(int appointmentId, String patientId) {
        String insertQuery = "INSERT INTO appointment_approved (Appointment_ID, Patient_ID, Name, PhoneNum, ApproveDate, AppointmentDate, AppointmentTime, Clinic, reason) "
                + "SELECT request_id, Patient_ID, Name, PhoneNum, CURDATE(), AppointmentDate, AppointmentTime, Clinic, Reason "
                + "FROM make_appointment WHERE request_id=? AND Patient_ID=?";
        String deleteQuery = "DELETE FROM make_appointment WHERE request_id = ?";
        java.sql.Connection con = Connection_Provider.getCon();

        try (PreparedStatement insertPs = con.prepareStatement(insertQuery);
             PreparedStatement deletePstmt = con.prepareStatement(deleteQuery)) {

            insertPs.setInt(1, appointmentId);
            insertPs.setString(2, patientId);
            int rowsInserted = insertPs.executeUpdate();

            if (rowsInserted > 0) {
                deletePstmt.setInt(1, appointmentId);
                deletePstmt.executeUpdate();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet getPendingAppointments(String patientIDorRequestedID) {
        String query = "SELECT * FROM make_appointment WHERE Patient_ID LIKE ? OR request_id LIKE ?";
        java.sql.Connection con = Connection_Provider.getCon();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "%" + patientIDorRequestedID + "%");
            ps.setString(2, "%" + patientIDorRequestedID + "%");
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet getApprovedAppointments(String patientIDorAppointmentID) {
        String query = "SELECT * FROM appointment_approved WHERE Patient_ID LIKE ? OR Appointment_ID LIKE ?";
        java.sql.Connection con = Connection_Provider.getCon();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "%" + patientIDorAppointmentID + "%");
            ps.setString(2, "%" + patientIDorAppointmentID + "%");
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    public boolean rejectAppointment(int appointmentId, String patientId, String reason) {
        String insertQuery = "INSERT INTO appointment_rejected (Request_ID, Patient_ID, Name, PhoneNum, RejectionDate, AppointmentRequestedDate, AppointmentRequestedTime, Clinic, RejectionReason) "
                + "SELECT request_id, Patient_ID, Name, PhoneNum, CURDATE(), AppointmentDate, AppointmentTime, Clinic, ? "
                + "FROM make_appointment WHERE request_id=? AND Patient_ID=?";
        String deleteQuery = "DELETE FROM make_appointment WHERE request_id = ?";
        java.sql.Connection con = Connection_Provider.getCon();

        try (PreparedStatement insertPs = con.prepareStatement(insertQuery);
             PreparedStatement deletePstmt = con.prepareStatement(deleteQuery)) {

            insertPs.setString(1, reason);
            insertPs.setInt(2, appointmentId);
            insertPs.setString(3, patientId);
            int rowsInserted = insertPs.executeUpdate();

            if (rowsInserted > 0) {
                deletePstmt.setInt(1, appointmentId);
                deletePstmt.executeUpdate();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            return false;
        }
    }
    
    public boolean updateAppointment(String appointmentId, String patientId, String doctorName) throws SQLException {
        
            String insertQuery = "INSERT INTO appointment_records (Appointment_ID, Patient_ID, Date, Time, DoctorName, Clinic, Reason, BillingStatus) "
                    + "SELECT Appointment_ID, Patient_ID, AppointmentDate, AppointmentTime, ?, Clinic, Reason, 'Completed' "
                    + "FROM appointment_approved WHERE Appointment_ID=? AND Patient_ID=?";
            String deleteQuery = "DELETE FROM appointment_approved WHERE Appointment_ID = ?";
            java.sql.Connection con = Connection_Provider.getCon();
            
            try (PreparedStatement insertPs = con.prepareStatement(insertQuery);
             PreparedStatement deletePstmt = con.prepareStatement(deleteQuery)) {
                insertPs.setString(1, doctorName);
                insertPs.setString(2, appointmentId);
                insertPs.setString(3, patientId);
                int rowsInserted = insertPs.executeUpdate();

                
                if (rowsInserted > 0) {
                deletePstmt.setString(1, appointmentId);
                deletePstmt.executeUpdate();
                return true;
            } else {
                return false;
            }
            }catch (SQLException e) {
            e.printStackTrace(); 
            return false;
        }

        
    }

    public boolean cancelAppointment(String appointmentId, String patientId, String cancellationReason) throws SQLException {
        
            String insertQuery = "INSERT INTO appointment_cancelled "
                    + "(Patient_ID, Appointment_ID, CancelDate, AppointmentDate, AppointmentTime, Clinic, CancellationReason) "
                    + "SELECT Patient_ID, Appointment_ID, CURDATE(), AppointmentDate, AppointmentTime, Clinic, ? "
                    + "FROM appointment_approved WHERE Patient_ID = ? AND Appointment_ID = ?";
            String deleteQuery = "DELETE FROM appointment_approved WHERE Appointment_ID = ?";
            java.sql.Connection con = Connection_Provider.getCon();
            try (PreparedStatement insertPs = con.prepareStatement(insertQuery);
             PreparedStatement deletePstmt = con.prepareStatement(deleteQuery)) {
                insertPs.setString(1, cancellationReason);
                insertPs.setString(2, patientId);
                insertPs.setString(3, appointmentId);
                int rowsInserted = insertPs.executeUpdate();
//                insertPs.executeUpdate();
                
                if (rowsInserted > 0) {
                deletePstmt.setString(1, appointmentId);
                deletePstmt.executeUpdate();
                return true;
            } else {
                return false;
            }
            }catch (SQLException e) {
            e.printStackTrace(); 
            return false;
        }
        }
    
}
