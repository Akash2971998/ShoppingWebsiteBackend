package com.edu.repository;

import java.sql.*;
import java.util.Scanner;

public class AdminOperation {
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
	
	public void viewAllCustomers() {
		try {
			System.out.println("All Customer:- \n");
			st = conn.createStatement();
			String s = "select * from registration where usertype ='customer'";
			rs = st.executeQuery(s);
			
			System.out.println("Customer Id\tCustomer Name\tCustomer Email");
			
			while(rs.next()) {
				int id = rs.getInt("UserId");
				String name = rs.getString("Username");
				String email = rs.getString("UserEmail");
				
				System.out.format("%s\t\t%s\t\t%s\n", id, name, email);
			}
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	
	public void viewAllDealers() {
		try {
			System.out.println("All Dealers:- \n");
			
			st = conn.createStatement();
			String s = "select * from registration where UserType ='dealer'";
			rs = st.executeQuery(s);
			
			System.out.println("Dealer Id\tDealer Name\tDealer Email");
			
			while(rs.next()) {
				int id = rs.getInt("UserId");
				String name = rs.getString("Username");
				String email = rs.getString("UserEmail");
				
				System.out.format("%s\t\t%s\t\t%s\n", id, name, email);
			}
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	
	public void viewAllProducts() {
		try {
			System.out.println("All Products:- \n");
			st = conn.createStatement();
			String s = "select * from products";
			rs = st.executeQuery(s);
			
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
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
	
	public void viewAllOrderedProducts() {
		try {
			st = conn.createStatement();
			String sqlSelect = "select c.orderid, r.username, p.pname, p.pdescription, c.cquantity, c.totalprice from cart c join products p on c.pid=p.pid join registration r on r.userid=c.userid order by r.userid";
			rs =st.executeQuery(sqlSelect);
			
			System.out.println("All Ordered Products: ");
			System.out.println("Order Id\tCustomer Name\tProduct Name\t\tProdcut Description\t\tOrdered Quantity\tTotal Price");
			
			while(rs.next()) {
				int OrderId = rs.getInt("orderid");
				String cname = rs.getString("username");
				String name = rs.getString("pname");
		        String description = rs.getString("pdescription");
		        int quant = rs.getInt("cquantity");
		        int price = rs.getInt("totalprice");
		        
		        System.out.format("%s\t\t%s\t\t%s\t\t\t%s\t\t\t%s\t\t\t	%s\n",OrderId, cname, name, description, quant, price);
			}
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}
