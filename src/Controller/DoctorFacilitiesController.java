/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.FacilitiesModel;
import View.doctorHome1;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cheong Wei San
 */
public class DoctorFacilitiesController {
    private FacilitiesModel model;
    private doctorHome1 view;
    

    public DoctorFacilitiesController(FacilitiesModel model, doctorHome1 view) {
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
//    view.jButton8.addActionListener(e -> addButton());
//    view.jButton11.addActionListener(e -> deleteButton());
//    view.f_typeComboBox.addActionListener(e -> populateFacilitiesNames());
//    view.jTable6.addMouseListener(new MouseAdapter() {
//        @Override
//        public void mouseClicked(MouseEvent e) {
//            if (e.getClickCount() == 2) {
//                updateFacilitiesStatus(e);
//            }
//        }
//    });
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
}

//    public void updateFacilitiesStatus(MouseEvent evt) {
//        int selectedRow = view.jTable6.getSelectedRow();
//        DefaultTableModel tableModel = (DefaultTableModel) view.jTable6.getModel();
//
//        String facilitiesType = tableModel.getValueAt(selectedRow, 0).toString();
//        String facilitiesName = tableModel.getValueAt(selectedRow, 1).toString();
//        String currentStatus = tableModel.getValueAt(selectedRow, 2).toString();
//
//        String newStatus = currentStatus.equals("Available") ? "Unavailable" : "Available";
//
//        int choice = JOptionPane.showConfirmDialog(view,
//                "Do you want to update the status of " + facilitiesName + " to " + newStatus + "?",
//                "Update Facilities Status",
//                JOptionPane.YES_NO_OPTION);
//
//        if (choice == JOptionPane.YES_OPTION) {
//            try {
//                model.updateFacilityStatus(facilitiesType, facilitiesName, newStatus);
//                JOptionPane.showMessageDialog(view, "Facilities status updated successfully!");
//                tableModel.setValueAt(newStatus, selectedRow, 2);
//            } catch (SQLException e) {
//                JOptionPane.showMessageDialog(view, "Error updating facilities status: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                e.printStackTrace();
//            }
//        }
//    }

