/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.AppointmentRecordsModel;
import javax.swing.JTable;

/**
 *
 * @author Cheong Wei San
 */
public class AppointmentRecordsController {
    private AppointmentRecordsModel model;
    private JTable tbl_appointmentRecords;

    public AppointmentRecordsController(JTable tbl_appointmentRecords, String userID) {
        this.tbl_appointmentRecords = tbl_appointmentRecords;
        this.model = new AppointmentRecordsModel(userID);
    }

    public void handleLoadAppointmentRecords() {
        tbl_appointmentRecords.setModel(model.getAppointmentRecords());
    }
}
