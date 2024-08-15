/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ReceiptRecordsModel;
import javax.swing.JTable;

/**
 *
 * @author Cheong Wei San
 */
public class ReceiptRecordsController {
    private ReceiptRecordsModel model;
    private JTable tbl_receiptRecords;

    public ReceiptRecordsController(JTable tbl_receiptRecords, String userID) {
        this.tbl_receiptRecords = tbl_receiptRecords;
        this.model = new ReceiptRecordsModel(userID);
    }

    public void handleLoadReceiptRecords() {
        tbl_receiptRecords.setModel(model.getReceiptRecords());
    }
}
