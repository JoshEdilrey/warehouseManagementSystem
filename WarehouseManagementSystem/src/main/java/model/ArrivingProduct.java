package model;

public class ArrivingProduct extends Product{
	private String origin, condition, arriving_dateandtime;
	
	
	public ArrivingProduct(int product_id, 
					String product_name, 
					String status,
					String origin,
					String condition,
					String arrival_dateandtime
					) {
		
		super(product_id, product_name, status);
		// TODO Auto-generated constructor stub
		
		this.origin = origin;
		this.condition = condition;
		this.arriving_dateandtime = arrival_dateandtime;
	}
	
	public ArrivingProduct(int product_id, 
			String product_name, 
			String status
			) {

	super(product_id, product_name, status);
	this.origin = "N/A";
	this.condition = "N/A";
	this.arriving_dateandtime = "N/A";
	}
	
	
	 @Override
	    public int getProductID() {
	        return super.getProductID();
	    }
	 
	 @Override
	    public String getProductName() {
	        return super.getProductName();
	    }

	 @Override
	    public String getStatus() {
	        return super.getStatus();
	    }
	 
	public String getOrigin() {return origin;}
    public void setOrigin(String origin) {this.origin = origin;}
	
    public String getCondition() {return condition;}
    public void setCondition(String condition) {this.condition = condition;}
	
    public String getArrivingDateandTime() {return arriving_dateandtime;}
    public void setArrivalDateandTime(String arrival_dateandtime) {this.arriving_dateandtime = arrival_dateandtime;}
	
    public String getDetails() {
    	return "Origin: " + origin + ", Condition:  " + condition +  ", Arrival Date: " + arriving_dateandtime;
    }
	
    
    
    
    
    
    
    
    
    
    
    
}
