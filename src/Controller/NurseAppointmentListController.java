/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ApproveAppointmentModel;
import View.doctorHome1;
import View.nurseHome;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cheong Wei San
 */
public class NurseAppointmentListController {

    private ApproveAppointmentModel model;
    private nurseHome view;

    public NurseAppointmentListController(ApproveAppointmentModel model, nurseHome view) {
        this.model = model;
        this.view = view;
        initListeners();
    }
    

    public void initView() {
        view.setVisible(true);
    }

    private void initListeners() {
        view.jButton4.addActionListener(e -> searchAppointmentList());
        view.refreshButton.addActionListener(e -> refreshAppointmentList());
        view.updateStatusButton.addActionListener(e -> {
            try {
                updateStatus();
            } catch (SQLException ex) {
                Logger.getLogger(NurseAppointmentListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        view.deleteAppointListButton.addActionListener(e -> {
            try {
                deleteAppointment();
            } catch (SQLException ex) {
                Logger.getLogger(NurseAppointmentListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void searchAppointmentList() {
        String patientIDorAppointmentID = view.jTextField15.getText();
        ResultSet rs = model.getApprovedAppointments(patientIDorAppointmentID);
        updateTable(rs);
    }


    private void refreshAppointmentList() {
        searchAppointmentList(); 
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

    private void updateStatus() throws SQLException {
    int selectedRow = view.jTable4.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please select a row to update.");
                return;
            }

            String appointmentId = view.jTable4.getValueAt(selectedRow, 0).toString();
            String patientId = view.jTable4.getValueAt(selectedRow, 1).toString();

            int confirmApprove = JOptionPane.showConfirmDialog(null, "Are you sure to update the status of appointment ID: " + appointmentId + "?", "Confirm Update", JOptionPane.YES_NO_OPTION);

            if (confirmApprove == JOptionPane.YES_OPTION) {
                String doctorName = JOptionPane.showInputDialog(null, "Enter the doctor name: ", "Doctor Name", JOptionPane.PLAIN_MESSAGE);

                if (doctorName == null || doctorName.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Doctor name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean success = model.updateAppointment(appointmentId, patientId, doctorName);

                if (success) {
                    DefaultTableModel tableModel = (DefaultTableModel) view.jTable4.getModel();
                    tableModel.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(null, "Appointment status updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update appointment status.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
    }

    private void deleteAppointment() throws SQLException {

    int selectedRow = view.jTable4.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to cancel.");
            return;
        }

        String appointmentId = view.jTable4.getValueAt(selectedRow, 0).toString();
        String patientId = view.jTable4.getValueAt(selectedRow, 1).toString();

        int confirmReject = JOptionPane.showConfirmDialog(null, "Are you sure to delete request ID: " + appointmentId + "?", "Confirm Cancellation", JOptionPane.YES_NO_OPTION);

        if (confirmReject == JOptionPane.YES_OPTION) {
            String cancellationReason = JOptionPane.showInputDialog(null, "Enter the reason: ", "Cancellation Reason", JOptionPane.PLAIN_MESSAGE);

            if (cancellationReason == null || cancellationReason.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cancellation reason cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean success = model.cancelAppointment(appointmentId, patientId, cancellationReason);

            if (success) {
                DefaultTableModel tableModel = (DefaultTableModel) view.jTable4.getModel();
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(null, "Appointment rejected.");
            } else {
                JOptionPane.showMessageDialog(null, "Error rejecting appointment.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
   

