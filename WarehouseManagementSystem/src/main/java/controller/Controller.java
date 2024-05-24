package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import gui.Dashboard_Final;
import model.ArrivingProduct;
import model.ProductRecord;
import sqldb.SQLCommandExecuter;

public class Controller {
	private Dashboard_Final view;
	private SQLCommandExecuter model;
	
	public Controller(SQLCommandExecuter model) {
		this.model = model;
		
		
	}
	
	public List<ProductRecord> loadDataFromDB() {
		try {
			return model.loadDataToProductRecord(model.getArrivingProducts());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);		}
			return new ArrayList<ProductRecord>();
	}
}
