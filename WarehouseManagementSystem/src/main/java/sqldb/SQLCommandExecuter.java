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

    public SQLCommandExecuter() {
        this.connection = ConnectionToSQL.getConnection();
    }
    
    // querying arriving product
    public List<ArrivingProduct> getArrivingProducts() throws SQLException {
        List<ArrivingProduct> arrivingProducts = new ArrayList<>();
        String query = "SELECT product_id, product_name, status, origin, condition, arrival_dateandtime FROM arrival_product_detail";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);

        while (result.next()) {
            int productID = result.getInt("Product_id");
            String productName = result.getString("Product_name");
            String status = result.getString("status");
            String origin = result.getString("origin");
            String condition = result.getString("condition");
            String arrival_dateandtime = result.getString("arrival_dateandtime");
            

            System.out.println(
            		"Fetched product ID: " + productID + 
            		", name: " + productName + 
            		", status: " + status +
            		", origin: " + origin + 
            		", condition: " + condition +
            		", arrival_dateandtime: " + arrival_dateandtime
            		);

            ArrivingProduct product = new ArrivingProduct(
            		productID, 
            		productName, 
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
    
    
    //
    public List<StoredProduct> getArrivingProducts() throws SQLException {
        List<ArrivingProduct> arrivingProducts = new ArrayList<>();
        String query = "SELECT product_id, product_name, status, origin, condition, arrival_dateandtime FROM arrival_product_detail";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);

        while (result.next()) {
            int productID = result.getInt("Product_id");
            String productName = result.getString("Product_name");
            String status = result.getString("status");
            String origin = result.getString("origin");
            String condition = result.getString("condition");
            String arrival_dateandtime = result.getString("arrival_dateandtime");
            

            System.out.println(
            		"Fetched product ID: " + productID + 
            		", name: " + productName + 
            		", status: " + status +
            		", origin: " + origin + 
            		", condition: " + condition +
            		", arrival_dateandtime: " + arrival_dateandtime
            		);

            ArrivingProduct product = new ArrivingProduct(
            		productID, 
            		productName, 
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    public List<ProductRecord> loadDataToProductRecord(List<ArrivingProduct> arrivingProducts) {
        List<ProductRecord> productRecords = new ArrayList<>();
        for (ArrivingProduct product : arrivingProducts) {
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




