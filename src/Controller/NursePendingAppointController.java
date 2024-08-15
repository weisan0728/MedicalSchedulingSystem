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

/**
 *
 * @author Cheong Wei San
 */
public class NursePendingAppointController {

    private ApproveAppointmentModel model;
    private nurseHome view;


    public NursePendingAppointController(ApproveAppointmentModel model, nurseHome view) {
        this.model = model;
        this.view = view;

        // Ensure listeners are added only once
        this.view.jButton2.addActionListener(e -> searchPendingAppoint());
        this.view.approveButton.addActionListener(e -> approvePending());
        this.view.rejectButton.addActionListener(e -> rejectPending());
    }

    public void initView() {
        view.setVisible(true);
    }

    public void searchPendingAppoint() {
        String patientIDorRequestedID = view.jTextField14.getText();

        System.out.println("Search Text: " + patientIDorRequestedID);

        ResultSet rs = model.getPendingAppointments(patientIDorRequestedID);
        DefaultTableModel tableModel = (DefaultTableModel) view.jTable3.getModel();
        tableModel.setRowCount(0);

        try {
            if (rs != null && rs.isBeforeFirst()) {
                while (rs.next()) {
                    tableModel.addRow(new Object[]{
                        rs.getString("request_id"),
                        rs.getString("Patient_ID"),
                        rs.getString("Name"),
                        rs.getString("PhoneNum"),
                        rs.getString("AppointmentDate"),
                        rs.getString("AppointmentTime"),
                        rs.getString("Clinic"),
                        rs.getString("Reason"),
                        rs.getString("SpecialNeeds")
                    });
                }
            } else {
                System.out.println("No data found");
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }

    public void approvePending() {
        int selectedRow = view.jTable3.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to approve.");
            return;
        }

        String appointmentId = view.jTable3.getValueAt(selectedRow, 0).toString();
        String patientId = view.jTable3.getValueAt(selectedRow, 1).toString();

        int confirmApprove = JOptionPane.showConfirmDialog(null, "Are you sure to approve request ID: " + appointmentId + "?", "Confirm Approve", JOptionPane.YES_NO_OPTION);

        if (confirmApprove == JOptionPane.YES_OPTION) {
            boolean success = model.approveAppointment(Integer.parseInt(appointmentId), patientId);

            if (success) {
                DefaultTableModel tableModel = (DefaultTableModel) view.jTable3.getModel();
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(null, "Records approved.");
            } else {
                JOptionPane.showMessageDialog(null, "Error approving appointment.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void rejectPending() {
        int selectedRow = view.jTable3.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to reject.");
            return;
        }

        String appointmentId = view.jTable3.getValueAt(selectedRow, 0).toString();
        String patientId = view.jTable3.getValueAt(selectedRow, 1).toString();

        int confirmReject = JOptionPane.showConfirmDialog(null, "Are you sure to reject request ID: " + appointmentId + "?", "Confirm Rejection", JOptionPane.YES_NO_OPTION);

        if (confirmReject == JOptionPane.YES_OPTION) {
            String rejectionReason = JOptionPane.showInputDialog(null, "Enter the reason for rejection: ", "Rejection Reason", JOptionPane.PLAIN_MESSAGE);

            if (rejectionReason == null || rejectionReason.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Rejection reason cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean success = model.rejectAppointment(Integer.parseInt(appointmentId), patientId, rejectionReason);

            if (success) {
                DefaultTableModel tableModel = (DefaultTableModel) view.jTable3.getModel();
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(null, "Appointment rejected.");
            } else {
                JOptionPane.showMessageDialog(null, "Error rejecting appointment.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
