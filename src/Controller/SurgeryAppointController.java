/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import Model.SurgeryAppointModel;
import View.doctorHome1;
import View.nurseHome;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import project.Connection_Provider;
import project.Select;

/**
 *
 * @author Cheong Wei San
 */
public class SurgeryAppointController {
    private SurgeryAppointModel model;
    private nurseHome view;
    private doctorHome1 docview;

    public SurgeryAppointController(SurgeryAppointModel model, doctorHome1 docview) {
        this.model = model;
        this.docview = docview;
    }

    public SurgeryAppointController(SurgeryAppointModel model, nurseHome view) {
        this.model = model;
        this.view = view;
    }

    public void initView() {
        view.setVisible(true);
    }
    
    public void bookRoomButton(){
        String id = view.patientIdField.getText();
        String patientName = view.patientNameField.getText();
        String phoneNumber = view.phoneNumField.getText();
        String nurseName = view.nurseNameField.getText();
        String nurseID = view.nurseIdField.getText();
        String docName = view.docNameField.getText();
        String docId = view.docIdField.getText();
        String anestheName = view.anestheNameField.getText();
        String anestheId = view.anestheIdField.getText();
        String facilityType = (String) view.f_typeComboBox.getSelectedItem();
        String facilityName = (String) view.f_nameComboBox.getSelectedItem();
        String sur_roomId = (String) view.roomComboBox.getSelectedItem();
        String sur_location = (String) view.sur_locationComboBox.getSelectedItem();

 
        Calendar calendar = Calendar.getInstance();
        java.sql.Date date = new java.sql.Date(calendar.getTime().getTime());

        String timeInput = view.timeField.getText(); 
        java.sql.Time time = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            java.util.Date parsedDate = sdf.parse(timeInput);
            time = new java.sql.Time(parsedDate.getTime());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Invalid time format. Please enter time in HH:mm:ss format.", "Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }

        try {
            if (!validatePhoneNumber(phoneNumber)) {
                JOptionPane.showMessageDialog(view, "Invalid Phone Number", "Error Message", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else{
            boolean success = model.registerBooking(id, patientName, phoneNumber, nurseID, nurseName, docId, docName, anestheId, anestheName, facilityType, facilityName, sur_location, sur_roomId, date, time);

            if (success) {
                JOptionPane.showMessageDialog(view, "Booking added successfully.");
            } else {
                JOptionPane.showMessageDialog(view, "Failed to add booking.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error adding booking: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
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
    

    DefaultTableModel tableModel = (DefaultTableModel) view.jTable6.getModel();
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

        // Confirm deletion with user
        int confirmDelete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete surgery appointment of patient ID: " + id + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

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
