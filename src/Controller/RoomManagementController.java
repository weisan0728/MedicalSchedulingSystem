/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.FacilitiesModel;
import Model.RoomManagementModel;
import View.doctorHome1;
import View.nurseHome;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author Cheong Wei San
 */
public class RoomManagementController {

    private RoomManagementModel model;
    private nurseHome view;

    public RoomManagementController(RoomManagementModel model, nurseHome view) {
        this.model = model;
        this.view = view;
        initView();
        initController();
    }

    private void initView() {
        view.setVisible(true);
        loadRoomData();
    }

    private void initController() {
        view.add.addActionListener(e -> addButton());
        view.delete.addActionListener(e -> {
            try {
                deleteButton();
            } catch (SQLException ex) {
                Logger.getLogger(RoomManagementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        view.jTable2.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 1) {
                try {
                    updateRoomStatus(e);
                } catch (SQLException ex) {
                    Logger.getLogger(RoomManagementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    });
    }

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

    public void addButton() {
        String roomNo = view.roomNumberField.getText();
        String location = (String) view.locationComboBox.getSelectedItem();
        String status = (String) view.statusComboBox.getSelectedItem();
        
        if (roomNo.isEmpty() || location.isEmpty() || status.isEmpty()) {
            JOptionPane.showMessageDialog(view, "All fields must be filled out.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (model.registerRoom(roomNo, location, status)) {
            JOptionPane.showMessageDialog(view, "Room added successfully.");
            loadRoomData();
        } else {
            JOptionPane.showMessageDialog(view, "Error adding room.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteButton() throws SQLException {
        int selectedRowIndex = view.jTable2.getSelectedRow();

        if (selectedRowIndex == -1) {
            JOptionPane.showMessageDialog(view, "Please select a row to delete.");
            return;
        }

        String roomNo = (String) view.jTable2.getValueAt(selectedRowIndex, 0);

        int confirmDelete = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete room : " + roomNo + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirmDelete == JOptionPane.YES_OPTION) {
            boolean success = model.deleteRoom(roomNo);
            if (success) {
                JOptionPane.showMessageDialog(view, "Room deleted successfully.");
                DefaultTableModel tableModel = (DefaultTableModel) view.jTable2.getModel();
                tableModel.removeRow(selectedRowIndex);
            } else {
                JOptionPane.showMessageDialog(view, "Failed to delete room.", "Error", JOptionPane.ERROR_MESSAGE);
            }
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

    public void updateRoomStatus(MouseEvent evt) throws SQLException {
        int selectedRow = view.jTable2.getSelectedRow();
        DefaultTableModel tableModel = (DefaultTableModel) view.jTable2.getModel();

        String roomNo = tableModel.getValueAt(selectedRow, 0).toString();
        String currentStatus = tableModel.getValueAt(selectedRow, 2).toString();

        String newStatus = currentStatus.equals("Unoccupied") ? "Occupied" : "Unoccupied";

        int choice = JOptionPane.showConfirmDialog(view,
                "Do you want to update the status of room " + roomNo + " to " + newStatus + "?",
                "Update Room Status",
                JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            model.updateRoomStatus(roomNo, newStatus);
            JOptionPane.showMessageDialog(view, "Room status updated successfully!");
            tableModel.setValueAt(newStatus, selectedRow, 2);
        }
    }

}
