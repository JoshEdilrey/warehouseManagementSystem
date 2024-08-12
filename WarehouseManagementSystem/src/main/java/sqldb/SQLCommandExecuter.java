package sqldb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import model.ArrivingProduct;
import model.Product;
import model.ProductRecord;
import model.ShippedProduct;
import model.StoredProduct;


public class SQLCommandExecuter {
    private Connection connection ;
    private Statement statement;
    
    public SQLCommandExecuter() {

    }
    
    //methods for connection and statement; to be execute every time a query is called
    public void establishConnection() {
   
		try {
			connection = ConnectionToSQL.databaseConnection();
			statement = connection.createStatement();
			if(connection == null) {
				System.out.println("Failed to establish Connection!");
			} else {
				System.out.println("Connected!");
			}
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
    }
    
    public void CloseConnection()  {
    	try {
			connection.close();
			statement.close();
			System.out.println("Connection Closed.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    // querying arriving product
    public List<ArrivingProduct> getArrivingProducts() throws SQLException {
        List<ArrivingProduct> arrivingProducts = new ArrayList<>();
        String query = "SELECT product_id, product_name, status, origin, condition, arriving_dateandtime FROM arriving_product_detail";
        ResultSet result = statement.executeQuery(query);

        while (result.next()) {
            int arriving_product_id = result.getInt("product_id");
            String product_name = result.getString("product_name");
            String status = result.getString("status");
            String origin = result.getString("origin");
            String condition = result.getString("condition");
            String arriving_dateandtime = result.getString("arriving_dateandtime");
            

            /*System.out.println(
            		"Arriving Products " +
            		"Fetched product ID: " + arriving_product_id + 
            		", name: " + product_name + 
            		", status: " + status +
            		", origin: " + origin + 
            		", condition: " + condition +
            		", arrival_dateandtime: " + arriving_dateandtime
            		);*/

            ArrivingProduct product = new ArrivingProduct(
            		arriving_product_id, 
            		product_name, 
            		status,
            		origin,
            		condition,
            		arriving_dateandtime
            		);
            
            
            arrivingProducts.add(product);
        }
        
        System.out.println("size: " + arrivingProducts.size());
        
        
        /*for(int i = 0;i < arrivingProducts.size(); i++) {
        	System.out.println(arrivingProducts.get(i).getProductName());
        }
        for (ArrivingProduct product : arrivingProducts) {
        	System.out.println(product.getProductName());
        }*/
        return arrivingProducts;
    }

    //querying stored products
    public List<StoredProduct> getStoredProducts() throws SQLException {
        List<StoredProduct> storedProducts = new ArrayList<>();
        String query = "SELECT product_id, product_name, status, shelf_location, stored_dateandtime FROM stored_product_detail";
        ResultSet result = statement.executeQuery(query);

        while (result.next()) {
        	int stored_product_id = result.getInt("product_id");
            String product_name = result.getString("product_name");
            String status = result.getString("status");
            int shelf_location = result.getInt("shelf_location");
            String stored_dateandtime = result.getString("stored_dateandtime");
            

           /* System.out.println(
            		"Stored Products " +
            		"Fetched product ID: " + stored_product_id + 
            		", name: " + product_name + 
            		", status: " + status +
            		", shelf_location: " + shelf_location + 
            		", stored_dateandtime: " + stored_dateandtime
            		);*/

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
        
        
        /*for(int i = 0;i < storedProducts.size(); i++) {
        	System.out.println(storedProducts.get(i).getProductName());
        }
        for (ArrivingProduct product : arrivingProducts) {
        	System.out.println(product.getProductName());
        }*/
        
     
        return storedProducts;
    
    }
    
    //querying Shipping products
    public List<ShippedProduct> getShippedProducts() throws SQLException {
        List<ShippedProduct> shippedProducts = new ArrayList<>();
        String query = "SELECT product_id, product_name, status, destination, courier, shipped_dateandtime FROM shipped_product_detail";
        ResultSet result = statement.executeQuery(query);

        while (result.next()) {
            int shipped_product_id = result.getInt("product_id");
            String product_name = result.getString("product_name");
            String status = result.getString("status");
            String destination = result.getString("destination");
            String courier = result.getString("courier");
            String shipped_dateandtime = result.getString("shipped_dateandtime");


            /*System.out.println(
            		"Stored Products " +
            		"Fetched product ID: " + shipped_product_id + 
            		", name: " + product_name + 
            		", status: " + status +
            		", destination: " + destination +
            		", courier: " + courier + 
            		", shipped_dateandtime: " + shipped_dateandtime
            		);*/

            ShippedProduct product = new ShippedProduct(
            		shipped_product_id, 
            		product_name, 
            		status,
            		destination,
            		courier,
            		shipped_dateandtime
            		);
            
            
            shippedProducts.add(product);
        }
        
        System.out.println("size: " + shippedProducts.size());
        
        
        /*for(int i = 0;i < shippedProducts.size(); i++) {
        	System.out.println(shippedProducts.get(i).getProductName());
        }
       for (ArrivingProduct product : arrivingProducts) {
        	System.out.println(product.getProductName());
        }*/
        
        
        return shippedProducts;
  
        
    }
    
    // collecting the retrieved record of three tables
    // summarizing to one detail
    public List<ProductRecord> loadDataToProductRecord(List<ArrivingProduct> arrivingProducts, List<StoredProduct> storedProducts, List<ShippedProduct> shippingProducts) {
        List<ProductRecord> productRecords = new ArrayList<>();
        
        for (ArrivingProduct product : arrivingProducts) {
            System.out.println("Creating ProductRecord for product ID: " + product.getProductID() + ", name: " + product.getProductName() + ", status: " + product.getStatus());
            ProductRecord productRecord = new ProductRecord(product.getProductID(), product.getProductName(), product.getStatus(), product.getDetails());
            productRecords.add(productRecord);
        }
        
        
        for (StoredProduct product : storedProducts) {
            System.out.println("Creating ProductRecord for product ID: " + product.getProductID() + ", name: " + product.getProductName() + ", status: " + product.getStatus());
            ProductRecord productRecord = new ProductRecord(product.getProductID(),product.getProductName(), product.getStatus(), product.getDetails());
            productRecords.add(productRecord);
        }
        
        
        for (ShippedProduct product : shippingProducts) {
            System.out.println("Creating ProductRecord for product ID: " + product.getProductID() + ", name: " + product.getProductName() + ", status: " + product.getStatus());
            ProductRecord productRecord = new ProductRecord(product.getProductID(),product.getProductName(), product.getStatus(), product.getDetails());
            productRecords.add(productRecord);
        }

        return productRecords;
    }


    
    
    // method for updating the already have data
    
    //updating product table
    public void updateProductTable(ProductRecord record) throws SQLException {
    	String query = "UPDATE product SET product_name = ?, status = ? WHERE product_id = ?";
    	PreparedStatement preparedStatement = connection.prepareStatement(query);
    	
    	preparedStatement.setString(1, record.getProductName());
    	preparedStatement.setString(2, record.getStatus());
    	preparedStatement.setInt(3, record.getProductID());
    	
    	int rowsAffected = preparedStatement.executeUpdate();
    	System.out.println("product table updated");
        System.out.println(rowsAffected + " row(s) updated");
    }
    
    //update arriving table
    public void updateArrivingTable(ArrivingProduct product) throws SQLException {
    	String query = "UPDATE arriving SET origin = ?, condition = ?, arriving_dateandtime = ?  WHERE product_id = ?";
    	PreparedStatement preparedStatement = connection.prepareStatement(query);
    	
    	preparedStatement.setString(1, product.getOrigin());
    	preparedStatement.setString(2, product.getCondition());
    	preparedStatement.setString(3, product.getArrivingDateandTime());
    	preparedStatement .setInt(4, product.getProductID());
    	
    	int rowsAffected = preparedStatement.executeUpdate();
    	System.out.println("arriving table updated");
        System.out.println(rowsAffected + " row(s) updated");
    }
    
  //update stored table
    public void updateStoredTable(StoredProduct product) throws SQLException {
    	String query = "UPDATE stored SET shelf_location = ?, stored_dateandtime = ?  WHERE product_id = ?";
    	PreparedStatement preparedStatement = connection.prepareStatement(query);
    	
    	preparedStatement.setInt(1, product.getShelfLocation());
    	preparedStatement.setString(2, product.getStoredDateandTime());
    	preparedStatement .setInt(4, product.getProductID());
    	
    	int rowsAffected = preparedStatement.executeUpdate();
    	System.out.println("stored table updated");
        System.out.println(rowsAffected + " row(s) updated");
    }
    
  //update shipped table
    public void updateShippedTable(ShippedProduct product) throws SQLException {
    	String query = "UPDATE shipped SET destination = ?, currier = ?, shipped_dateandtime = ?  WHERE product_id = ?";
    	PreparedStatement preparedStatement = connection.prepareStatement(query);
    	
    	preparedStatement.setString(1, product.getDestination());
    	preparedStatement.setString(2, product.getCurrier());
    	preparedStatement.setString(3, product.getShippedDateandTime());
    	preparedStatement .setInt(4, product.getProductID());
    	
    	int rowsAffected = preparedStatement.executeUpdate();
    	System.out.println("Shipped table updated");
        System.out.println(rowsAffected + " row(s) updated");
    }
    
    
    public int uniqueIDGenerator(int min, int max, List<Integer> existingNumbers) {
        Random random = new Random();
        Set<Integer> existingNumberSet = new HashSet<>(existingNumbers);

        int number;
        do {
            number = random.nextInt((max - min) + 1) + min;
        } while (existingNumberSet.contains(number));
        
        return number;
    }
    
    
    
    // method of saving new data
    
    
    // save new product
    public void insertNewDataProduct(Product product) throws SQLException {
    	String query = "INSERT INTO product (product_id, product_name, status) VALUES (?, ?, ?)";
    	PreparedStatement preparedStatement = connection.prepareStatement(query);
    	
    	preparedStatement.setInt(1, product.getProductID());
    	preparedStatement.setString(2, product.getProductName());
    	preparedStatement.setString(3, product.getStatus());
  	
    	int rowsAffected = preparedStatement.executeUpdate();
    	System.out.println("inserted new data on product table");
        System.out.println(rowsAffected + " row(s) added");
    }
    
    
    //save new arriving
    public void insertNewDataArriving(ArrivingProduct product) throws SQLException {
    	
    	String query = "INSERT INTO arriving (product_id, condition, origin, arriving_dateandtime, arriving_id) VALUES (?, ?, ?, ?, ?)";
    	PreparedStatement preparedStatement = connection.prepareStatement(query);
    	
    	
    	
    	preparedStatement.setInt(1, product.getProductID());
    	preparedStatement.setString(2, product.getCondition());
    	preparedStatement.setString(3, product.getOrigin());
    	preparedStatement.setString(4, product.getArrivingDateandTime());
    	preparedStatement.setInt(5, product.getProductID());
    	
    	int rowsAffected = preparedStatement.executeUpdate();
    	System.out.println("inserted new data on arriving table");
        System.out.println(rowsAffected + " row(s) added");
    }
    
    

    //save new stored
    public void insertNewDatastored(StoredProduct product) throws SQLException {
    	String query = "INSERT INTO stored (product_id, shelf_id, dateandtimestored, stored_id) VALUES ( ?, ?, ?, ?)";
    	PreparedStatement preparedStatement = connection.prepareStatement(query);
    	
    	preparedStatement.setInt(1, product.getProductID());
    	preparedStatement.setInt(2, product.getShelfLocation());
    	preparedStatement.setString(3, product.getStoredDateandTime());
    	preparedStatement.setInt(4, product.getProductID());
    
    	int rowsAffected = preparedStatement.executeUpdate();
    	System.out.println("inserted new data on stored table");
        System.out.println(rowsAffected + " row(s) added");
    }
    
    

    //save new shipped
    public void insertNewDataShipped(ShippedProduct product) throws SQLException {
    	String query = "INSERT INTO shipped (product_id, destination, courier, dateandtimeshipped, shipped_id) VALUES ( ?, ?, ?, ?, ?)";
    	PreparedStatement preparedStatement = connection.prepareStatement(query);
    	
    	preparedStatement.setInt(1, product.getProductID());
    	preparedStatement.setString(2, product.getDestination());
    	preparedStatement.setString(3, product.getCurrier());
    	preparedStatement.setString(4, product.getShippedDateandTime());
    	preparedStatement.setInt(5, product.getProductID());
    	
    
    	int rowsAffected = preparedStatement.executeUpdate();
    	System.out.println("inserted new data on shipped table");
        System.out.println(rowsAffected + " row(s) added");
    }
    
    
    
 // Deleting records

    public void deleteProduct(ProductRecord record) throws SQLException {
        String query = "DELETE FROM product WHERE product_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, record.getProductID());
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Deleted data on product table");
            System.out.println(rowsAffected + " row(s) deleted");
        }
    }

    // Delete arriving 
    public void deleteArriving(ProductRecord record) throws SQLException {
        String query = "DELETE FROM arriving WHERE product_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, record.getProductID());
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Deleted data on arriving table");
            System.out.println(rowsAffected + " row(s) deleted");
        }
    }

    // Delete stored 
    public void deleteStored(ProductRecord record) throws SQLException {
        String query = "DELETE FROM stored WHERE product_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, record.getProductID());
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Deleted data on stored table");
            System.out.println(rowsAffected + " row(s) deleted");
        }
    }

    // Delete shipped 
    public void deleteShipped(ProductRecord record) throws SQLException {
        String query = "DELETE FROM shipped WHERE product_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, record.getProductID());
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Deleted data on shipped table");
            System.out.println(rowsAffected + " row(s) deleted");
        }
    }
 
    
    // setter that will catch productRecord for edited data 
    // use condition for arriving, stored, shipping 
    // concatinate the detail
    // store in specific list
    // use SQL query to UPDATE  or INSERT INTO
   
    //closing statements result and connection

    
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




