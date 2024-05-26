package model;

public class ProductRecord {
	private int product_id;
    private String product_name;
    private String status;
    private String details;

    public ProductRecord(String product_name, String status, String details) {
        this.product_name = product_name;
        this.status = status;
        this.details = details;
    }
    
    public ProductRecord(int product_id, String product_name, String status, String details) {
        this.product_id = product_id;
    	this.product_name = product_name;
        this.status = status;
        this.details = details;
    }
    
    
    
    public int getProductID() {return product_id;}
    public void setProductID(int product_id) {this.product_id = product_id;}

    public String getProductName() {return product_name;}
    public void setProductName(String product_name) {this.product_name = product_name;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public String getDetails() {return details;}
    public void setDetails(String details) {this.details = details;}

    
    public ArrivingProduct arrivingSplitter(String status, String longDetails) {
    	String[] details = longDetails.split(", ");
    	ArrivingProduct arriving = new ArrivingProduct(product_id, product_name, status);
    	
    	arriving.setOrigin(details[0].split(": ")[1]);
		arriving.setCondition(details[1].split(": ")[1]);
		arriving.setArrivalDateandTime(details[2].split(": ")[1]);
		
		return arriving;	
    }
    
    public StoredProduct storedSplitter(String status, String longDetails) {
    	String[] details = longDetails.split(", ");
    	StoredProduct stored = new StoredProduct(product_id, product_name, status);
    	
    	stored.setShelfLocation(Integer.parseInt(details[0].split(": ")[1]));
    	stored.setStoredDateandTime(details[1].split(": ")[1]);
    	
		return stored;	
    }
   
    public ShippedProduct shippedSplitter(String status, String longDetails) {
    	String[] details = longDetails.split(", ");
    	ShippedProduct shipped = new ShippedProduct(product_id, product_name, status);
    	
    	shipped.setDestination(details[0].split(": ")[1]);
    	shipped.setCurrier(details[1].split(": ")[1]);
    	shipped.setShippedDateandTime(details[2].split(": ")[1]);
		
		return shipped;	
    }

}


