package Controller;

import Model.BillModel;
import View.nurseHome;
import java.sql.Date;
import java.sql.Time;
import javax.swing.JOptionPane;
import java.sql.*;
import java.text.ParseException;
import java.util.Calendar;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import project.InsertUpdateDelete;
import project.Select;


public class BillController {
    private nurseHome view;
    private BillModel model;

    public BillController(BillModel billmodel, nurseHome view) {
        this.view = view;
        this.model = billmodel;
    }

    public void initView() {
        view.setVisible(true);
    }

    public void processBill() {
        String receiptId = view.receiptIdField.getText();
        String customerId = view.customerIdField.getText();
        String amount = view.amountField.getText();
        String paymentMethod = (String) view.paymentMethodComboBox.getSelectedItem();
        String payLocation = (String) view.payLocationComboBox.getSelectedItem();
        String phoneNum = view.customerPhoneNumField.getText();

        // date
        Date payDate = new Date(view.jDateChooser2.getDate().getTime());

        // time
        String timeInput = view.payTimeField.getText();
        Time payTime = BillModel.parseTime(timeInput);

        if (payTime == null) {
            return; 
        }
        if (view.jDateChooser2.getDate() == null) {
        JOptionPane.showMessageDialog(null, "Please select a date.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
       }
        
        if (!validatePhoneNumber(phoneNum)) {
                JOptionPane.showMessageDialog(view, "Invalid Phone Number", "Error Message", JOptionPane.ERROR_MESSAGE);
                return;
            }

        double amountDouble;
        try {
            amountDouble = Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid amount format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        BillModel bill = new BillModel(receiptId, customerId, payDate, payTime, amountDouble, paymentMethod, payLocation, phoneNum);

        if (bill.saveToDatabase()) {
            JOptionPane.showMessageDialog(null, "Successfully Updated");
            loadBillData();
        } else {
            System.out.println("Error adding receipt");
            JOptionPane.showMessageDialog(view, "Error adding receipt.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    public void clear(){
    view.jDateChooser2.setDate(null); 
    
    view.customerIdField.setText("");
    view.receiptIdField.setText("");
    view.amountField.setText("");
    view.customerPhoneNumField.setText("");

    view.paymentMethodComboBox.setSelectedIndex(0); 
    view.payLocationComboBox.setSelectedIndex(0); 

    view.setVisible(true);
    }
    
    public void searchBill() {
    String receiptId = view.billCodeSearchField.getText();
    System.out.println("Search Text: " + receiptId);

    ResultSet rs = Select.getData("SELECT * FROM bill WHERE receiptId LIKE '%" + receiptId + "%'");
    DefaultTableModel model = (DefaultTableModel) view.jTable7.getModel();
    model.setRowCount(0);

    try {
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("receiptId"),
                rs.getString("customerId"),
                rs.getDate("payDate"),
                rs.getTime("payTime"),
                rs.getDouble("amount"),
                rs.getString("paymentMethod"),
                rs.getString("payLocation"),
                rs.getString("phoneNum")
            });
        }
        rs.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}

    
    public void deleteBill(){
        int selectedRowIndex = view.jTable7.getSelectedRow();

        if (selectedRowIndex == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.");
            return;
        }

        String receiptId = view.jTable7.getValueAt(selectedRowIndex, 0).toString();

        int confirmDelete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete receipt ID: " + receiptId + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirmDelete == JOptionPane.YES_OPTION) {

            try {
                String deleteQuery = "DELETE FROM bill WHERE receiptId = '" + receiptId + "'";
                InsertUpdateDelete.setData(deleteQuery, "Receipt deleted successfully");

                DefaultTableModel model = (DefaultTableModel) view.jTable7.getModel();
                model.removeRow(selectedRowIndex);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error deleting bill: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace(); 
            }
        }
        
    }

    private void loadBillData() {
        try {
            ResultSet rs = model.getAllBill();
            DefaultTableModel tableModel = (DefaultTableModel) view.jTable7.getModel();
            tableModel.setRowCount(0); // Clear existing rows

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getString("receiptId"),
                    rs.getString("customerId"),
                    rs.getString("payDate"),
                    rs.getString("payTime"),
                    rs.getString("amount"),
                    rs.getString("paymentMethod"),
                    rs.getString("payLocation"),
                    rs.getString("phoneNum")
                });
            }
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Error loading receipt data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private boolean validatePhoneNumber(String phoneNo) {
        String phonePattern = "^\\d{3}-\\d{7}$";
        Pattern pattern = Pattern.compile(phonePattern);
        Matcher matcher = pattern.matcher(phoneNo);
        return matcher.matches();
    }
}
