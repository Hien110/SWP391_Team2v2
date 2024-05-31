/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class User {
    private int userid;
    private String username;
    private String email;
    private String password;
    private int roleid;
    private String imgavt;
    private String imgbg;
    private boolean banstatus;

    public User(int userid, String username, String email, String password, int roleid, String imgavt, String imgbg, boolean banstatus) {
        this.userid = userid;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roleid = roleid;
        this.imgavt = imgavt;
        this.imgbg = imgbg;
        this.banstatus = banstatus;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    
    public User(String password, String email){
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

    public String getImgbg() {
        return imgbg;
    }

    public void setImgbg(String imgbg) {
        this.imgbg = imgbg;
    }

    public boolean isBanstatus() {
        return banstatus;
    }

    public void setBanstatus(boolean banstatus) {
        this.banstatus = banstatus;
    }

    @Override
    public String toString() {
        return "User{" + "userid=" + userid + ", username=" + username + ", email=" + email + ", password=" + password + ", roleid=" + roleid + ", imgavt=" + imgavt + ", imgbg=" + imgbg + ", banstatus=" + banstatus + '}';
    }
    
    
    
            
}
