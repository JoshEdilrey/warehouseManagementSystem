package com.main.WarehouseManagementSystem;

import gui.Dashboard;
import gui.Dashboard_Final;
import sqldb.SQLCommandExecuter;
import controller.Controller;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SQLCommandExecuter model = new SQLCommandExecuter();
		Controller controller = new Controller(model);
		Dashboard view = new Dashboard(controller.loadDataFromDB());
	}
}