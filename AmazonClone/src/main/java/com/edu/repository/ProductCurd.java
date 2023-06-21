package com.edu.repository;

import java.sql.*;
import java.util.*;
import java.io.*;

public class ProductCurd {
	private ResultSet rs;
	private Statement st;
	private PreparedStatement ps;

	
	Scanner sc = new Scanner(System.in);
	
	private static Connection conn=null;
	static {
		try {
			conn=DatabaseConnection.getDatabaseConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertProduct(int id) throws SQLException {
		try {
			System.out.println("Enter the product name: ");
			String name = sc.nextLine();
			
			String sq = "select * from products where pname = '"+name+"'";
			st=conn.createStatement();
			rs = st.executeQuery(sq);
			
			if(!rs.next()) {
				System.out.println("Enter the product description: ");
				String desc = sc.nextLine();
				
				System.out.println("Enter the quantity of the products: ");
				int quant = sc.nextInt();
				
				System.out.println("Enter the price of each product: ");
				float price = sc.nextFloat();
				
				String ins = "insert into products(pname, pdescription, pquantity, pprice, userid) values(?,?,?,?,?)";
				ps = conn.prepareStatement(ins);
				
				ps.setString(1, name);
				ps.setString(2, desc);
				ps.setInt(3, quant);
				ps.setFloat(4, price);
				ps.setInt(5, id);
				
				int r = ps.executeUpdate();
				
				if(r>0) {
					  System.out.println("Product Registered"); 
				}else {
					   System.out.println("Some ERROR !!!!!!");
				}
			}else {
				System.out.println(name+" already exists");
			}
		}
		catch(Exception e){
			System.err.println(e);
		}
		
	}
	
	public void updateProduct(String uname, int uid) throws SQLException {
		try {
			System.out.println("Enter the name of the product: ");
			String name = sc.nextLine();
			
			String s  = "select * from products where pname = '"+name+"' and userid ="+uid ;
			
			st = conn.createStatement();
			rs = st.executeQuery(s);
			
			int ch,check;
			String var, query;
			if(rs.next()==false) {
				System.out.println(name+" not exits");
			}
			else {
				do {
					System.out.println("What to update--");
					System.out.println("1. Description\n2. quantity\n3. Price\nEnter you choice: ");
					ch = sc.nextInt();
					
					switch(ch) {
					case 1: System.out.println("Enter the new Description: ");
							String desc = sc.next();
							
							query = "update products set pdescription = ? where pname =?";
							ps = conn.prepareStatement(query);
							ps.setString(1, desc);
							ps.setString(2, name);
							
							check = ps.executeUpdate();
							if(check>0) {
								  System.out.println("Product updated"); 
							}else {
								   System.out.println("Some ERROR !!!!!!");
							}
							break;
							
					case 2: System.out.println("Enter the new Quantity: ");
							int quant = sc.nextInt();
							
							query = "update products set pquantity = ? where pname =?";
							ps = conn.prepareStatement(query);
							ps.setInt(1, quant);
							ps.setString(2, name);
							
							check = ps.executeUpdate();
							if(check>0) {
								  System.out.println("Product updated"); 
							}else {
								   System.out.println("Some ERROR !!!!!!");
							}
							break;
					
					case 3: System.out.println("Enter the new Price: ");
							float price = sc.nextFloat();
							
							query = "update products set pprice = ? where pname =?";
							ps = conn.prepareStatement(query);
							ps.setFloat(1, price);
							ps.setString(2, name);
							
							check = ps.executeUpdate();
							if(check>0) {
								  System.out.println("Product updated"); 
							}else {
								   System.out.println("Some ERROR !!!!!!");
							}
							break;
							
					default: System.exit(0);
					}
				}while(ch>4);
				
			}
		}catch(Exception e){
			System.err.println(e);
		}
	}
	
	public void deleteProduct(String unmae, int uid) throws SQLException {
		try {
			System.out.println("Enter the name of the product: ");
			String name = sc.nextLine();
			
			String s = "select * from products where pname= '"+name+ "' and userid = "+uid;
			
			st = conn.createStatement();
			rs = st.executeQuery(s);
			
			if(!rs.next()) {
				System.out.println(name+" not exists");
			}
			else {
				String query = "delete from products where pname = ? ";
				
				ps=conn.prepareStatement(query);
				
				ps.setString(1, name);
				
				int check = ps.executeUpdate();
				if(check>0) {
					  System.out.println(name+ " Product deleted"); 
				}else {
					   System.out.println("Some ERROR !!!!!!");
				}
			}
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	
	public void displayProducts(String userName, int uid) throws SQLException {
		try {
			System.out.println("Welcome "+userName);
			String s = "select * from products where userid = "+uid;
			st = conn.createStatement();
			rs = st.executeQuery(s);
			
			System.out.println("All Products: ");
			System.out.println("Id\tName\t\tDescription\t\tQuantity\tPrice");
			
			while(rs.next()) {
				{
			        int id = rs.getInt("pid");
			        String name = rs.getString("pname");
			        String description = rs.getString("pdescription");
			        int quant = rs.getInt("pquantity");
			        int price = rs.getInt("pprice");
			        
			        // print the results
			        System.out.format("%s\t%s\t\t%s\t\t%s\t\t%s\n", id, name, description, quant, price);
			      }
			}
			System.out.println();
		}
		catch(Exception e){
			System.err.println(e);
		}
		
	}
}
