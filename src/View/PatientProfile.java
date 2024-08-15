package View;

import Controller.AppointmentApprovedController;
import Controller.AppointmentCancelledController;
import Controller.AppointmentRecordsController;
import Controller.AppointmentRejectedController;
import Controller.MakeAppointmentController;
import Controller.PatientProfileController;
import Controller.ReceiptRecordsController;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class PatientProfile extends javax.swing.JFrame {

    private static final String username = "root";
    private static final String password = "murberry02";
    private static final String dataConnect = "jdbc:mysql://localhost:3306/hospital";
    
    
    private String userPhoneNumber;
    private String userID;
    
    private PatientProfileController controller;
    private MakeAppointmentController makeAppointmentController;
    private AppointmentApprovedController appointmentApprovedController;
    private AppointmentRejectedController appointmentRejectedController;
    private AppointmentCancelledController appointmentCancelledController;
    private AppointmentRecordsController appointmentRecordsController;
    private ReceiptRecordsController receiptRecordsController;
    
    private boolean isInitialized = false;
//    public PatientProfile(String phoneNumber) {
//        this.userPhoneNumber = phoneNumber;
//        initComponents();
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        
//        //setExtendedState(JFrame.MAXIMIZED_BOTH);
//        
//        getUserID();
//        profile();
//        appointmentApproved();
//        appointmentRejected();
//        appointmentCancelled();
//        appointmentRecords();
//        receiptRecords();
//    }
    public PatientProfile(String phoneNumber) {
        this.userPhoneNumber = phoneNumber; 
        initComponents();
        controller = new PatientProfileController(
            profile_fname, profile_lname, profile_ID,
            profile_gender, profile_ICorPassport,
            profile_phoneNum, profile_email, profile_address
        );
        controller.handleProfileLoadActionPerformed(null, phoneNumber);
        getUserID();
    }

    
//-------------------------------------------------------------------------------check user ID-----------------------------------------------------------------------------//
    private void getUserID() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dataConnect, username, password);
            
            
            String sql = "SELECT id FROM user WHERE phoneNum = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userPhoneNumber);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                userID = rs.getString("id");
                
                // Initialize controllers after userID is set
                makeAppointmentController = new MakeAppointmentController();
                appointmentApprovedController = new AppointmentApprovedController(tbl_appointmentApproved, userID);
                appointmentRejectedController = new AppointmentRejectedController(tbl_appointmentRejected, userID);
                appointmentCancelledController = new AppointmentCancelledController(tbl_appointmentCancelled, userID);
                appointmentRecordsController = new AppointmentRecordsController(tbl_appointmentRecords, userID);
                receiptRecordsController = new ReceiptRecordsController(tbl_receiptRecords, userID);
                
                // Load appointment data
                appointmentApprovedController.handleLoadAppointmentApproved();
                appointmentRejectedController.handleLoadAppointmentRejected();
                appointmentCancelledController.handleLoadAppointmentCancelled();
                appointmentRecordsController.handleLoadAppointmentRecords();
                receiptRecordsController.handleLoadReceiptRecords();
                
                isInitialized = true;
            } else {
                JOptionPane.showMessageDialog(null, "No patient found. Please check your ID again.");
            }
            
            conn.close();

            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

//-------------------------------------------------------------------------------Patient Profile-----------------------------------------------------------------------------//
//    public void profile(){
//         try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection(dataConnect, username, password);
//            
//            String sql = "SELECT firstName, lastName, id, gender, ic, phoneNum, email, address FROM user WHERE phoneNum = ?";
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, userPhoneNumber);
//            ResultSet rs = pstmt.executeQuery();
//            
//
//            if (rs.next()) {
//                String fname = rs.getString("firstName");
//                String lname = rs.getString("lastName");
//                String id = rs.getString("id");
//                String gender = rs.getString("gender");
//                String icOrPassport = rs.getString("ic");
//                String phoneNum = rs.getString("phoneNum");
//                String email = rs.getString("email");
//                String address = rs.getString("address");
//                
//                
//
//                // Set the data to the labels
//                profile_fname.setText(fname);
//                profile_lname.setText(lname);
//                profile_ID.setText(id);
//                profile_gender.setText(gender);
//                profile_ICorPassport.setText(icOrPassport);
//                profile_phoneNum.setText(phoneNum);
//                profile_email.setText(email);
//                profile_address.setText(address);
// 
//            } else {
//                JOptionPane.showMessageDialog(null, "No patient found with the given phone number.");
//            }
//
//            conn.close();
//            
//        }catch(Exception e){
//            JOptionPane.showMessageDialog(null, "No patient found with the given phone number.");
//        }
//    }
    
//-------------------------------------------------------------------------------Make Appointment----------------------------------------------------------------------------//    
//    public void makeAppointment(){
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection(dataConnect, username, password);
//            
//            String sql = "INSERT into make_appointment(Patient_ID, Name, PhoneNum, AppointmentDate, AppointmentTime, Clinic, Reason, SpecialNeeds) values (?,?,?,?,?,?,?,?)";
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1,userID);
//            pstmt.setString(2,appointment_firstName.getText());
//            pstmt.setString(3,appointment_phoneNum.getText());
//            
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String date = sdf.format(appointment_date.getDate());
//            
//            pstmt.setString(4,date);
//            pstmt.setString(5,appointment_time.getText());
//            
//            String clinic = appointment_clinic.getSelectedItem().toString();
//            
//            pstmt.setString(6,clinic);
//            pstmt.setString(7,appointment_reason.getText());
//            pstmt.setString(8,appointment_specialNeeds.getText());
//            
//            pstmt.executeUpdate();
//            JOptionPane.showMessageDialog(null,"Appointment Requested !");
//            
//            conn.close();
//            
//            
//        }catch(Exception e){
//            JOptionPane.showMessageDialog(null, "No patient found. Please check your ID again.");
//        }
//    }
    
 //-------------------------------------------------------------------------------Appointment Approved-------------------------------------------------------------------------//
//    public void appointmentApproved(){
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection(dataConnect, username, password);
//            
//            String sql = "SELECT Patient_ID, Appointment_ID, ApproveDate, AppointmentDate, AppointmentTime, Clinic FROM appointment_approved WHERE Patient_ID = ?";
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, userID); 
//            ResultSet rs = pstmt.executeQuery();
//            
//            DefaultTableModel model = (DefaultTableModel)tbl_appointmentApproved.getModel();
//            model.setRowCount(0);
//            while(rs.next()){
//                model.addRow(new Object[]{
//                rs.getString("Patient_ID"),
//                rs.getString("Appointment_ID"),
//                rs.getString("ApproveDate"),
//                rs.getString("AppointmentDate"),
//                rs.getString("AppointmentTime"),
//                rs.getString("Clinic")
//            });
//
//            }
//            
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//        
//    }
  
 //-------------------------------------------------------------------------------Appointment Rejected-------------------------------------------------------------------------//
//    public void appointmentRejected(){
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection(dataConnect, username, password);
//            
//            String sql = "SELECT Patient_ID, Request_ID, RejectionDate, AppointmentRequestedDate, AppointmentRequestedTime, Clinic, RejectionReason FROM appointment_rejected WHERE Patient_ID = ?";
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, userID); 
//            ResultSet rs = pstmt.executeQuery();
//            
//            DefaultTableModel model = (DefaultTableModel)tbl_appointmentRejected.getModel();
//            model.setRowCount(0);
//            while(rs.next()){
//                model.addRow(new Object[]{
//                rs.getString("Patient_ID"),
//                rs.getString("Request_ID"),
//                rs.getString("RejectionDate"),
//                rs.getString("AppointmentRequestedDate"),
//                rs.getString("AppointmentRequestedTime"),
//                rs.getString("Clinic"),
//                rs.getString("RejectionReason")
//            });
//
//            }
//            
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//    }
    
//--------------------------------------------------------------------------------Appointment Cancelled-------------------------------------------------------------------------//
//    public void appointmentCancelled(){
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection(dataConnect, username, password);
//            
//            String sql = "SELECT Patient_ID, Appointment_ID, CancelDate, AppointmentDate, AppointmentTime, Clinic, CancellationReason FROM appointment_cancelled WHERE Patient_ID = ?";
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, userID); 
//            ResultSet rs = pstmt.executeQuery();
//            
//            DefaultTableModel model = (DefaultTableModel)tbl_appointmentCancelled.getModel();
//            model.setRowCount(0);
//            while(rs.next()){
//                model.addRow(new Object[]{
//                rs.getString("Patient_ID"),
//                rs.getString("Appointment_ID"),
//                rs.getString("CancelDate"),
//                rs.getString("AppointmentDate"),
//                rs.getString("AppointmentTime"),
//                rs.getString("Clinic"),
//                rs.getString("CancellationReason")
//            });
//
//            }
//            
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//    }
    
//--------------------------------------------------------------------------------Appointment Records-------------------------------------------------------------------------//
//    public void appointmentRecords(){
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection(dataConnect, username, password);
//            
//            String sql = "SELECT Patient_ID,Appointment_ID , Date, Time, DoctorName, Clinic, BillingStatus FROM appointment_records WHERE Patient_ID = ?";
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, userID); 
//            ResultSet rs = pstmt.executeQuery();
//            
//            DefaultTableModel model = (DefaultTableModel)tbl_appointmentRecords.getModel();
//            model.setRowCount(0);
//            while(rs.next()){
//                model.addRow(new Object[]{
//                rs.getString("Patient_ID"),
//                rs.getString("Appointment_ID"),
//                rs.getString("Date"),
//                rs.getString("Time"),
//                rs.getString("DoctorName"),
//                rs.getString("Clinic"),
//                rs.getString("BillingStatus")
//            });
//
//            }
//            
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//    }
    
//----------------------------------------------------------------------------------Receipt Records---------------------------------------------------------------------------//
//    public void receiptRecords(){
//
//try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection(dataConnect, username, password);
//            
//            String sql = "SELECT receiptId, customerId, payDate, payTime, amount, paymentMethod, payLocation, phoneNum FROM bill WHERE customerId = ?";
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, userID); 
//            ResultSet rs = pstmt.executeQuery();
//            
//            DefaultTableModel model = (DefaultTableModel)tbl_receiptRecords.getModel();
//            model.setRowCount(0);
//            while(rs.next()){
//                model.addRow(new Object[]{
//                rs.getString("receiptId"),
//                rs.getString("customerId"),
//                rs.getString("payDate"),
//                rs.getString("payTime"),
//                rs.getString("amount"),
//                rs.getString("paymentMethod"),
//                rs.getString("payLocation"),
//                rs.getString("phoneNum")
//            });
//            }
//            
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//    }
  
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel19 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        profile_fname = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        profile_lname = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        profile_ID = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        profile_gender = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        profile_ICorPassport = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        profile_phoneNum = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        profile_email = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        profile_address = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_appointmentApproved = new javax.swing.JTable();
        refreshButton1 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_appointmentRejected = new javax.swing.JTable();
        refreshButton2 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_appointmentCancelled = new javax.swing.JTable();
        refreshButton3 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_appointmentRecords = new javax.swing.JTable();
        refreshButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_receiptRecords = new javax.swing.JTable();
        refreshButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        appointment_firstName = new javax.swing.JTextField();
        appointment_phoneNum = new javax.swing.JTextField();
        appointment_time = new javax.swing.JTextField();
        appointment_clinic = new javax.swing.JComboBox<>();
        appointment_reason = new javax.swing.JTextField();
        submitAppointmentButton = new javax.swing.JButton();
        appointment_date = new com.toedter.calendar.JDateChooser();
        appointment_specialNeeds = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        resetButton = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        patient_id = new javax.swing.JTextField();
        appointment_lastName = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();

        jLabel19.setText("jLabel19");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton1.setText("Profile");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton2.setText("Make Appointment");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton3.setText("Appointment Approved");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton4.setText("Appointment Rejected");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton5.setText("Appointment Cancelled");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton6.setText("Appointment Record");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton7.setText("Receipt");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1430, -1));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jTabbedPane1ComponentShown(evt);
            }
        });

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setText("First Name :");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, -1, -1));

        profile_fname.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        profile_fname.setText("-");
        jPanel2.add(profile_fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 430, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("Last Name :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 100, -1, -1));

        profile_lname.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        profile_lname.setText("-");
        jPanel2.add(profile_lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 100, 350, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setText("ID :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, -1, -1));

        profile_ID.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        profile_ID.setText("-");
        jPanel2.add(profile_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 420, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setText("Gender :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 170, -1, -1));

        profile_gender.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        profile_gender.setText("-");
        jPanel2.add(profile_gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 170, 310, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel9.setText("IC Number / Passport Number :");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, -1, -1));

        profile_ICorPassport.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        profile_ICorPassport.setText("-");
        jPanel2.add(profile_ICorPassport, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 240, 510, -1));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel11.setText("Phone Number :");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, -1, -1));

        profile_phoneNum.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        profile_phoneNum.setText("-");
        jPanel2.add(profile_phoneNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 310, 370, 20));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel13.setText("Email :");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 240, -1, -1));

        profile_email.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        profile_email.setText("-");
        jPanel2.add(profile_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 240, 380, -1));

        jLabel15.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel15.setText("Address :");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 390, -1, -1));

        profile_address.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        profile_address.setText("-");
        jPanel2.add(profile_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 390, 1010, -1));

        logoutButton.setText("Log Out");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });
        jPanel2.add(logoutButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 580, 80, 40));

        jTabbedPane1.addTab("tab2", jPanel2);

        jLabel12.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Appointment Approved");

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane5.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jScrollPane5ComponentShown(evt);
            }
        });

        tbl_appointmentApproved.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbl_appointmentApproved.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Patient ID", "Appointment ID", "Approve Date", "Appointment Date", "Appointment Time", "Clinic"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tbl_appointmentApproved);

        refreshButton1.setText("Refresh");
        refreshButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButton1ActionPerformed(evt);
            }
        });

        jButton12.setText("Cancel Appointment");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refreshButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(461, 461, 461)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1294, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel12)
                .addGap(44, 44, 44)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(refreshButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab4", jPanel5);

        jLabel8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Appointment Rejected");

        tbl_appointmentRejected.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbl_appointmentRejected.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Patient ID", "Request ID", "Rejection Date", "Appointment Requested Date", "Appointment Requested Time", "Clinic", "Rejection Reason"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tbl_appointmentRejected);

        refreshButton2.setText("Refresh");
        refreshButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(674, 674, 674)
                .addComponent(refreshButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel8)
                .addGap(44, 44, 44)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(refreshButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab5", jPanel6);

        jLabel10.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Appointment Cancelled");

        jScrollPane1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jScrollPane1ComponentShown(evt);
            }
        });

        tbl_appointmentCancelled.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbl_appointmentCancelled.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Patient ID", "Appointment ID", "Cancel Date", "Appointment Date", "Appoitment Time", "Clinic", "Cancellation Reason"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_appointmentCancelled.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tbl_appointmentCancelledComponentShown(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_appointmentCancelled);

        refreshButton3.setText("Refresh");
        refreshButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1290, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(667, 667, 667)
                        .addComponent(refreshButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel10)
                .addGap(46, 46, 46)
                .addComponent(jScrollPane1)
                .addGap(44, 44, 44)
                .addComponent(refreshButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab6", jPanel7);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Appointment Records");

        tbl_appointmentRecords.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbl_appointmentRecords.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Patient ID", "Appointment ID", "Date", "Time", "Reason", "Doctor Name", "Clinic", "Billing Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbl_appointmentRecords);

        jScrollPane3.setViewportView(jScrollPane2);

        refreshButton4.setText("Refresh");
        refreshButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(672, 672, 672)
                        .addComponent(refreshButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1287, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel6)
                .addGap(49, 49, 49)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(refreshButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

        jTabbedPane1.addTab("tab7", jPanel8);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel63.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel63.setText("Receipt Records");
        jPanel1.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 50, 1420, -1));

        tbl_receiptRecords.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbl_receiptRecords.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Patient ID", "Receipt ID", "Clinic Name", "Clinic Location", "Date", "Time", "Payment Method", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tbl_receiptRecords);

        jPanel1.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 1270, 420));

        refreshButton5.setText("Refresh");
        refreshButton5.setMaximumSize(new java.awt.Dimension(79, 37));
        refreshButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(refreshButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 590, 80, 40));

        jTabbedPane1.addTab("tab1", jPanel1);

        jLabel18.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel18.setText("First Name:");

        jLabel20.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel20.setText("Phone Number :");

        jLabel21.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel21.setText("Date :");

        jLabel22.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel22.setText("Time :");

        jLabel23.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel23.setText("Clinic :");

        jLabel24.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel24.setText("Reason :");

        jLabel25.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel25.setText("Special needs :");

        appointment_firstName.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        appointment_firstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appointment_firstNameActionPerformed(evt);
            }
        });

        appointment_phoneNum.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        appointment_phoneNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appointment_phoneNumActionPerformed(evt);
            }
        });

        appointment_time.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        appointment_time.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                appointment_timeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                appointment_timeFocusLost(evt);
            }
        });
        appointment_time.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appointment_timeActionPerformed(evt);
            }
        });

        appointment_clinic.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        appointment_clinic.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Clinic", "Clinic XXX Serdang", "Clinic XXX Kajang", "Clinic XXX Puchong" }));

        appointment_reason.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        submitAppointmentButton.setText("Submit");
        submitAppointmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitAppointmentButtonActionPerformed(evt);
            }
        });

        appointment_date.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        appointment_specialNeeds.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("(Optional)");

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setText("(HH : MM   AM or PM)");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("ID: ");

        patient_id.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        patient_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patient_idActionPerformed(evt);
            }
        });

        appointment_lastName.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        appointment_lastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appointment_lastNameActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel16.setText("Last Name:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21))
                        .addGap(71, 71, 71)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(appointment_phoneNum, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(appointment_date, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(submitAppointmentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(71, 71, 71)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(appointment_firstName, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                                .addComponent(patient_id))
                            .addComponent(appointment_lastName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel25))
                                .addGap(70, 70, 70)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(appointment_reason, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(appointment_time, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(appointment_specialNeeds, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(71, 71, 71)
                                .addComponent(appointment_clinic, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel4)))
                    .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(appointment_firstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(appointment_clinic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(appointment_reason, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(appointment_lastName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(50, 50, 50)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(appointment_time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(61, 61, 61)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(appointment_specialNeeds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(patient_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(appointment_phoneNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21)
                            .addComponent(appointment_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitAppointmentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
        );

        jTabbedPane1.addTab("tab3", jPanel4);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1430, 740));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(6);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(5);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(dataConnect, username, password);

        // Prepare SQL query to select the logged-in user's details
        String query = "SELECT * FROM user WHERE phoneNum = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, userPhoneNumber);
        
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            // Populate text fields with the user's details
            appointment_firstName.setText(rs.getString("firstName"));
            appointment_lastName.setText(rs.getString("lastName"));
            patient_id.setText(rs.getString("id"));
            appointment_phoneNum.setText(rs.getString("phoneNum"));
            
            System.out.println("Record found: " + rs.getString("firstName") + " " + rs.getString("lastName"));
        } else {
            // Handle case where no record is found
            JOptionPane.showMessageDialog(null, "No user found with the phone number: " + userPhoneNumber);
        }

        // Set text fields to non-editable
        patient_id.setEditable(false);
        appointment_phoneNum.setEditable(false);
        appointment_firstName.setEditable(false);
        appointment_lastName.setEditable(false);
        
        pstmt.close();
        con.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
    }//GEN-LAST:event_formComponentShown

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        // TODO add your handling code here
        
        int a=JOptionPane.showConfirmDialog(null,"Do you really wan to log out ? ","Select",JOptionPane.YES_NO_OPTION);
        if(a==0){
            dispose();
            new Login().setVisible(true);
        }
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void refreshButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButton4ActionPerformed
        // TODO add your handling code here:
        appointmentRecordsController.handleLoadAppointmentRecords();
    }//GEN-LAST:event_refreshButton4ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        new CancelAppointment().setVisible(true);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void refreshButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButton1ActionPerformed
        // TODO add your handling code here:
        appointmentApprovedController.handleLoadAppointmentApproved();
    }//GEN-LAST:event_refreshButton1ActionPerformed

    private void refreshButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButton2ActionPerformed
        // TODO add your handling code here:
        appointmentRejectedController.handleLoadAppointmentRejected();
    }//GEN-LAST:event_refreshButton2ActionPerformed

    private void refreshButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButton3ActionPerformed
        // TODO add your handling code here:
        appointmentCancelledController.handleLoadAppointmentCancelled();
    }//GEN-LAST:event_refreshButton3ActionPerformed

    private void refreshButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButton5ActionPerformed
        // TODO add your handling code here:
        receiptRecordsController.handleLoadReceiptRecords();
    }//GEN-LAST:event_refreshButton5ActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        // TODO add your handling code here:
//        appointment_firstName.setText("");
//        appointment_patientID.setText("");
//        appointment_phoneNum.setText("");
        appointment_date.setDate(null);
        appointment_time.setText("");
        appointment_clinic.setSelectedIndex(0);
        appointment_reason.setText("");
        appointment_specialNeeds.setText("");
    }//GEN-LAST:event_resetButtonActionPerformed

    private void submitAppointmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitAppointmentButtonActionPerformed
        // TODO add your handling code here:
        // Ensure makeAppointmentController is initialized
        if (!isInitialized || makeAppointmentController == null) {
            JOptionPane.showMessageDialog(null, "Appointment controller is not initialized.");
            return;
        }

        // Ensure userID is initialized
        if (userID == null) {
            JOptionPane.showMessageDialog(null, "User ID is not initialized.");
            return;
        }

        makeAppointmentController.handleMakeAppointmentActionPerformed(
            evt,
            userID,
            appointment_firstName,
            appointment_phoneNum,
            appointment_date,
            appointment_time,
            appointment_clinic,
            appointment_reason,
            appointment_specialNeeds
        );
    }//GEN-LAST:event_submitAppointmentButtonActionPerformed

    private void appointment_timeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appointment_timeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_appointment_timeActionPerformed

    private void appointment_timeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_appointment_timeFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_appointment_timeFocusLost

    private void appointment_timeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_appointment_timeFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_appointment_timeFocusGained

    private void patient_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patient_idActionPerformed
        // TODO add your handling code here:
        patient_id.setEditable(false);

    }//GEN-LAST:event_patient_idActionPerformed

    private void jScrollPane1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jScrollPane1ComponentShown
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jScrollPane1ComponentShown

    private void tbl_appointmentCancelledComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tbl_appointmentCancelledComponentShown
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tbl_appointmentCancelledComponentShown

    private void jScrollPane5ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jScrollPane5ComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane5ComponentShown

    private void jTabbedPane1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTabbedPane1ComponentShown
        // TODO add your handling code here:

    }//GEN-LAST:event_jTabbedPane1ComponentShown

    private void appointment_phoneNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appointment_phoneNumActionPerformed
        // TODO add your handling code here:
        appointment_phoneNum.setEditable(false);
    }//GEN-LAST:event_appointment_phoneNumActionPerformed

    private void appointment_lastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appointment_lastNameActionPerformed
        // TODO add your handling code here:
        appointment_lastName.setEditable(false);
    }//GEN-LAST:event_appointment_lastNameActionPerformed

    private void appointment_firstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appointment_firstNameActionPerformed
        // TODO add your handling code here:
        appointment_firstName.setEditable(false);
    }//GEN-LAST:event_appointment_firstNameActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PatientProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PatientProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PatientProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PatientProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        String phoneNumber = "-";

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PatientProfile(phoneNumber).setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> appointment_clinic;
    private com.toedter.calendar.JDateChooser appointment_date;
    private javax.swing.JTextField appointment_firstName;
    private javax.swing.JTextField appointment_lastName;
    private javax.swing.JTextField appointment_phoneNum;
    private javax.swing.JTextField appointment_reason;
    private javax.swing.JTextField appointment_specialNeeds;
    private javax.swing.JTextField appointment_time;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton logoutButton;
    private javax.swing.JTextField patient_id;
    private javax.swing.JLabel profile_ICorPassport;
    private javax.swing.JLabel profile_ID;
    private javax.swing.JLabel profile_address;
    private javax.swing.JLabel profile_email;
    private javax.swing.JLabel profile_fname;
    private javax.swing.JLabel profile_gender;
    private javax.swing.JLabel profile_lname;
    private javax.swing.JLabel profile_phoneNum;
    private javax.swing.JButton refreshButton1;
    private javax.swing.JButton refreshButton2;
    private javax.swing.JButton refreshButton3;
    private javax.swing.JButton refreshButton4;
    private javax.swing.JButton refreshButton5;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton submitAppointmentButton;
    private javax.swing.JTable tbl_appointmentApproved;
    private javax.swing.JTable tbl_appointmentCancelled;
    private javax.swing.JTable tbl_appointmentRecords;
    private javax.swing.JTable tbl_appointmentRejected;
    private javax.swing.JTable tbl_receiptRecords;
    // End of variables declaration//GEN-END:variables
}
