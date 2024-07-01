/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class productColors {
    private String colors;

    public productColors(String colors) {
        this.colors = colors;
    }

    public productColors() {
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    @Override
    public String toString() {
        return "productColors{" + "colors=" + colors + '}';
    }
   
}
