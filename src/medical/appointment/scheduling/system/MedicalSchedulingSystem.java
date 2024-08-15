/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package medical.appointment.scheduling.system;

import View.Login;

/**
 *
 * @author Cheong Wei San
 */
public class MedicalSchedulingSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //login
        Login LoginFrame=new Login();
        LoginFrame.setVisible(true);
        LoginFrame.pack();
        LoginFrame.setLocationRelativeTo(null);
        
        //sign up
//        SignUp SignUpFrame= new SignUp();
//        SignUpFrame.setVisible(true);
//        SignUpFrame.pack();
//        SignUpFrame.setLocationRelativeTo(null);
        
        
    }
    
}
