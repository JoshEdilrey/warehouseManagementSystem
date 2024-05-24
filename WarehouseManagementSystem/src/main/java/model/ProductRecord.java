package model;

public class ProductRecord {
    private String productName;
    private String status;
    private String details;

    public ProductRecord(String productName, String status, String details) {
        this.productName = productName;
        this.status = status;
        this.details = details;
    }

    public String getProductName() {return productName;}
    public void setProductName(String productName) {this.productName = productName;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public String getDetails() {return details;}
    public void setDetails(String details) {this.details = details;}
}


