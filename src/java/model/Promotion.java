/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DELL
 */
public class Promotion {
    
    private int promotionId;
    private String promotionName;
    private int percentPromotion;
    private int quantity;
    private String description;
    private String startDate;
    private String endDate;

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public int getPercentPromotion() {
        return percentPromotion;
    }

    public void setPercentPromotion(int percentPromotion) {
        this.percentPromotion = percentPromotion;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Promotion{" + "promotionId=" + promotionId + ", promotionName=" + promotionName + ", percentPromotion=" + percentPromotion + ", quantity=" + quantity + ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + '}';
    }
    
    
    
}
