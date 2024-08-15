/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.AppointmentRejectedModel;
import javax.swing.JTable;

/**
 *
 * @author Cheong Wei San
 */
public class AppointmentRejectedController {
    private AppointmentRejectedModel model;
    private JTable tbl_appointmentRejected;

    public AppointmentRejectedController(JTable tbl_appointmentRejected, String userID) {
        this.tbl_appointmentRejected = tbl_appointmentRejected;
        this.model = new AppointmentRejectedModel(userID);
    }

    public void handleLoadAppointmentRejected() {
        tbl_appointmentRejected.setModel(model.getAppointmentRejected());
    }
}
