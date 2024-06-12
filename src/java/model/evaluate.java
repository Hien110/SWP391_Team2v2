package model;

/**
 *
 * @author TranHoangAnh
 */
public class evaluate {

    private String userName;
    private String image;
    private String comment;
    private int star;

    public evaluate() {
    }

    public evaluate(String userName, String image, String comment, int star) {
        this.userName = userName;
        this.image = image;
        this.comment = comment;
        this.star = star;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    @Override
    public String toString() {
        return "evaluate{" + "userName=" + userName + ", comment=" + comment + ", image=" + image + ", star=" + star + '}';
    }

}
