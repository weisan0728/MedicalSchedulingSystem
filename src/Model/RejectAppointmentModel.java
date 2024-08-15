/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.ResultSet;
import project.Connection_Provider;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Cheong Wei San
 */
public class RejectAppointmentModel {
    public ResultSet getRejectedAppointments(String patientIDorRejectID) {
    String query = "SELECT * FROM appointment_rejected WHERE Patient_ID LIKE ? OR Request_ID LIKE ?";
        java.sql.Connection con = Connection_Provider.getCon();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "%" + patientIDorRejectID + "%");
            ps.setString(2, "%" + patientIDorRejectID + "%");
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
}

}
