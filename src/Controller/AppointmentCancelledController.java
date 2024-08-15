/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.AppointmentCancelledModel;
import javax.swing.JTable;

/**
 *
 * @author Cheong Wei San
 */
public class AppointmentCancelledController {
    private AppointmentCancelledModel model;
    private JTable tbl_appointmentCancelled;

    public AppointmentCancelledController(JTable tbl_appointmentCancelled, String userID) {
        this.tbl_appointmentCancelled = tbl_appointmentCancelled;
        this.model = new AppointmentCancelledModel(userID);
    }

    public void handleLoadAppointmentCancelled() {
        tbl_appointmentCancelled.setModel(model.getAppointmentCancelled());
    }
}
