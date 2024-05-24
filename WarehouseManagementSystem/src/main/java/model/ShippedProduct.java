package model;

public class ShippedProduct extends Product{
	private String destination, currier, shipped_dateandtime;
	
	ShippedProduct(int product_id, 
			String product_name, 
			String status,
			String destination,
			String currier,
			String shipped_dateandtime
			) {
	
		super(product_id, product_name, status);
		// TODO Auto-generated constructor stub
		
		this.destination = destination;
		this.currier = currier;
		this.shipped_dateandtime = shipped_dateandtime;
		
	}
	
	public String getDestination() {return destination;}
    public void setDestination(String destination) {this.destination = destination;}
	
    public String getCurrier() {return currier;}
    public void setCurrier(String currier) {this.currier = currier;}
	
    public String getShippedDateandTime() {return shipped_dateandtime;}
    public void setShippedDateandTime(String shipped_dateandtime) {this.shipped_dateandtime = shipped_dateandtime;}
	
    public String getDetail() {
    	return "destination: " + destination + ", currier: " + currier  + ", Shipped Date and Time: " + shipped_dateandtime;
    }
	
	
	
	
	
}
