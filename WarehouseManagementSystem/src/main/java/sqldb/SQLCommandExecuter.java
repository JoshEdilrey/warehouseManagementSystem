package sqldb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.ArrivingProduct;
import model.ProductRecord;
import model.ShippingProduct;
import model.StoredProduct;

public class SQLCommandExecuter {
    private Connection connection;

    public SQLCommandExecuter() {
        this.connection = ConnectionToSQL.getConnection();
    }
    
    // querying arriving product
    public List<ArrivingProduct> getArrivingProducts() throws SQLException {
        List<ArrivingProduct> arrivingProducts = new ArrayList<>();
        String query = "SELECT arrive_product_id, product_name, status, origin, condition, arrival_dateandtime FROM arrival_product_detail";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);

        while (result.next()) {
            int arrive_product_id = result.getInt("arrive_product_id");
            String product_name = result.getString("product_name");
            String status = result.getString("status");
            String origin = result.getString("origin");
            String condition = result.getString("condition");
            String arrival_dateandtime = result.getString("arrival_dateandtime");
            

            System.out.println(
            		"Arriving Products " +
            		"Fetched product ID: " + arrive_product_id + 
            		", name: " + product_name + 
            		", status: " + status +
            		", origin: " + origin + 
            		", condition: " + condition +
            		", arrival_dateandtime: " + arrival_dateandtime
            		);

            ArrivingProduct product = new ArrivingProduct(
            		arrive_product_id, 
            		product_name, 
            		status,
            		origin,
            		condition,
            		arrival_dateandtime
            		);
            
            
            arrivingProducts.add(product);
        }
        
        System.out.println("size: " + arrivingProducts.size());
        
        
        for(int i = 0;i < arrivingProducts.size(); i++) {
        	System.out.println(arrivingProducts.get(i).getProductName());
        }
        /*for (ArrivingProduct product : arrivingProducts) {
        	System.out.println(product.getProductName());
        }*/
        return arrivingProducts;
    }
    
    
    //querying stored products
    public List<StoredProduct> getStoredProducts() throws SQLException {
        List<StoredProduct> storedProducts = new ArrayList<>();
        String query = "SELECT stored_product_id, product_name, status, shelf_location, stored_dateandtime FROM stored_product_detail";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);

        while (result.next()) {
        	int stored_product_id = result.getInt("stored_product_id");
            String product_name = result.getString("product_name");
            String status = result.getString("status");
            String shelf_location = result.getString("shelf_location");
            String stored_dateandtime = result.getString("stored_dateandtime");
            

            System.out.println(
            		"Stored Products " +
            		"Fetched product ID: " + stored_product_id + 
            		", name: " + product_name + 
            		", status: " + status +
            		", shelf_location: " + shelf_location + 
            		", stored_dateandtime: " + stored_dateandtime
            		);

            StoredProduct product = new StoredProduct(
            		stored_product_id, 
            		product_name, 
            		status,
            		shelf_location,
            		stored_dateandtime
            		);
            
            
            storedProducts.add(product);
        }
        
        System.out.println("size: " + storedProducts.size());
        
        
        for(int i = 0;i < storedProducts.size(); i++) {
        	System.out.println(storedProducts.get(i).getProductName());
        }
        /*for (ArrivingProduct product : arrivingProducts) {
        	System.out.println(product.getProductName());
        }*/
        return storedProducts;
    }
    
  //querying Shipping products
    public List<ShippingProduct> getShippingProducts() throws SQLException {
        List<ShippingProduct> shippingProducts = new ArrayList<>();
        String query = "SELECT shipping_product_id, product_name, status, destination, courier, shipping_dateandtime FROM shipping_product_detail";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);

        while (result.next()) {
            int shipping_product_id = result.getInt("shipping_product_id");
            String product_name = result.getString("product_name");
            String status = result.getString("status");
            String destination = result.getString("destination");
            String courier = result.getString("courier");
            String shipping_dateandtime = result.getString("shipping_dateandtime");


            System.out.println(
            		"Stored Products " +
            		"Fetched product ID: " + shipping_product_id + 
            		", name: " + product_name + 
            		", status: " + status +
            		", destination: " + destination +
            		", courier: " + courier + 
            		", shipping_dateandtime: " + shipping_dateandtime
            		);

            ShippingProduct product = new ShippingProduct(
            		shipping_product_id, 
            		product_name, 
            		status,
            		destination,
            		courier,
            		shipping_dateandtime
            		);
            
            
            shippingProducts.add(product);
        }
        
        System.out.println("size: " + shippingProducts.size());
        
        
        for(int i = 0;i < shippingProducts.size(); i++) {
        	System.out.println(shippingProducts.get(i).getProductName());
        }
        /*for (ArrivingProduct product : arrivingProducts) {
        	System.out.println(product.getProductName());
        }*/
        return shippingProducts;
    }
    

    public List<ProductRecord> loadDataToProductRecord(List<ArrivingProduct> arrivingProducts, List<StoredProduct> storedProducts, List<ShippingProduct> shippingProducts) {
        List<ProductRecord> productRecords = new ArrayList<>();
        for (ArrivingProduct product : arrivingProducts) {
            System.out.println("Creating ProductRecord for product ID: " + product.getProductID() + ", name: " + product.getProductName() + ", status: " + product.getStatus());
            ProductRecord productRecord = new ProductRecord(product.getProductName(), product.getStatus(), product.getDetails());
            productRecords.add(productRecord);
        }
        
        
        for (StoredProduct product : storedProducts) {
            System.out.println("Creating ProductRecord for product ID: " + product.getProductID() + ", name: " + product.getProductName() + ", status: " + product.getStatus());
            ProductRecord productRecord = new ProductRecord(product.getProductName(), product.getStatus(), product.getDetails());
            productRecords.add(productRecord);
        }
        
        for (ShippingProduct product : shippingProducts) {
            System.out.println("Creating ProductRecord for product ID: " + product.getProductID() + ", name: " + product.getProductName() + ", status: " + product.getStatus());
            ProductRecord productRecord = new ProductRecord(product.getProductName(), product.getStatus(), product.getDetails());
            productRecords.add(productRecord);
        }

        return productRecords;
    }

    private String getProductDetails(int productID) {
        // Implement the method to retrieve product details from the database
        // ...

        System.out.println("Fetched product details for product ID: " + productID);

        return "Product details for ID " + productID;
    }
    
 


}










/*
 
 
 
 package sqldb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.ArrivingProduct;
import model.ProductRecord;

public class SQLCommandExecuter {
	private Connection connection;
	
	
	public SQLCommandExecuter(){
		this.connection = ConnectionToSQL.getConnection();
		
	}
	
	
	//method for querying stored products
	//method for querying shipped products
	
	
	//querying arriving products
	public ArrayList<ArrivingProduct> getArrivingProduct() throws SQLException {
		
		ArrayList<ArrivingProduct> arrivingProducts = new ArrayList<>();
		String query = "SELECT Product_id, Product_name, 'Arriving' AS status FROM Product";
		Statement statement = connection.createStatement();
		ResultSet result  = statement.executeQuery(query);
		
		while(result.next()) {
			arrivingProducts.add(new ArrivingProduct(
				result.getInt("Product_id"),
				result.getString("Product_name"),
				"Arriving"
				));
			System.out.println("Fetched Product ID: " + result.getInt("Product_id") + ", Product Name: " + result.getString("Product_name"));
		}
		
		for (ArrivingProduct product : arrivingProducts) {
			System.out.println(product.getProductName() +" "+ product.getProductID());
		}
		return arrivingProducts;
	}
	
	
	//adding the arriving, stored, and shipped product to product record using getDetails
	public ArrayList<ProductRecord> loadDataToProductRecord(ArrayList<ArrivingProduct> arrivingProducts){
		ArrayList<ProductRecord> productRecords = new ArrayList<>();
		for (ArrivingProduct product : arrivingProducts) {
            ProductRecord productRecord = new ProductRecord(product.getProductName(), 
            												product.getStatus(), 
            												product.getDetails());
            productRecords.add(productRecord);
        }
		for (ProductRecord  record: productRecords) {
			System.out.println(record.getProductName());
		}
		
		return productRecords;
	}
}

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 */








	


/*
public ArrayList<ProductRecord> SQLConnection() {
	
	ArrayList<ProductRecord> arrivingProducts = new ArrayList<>();

	try {
		
		
		
		
		

		
		}
		
		
		
		connection.close();
	} catch (SQLException e) {
		
		
	}
	return arrivingProducts;
}
*/




