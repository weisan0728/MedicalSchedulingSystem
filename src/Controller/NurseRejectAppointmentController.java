/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.RejectAppointmentModel;
import View.nurseHome;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Cheong Wei San
 */
public class NurseRejectAppointmentController {
    private nurseHome view;
    private RejectAppointmentModel rejectmodel;

    public NurseRejectAppointmentController(RejectAppointmentModel rejectmodel, nurseHome view) {
        this.view = view;
        this.rejectmodel = rejectmodel;
    }

    public void searchReject(){
    String patientIDorRejectID = view.jTextField16.getText();

        System.out.println("Search Text: " + patientIDorRejectID);

        ResultSet rs = rejectmodel.getRejectedAppointments(patientIDorRejectID);
        DefaultTableModel model = (DefaultTableModel) view.jTable5.getModel();
        model.setRowCount(0);

        try {

            if (!rs.isBeforeFirst()) {
                System.out.println("No data found");
            }

            while (rs.next()) {

                System.out.println("Adding Row: " + rs.getString("Request_ID") + ", " + rs.getString("Patient_ID") + ", " + rs.getString("Name") +", " + rs.getString("PhoneNum") + ", "  + rs.getString("RejectionDate") + ", " + rs.getString("AppointmentRequestedDate") + ", " + rs.getString("AppointmentRequestedTime") + ", " + rs.getString("Clinic")+","+ rs.getString("RejectionReason"));

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)});
            }

            rs.close();
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }
    
    

    }

