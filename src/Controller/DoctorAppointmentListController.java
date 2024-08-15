/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ApproveAppointmentModel;
import View.doctorHome1;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cheong Wei San
 */
public class DoctorAppointmentListController {
    private doctorHome1 view;
    private ApproveAppointmentModel model;

    public DoctorAppointmentListController(ApproveAppointmentModel model, doctorHome1 view) {
        this.view = view;
        this.model = model;
    }
    
    public void initView() {
        view.setVisible(true);
    }

    private void initListeners() {
        view.searchBtn.addActionListener(e -> searchAppointmentList());
        view.clearBtn.addActionListener(e -> clearAppointmentList());
        
    }

    public void searchAppointmentList() {
        String patientID = view.jTextField15.getText();
        ResultSet rs = model.getApprovedAppointments(patientID);
        updateTable(rs);
    }
    
    private void updateTable(ResultSet rs) {
        DefaultTableModel tableModel = (DefaultTableModel) view.jTable4.getModel();
        tableModel.setRowCount(0);

        try {
            if (!rs.isBeforeFirst()) {
                System.out.println("No data found");
            }

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getString("Appointment_ID"),
                    rs.getString("Patient_ID"),
                    rs.getString("Name"),
                    rs.getString("PhoneNum"),
                    rs.getString("ApproveDate"),
                    rs.getString("AppointmentDate"),
                    rs.getString("AppointmentTime"),
                    rs.getString("Clinic"),
                    rs.getString("reason")
                });
            }

            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }
    
    public void clearAppointmentList(){
        view.jTextField15.setText("");
        ResultSet rs = model.getApprovedAppointments("");  
        updateTable(rs); 
    }
}
