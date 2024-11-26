package model;

public class ProductReport {
    private int sequenceNumber; // Thêm trường số thứ tự
    private int reportProductId;
    private int userId;
    private int productId;
    private String reasonReport;

    public ProductReport( int reportProductId, int userId, int productId, String reasonReport) {
        this.reportProductId = reportProductId;
        this.userId = userId;
        this.productId = productId;
        this.reasonReport = reasonReport;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public int getReportProductId() {
        return reportProductId;
    }

    public void setReportProductId(int reportProductId) {
        this.reportProductId = reportProductId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getReasonReport() {
        return reasonReport;
    }

    public void setReasonReport(String reasonReport) {
        this.reasonReport = reasonReport;
    }
}
