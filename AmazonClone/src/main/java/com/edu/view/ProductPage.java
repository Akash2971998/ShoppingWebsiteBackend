package com.edu.view;

import java.sql.*;
import java.util.*;

import com.edu.repository.DatabaseConnection;
import com.edu.service.ProductImp;
import com.edu.service.ProductInterface;

public class ProductPage {
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
	
	public void productMenu(String name, int id) throws SQLException {
		System.out.println("Hello "+ name +" welcome to Product ");
		do {
			System.out.println("1. Insert product\n2. Update Product\n3. Delete Product\n4. Display Products\n5. Logout\nEnter choice: ");
			int ch = sc.nextInt();
			
			ProductInterface pI = new ProductImp();
			
			switch(ch) {
			case 1: pI.insertProduct(id); 
					break;
					
			case 2: pI.updateProduct(name, id);
					break;
					
			case 3: pI.deleteProduct(name, id);
					break;
			
			case 4: pI.displayProduct(name,id);
					break;
					
			case 5: System.out.println("Visit Again...");
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
