/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import project.Connection_Provider;

/**
 *
 * @author Cheong Wei San
 */
public class ReceiptRecordsModel {
    private static final String username = "root";
    private static final String password = "murberry02";
    private static final String dataConnect = "jdbc:mysql://localhost:3306/hospital";

    private String userID;

    public ReceiptRecordsModel(String userID) {
        this.userID = userID;
    }

    public DefaultTableModel getReceiptRecords() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{
            "Patient_ID", "Receipt_ID", "ClinicName", "ClinicLocation", 
            "Date", "Time", "PaymentMethod", "Amount"
        }, 0);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dataConnect, username, password);
            String sql = "SELECT receiptId, customerId, payDate, payTime, amount, paymentMethod, payLocation, phoneNum FROM bill WHERE customerId = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userID);
            ResultSet rs = pstmt.executeQuery();

//            DefaultTableModel model = (DefaultTableModel)tbl_receiptRecords.getModel();
//            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{
//                    rs.getString("Patient_ID"),
//                    rs.getString("Receipt_ID"),
//                    rs.getString("ClinicName"),
//                    rs.getString("ClinicLocation"),
//                    rs.getString("Date"),
//                    rs.getString("Time"),
//                    rs.getString("PaymentMethod"),
//                    rs.getFloat("Amount")
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
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return model;
    }
}
