/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.UserModel;
import View.nurseHome;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import javax.swing.event.MouseInputAdapter;
/**
 *
 * @author Cheong Wei San
 */
public class NurseUserController {
    
    private UserModel model;
    private nurseHome view;


    public NurseUserController(UserModel model, nurseHome view) {
        this.model = model;
        this.view = view;
        initView();
        initController();
    }

    private void initView() {
        view.setVisible(true);
    }

    private void initController() {
        view.searchField.addActionListener(e -> searchUser());
        view.clear.addActionListener(e -> clearUser());

        view.userTable.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {  // Detect double click
                    deleteUser();
                }
            }
        });
    }

    public void searchUser() {
    String idOrPhoneNum = view.jTextField1.getText();
    try (ResultSet rs = model.searchUser(idOrPhoneNum)) {
        DefaultTableModel tableModel = (DefaultTableModel) view.userTable.getModel();
        tableModel.setRowCount(0);  
        
        while (rs.next()) {
            tableModel.addRow(new Object[]{
                rs.getString("id"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("ic"),
                rs.getString("gender"),
                rs.getString("phoneNum"),
                rs.getString("email"),
                rs.getString("address"),
                rs.getString("password")
            });
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(view, "Error fetching user data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}


    public void deleteUser() {
        int selectedRowIndex = view.userTable.getSelectedRow();
        if (selectedRowIndex == -1) {
            JOptionPane.showMessageDialog(view, "Please select a row to delete.");
            return;
        }

        String id = (String) view.userTable.getValueAt(selectedRowIndex, 0);
        int confirmDelete = JOptionPane.showConfirmDialog(view, "Do you want to delete ID " + id + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirmDelete == JOptionPane.YES_OPTION) {
            try {
                boolean success = model.deleteUser(id);
                if (success) {
                    JOptionPane.showMessageDialog(view, "User deleted successfully.");
                    DefaultTableModel tableModel = (DefaultTableModel) view.userTable.getModel();
                    tableModel.removeRow(selectedRowIndex);
                } else {
                    JOptionPane.showMessageDialog(view, "Failed to delete user.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(view, "Error deleting user: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    public void clearUser() {
        view.jTextField1.setText("");
        try (ResultSet rs = model.getAllUsers()) { 
        DefaultTableModel tableModel = (DefaultTableModel) view.userTable.getModel();
        tableModel.setRowCount(0); 
       
        while (rs.next()) {
            tableModel.addRow(new Object[]{
                rs.getString("id"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("ic"),
                rs.getString("gender"),
                rs.getString("phoneNum"),
                rs.getString("email"),
                rs.getString("address"),
                rs.getString("password")
            });
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(view, "Error fetching user data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } 
   }
    }
