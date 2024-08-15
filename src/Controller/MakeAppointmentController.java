package Controller;

import Model.MakeAppointmentModel;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MakeAppointmentController {
    
    private MakeAppointmentModel model;

    public MakeAppointmentController() {
        this.model = new MakeAppointmentModel();
    }

    public void handleMakeAppointmentActionPerformed(ActionEvent evt,
        String userID,
        JTextField appointmentNameField,
        JTextField appointmentPhoneNumField,
        JDateChooser appointmentDateChooser,
        JTextField appointmentTimeField,
        JComboBox<String> appointmentClinicComboBox,
        JTextField appointmentReasonArea,
        JTextField appointmentSpecialNeedsArea) {
        
        if (userID == null || userID.isEmpty()) {
            JOptionPane.showMessageDialog(null, "User ID is not set. Please log in again.");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(appointmentDateChooser.getDate());
        
        model.setUserID(userID);
        model.setAppointmentDetails(
            appointmentNameField.getText(),
            appointmentPhoneNumField.getText(),
            date,
            appointmentTimeField.getText(),
            appointmentClinicComboBox.getSelectedItem().toString(),
            appointmentReasonArea.getText(),
            appointmentSpecialNeedsArea.getText()
        );
        
        if (model.makeAppointment()) {
            JOptionPane.showMessageDialog(null, "Appointment successfully requested!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to request appointment. Please try again.");
        }
    }
}
