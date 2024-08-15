package Controller;

import Model.PatientProfileModel;
import Model.UserModel;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class PatientProfileController {
   private PatientProfileModel model;
    private JLabel profile_fname;
    private JLabel profile_lname;
    private JLabel profile_ID;
    private JLabel profile_gender;
    private JLabel profile_ICorPassport;
    private JLabel profile_phoneNum;
    private JLabel profile_email;
    private JLabel profile_address;

    public PatientProfileController(
        JLabel profile_fname,
        JLabel profile_lname,
        JLabel profile_ID,
        JLabel profile_gender,
        JLabel profile_ICorPassport,
        JLabel profile_phoneNum,
        JLabel profile_email,
        JLabel profile_address
    ) {
        this.model = new PatientProfileModel();
        this.profile_fname = profile_fname;
        this.profile_lname = profile_lname;
        this.profile_ID = profile_ID;
        this.profile_gender = profile_gender;
        this.profile_ICorPassport = profile_ICorPassport;
        this.profile_phoneNum = profile_phoneNum;
        this.profile_email = profile_email;
        this.profile_address = profile_address;
    }

    public void handleProfileLoadActionPerformed(ActionEvent evt, String phoneNumber) {
        model.setUserPhoneNumber(phoneNumber);
        model.fetchUserID();
        if (model.profile()) {
            profile_fname.setText(model.getFname());
            profile_lname.setText(model.getLname());
            profile_ID.setText(model.getUserID());
            profile_gender.setText(model.getGender());
            profile_ICorPassport.setText(model.getICorPassport());
            profile_phoneNum.setText(model.getPhoneNum());
            profile_email.setText(model.getEmail());
            profile_address.setText(model.getAddress());
        }
    }

}
