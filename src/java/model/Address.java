package model;

public class Address {
    private int receiverInfoId;
    private String nameOfReceiver;
    private String phoneNumber;
    private String address;
    private int userId;

    // Getters and Setters
    public int getReceiverInfoId() {
        return receiverInfoId;
    }

    public void setReceiverInfoId(int receiverInfoId) {
        this.receiverInfoId = receiverInfoId;
    }

    public String getNameOfReceiver() {
        return nameOfReceiver;
    }

    public void setNameOfReceiver(String nameOfReceiver) {
        this.nameOfReceiver = nameOfReceiver;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Address{" + "receiverInfoId=" + receiverInfoId + ", nameOfReceiver=" + nameOfReceiver + ", phoneNumber=" + phoneNumber + ", address=" + address + ", userId=" + userId + '}';
    }
}
