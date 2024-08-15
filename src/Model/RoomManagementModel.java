/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


import java.sql.*;
import project.Connection_Provider;


/**
 *
 * @author Cheong Wei San
 */
public class RoomManagementModel {
    
    public ResultSet getRoomByLocation(String roomNo) throws SQLException {
        String query = "SELECT roomNo FROM room WHERE location = ? AND status='Unoccupied'";
        java.sql.Connection con = Connection_Provider.getCon();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, roomNo);
        return ps.executeQuery();
    }

    public boolean registerRoom(String roomNo, String location, String status) {
        try (java.sql.Connection con = Connection_Provider.getCon();
             PreparedStatement ps = con.prepareStatement(
                "INSERT INTO room (roomNo, location, status) VALUES (?, ?, ?)")) {
            ps.setString(1, roomNo);
            ps.setString(2, location);
            ps.setString(3, status);

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void updateRoomStatus(String roomNo, String status) throws SQLException {
        String query = "UPDATE room SET status = ? WHERE roomNo = ?";
        try (java.sql.Connection con = Connection_Provider.getCon();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, status);
            ps.setString(2, roomNo);
            ps.executeUpdate();
        }
    }

    public ResultSet getAllRooms() throws SQLException {
        String query = "SELECT * FROM room";
        java.sql.Connection con = Connection_Provider.getCon();
        Statement stmt = con.createStatement();
        return stmt.executeQuery(query);
    }

    public boolean deleteRoom(String roomNo) throws SQLException {
        try (java.sql.Connection con = Connection_Provider.getCon();
             PreparedStatement ps = con.prepareStatement("DELETE FROM room WHERE roomNo = ?")) {
            ps.setString(1, roomNo);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
    }

}
