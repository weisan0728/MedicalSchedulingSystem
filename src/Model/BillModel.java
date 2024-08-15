package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import project.Connection_Provider;

public class BillModel {
    private String receiptId;
    private String customerId;
    private Date payDate;
    private Time payTime;
    private double amount;
    private String paymentMethod;
    private String payLocation;
    private String phoneNum;

    public BillModel(String receiptId, String customerId, Date payDate, Time payTime, double amount, String paymentMethod, String payLocation, String phoneNum) {
        this.receiptId = receiptId;
        this.customerId = customerId;
        this.payDate = payDate;
        this.payTime = payTime;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.payLocation = payLocation;
        this.phoneNum = phoneNum;
    }


    public boolean saveToDatabase() {
        try {
            Connection conn = Connection_Provider.getCon();
            String sql = "INSERT INTO bill (receiptId, customerId, payDate, payTime, amount, paymentMethod, payLocation, phoneNum) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, receiptId);
            pstmt.setString(2, customerId);
            pstmt.setDate(3, payDate);
            pstmt.setTime(4, payTime);
            pstmt.setDouble(5, amount);
            pstmt.setString(6, paymentMethod);
            pstmt.setString(7, payLocation);
            pstmt.setString(8, phoneNum);

            int rowsInserted = pstmt.executeUpdate();
            conn.close();

            return rowsInserted > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error saving bill: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }

    public static Date parseDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static Time parseTime(String timeInput) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            java.util.Date parsedTime = sdf.parse(timeInput);
            return new java.sql.Time(parsedTime.getTime());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Invalid time format. Please enter time in HH:mm:ss format.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ResultSet getAllBill() throws SQLException {
        String query = "SELECT * FROM bill";
        Connection con = Connection_Provider.getCon();
        Statement stmt = con.createStatement();
        return stmt.executeQuery(query);
    }
}
