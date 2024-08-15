/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;


import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Cheong Wei San
 */
public class Connection_Provider {
    public static Connection getCon()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","murberry02");
            return con;
        }
        catch(Exception e){
            return null;
        }
    }
}
