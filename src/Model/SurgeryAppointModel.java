/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import project.Connection_Provider;


/**
 *
 * @author Cheong Wei San
 */
public class SurgeryAppointModel {
    public boolean registerBooking(String id, String patientName, String phoneNumber, String nurseID, String nurseName, 
                                   String docId, String docName, String anestheId, String anestheName, 
                                   String facilityType, String facilityName, String sur_location, String sur_roomId, 
                                   Date date, Time time) {
        Connection con = Connection_Provider.getCon();

        if (id.isEmpty() || patientName.isEmpty() || phoneNumber.isEmpty() || nurseID.isEmpty() || nurseName.isEmpty() ||
            docId.isEmpty() || docName.isEmpty() || anestheId.isEmpty() || anestheName.isEmpty() ||
            facilityType.isEmpty() || facilityName.isEmpty() || sur_location.isEmpty() || sur_roomId.isEmpty()) {
            return false; 
        }

        String insertQuery = "INSERT INTO customer (id, name, phone, nurseId, nurseName, docId, docName, anestheId, anestheName, facilitiesType, facilitiesName, location, roomId, date, time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
            ps.setString(1, id);
            ps.setString(2, patientName);
            ps.setString(3, phoneNumber);
            ps.setString(4, nurseID);
            ps.setString(5, nurseName);
            ps.setString(6, docId);
            ps.setString(7, docName);
            ps.setString(8, anestheId);
            ps.setString(9, anestheName);
            ps.setString(10, facilityType);
            ps.setString(11, facilityName);
            ps.setString(12, sur_location);
            ps.setString(13, sur_roomId);
            ps.setDate(14, date);
            ps.setTime(15, time);

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace(); 
            return false; 
        }
    }
    
  
}
