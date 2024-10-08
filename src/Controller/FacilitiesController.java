/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.FacilitiesModel;
import View.doctorHome1;
import View.nurseHome;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

/**
 *
 * @author Cheong Wei San
 */
public class FacilitiesController {
    
    private FacilitiesModel model;
    private nurseHome view;
    

    public FacilitiesController(FacilitiesModel model, nurseHome view) {
        this.model = model;
        this.view = view;
        initView();
        initController();
    }

    private void initView() {
        view.setVisible(true);
        loadFacilitiesData();
    }

    private void initController() {
    view.jButton8.addActionListener(e -> addButton());
    view.jButton11.addActionListener(e -> deleteButton());
    view.f_typeComboBox.addActionListener(e -> populateFacilitiesNames());
    view.jTable6.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 1) {
                updateFacilitiesStatus(e);
            }
        }
    });
    }

    private void loadFacilitiesData() {
        try {
        ResultSet rs = model.getAllFacilities();
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Type", "Name", "Location", "Status"}, 0);
        view.jTable6.setModel(tableModel);

        while (rs.next()) {
            tableModel.addRow(new Object[]{
                rs.getString("type"),
                rs.getString("name"),
                rs.getString("location"),
                rs.getString("status")
            });
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(view, "Error loading facilities data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    }

    public void addButton() {
    System.out.println("addButton called");
    String type = (String) view.i_typeComboBox.getSelectedItem();
    String name = view.i_nameField.getText();
    String location = (String) view.i_locationComboBox.getSelectedItem();
    String status = (String) view.i_statusComboBox.getSelectedItem();
    
    if (type.isEmpty() ||name.isEmpty() || location.isEmpty()|| status.isEmpty()) {
        JOptionPane.showMessageDialog(view, "All fields must be filled out.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (model.registerFacility(type, name, location, status)) {
        System.out.println("Facility added successfully");
        JOptionPane.showMessageDialog(view, "Facility added successfully.");
        loadFacilitiesData();
    } else {
        System.out.println("Error adding facility");
        JOptionPane.showMessageDialog(view, "Error adding facility.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    public void deleteButton() {
        int selectedRowIndex = view.jTable6.getSelectedRow();

        if (selectedRowIndex == -1) {
            JOptionPane.showMessageDialog(view, "Please select a row to delete.");
            return;
        }

        String name = (String) view.jTable6.getValueAt(selectedRowIndex, 1);

        int confirmDelete = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete facility : " + name + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirmDelete == JOptionPane.YES_OPTION) {
            try {
                boolean success = model.deleteFacility(name);
                if (success) {
                    JOptionPane.showMessageDialog(view, "Facility deleted successfully.");
                    DefaultTableModel tableModel = (DefaultTableModel) view.jTable6.getModel();
                    tableModel.removeRow(selectedRowIndex);
                } else {
                    JOptionPane.showMessageDialog(view, "Failed to delete facility.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(view, "Error deleting facility: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
        
    }

    public void populateFacilitiesNames() {
        view.f_nameComboBox.removeAllItems();
        String facilitiesType = (String) view.f_typeComboBox.getSelectedItem();

        if (facilitiesType == null) {
            return;
        }

        try {
            ResultSet rs = model.getFacilitiesByType(facilitiesType);
            while (rs.next()) {
                view.f_nameComboBox.addItem(rs.getString("name"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error populating facility names: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public void updateFacilitiesStatus(MouseEvent evt) {
        int selectedRow = view.jTable6.getSelectedRow();
        DefaultTableModel tableModel = (DefaultTableModel) view.jTable6.getModel();

        String facilitiesType = tableModel.getValueAt(selectedRow, 0).toString();
        String facilitiesName = tableModel.getValueAt(selectedRow, 1).toString();
        String location = tableModel.getValueAt(selectedRow, 2).toString();
        String currentStatus = tableModel.getValueAt(selectedRow, 3).toString();
        
        String newStatus = currentStatus.equals("Available") ? "Unavailable" : "Available";

        int choice = JOptionPane.showConfirmDialog(view,
                "Do you want to update the status of " + facilitiesName + " to " + newStatus + "?",
                "Update Facilities Status",
                JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            try {
                model.updateFacilityStatus(facilitiesType, facilitiesName, newStatus, location);
                JOptionPane.showMessageDialog(view, "Facilities status updated successfully!");
                tableModel.setValueAt(newStatus, selectedRow, 3);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(view, "Error updating facilities status: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}
