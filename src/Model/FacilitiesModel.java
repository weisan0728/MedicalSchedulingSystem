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
public class FacilitiesModel {

    public boolean registerFacility(String type, String name, String location, String status) {
        try (Connection con = Connection_Provider.getCon();
             PreparedStatement ps = con.prepareStatement(
                "INSERT INTO facilities (type, name, location, status) VALUES (?, ?, ?, ?)")) {
            ps.setString(1, type);
            ps.setString(2, name);
            ps.setString(3, location);
            ps.setString(4, status);

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void updateFacilityStatus(String type, String name, String status) throws SQLException {
        String query = "UPDATE facilities SET status = ? WHERE type = ? AND name = ?";
        try (Connection con = Connection_Provider.getCon();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, status);
            ps.setString(2, type);
            ps.setString(3, name);
            ps.executeUpdate();
        }
    }

    public ResultSet getFacilitiesByType(String type) throws SQLException {
        String query = "SELECT name FROM facilities WHERE type = ? AND status='Available'";
        Connection con = Connection_Provider.getCon();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, type);
        return ps.executeQuery();
    }

    public ResultSet getAllFacilities() throws SQLException {
        String query = "SELECT * FROM facilities";
        Connection con = Connection_Provider.getCon();
        Statement stmt = con.createStatement();
        return stmt.executeQuery(query);
    }

    public boolean deleteFacility(String name) throws SQLException {
        try (Connection con = Connection_Provider.getCon();
             PreparedStatement ps = con.prepareStatement("DELETE FROM facilities WHERE name = ?")) {
            ps.setString(1, name);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
    }

}
