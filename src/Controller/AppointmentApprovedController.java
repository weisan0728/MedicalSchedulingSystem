/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.AppointmentApprovedModel;
import javax.swing.JTable;

/**
 *
 * @author Cheong Wei San
 */
public class AppointmentApprovedController {
    private AppointmentApprovedModel model;
    private JTable tbl_appointmentApproved;

    public AppointmentApprovedController(JTable tbl_appointmentApproved, String userID) {
        this.tbl_appointmentApproved = tbl_appointmentApproved;
        this.model = new AppointmentApprovedModel(userID);
    }

    public void handleLoadAppointmentApproved() {
        tbl_appointmentApproved.setModel(model.getAppointmentApproved());
    }
}
