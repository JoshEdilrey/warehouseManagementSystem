package com.main.WarehouseManagementSystem;

import gui.Dashboard_Final;
import sqldb.SQLCommandExecuter;
import controller.Controller;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SQLCommandExecuter model = new SQLCommandExecuter();
		Controller controller = new Controller(model);
		Dashboard_Final view = new Dashboard_Final(controller.loadDataFromDB());
		
	}

}
