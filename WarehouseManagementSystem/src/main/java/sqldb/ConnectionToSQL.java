package sqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectionToSQL {
	 static String  sqlurl = "jdbc:sqlserver://LAPTOP-S4DGMEM3\\WAREHOUSEMS;database=WarehouseManagementdb;username=sa;password=password1234;portNumber=1433;trustServerCertificate=true";

	public static Connection databaseConnection() {
		try {
			 return DriverManager.getConnection(sqlurl);
		} catch (SQLException e) {
			 throw new RuntimeException("Error connecting to the database", e);
		}
		
	}
	
}





	//connection.close();
	


/*	
 * 
 * Statement statement = connection.createStatement();
		
		String query = "SELECT * FROM nameDetails";
ResultSet result  = statement.executeQuery(query);

while(result.next()) {
	int id = result.getInt("name_id");
	String first_name = result.getString("first_name");
	String last_name = result.getString("last_name");
	
	
	System.out.println("id " + id + " first name " + first_name + " last name " + last_name);
}
		statement.close();
		result.close();
		
		
		
		
		
		
		
		
		
		
		
			Statement statement = connection.createStatement();
			
			String query = "SELECT Product_name, Category, Status FROM Product";
			ResultSet result  = statement.executeQuery(query);
			
			
			while(result.next()) {
				String id = result.getString("Product_name");
				String first_name = result.getString("Category");
				String last_name = result.getString("Status");
				
				
				System.out.println("id " + id + " first name " + first_name + " last name " + last_name);
			}
			
			
	
*/	