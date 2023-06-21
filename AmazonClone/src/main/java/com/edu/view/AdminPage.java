package com.edu.view;

import java.sql.*;
import java.util.*;

import com.edu.repository.DatabaseConnection;
import com.edu.service.*;

public class AdminPage {
	static Connection conn=null;
	Statement st = null;
	ResultSet rs = null;
	 static {
		 DatabaseConnection dc = new DatabaseConnection();
	        try {
	            conn = dc.getDatabaseConnection();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	
	String userContinue = "";
	Scanner sc = new Scanner(System.in);
	
	public void adminMenu(String name, int id) throws SQLException {
		System.out.println("Hello "+ name +" welcome to Admin ");
		do {
			System.out.println("1.Dealers Registration\n2. View all Customer\n3. View all Dealers\n4. View all Available Products");
			System.out.println("5. View all Ordered Products\n6. Logout\nEnter choice: ");
			int ch = sc.nextInt();
			
			AdminInterface aI = new AdminImp();
			
			switch(ch) {
			case 1: aI.dealersResgistration();
					break;
					
			case 2: aI.viewAllCustomers(); 
					break;
					
			case 3: aI.viewAllDealers();
					break;
					
			case 4: aI.viewAllProducts();
					break;
			
			case 5: aI.viewOrderedProducts();
					break;
					
			case 6: System.out.println("Visit Again...");
					userContinue="no";
					break;
			}
			
			if(userContinue!="no") {
				System.out.println(" You want to perform other operation");
				userContinue = sc.next().toLowerCase();
			}
		}while(userContinue.equals("yes"));
	}
}
