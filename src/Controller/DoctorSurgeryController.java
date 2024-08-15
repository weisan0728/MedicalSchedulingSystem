/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DoctorSurgeryAppointmentModel;
import View.doctorHome1;
import com.toedter.calendar.JDateChooser;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import project.Select;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import project.Connection_Provider;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Nah Yi
 */
public class DoctorSurgeryController {
     private DoctorSurgeryAppointmentModel model;
    private doctorHome1 view;

    public DoctorSurgeryController(DoctorSurgeryAppointmentModel model, doctorHome1 view) {
        this.model = model;
        this.view = view;
    }

    public void initView() {
        view.setVisible(true);
    }

    public void bookRoomActionPerformed(JTextField patientIdField, JTextField patientNameField, JTextField phoneNumField, JTextField docIdField, JTextField docNameField, JTextField nurseIdField, JTextField nurseNameField, JTextField anestheNameField, JTextField anestheIdField, JComboBox<String> f_nameComboBox, JComboBox<String> f_typeComboBox, JComboBox<String> sur_locationComboBox, JComboBox<String> roomComboBox, JDateChooser jDateChooser1, JTextField timeField) {

    
    if (!validatePhoneNumber(phoneNumField.getText())) {
        JOptionPane.showMessageDialog(view, "Invalid Phone Number", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

   
    java.sql.Date date = null;
    if (jDateChooser1.getDate() != null) {
        date = new java.sql.Date(jDateChooser1.getDate().getTime());
    } else {
        JOptionPane.showMessageDialog(view, "Date cannot be null", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }


    model.setPatientID(patientIdField.getText());
    model.setPatientName(patientNameField.getText());
    model.setPatientPhone(phoneNumField.getText());
    model.setDocID(docIdField.getText());
    model.setDocName(docNameField.getText());
    model.setNurseID(nurseIdField.getText());
    model.setNurseName(nurseNameField.getText());
    model.setAnestheID(anestheIdField.getText());
    model.setAnestheName(anestheNameField.getText());
    model.setLocation(sur_locationComboBox.getSelectedItem().toString());
    model.setRoomID(roomComboBox.getSelectedItem().toString());
    model.setEquipmentType(f_typeComboBox.getSelectedItem().toString());
    model.setEquipmentName(f_nameComboBox.getSelectedItem().toString());
    model.setDate(date);

    // Process time field
    String timeInput = timeField.getText(); 
    java.sql.Time time = null;

    try {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        java.util.Date parsedDate = sdf.parse(timeInput);
        time = new java.sql.Time(parsedDate.getTime());
    } catch (ParseException ex) {
        JOptionPane.showMessageDialog(view, "Invalid time format. Please enter time in HH:mm:ss format.", "Error", JOptionPane.ERROR_MESSAGE);
        return; 
    }

    model.setTime(time);

    boolean success = model.surRegistration();

    if (success) {
        JOptionPane.showMessageDialog(view, "Surgery registration completed successfully.");
    } else {
        JOptionPane.showMessageDialog(view, "Failed to register surgery.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
    public void clear() {

    view.jDateChooser1.setDate(null); 
    view.docIdField.setText("");
    view.docNameField.setText("");
    view.anestheIdField.setText("");
    view.anestheNameField.setText("");
    view.nurseIdField.setText("");
    view.nurseNameField.setText("");
    view.timeField.setText("");
    view.patientNameField.setText("");
    view.patientIdField.setText("");
    view.patientIdField.setText("");
    view.phoneNumField.setText("");

    view.f_nameComboBox.removeAllItems();
    view.f_typeComboBox.setSelectedIndex(0); 
    view.sur_locationComboBox.setSelectedIndex(0); 
    view.roomComboBox.setSelectedIndex(0); 
    

    DefaultTableModel tableModel = (DefaultTableModel) view.jTable8.getModel();
    tableModel.setRowCount(0);

    view.setVisible(true);
}


    public void search() {
            
        String patientId = view.searchSAL.getText();
        String sur_id = view.searchSAL.getText();

        System.out.println("Search Text: " + patientId);

        ResultSet rs = Select.getData("SELECT * FROM customer WHERE id LIKE '%" + patientId + "%' OR sur_id LIKE '%" + sur_id + "%'");
        DefaultTableModel model = (DefaultTableModel) view.jTable8.getModel();
        model.setRowCount(0);

        try {
            if (!rs.isBeforeFirst()) {
                System.out.println("No data found");
            }

            while (rs.next()) {
                String sur_Id = rs.getString("sur_id"); 
                String id = rs.getString("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String nurseId = rs.getString("nurseId");
                String nurseName = rs.getString("nurseName");
                String docId = rs.getString("docId");
                String docName = rs.getString("docName");
                String anestheId = rs.getString("anestheId");
                String anestheName = rs.getString("anestheName");
                String facilitiesType = rs.getString("facilitiesType");
                String facilitiesName = rs.getString("facilitiesName");
                String location = rs.getString("location");
                String roomId = rs.getString("roomId");
                String date = rs.getDate("date").toString();
                String time = rs.getTime("time").toString();

                System.out.println("Adding Row: " + sur_Id + ", " + id + ", " + name + ", " + phone + ", " 
                    + nurseId + ", " + nurseName + ", " + docId + ", " + docName + ", " 
                    + anestheId + ", " + anestheName + ", " + facilitiesType + ", " 
                    + facilitiesName + ", " + location + ", " + roomId + ", " + date + ", " + time);

                model.addRow(new Object[]{sur_Id, id, name, phone, nurseId, nurseName, docId, docName, 
                                           anestheId, anestheName, facilitiesType, 
                                           facilitiesName, location, roomId, date, time});
            }

            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace(); 
        }
    }

    public void delete() {
        int selectedRowIndex = view.jTable8.getSelectedRow();

        if (selectedRowIndex == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.");
            return;
        }

        String id = view.jTable8.getValueAt(selectedRowIndex, 0).toString();
        int confirmDelete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the surgery appointment of patient ID: " + id + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirmDelete == JOptionPane.YES_OPTION) {
            try {
                String deleteQuery = "DELETE FROM customer WHERE id = ?";
                try (PreparedStatement ps = Connection_Provider.getCon().prepareStatement(deleteQuery)) {
                    ps.setString(1, id);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Appointment deleted successfully.");

                    DefaultTableModel model = (DefaultTableModel) view.jTable8.getModel();
                    model.removeRow(selectedRowIndex);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error deleting appointment: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
    
    private boolean validatePhoneNumber(String phoneNo) {
        String phonePattern = "^\\d{3}-\\d{7}$";
        Pattern pattern = Pattern.compile(phonePattern);
        Matcher matcher = pattern.matcher(phoneNo);
        return matcher.matches();
    }
}
