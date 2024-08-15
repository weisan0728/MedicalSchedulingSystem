/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.UserModel;
import View.doctorHome1;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Nah Yi
 */
public class DoctorUserController {
    private doctorHome1 view;
    private UserModel model;

    public DoctorUserController(UserModel model, doctorHome1 view) {
        this.view = view;
        this.model = model;
        initView();
        initController();
    }
    
   

    public void initView() {
        view.setVisible(true);
    }
    
    private void initController() {
        view.searchField.addActionListener(e -> searchUser());
        view.clear.addActionListener(e -> clearUser());
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
    
    
   public void clearUser(){
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
