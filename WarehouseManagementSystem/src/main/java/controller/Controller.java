package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import gui.Dashboard_Final;
import model.ArrivingProduct;
import model.ProductRecord;
import model.ShippedProduct;
import model.StoredProduct;
import sqldb.SQLCommandExecuter;

public class Controller {
	private Dashboard_Final view;
	private SQLCommandExecuter model;
	
	public Controller(SQLCommandExecuter model) {
		this.model = model;
		
		
	}
	
	public List<ProductRecord> loadDataFromDB() {
		
		
		model.establishConnection();
		try {
			
			List<ProductRecord> productRecord = model.loadDataToProductRecord(model.getArrivingProducts(), model.getStoredProducts(), model.getShippedProducts());
			model.CloseConnection();
			return productRecord;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "An error occurred: around loadDataFromDB " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);		
			model.CloseConnection();
			return new ArrayList<ProductRecord>();
		}
		
	}
	
	//update the existing record on the database
	public void updateDBRecord(ProductRecord record) {
		
		model.establishConnection();
		
		try {
			
			
			
			
			
			
			
			switch (record.getStatus()) {
			case "Arriving":
				ArrivingProduct arriving = record.arrivingSplitter(record.getStatus(), record.getDetails());
				model.updateArrivingTable(arriving);
				break;
			case "Stored":
				StoredProduct stored = record.storedSplitter(record.getStatus(), record.getDetails());
				model.updateStoredTable(stored);
				break;
			case "Shipped":
				ShippedProduct shipped = record.shippedSplitter(record.getStatus(), record.getDetails());
				model.updateShippedTable(shipped);
				break;
			}
			model.updateProductTable(record);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "An error occurred around updateDBRecord: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		model.CloseConnection();
	}
	
	
	public void addNewArriving(ArrivingProduct arriving) {
		
		model.establishConnection();
		try {
			model.insertNewDataProduct(arriving);
			model.insertNewDataArriving(arriving);
		} catch (SQLException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "An error occurred around addNewArriving:" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		model.CloseConnection();
	}
	
		
	public void addNewStored(StoredProduct stored) {
			model.establishConnection();
			try {
				model.insertNewDataProduct(stored);
				model.insertNewDatastored(stored);
			} catch (SQLException e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "An error occurred around addNewStored:" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			model.CloseConnection();
		}
	
	public void addNewShipped(ShippedProduct stored) {
		model.establishConnection();
		try {
			model.insertNewDataProduct(stored);
			model.insertNewDataShipped(stored);
		} catch (SQLException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "An error occurred around addNewShipped:" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		model.CloseConnection();
	}
	
	//deleting records
	
	public void deleteRecord(ProductRecord record) {
		model.establishConnection();
		try {
			switch (record.getStatus()) {
			case "Arriving":
				model.deleteArriving(record);
				break;
			case "Stored":
				model.deleteStored(record);
				break;
			case "Shipped":
				model.deleteShipped(record);
				break;
			
			}
			model.deleteProduct(record);
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "An error occurred around deleteRecord:" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		model.CloseConnection();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
