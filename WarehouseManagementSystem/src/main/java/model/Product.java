package model;

public class Product {
	private int product_id;
	private String product_name, status;
	
	
	Product(int product_id,
			String product_name,
			String status){
	
	this.product_id = product_id;
	this.product_name = product_name;
	this.status = status;
	
	}
	
	public int getProductID() {return product_id;}
    public void setProductID(int product_id) {this.product_id = product_id;}
	
	public String getProductName() {return product_name;}
    public void setProductName(String product_name) {this.product_name = product_name;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

}
