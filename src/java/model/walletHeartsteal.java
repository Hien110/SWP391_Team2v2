/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class walletHeartsteal {
    private int walletid;
    private int userid;
    private float surplus;

    public walletHeartsteal() {
    }

    
    public walletHeartsteal(int walletid, int userid, float surplus) {
        this.walletid = walletid;
        this.userid = userid;
        this.surplus = surplus;
    }

    public int getWalletid() {
        return walletid;
    }

    public void setWalletid(int walletid) {
        this.walletid = walletid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public float getSurplus() {
        return surplus;
    }

    public void setSurplus(float surplus) {
        this.surplus = surplus;
    }

    @Override
    public String toString() {
        return "walletHeartsteal{" + "walletid=" + walletid + ", userid=" + userid + ", surplus=" + surplus + '}';
    }
    
    
}
