/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Cheong Wei San
 */
public class DoctorSurgeryAppointmentModel {
    private static final String username = "root";
    private static final String password = "murberry02";
    private static final String dataConnect = "jdbc:mysql://localhost:3306/hospital";
    
    private static final String PHONE_NUMBER_REGEX = "^\\d{3}-\\d{7}$";
    
    private String patientName;
    private String patientID;
    private String patientPhone;
    private String docName;
    private String docID;
    private String nurseName;
    private String nurseID;
    private String anestheName;
    private String anestheID;
    private String roomID;
    private String location;
    private String equipmentType;
    private String equipmentName;
    private Date date;
    private java.sql.Time time;

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    public void setNurseID(String nurseID) {
        this.nurseID = nurseID;
    }

    public void setAnestheName(String anestheName) {
        this.anestheName = anestheName;
    }

    public void setAnestheID(String anestheID) {
        this.anestheID = anestheID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    
    public boolean surRegistration() {
        if (!patientPhone.matches(PHONE_NUMBER_REGEX)) {
            JOptionPane.showMessageDialog(null, "Please enter a valid phone number with format XXX-XXXXXXX.");
            return false;
        }

        SurgeryAppointModel surgeryAppointModel = new SurgeryAppointModel();
        boolean isRegistered = surgeryAppointModel.registerBooking(patientID, patientName, patientPhone, nurseID, nurseName, 
            docID, docName, anestheID, anestheName, 
            equipmentType, equipmentName, location, roomID, (java.sql.Date) date, time
        );

        if (isRegistered) {
            JOptionPane.showMessageDialog(null, "Surgery Registered Successfully!");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Error making surgery registration. Please check your details.");
            return false;
        }
    }

}
