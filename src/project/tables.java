
package project;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Cheong Wei San
 */
public class tables {
    public static void main (String[] args)
    {
        Connection con=null;
        Statement st=null;
        
        try {
            con=Connection_Provider.getCon();
            st=con.createStatement();
            st.executeUpdate("CREATE TABLE user(id varchar(10), name varchar(200),ic varchar(12), email VARCHAR(200), phoneNum VARCHAR(11), address VARCHAR(300))");            
//            st.executeUpdate("CREATE TABLE room(roomNo varchar(10), location varchar(200), status VARCHAR(20))");
//            st.executeUpdate("CREATE TABLE facilities(type varchar(200), name varchar(200), status VARCHAR(20),location varchar(200))");
//            st.executeUpdate("CREATE TABLE customer(id VARCHAR(10), name varchar(200), phone VARCHAR(11), nurseId VARCHAR(10), nurseName varchar(200), docId VARCHAR(10), docName varchar(200), anestheName varchar(200), anestheId VARCHAR(10), facilitiesType varchar(200), facilitiesName varchar(200), location varchar(200), roomId varchar(10), date DATE, time varchar(8))");
//              st.executeUpdate("CREATE TABLE bill(receiptId varchar(100), customerId varchar(100), payDate DATE, payTime varchar(8), amount double, paymentMethod varchar(200), payLocation varchar(200), phoneNum VARCHAR(11))");
//                st.executeUpdate("CREATE TABLE nurse(nurseId varchar(10), nurseName varchar(200),nurseIc varchar(12), nurseEmail VARCHAR(200), nursePhoneNum VARCHAR(11), nurseAddress VARCHAR(300))");
            st.executeUpdate("CREATE TABLE doc(docId varchar(10), docName varchar(200),docIc varchar(12), docEmail VARCHAR(200), docPhoneNum VARCHAR(11), docAddress VARCHAR(300))"); 
            JOptionPane.showMessageDialog(null, "Table created successfully");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        finally
        {
            try {
                con.close();
                st.close();
            } catch (Exception e) {
            }
        }
    }
}


