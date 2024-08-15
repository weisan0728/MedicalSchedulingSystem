/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.BillController;
import Controller.FacilitiesController;
import Controller.NurseAppointmentListController;
import Controller.NurseHomeController;
import Controller.NursePendingAppointController;
import Controller.NurseProfileController;
import Controller.NurseRejectAppointmentController;
import Controller.NurseUserController;
import Controller.RoomManagementController;
import Controller.SurgeryAppointController;
import Model.AppointmentRecordsModel;
import Model.ApproveAppointmentModel;
import Model.BillModel;
import Model.FacilitiesModel;
import Model.MakeAppointmentModel;
import Model.RejectAppointmentModel;
import Model.RoomManagementModel;
import Model.StaffModel;
import Model.SurgeryAppointModel;
import Model.UserModel;
import View.Login;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JOptionPane;
import project.*;
import java.sql.*;
import java.text.ParseException;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Cheong Wei San
 */
public class nurseHome extends javax.swing.JFrame {
    
   
    public String nurseId;
    String roomId;
    String facilitiesType;
    String facilitiesName;
    String location;
    
    //model
    private StaffModel model;
    private UserModel usermodel;
    private MakeAppointmentModel makeappomodel;
    private ApproveAppointmentModel approvemodel;
    private RejectAppointmentModel rejectmodel;
    private AppointmentRecordsModel apprecordsmodel;
    private RoomManagementModel roommodel;
    private FacilitiesModel facilitiesmodel;
    private SurgeryAppointModel surgerymodel;
    private BillModel billmodel;
    
    //controller
    private NurseHomeController controller;
    private NurseUserController usercontroller;
    private NurseProfileController profilecontroller;
    private NursePendingAppointController pendingcontroller;
    private NurseAppointmentListController appointmentlistcontroller;
    private NurseRejectAppointmentController rejectappcontroller;
    private RoomManagementController roomcontroller;
    private FacilitiesController facilitiescontroller;
    private SurgeryAppointController surgerycontroller;
    private BillController billcontroller;

    
    public nurseHome(String staffId) throws ParseException {
        this.nurseId=staffId; 
        initComponents();
        
        //model
        model = new StaffModel();
        usermodel = new UserModel();
        facilitiesmodel = new FacilitiesModel();
        roommodel = new RoomManagementModel();
        surgerymodel = new SurgeryAppointModel();
        rejectmodel = new RejectAppointmentModel();
        billmodel = new BillModel("", "", new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()), 0.0, "", "", "");
        approvemodel = new ApproveAppointmentModel();
        

        //controller
        controller = new NurseHomeController(model, this);
        usercontroller = new NurseUserController(usermodel, this);
        profilecontroller = new NurseProfileController(model, this);
        pendingcontroller = new NursePendingAppointController(approvemodel,this);
        appointmentlistcontroller = new NurseAppointmentListController(approvemodel,this);
        rejectappcontroller = new NurseRejectAppointmentController(rejectmodel,this);
        roomcontroller = new RoomManagementController(roommodel,this);
        facilitiescontroller = new FacilitiesController(facilitiesmodel,this);
        surgerycontroller = new SurgeryAppointController(surgerymodel, this);
        billcontroller = new BillController(billmodel,this);
        
        timeField.setEditable(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Add action listener to f_typeComboBox
        f_typeComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                facilitiescontroller.populateFacilitiesNames(); 
            }
        });

        // Add action listener to locationComboBox to populate roomComboBox
        sur_locationComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                roomcontroller.populateRoomID();
            }
        });
        }
    
    public String getNurseId() {
        return nurseId;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getFacilitiesType() {
        return facilitiesType;
    }

    public String getFacilitiesName() {
        return facilitiesName;
    }

    public JButton getAdd() {
        return add;
    }

    public JButton getAddBillButton() {
        return addBillButton;
    }

    public JTextField getAmountField() {
        return amountField;
    }

    public JTextField getAnestheIdField() {
        return anestheIdField;
    }

    public JTextField getAnestheNameField() {
        return anestheNameField;
    }

    public JPanel getAppoinListPanel() {
        return appoinListPanel;
    }

    public JButton getApproveButton() {
        return approveButton;
    }

    public JTextField getBillCodeSearchField() {
        return billCodeSearchField;
    }

    public JButton getBillSearch() {
        return billSearch;
    }

    public JPanel getBillsPanel() {
        return billsPanel;
    }

    public JButton getBookRoom() {
        return bookRoom;
    }

    public JButton getClear() {
        return clear;
    }

    public JButton getClearBillButton() {
        return clearBillButton;
    }

    public JButton getClockInButton() {
        return clockInButton;
    }

    public JButton getClockOutButton() {
        return clockOutButton;
    }

    public JTextField getCustomerIdField() {
        return customerIdField;
    }

    public JTextField getCustomerPhoneNumField() {
        return customerPhoneNumField;
    }

    public JButton getDeleteAppointListButton() {
        return deleteAppointListButton;
    }

    public JButton getDeleteBillButton() {
        return deleteBillButton;
    }

    public JButton getDeleteSaListButton() {
        return deleteSaListButton;
    }

    public JTextField getDocIdField() {
        return docIdField;
    }

    public JTextField getDocNameField() {
        return docNameField;
    }

    public JButton getExit() {
        return exit;
    }

    public JComboBox<String> getF_nameComboBox() {
        return f_nameComboBox;
    }

    public JComboBox<String> getF_typeComboBox() {
        return f_typeComboBox;
    }

    public JPanel getFacilitiesPanel() {
        return facilitiesPanel;
    }

    public JComboBox<String> getI_locationComboBox() {
        return i_locationComboBox;
    }

    public JTextField getI_nameField() {
        return i_nameField;
    }

    public JComboBox<String> getI_statusComboBox() {
        return i_statusComboBox;
    }

    public JComboBox<String> getI_typeComboBox() {
        return i_typeComboBox;
    }

    public JButton getjButton1() {
        return jButton1;
    }

    public JButton getjButton10() {
        return jButton10;
    }

    public JButton getjButton11() {
        return jButton11;
    }

    public JButton getjButton2() {
        return jButton2;
    }

    public JButton getjButton3() {
        return jButton3;
    }

    public JButton getjButton4() {
        return jButton4;
    }

    public JButton getjButton5() {
        return jButton5;
    }

    public JButton getjButton6() {
        return jButton6;
    }

    public JButton getjButton7() {
        return jButton7;
    }

    public JButton getjButton8() {
        return jButton8;
    }

    public JDateChooser getjDateChooser1() {
        return jDateChooser1;
    }

    public JDateChooser getjDateChooser2() {
        return jDateChooser2;
    }

    public JTextField getjTextField1() {
        return jTextField1;
    }

    public JTextField getjTextField14() {
        return jTextField14;
    }

    public JTextField getjTextField15() {
        return jTextField15;
    }

    public JTable getjTable4() {
        return jTable4;
    }

    public JTextField getjTextField16() {
        return jTextField16;
    }

    public JTextField getjTextField2() {
        return searchSAL;
    }

    /**
     *
     * @return
     */
    public JComboBox<String> getLocationComboBox() {
        return locationComboBox;
    }

    public JButton getLogout() {
        return logout;
    }

    public JTextField getNurseIdField() {
        return nurseIdField;
    }

    public JTextField getNurseNameField() {
        return nurseNameField;
    }

    public JTextField getPatientIdField() {
        return patientIdField;
    }

    public JTextField getPatientNameField() {
        return patientNameField;
    }

    public JComboBox<String> getPayLocationComboBox() {
        return payLocationComboBox;
    }

    public JTextField getPayTimeField() {
        return payTimeField;
    }

    public JComboBox<String> getPaymentMethodComboBox() {
        return paymentMethodComboBox;
    }

    public JPanel getPendingAppoinPanel() {
        return pendingAppoinPanel;
    }

    public JTextField getPhoneNumField() {
        return phoneNumField;
    }

    public JPanel getProfilePanel() {
        return profilePanel;
    }

    public JTextField getReceiptIdField() {
        return receiptIdField;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }

    public JButton getRejectButton() {
        return rejectButton;
    }

    public JComboBox<String> getRoomComboBox() {
        return roomComboBox;
    }

    public JPanel getRoomManagementPanel() {
        return roomManagementPanel;
    }

    public JTextField getRoomNumberField() {
        return roomNumberField;
    }

    public JButton getSearchField() {
        return searchField;
    }

    public JButton getSearchSaList() {
        return searchSaList;
    }

    public JComboBox<String> getStatusComboBox() {
        return statusComboBox;
    }

    public JComboBox<String> getSur_locationComboBox() {
        return sur_locationComboBox;
    }

    public JTextField getTimeField() {
        return timeField;
    }

    public JTextArea getUpdateAddressField() {
        return updateAddressField;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JTextField getUpdateEmailField() {
        return updateEmailField;
    }

    public JTextField getUpdateID() {
        return updateID;
    }

    public JTextField getUpdateIcField() {
        return updateIcField;
    }

    public JTextField getUpdateNameField() {
        return updateNameField;
    }

    public JTextField getUpdatePhoneNumField() {
        return updatePhoneNumField;
    }

    public JButton getUpdateStatusButton() {
        return updateStatusButton;
    }

    public void deleteUserMouseClicked(MouseAdapter mouseAdapter){
        userTable.addMouseListener(mouseAdapter);
    }  
    
    public JTable getUserTable() {
        return userTable;
    }

    public JTable getjTable7() {
        return jTable7;
    }

    public JComboBox<String> getroomComboBox(){
        return roomComboBox;
    }

    public JTable getjTable2() {
        return jTable2;
    }



    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        logout = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        clockInButton = new javax.swing.JButton();
        clockOutButton = new javax.swing.JButton();
        StaffList = new javax.swing.JTabbedPane();
        profilePanel = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        updateNameField = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        updateIcField = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        updatePhoneNumField = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        updateEmailField = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        updateAddressField = new javax.swing.JTextArea();
        updateButton = new javax.swing.JButton();
        jLabel46 = new javax.swing.JLabel();
        updateID = new javax.swing.JTextField();
        usersPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        searchField = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        clear = new javax.swing.JButton();
        pendingAppoinPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        approveButton = new javax.swing.JButton();
        rejectButton = new javax.swing.JButton();
        appoinListPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        deleteAppointListButton = new javax.swing.JButton();
        updateStatusButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        rejectedPanel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        facilitiesPanel = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        i_typeComboBox = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        i_nameField = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        i_statusComboBox = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        i_locationComboBox = new javax.swing.JComboBox<>();
        jButton8 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        roomManagementPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        roomNumberField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        statusComboBox = new javax.swing.JComboBox<>();
        add = new javax.swing.JButton();
        locationComboBox = new javax.swing.JComboBox<>();
        delete = new javax.swing.JButton();
        surgeryAppointPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        patientNameField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        patientIdField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        nurseNameField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        nurseIdField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        anestheNameField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        anestheIdField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        docIdField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        docNameField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        timeField = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        f_typeComboBox = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        sur_locationComboBox = new javax.swing.JComboBox<>();
        bookRoom = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        roomComboBox = new javax.swing.JComboBox<>();
        f_nameComboBox = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        phoneNumField = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jLabel39 = new javax.swing.JLabel();
        searchSAL = new javax.swing.JTextField();
        searchSaList = new javax.swing.JButton();
        deleteSaListButton = new javax.swing.JButton();
        billsPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        billCodeSearchField = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        billSearch = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        receiptIdField = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        customerIdField = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel33 = new javax.swing.JLabel();
        payTimeField = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        amountField = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        paymentMethodComboBox = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        payLocationComboBox = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        customerPhoneNumField = new javax.swing.JTextField();
        addBillButton = new javax.swing.JButton();
        deleteBillButton = new javax.swing.JButton();
        clearBillButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setText("Nurse Section");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 153, -1));

        logout.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        logout.setText("Log Out");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        getContentPane().add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 10, 80, 40));

        exit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        getContentPane().add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1370, 10, 80, 40));

        clockInButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        clockInButton.setText("Clock In");
        clockInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clockInButtonActionPerformed(evt);
            }
        });
        getContentPane().add(clockInButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 10, 90, 40));

        clockOutButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        clockOutButton.setText("Clock Out");
        clockOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clockOutButtonActionPerformed(evt);
            }
        });
        getContentPane().add(clockOutButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1178, 10, 90, 40));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel40.setText("Profile");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel41.setText("Name:");

        updateNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateNameFieldActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel42.setText("IC:");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel43.setText("Phone Number:");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel44.setText("Email:");

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel45.setText("Address:");

        updateAddressField.setColumns(20);
        updateAddressField.setRows(5);
        jScrollPane9.setViewportView(updateAddressField);

        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel46.setText("ID:");

        updateID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateIDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout profilePanelLayout = new javax.swing.GroupLayout(profilePanel);
        profilePanel.setLayout(profilePanelLayout);
        profilePanelLayout.setHorizontalGroup(
            profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profilePanelLayout.createSequentialGroup()
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(profilePanelLayout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(profilePanelLayout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(updateNameField)
                                .addComponent(updateIcField, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(updateID))
                            .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(235, 235, 235)
                        .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updatePhoneNumField, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 668, Short.MAX_VALUE))
        );
        profilePanelLayout.setVerticalGroup(
            profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profilePanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel40)
                .addGap(28, 28, 28)
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jLabel46))
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(profilePanelLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(updatePhoneNumField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(profilePanelLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(updateID, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46)
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(jLabel41))
                .addGap(42, 42, 42)
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(jLabel42))
                .addGap(38, 38, 38)
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(profilePanelLayout.createSequentialGroup()
                        .addComponent(updateIcField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(744, Short.MAX_VALUE))
        );

        StaffList.addTab("Profile", profilePanel);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Search By ID:");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        searchField.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        searchField.setText("Search");
        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });

        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "First Name", "Last Name", "IC / Passport No.", "Gender", "Phone No.", "Email", "Address", "password"
            }
        ));
        userTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(userTable);

        clear.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout usersPanelLayout = new javax.swing.GroupLayout(usersPanel);
        usersPanel.setLayout(usersPanelLayout);
        usersPanelLayout.setHorizontalGroup(
            usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usersPanelLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(usersPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchField)
                        .addGap(758, 758, 758)
                        .addComponent(clear))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1303, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(643, Short.MAX_VALUE))
        );
        usersPanelLayout.setVerticalGroup(
            usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usersPanelLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchField)
                        .addComponent(clear))
                    .addComponent(jLabel1))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(712, Short.MAX_VALUE))
        );

        StaffList.addTab("Users", usersPanel);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Request ID", "Patient ID", "Name", "Phone Number", "Appointment Date", "Appointment Time", "Clinic", "Reason", "Special Needs"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Search By Patient ID or Requested ID:");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Search");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        approveButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        approveButton.setText("Approve");
        approveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approveButtonActionPerformed(evt);
            }
        });

        rejectButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rejectButton.setText("Reject");
        rejectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rejectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pendingAppoinPanelLayout = new javax.swing.GroupLayout(pendingAppoinPanel);
        pendingAppoinPanel.setLayout(pendingAppoinPanelLayout);
        pendingAppoinPanelLayout.setHorizontalGroup(
            pendingAppoinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pendingAppoinPanelLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(pendingAppoinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pendingAppoinPanelLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(401, 401, 401)
                        .addComponent(approveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rejectButton))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1336, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 435, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(98, 98, 98))
        );
        pendingAppoinPanelLayout.setVerticalGroup(
            pendingAppoinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pendingAppoinPanelLayout.createSequentialGroup()
                .addContainerGap(626, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(693, 693, 693))
            .addGroup(pendingAppoinPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(pendingAppoinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(approveButton)
                    .addComponent(rejectButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        StaffList.addTab(" Pending appointments", pendingAppoinPanel);

        appoinListPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                appoinListPanelMouseClicked(evt);
            }
        });

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Appointment ID", "Patient ID", "Name", "Phone Number", "Approve Date", "Appointment Date", "Appointment Time", "Clinic", "Reason"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Search By Patient ID or Appointment ID:");

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setText("Search");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setText("Clear");

        deleteAppointListButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        deleteAppointListButton.setText("Delete");
        deleteAppointListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAppointListButtonActionPerformed(evt);
            }
        });

        updateStatusButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        updateStatusButton.setText("Update");
        updateStatusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStatusButtonActionPerformed(evt);
            }
        });

        refreshButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout appoinListPanelLayout = new javax.swing.GroupLayout(appoinListPanel);
        appoinListPanel.setLayout(appoinListPanelLayout);
        appoinListPanelLayout.setHorizontalGroup(
            appoinListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(appoinListPanelLayout.createSequentialGroup()
                .addGroup(appoinListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(appoinListPanelLayout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1319, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(appoinListPanelLayout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refreshButton)
                        .addGap(18, 18, 18)
                        .addComponent(updateStatusButton)
                        .addGap(18, 18, 18)
                        .addComponent(deleteAppointListButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 442, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(69, 69, 69))
        );
        appoinListPanelLayout.setVerticalGroup(
            appoinListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(appoinListPanelLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(appoinListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jLabel21)
                    .addComponent(deleteAppointListButton)
                    .addComponent(updateStatusButton)
                    .addComponent(refreshButton))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(680, Short.MAX_VALUE))
        );

        StaffList.addTab("Appointments's List", appoinListPanel);

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Request ID", "Patient ID", "Name", "Phone Number", "Rejection Date", "Requested Date", "Requested Time", "Clinic", "Rejection Reason"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable5);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Search By Patient ID or Requested ID:");

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton6.setText("Search");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton7.setText("Clear");

        javax.swing.GroupLayout rejectedPanelLayout = new javax.swing.GroupLayout(rejectedPanel);
        rejectedPanel.setLayout(rejectedPanelLayout);
        rejectedPanelLayout.setHorizontalGroup(
            rejectedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rejectedPanelLayout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addGroup(rejectedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(rejectedPanelLayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addGap(621, 621, 621))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1318, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 451, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(68, 68, 68))
        );
        rejectedPanelLayout.setVerticalGroup(
            rejectedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rejectedPanelLayout.createSequentialGroup()
                .addContainerGap(626, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(695, 695, 695))
            .addGroup(rejectedPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(rejectedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        StaffList.addTab("Rejected Appointments", rejectedPanel);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("Type");

        i_typeComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        i_typeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Electrosurgical Equipment", "Cryosurgery Equipment", "Imaging Equipment", "Endoscopic Equipment" }));
        i_typeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                i_typeComboBoxActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("Name");

        i_nameField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        i_nameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                i_nameFieldActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setText("Status");

        i_statusComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        i_statusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Unavailable" }));
        i_statusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                i_statusComboBoxActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setText("Location");

        i_locationComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        i_locationComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Clinic XXX Serdang", "Clinic XXX Kajang", "Clinic XXX Puchong" }));
        i_locationComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                i_locationComboBoxActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton8.setText("Add");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setText("Medical Equipment Management");

        jButton11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton11.setText("Delete");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Type", "Name", "Location", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane10.setViewportView(jTable6);

        javax.swing.GroupLayout facilitiesPanelLayout = new javax.swing.GroupLayout(facilitiesPanel);
        facilitiesPanel.setLayout(facilitiesPanelLayout);
        facilitiesPanelLayout.setHorizontalGroup(
            facilitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(facilitiesPanelLayout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addGroup(facilitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(facilitiesPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(157, 157, 157)
                        .addGroup(facilitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(facilitiesPanelLayout.createSequentialGroup()
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(i_typeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(i_nameField)
                            .addComponent(i_locationComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(i_statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(647, Short.MAX_VALUE))
        );
        facilitiesPanelLayout.setVerticalGroup(
            facilitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(facilitiesPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(facilitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(facilitiesPanelLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addComponent(i_typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel24)
                        .addGap(18, 18, 18)
                        .addComponent(i_nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel26)
                        .addGap(18, 18, 18)
                        .addComponent(i_locationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel25)
                        .addGap(18, 18, 18)
                        .addComponent(i_statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addGroup(facilitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton8)
                            .addComponent(jButton11)))
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(681, Short.MAX_VALUE))
        );

        StaffList.addTab("Medical Equipment", facilitiesPanel);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Room ID", "Location", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Room ID");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Location");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Status");

        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Occupied", "Unoccupied", " " }));
        statusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusComboBoxActionPerformed(evt);
            }
        });

        add.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add.setText("Add");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        locationComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Clinic XXX Serdang", "Clinic XXX Kajang", "Clinic XXX Puchong" }));
        locationComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locationComboBoxActionPerformed(evt);
            }
        });

        delete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roomManagementPanelLayout = new javax.swing.GroupLayout(roomManagementPanel);
        roomManagementPanel.setLayout(roomManagementPanelLayout);
        roomManagementPanelLayout.setHorizontalGroup(
            roomManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roomManagementPanelLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(151, 151, 151)
                .addGroup(roomManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(roomManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roomManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(roomNumberField)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(locationComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roomManagementPanelLayout.createSequentialGroup()
                        .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(669, Short.MAX_VALUE))
        );
        roomManagementPanelLayout.setVerticalGroup(
            roomManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roomManagementPanelLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(roomManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roomManagementPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roomNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(locationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addGroup(roomManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(690, Short.MAX_VALUE))
        );

        StaffList.addTab("Rooms Management", roomManagementPanel);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Patient Name");

        patientNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientNameFieldActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Patient ID");

        patientIdField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientIdFieldActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Nurse Name");

        nurseNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nurseNameFieldActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Nurse ID");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Anesthesiologist Name");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Anesthesiologist ID");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Doctor ID");

        docIdField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                docIdFieldActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Doctor Name");

        docNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                docNameFieldActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Date");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Time (HH:mm:ss)");

        timeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeFieldActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Location");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Room ID");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Equipment Type");

        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        f_typeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Electrosurgical Equipment", "Cryosurgery Equipment", "Imaging Equipment", "Endoscopic Equipment" }));
        f_typeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f_typeComboBoxActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setText("Equipment Name");

        sur_locationComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Clinic XXX Serdang", "Clinic XXX Kajang", "Clinic XXX Puchong" }));
        sur_locationComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sur_locationComboBoxActionPerformed(evt);
            }
        });

        bookRoom.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bookRoom.setText("Book Room");
        bookRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookRoomActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton10.setText("Clear");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        roomComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomComboBoxActionPerformed(evt);
            }
        });

        f_nameComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f_nameComboBoxActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setText("Phone Number");

        phoneNumField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneNumFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout surgeryAppointPanelLayout = new javax.swing.GroupLayout(surgeryAppointPanel);
        surgeryAppointPanel.setLayout(surgeryAppointPanelLayout);
        surgeryAppointPanelLayout.setHorizontalGroup(
            surgeryAppointPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, surgeryAppointPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
            .addGroup(surgeryAppointPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(surgeryAppointPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patientIdField, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                    .addComponent(patientNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneNumField))
                .addGap(99, 99, 99)
                .addGroup(surgeryAppointPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(anestheIdField)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nurseNameField)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nurseIdField, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(docNameField)
                    .addComponent(docIdField)
                    .addComponent(anestheNameField)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(96, 96, 96)
                .addGroup(surgeryAppointPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f_nameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f_typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sur_locationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roomComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(surgeryAppointPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(bookRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(timeField, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(701, Short.MAX_VALUE))
        );
        surgeryAppointPanelLayout.setVerticalGroup(
            surgeryAppointPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(surgeryAppointPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(surgeryAppointPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(surgeryAppointPanelLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(f_typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel28)
                        .addGap(12, 12, 12)
                        .addComponent(f_nameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(sur_locationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roomComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(surgeryAppointPanelLayout.createSequentialGroup()
                        .addGroup(surgeryAppointPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(surgeryAppointPanelLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(16, 16, 16))
                            .addGroup(surgeryAppointPanelLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)))
                        .addGroup(surgeryAppointPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(surgeryAppointPanelLayout.createSequentialGroup()
                                .addComponent(nurseNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(nurseIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(surgeryAppointPanelLayout.createSequentialGroup()
                                .addComponent(patientNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(patientIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(surgeryAppointPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(surgeryAppointPanelLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(docNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(surgeryAppointPanelLayout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addGap(18, 18, 18)
                                .addComponent(phoneNumField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(docIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(surgeryAppointPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(surgeryAppointPanelLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(anestheNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(anestheIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(surgeryAppointPanelLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(timeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(bookRoom)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton10)
                                .addGap(59, 59, 59)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(690, Short.MAX_VALUE))
        );

        StaffList.addTab("Surgery Appoint.", surgeryAppointPanel);

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel38.setText("Surgery Appointment's List");

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Surgery ID", "Patient ID", "Patient Name", "Phone Number", "Nurse ID", "Nurse Name", "Doctor ID", "Doctor Name", "Anesthesiologist Name", "Anesthesiologist ID", "Equipment Type", "Equipment Name", "Location", "Room ID", "Date", "Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable8MouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jTable8);

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setText("Search by Patient ID or Surgery ID:");

        searchSaList.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        searchSaList.setText("Search");
        searchSaList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchSaListActionPerformed(evt);
            }
        });

        deleteSaListButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        deleteSaListButton.setText("Delete");
        deleteSaListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSaListButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 1167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchSAL, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchSaList)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteSaListButton)))
                .addContainerGap(691, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel38)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(searchSAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchSaList)
                    .addComponent(deleteSaListButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(642, Short.MAX_VALUE))
        );

        StaffList.addTab("Surgery Appoint. List", jPanel1);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Search by receipt ID: ");

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Receipt ID", "Customer ID", "Date", "Time", "Amount (RM)", "Payment Method", "Location", "Clinic Contact No."
            }
        ));
        jScrollPane7.setViewportView(jTable7);

        billSearch.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        billSearch.setText("Search");
        billSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                billSearchActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setText("Receipt ID");

        receiptIdField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receiptIdFieldActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setText("Customer ID");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setText("Date");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setText("Time (HH:mm:ss)");

        payTimeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payTimeFieldActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setText("Amount  (RM)");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setText("Payment Method");

        paymentMethodComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Debit Card", "Credit Card", "TNG eWallet", "Online Banking", " " }));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setText("Location");

        payLocationComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Clinic XXX Serdang", "Clinic XXX Kajang", "Clinic XXX Puchong" }));

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setText("Clinic Contact No.");

        customerPhoneNumField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerPhoneNumFieldActionPerformed(evt);
            }
        });

        addBillButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addBillButton.setText("Add");
        addBillButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBillButtonActionPerformed(evt);
            }
        });

        deleteBillButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        deleteBillButton.setText("Delete");
        deleteBillButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBillButtonActionPerformed(evt);
            }
        });

        clearBillButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        clearBillButton.setText("Clear");
        clearBillButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBillButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout billsPanelLayout = new javax.swing.GroupLayout(billsPanel);
        billsPanel.setLayout(billsPanelLayout);
        billsPanelLayout.setHorizontalGroup(
            billsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(billsPanelLayout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addGroup(billsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 921, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(billsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(billCodeSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(billSearch)
                        .addGap(18, 18, 18)
                        .addComponent(deleteBillButton)))
                .addGap(84, 84, 84)
                .addGroup(billsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(billsPanelLayout.createSequentialGroup()
                        .addComponent(addBillButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clearBillButton))
                    .addComponent(customerPhoneNumField)
                    .addComponent(payLocationComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paymentMethodComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(amountField)
                    .addComponent(payTimeField)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(customerIdField)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(receiptIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(578, Short.MAX_VALUE))
        );
        billsPanelLayout.setVerticalGroup(
            billsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(billsPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(billsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(billsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(18, 18, 18)
                        .addComponent(receiptIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel31)
                        .addGap(18, 18, 18)
                        .addComponent(customerIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel32)
                        .addGap(24, 24, 24)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel33)
                        .addGap(18, 18, 18)
                        .addComponent(payTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel34)
                        .addGap(18, 18, 18)
                        .addComponent(amountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel35)
                        .addGap(18, 18, 18)
                        .addComponent(paymentMethodComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel36)
                        .addGap(18, 18, 18)
                        .addComponent(payLocationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel37)
                        .addGap(18, 18, 18)
                        .addComponent(customerPhoneNumField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(billsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addBillButton)
                            .addComponent(clearBillButton)))
                    .addGroup(billsPanelLayout.createSequentialGroup()
                        .addGroup(billsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(billCodeSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(billSearch)
                            .addComponent(deleteBillButton))
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane7)))
                .addContainerGap(678, Short.MAX_VALUE))
        );

        StaffList.addTab("Receipt", billsPanel);

        getContentPane().add(StaffList, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logoutActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exitActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        try {
            // Fetch and populate user data
            ResultSet rsUser = Select.getData("select *from user");
            DefaultTableModel userModel = (DefaultTableModel) userTable.getModel();
            userModel.setRowCount(0);

            while (rsUser.next()) {
                userModel.addRow(new Object[]{
                    rsUser.getString(1),
                    rsUser.getString(2),
                    rsUser.getString(3),
                    rsUser.getString(4),
                    rsUser.getString(5),
                    rsUser.getString(6),
                    rsUser.getString(7),
                    rsUser.getString(8),
                    rsUser.getString(9)
                });
            }
            rsUser.close();

            // Fetch and populate room data
            ResultSet rsRooms = Select.getData("SELECT * FROM room");
            DefaultTableModel roomModel = (DefaultTableModel) jTable2.getModel();
            roomModel.setRowCount(0);

            while (rsRooms.next()) {
                roomModel.addRow(new Object[]{
                    rsRooms.getString(1),
                    rsRooms.getString(2),
                    rsRooms.getString(3)
                });
            }
            rsRooms.close();

            // Fetch and populate facilities data
            ResultSet rsFacilities = Select.getData("SELECT * FROM facilities");
            DefaultTableModel facilitiesModel = (DefaultTableModel) jTable6.getModel();
            facilitiesModel.setRowCount(0); // Resetting the facilities table

            while (rsFacilities.next()) {
                facilitiesModel.addRow(new Object[]{
                    rsFacilities.getString(1),
                    rsFacilities.getString(2),
                    rsFacilities.getString(3),
                    rsFacilities.getString(4)
                });
            }
            rsFacilities.close();
            
            // Fetch and populate bills data
            ResultSet rsBill = Select.getData("SELECT * FROM bill");
            DefaultTableModel billModel = (DefaultTableModel) jTable7.getModel();
            billModel.setRowCount(0); // Resetting the facilities table

            while (rsBill.next()) {
                billModel.addRow(new Object[]{
                    rsBill.getString(1),
                    rsBill.getString(2),
                    rsBill.getString(3),
                    rsBill.getString(4),
                    rsBill.getString(5),
                    rsBill.getString(6),
                    rsBill.getString(7),
                    rsBill.getString(8)
                });
            }
            rsBill.close();
            
            // Fetch and populate surgery appointments data
            ResultSet rsCustomer = Select.getData("SELECT * FROM customer");
            DefaultTableModel customerModel = (DefaultTableModel) jTable8.getModel();
            customerModel.setRowCount(0); // Resetting the facilities table

            while (rsCustomer.next()) {
                customerModel.addRow(new Object[]{
                    rsCustomer.getString(1),
                    rsCustomer.getString(2),
                    rsCustomer.getString(3),
                    rsCustomer.getString(4),
                    rsCustomer.getString(5),
                    rsCustomer.getString(6),
                    rsCustomer.getString(7),
                    rsCustomer.getString(8),
                    rsCustomer.getString(9),
                    rsCustomer.getString(10),
                    rsCustomer.getString(11),
                    rsCustomer.getString(12),
                    rsCustomer.getString(13),
                    rsCustomer.getString(14),
                    rsCustomer.getString(15),
                    rsCustomer.getString(16)
                });
            }
            rsCustomer.close();
            
            // Fetch and populate user appointment request data
            ResultSet rsMakeAppointment = Select.getData("SELECT * FROM make_appointment");
            DefaultTableModel makeAppointmentModel = (DefaultTableModel) jTable3.getModel();
            makeAppointmentModel.setRowCount(0); // Resetting the facilities table

            while (rsMakeAppointment.next()) {
                makeAppointmentModel.addRow(new Object[]{
                    rsMakeAppointment.getString(1),
                    rsMakeAppointment.getString(2),
                    rsMakeAppointment.getString(3),
                    rsMakeAppointment.getString(4),
                    rsMakeAppointment.getString(5),
                    rsMakeAppointment.getString(6),
                    rsMakeAppointment.getString(7),
                    rsMakeAppointment.getString(8),
                    rsMakeAppointment.getString(9)
                });
            }
            rsMakeAppointment.close();
        
            
            // Fetch and populate user appointment approved data
        ResultSet rsAppointmentApproved = Select.getData("SELECT * FROM appointment_approved");
        DefaultTableModel appointmentApprovedModel = (DefaultTableModel) jTable4.getModel();
        appointmentApprovedModel.setRowCount(0); // Resetting the approved appointment table

        while (rsAppointmentApproved.next()) {
            appointmentApprovedModel.addRow(new Object[]{
                rsAppointmentApproved.getString(1),
                rsAppointmentApproved.getString(2),
                rsAppointmentApproved.getString(3),
                rsAppointmentApproved.getString(4),
                rsAppointmentApproved.getString(5),
                rsAppointmentApproved.getString(6),
                rsAppointmentApproved.getString(7),
                rsAppointmentApproved.getString(8),
                rsAppointmentApproved.getString(9)
            });
        }
        rsAppointmentApproved.close();
            
            // Fetch and populate user appointment rejected data
            ResultSet rsAppointmentRejected = Select.getData("SELECT * FROM appointment_rejected");
            DefaultTableModel appointmentRejectedModel = (DefaultTableModel) jTable5.getModel();
            appointmentRejectedModel.setRowCount(0); // Resetting the facilities table

            while (rsAppointmentRejected.next()) {
                appointmentRejectedModel.addRow(new Object[]{
                    rsAppointmentRejected.getString(1),
                    rsAppointmentRejected.getString(2),
                    rsAppointmentRejected.getString(3),
                    rsAppointmentRejected.getString(4),
                    rsAppointmentRejected.getString(5),
                    rsAppointmentRejected.getString(6),
                    rsAppointmentRejected.getString(7),
                    rsAppointmentRejected.getString(8),
                    rsAppointmentRejected.getString(9)
                });
            }
            rsAppointmentRejected.close();

            Connection con= Connection_Provider.getCon();
            Statement st=con.createStatement();
            String query = "SELECT * FROM nurse WHERE nurseId = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, nurseId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Populate text fields with the user's details
                updateID.setText(rs.getString("nurseId"));
                    updateNameField.setText(rs.getString("nurseName"));
                    updateIcField.setText(rs.getString("nurseIc"));
                    updateEmailField.setText(rs.getString("nurseEmail"));
                    updatePhoneNumField.setText(rs.getString("nursePhoneNum"));
                    updateAddressField.setText(rs.getString("nurseAddress"));

            } else {
                // Handle case where no record is found
                JOptionPane.showMessageDialog(null, "No user found with the nurse ID: " + nurseId);
            }

            // Set text fields to non-editable
            updateID.setEditable(false);

            pstmt.close();
            con.close();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

    }//GEN-LAST:event_formComponentShown

    private void clockInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clockInButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clockInButtonActionPerformed

    private void clockOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clockOutButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clockOutButtonActionPerformed

    private void updateIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateIDActionPerformed
        // TODO add your handling code here:
        updateID.setEditable(false);
    }//GEN-LAST:event_updateIDActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
            profilecontroller.updateButton();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void searchSaListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchSaListActionPerformed
        // TODO add your handling code here:
        surgerycontroller.search();
    }//GEN-LAST:event_searchSaListActionPerformed

    private void phoneNumFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneNumFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneNumFieldActionPerformed

    private void f_nameComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_nameComboBoxActionPerformed
        // TODO add your handling code here:
        facilitiesName = (String) f_nameComboBox.getSelectedItem();
        String query = "SELECT * FROM facilities WHERE name = ?";
                
        try{
            ResultSet rs = Select.getData("SELECT * FROM facilities WHERE type = '"+facilitiesName+"'");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }//GEN-LAST:event_f_nameComboBoxActionPerformed

    private void roomComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomComboBoxActionPerformed
        // TODO add your handling code here:
        roomId = (String) roomComboBox.getSelectedItem();
        try {
            ResultSet rs = Select.getData("SELECT * FROM room WHERE roomNo = '"+roomId+"'");
          
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

    }//GEN-LAST:event_roomComboBoxActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
          surgerycontroller.clear();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void bookRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookRoomActionPerformed
        // TODO add your handling code here:
        surgerycontroller.bookRoomButton();
    }//GEN-LAST:event_bookRoomActionPerformed

    private void sur_locationComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sur_locationComboBoxActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_sur_locationComboBoxActionPerformed

    private void f_typeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_typeComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_f_typeComboBoxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void timeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timeFieldActionPerformed

    private void docNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_docNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_docNameFieldActionPerformed

    private void docIdFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_docIdFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_docIdFieldActionPerformed

    private void nurseNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nurseNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nurseNameFieldActionPerformed

    private void patientIdFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientIdFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientIdFieldActionPerformed

    private void patientNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientNameFieldActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
//          facilitiescontroller.deleteButton();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
//          facilitiescontroller.addButton();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void i_locationComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_i_locationComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_i_locationComboBoxActionPerformed

    private void i_statusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_i_statusComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_i_statusComboBoxActionPerformed

    private void i_nameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_i_nameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_i_nameFieldActionPerformed

    private void i_typeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_i_typeComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_i_typeComboBoxActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
//            roomcontroller.deleteRoom();
    }//GEN-LAST:event_deleteActionPerformed

    private void locationComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locationComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_locationComboBoxActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_addActionPerformed

    private void statusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusComboBoxActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
            rejectappcontroller.searchReject();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
          appointmentlistcontroller.searchAppointmentList();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void rejectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rejectButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rejectButtonActionPerformed

    private void approveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approveButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_approveButtonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
          pendingcontroller.searchPendingAppoint();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:
          usercontroller.clearUser();
    }//GEN-LAST:event_clearActionPerformed

    private void userTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userTableMouseClicked
        // TODO add your handling code here:
           usercontroller.deleteUser();
    }//GEN-LAST:event_userTableMouseClicked

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        // TODO add your handling code here:
          usercontroller.searchUser();
    }//GEN-LAST:event_searchFieldActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void appoinListPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appoinListPanelMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_appoinListPanelMouseClicked

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTable4MouseClicked

    private void deleteAppointListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAppointListButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteAppointListButtonActionPerformed

    private void updateStatusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStatusButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateStatusButtonActionPerformed

    private void updateNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateNameFieldActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void deleteSaListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteSaListButtonActionPerformed
        // TODO add your handling code here:
        surgerycontroller.delete();
    }//GEN-LAST:event_deleteSaListButtonActionPerformed

    private void jTable8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable8MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTable8MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTable2MouseClicked

    private void clearBillButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBillButtonActionPerformed
        // TODO add your handling code here:
        billcontroller.clear();
    }//GEN-LAST:event_clearBillButtonActionPerformed

    private void deleteBillButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBillButtonActionPerformed
        // TODO add your handling code here:
        billcontroller.deleteBill();
    }//GEN-LAST:event_deleteBillButtonActionPerformed

    private void addBillButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBillButtonActionPerformed
        // TODO add your handling code here:
        billcontroller.processBill();
    }//GEN-LAST:event_addBillButtonActionPerformed

    private void customerPhoneNumFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerPhoneNumFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customerPhoneNumFieldActionPerformed

    private void payTimeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payTimeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_payTimeFieldActionPerformed

    private void receiptIdFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receiptIdFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_receiptIdFieldActionPerformed

    private void billSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_billSearchActionPerformed
        // TODO add your handling code here:
        billcontroller.searchBill();
    }//GEN-LAST:event_billSearchActionPerformed

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
            java.util.logging.Logger.getLogger(nurseHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(nurseHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(nurseHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(nurseHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        String nurseId="-";
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new nurseHome(nurseId).setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(nurseHome.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTabbedPane StaffList;
    public javax.swing.JButton add;
    private javax.swing.JButton addBillButton;
    public javax.swing.JTextField amountField;
    public javax.swing.JTextField anestheIdField;
    public javax.swing.JTextField anestheNameField;
    private javax.swing.JPanel appoinListPanel;
    public javax.swing.JButton approveButton;
    public javax.swing.JTextField billCodeSearchField;
    private javax.swing.JButton billSearch;
    private javax.swing.JPanel billsPanel;
    private javax.swing.JButton bookRoom;
    public javax.swing.JButton clear;
    private javax.swing.JButton clearBillButton;
    public javax.swing.JButton clockInButton;
    public javax.swing.JButton clockOutButton;
    public javax.swing.JTextField customerIdField;
    public javax.swing.JTextField customerPhoneNumField;
    public javax.swing.JButton delete;
    public javax.swing.JButton deleteAppointListButton;
    private javax.swing.JButton deleteBillButton;
    private javax.swing.JButton deleteSaListButton;
    public javax.swing.JTextField docIdField;
    public javax.swing.JTextField docNameField;
    public javax.swing.JButton exit;
    public javax.swing.JComboBox<String> f_nameComboBox;
    public javax.swing.JComboBox<String> f_typeComboBox;
    private javax.swing.JPanel facilitiesPanel;
    public javax.swing.JComboBox<String> i_locationComboBox;
    public javax.swing.JTextField i_nameField;
    public javax.swing.JComboBox<String> i_statusComboBox;
    public javax.swing.JComboBox<String> i_typeComboBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    public javax.swing.JButton jButton11;
    public javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    public javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    public javax.swing.JButton jButton8;
    public com.toedter.calendar.JDateChooser jDateChooser1;
    public com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    public javax.swing.JTable jTable2;
    public javax.swing.JTable jTable3;
    public javax.swing.JTable jTable4;
    public javax.swing.JTable jTable5;
    public javax.swing.JTable jTable6;
    public javax.swing.JTable jTable7;
    public javax.swing.JTable jTable8;
    public javax.swing.JTextField jTextField1;
    public javax.swing.JTextField jTextField14;
    public javax.swing.JTextField jTextField15;
    public javax.swing.JTextField jTextField16;
    public javax.swing.JComboBox<String> locationComboBox;
    public javax.swing.JButton logout;
    public javax.swing.JTextField nurseIdField;
    public javax.swing.JTextField nurseNameField;
    public javax.swing.JTextField patientIdField;
    public javax.swing.JTextField patientNameField;
    public javax.swing.JComboBox<String> payLocationComboBox;
    public javax.swing.JTextField payTimeField;
    public javax.swing.JComboBox<String> paymentMethodComboBox;
    private javax.swing.JPanel pendingAppoinPanel;
    public javax.swing.JTextField phoneNumField;
    private javax.swing.JPanel profilePanel;
    public javax.swing.JTextField receiptIdField;
    public javax.swing.JButton refreshButton;
    public javax.swing.JButton rejectButton;
    private javax.swing.JPanel rejectedPanel;
    public javax.swing.JComboBox<String> roomComboBox;
    private javax.swing.JPanel roomManagementPanel;
    public javax.swing.JTextField roomNumberField;
    public javax.swing.JButton searchField;
    public javax.swing.JTextField searchSAL;
    private javax.swing.JButton searchSaList;
    public javax.swing.JComboBox<String> statusComboBox;
    public javax.swing.JComboBox<String> sur_locationComboBox;
    private javax.swing.JPanel surgeryAppointPanel;
    public javax.swing.JTextField timeField;
    public javax.swing.JTextArea updateAddressField;
    private javax.swing.JButton updateButton;
    public javax.swing.JTextField updateEmailField;
    public javax.swing.JTextField updateID;
    public javax.swing.JTextField updateIcField;
    public javax.swing.JTextField updateNameField;
    public javax.swing.JTextField updatePhoneNumField;
    public javax.swing.JButton updateStatusButton;
    public javax.swing.JTable userTable;
    private javax.swing.JPanel usersPanel;
    // End of variables declaration//GEN-END:variables
}
