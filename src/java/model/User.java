/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class User {

    private int userid;
    private String username;
    private String fullname;
    private String phonenumber;
    private Boolean gender;
    private String dob;
    private String email;
    private String password;
    private int roleid;
    private String imgavt;
    private String bankname;
    private String banknumber;
    private String emailpaypal;
    private Boolean banstatus;

    public User(int userid, String username, String fullname, String phonenumber, Boolean gender, String dob, String email, String password, int roleid, String imgavt, String bankname, String banknumber, String emailpaypal, Boolean banstatus) {
        this.userid = userid;
        this.username = username;
        this.fullname = fullname;
        this.phonenumber = phonenumber;
        this.gender = gender;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.roleid = roleid;
        this.imgavt = imgavt;
        this.bankname = bankname;
        this.banknumber = banknumber;
        this.emailpaypal = emailpaypal;
        this.banstatus = banstatus;
    }

    public User(int userid, String fullname, String phonenumber, Boolean gender, String dob) {
        this.userid = userid;
        this.fullname = fullname;
        this.phonenumber = phonenumber;
        this.gender = gender;
        this.dob = dob;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public User() {
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getImgavt() {
        return imgavt;
    }

    public void setImgavt(String imgavt) {
        this.imgavt = imgavt;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBanknumber() {
        return banknumber;
    }

    public void setBanknumber(String banknumber) {
        this.banknumber = banknumber;
    }

    public String getEmailpaypal() {
        return emailpaypal;
    }

    public void setEmailpaypal(String emailpaypal) {
        this.emailpaypal = emailpaypal;
    }

    public Boolean isBanstatus() {
        return banstatus;
    }

    public void setBanstatus(Boolean banstatus) {
        this.banstatus = banstatus;
    }

    @Override
    public String toString() {
        return "User{" + "userid=" + userid + ", username=" + username + ", fullname=" + fullname + ", phonenumber=" + phonenumber + ", gender=" + gender + ", dob=" + dob + ", email=" + email + ", password=" + password + ", roleid=" + roleid + ", imgavt=" + imgavt + ", bankname=" + bankname + ", banknumber=" + banknumber + ", emailpaypal=" + emailpaypal + ", banstatus=" + banstatus + '}';
    }

}
