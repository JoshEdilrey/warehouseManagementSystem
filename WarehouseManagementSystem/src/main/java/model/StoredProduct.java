package model;

public class StoredProduct extends Product{
	private String shelf_location, stored_dateandtime;
	
	StoredProduct(int product_id, 
			String product_name, 
			String status,
			String shelf_location,
			String stored_dateandtime) {
		
		super(product_id, product_name, status);
		// TODO Auto-generated constructor stub
		this.shelf_location = shelf_location;
		this.stored_dateandtime = stored_dateandtime;
		
	}
	
	public String getShelfLocation() {return shelf_location;}
    public void setShelfLocation(String shelf_location) {this.shelf_location = shelf_location;}
	
    public String getStoredDateandTime() {return stored_dateandtime;}
    public void setStoredDateandTime(String stored_dateandtime) {this.stored_dateandtime = stored_dateandtime;}
	
    public String getDetail() {
    	return "Shelf Location: " + shelf_location + ", Stored Date and Time: " + stored_dateandtime;
    }
	
	
	
	
	
	

}
