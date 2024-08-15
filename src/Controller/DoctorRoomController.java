/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.RoomManagementModel;
import View.doctorHome1;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cheong Wei San
 */
public class DoctorRoomController {
    private RoomManagementModel model;
    private doctorHome1 view;

    public DoctorRoomController(RoomManagementModel model, doctorHome1 view) {
        this.model = model;
        this.view = view;
    }
    
    private void initView() {
        view.setVisible(true);
        loadRoomData();
    }

//    private void initController() {
//
//    }
    private void loadRoomData() {
        try {
            ResultSet rs = model.getAllRooms();
            DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Room No", "Location", "Status"}, 0);
            view.jTable2.setModel(tableModel);

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getString("roomNo"),
                    rs.getString("location"),
                    rs.getString("status")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Error loading room data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    public void populateRoomID() {
        view.roomComboBox.removeAllItems();
        String sur_location = (String) view.sur_locationComboBox.getSelectedItem();

        if (sur_location == null) {
            return;
        }

        try {
            ResultSet rs = model.getRoomByLocation(sur_location);
            while (rs.next()) {
                view.roomComboBox.addItem(rs.getString("roomNo"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error populating Room ID: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
