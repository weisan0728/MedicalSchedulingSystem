/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.util.Date;

/**
 *
 * @author Cheong Wei San
 */
public class Appointment {
    private String name;
    private String phoneNum;
    private Date date;
    private String time;
    private String clinic;
    private String reason;
    private String specialNeeds;

    // Constructor
    public Appointment(String name, String phoneNum, Date date, String time, String clinic, String reason, String specialNeeds) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.date = date;
        this.time = time;
        this.clinic = clinic;
        this.reason = reason;
        this.specialNeeds = specialNeeds;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSpecialNeeds() {
        return specialNeeds;
    }

    public void setSpecialNeeds(String specialNeeds) {
        this.specialNeeds = specialNeeds;
    }
}
