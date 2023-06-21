package com.edu.service;

import java.sql.*;
import java.util.*;

import com.edu.repository.DatabaseConnection;

public class CustomerOperation {
	static Connection conn=null;
	private PreparedStatement ps;
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
	
	Scanner sc = new Scanner(System.in);
	
	Random rand = new Random();
	
	public void CustomerEditProfile(int id) {
		int LoggedId = id;
		boolean val= false;
		try {
			st = conn.createStatement();
			String sqlSelect = "select * from registration where userId='"+LoggedId+"'";
			rs =st.executeQuery(sqlSelect);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rs!=null) { //if id is exist..logged in user details fetch successfully
			rs=null;
			System.out.println("What do you want to Edit");
			System.out.println("1. Name");
			System.out.println("2. Email");
			System.out.println("3. Password");
			System.out.println("Enter Number");
			int num = sc.nextInt();
			if(num==1) {
				System.out.println("Enter new Name");
				Scanner scanner = new Scanner(System.in);
				String newname = scanner.nextLine();
				try {
					st = conn.createStatement();
					String UpdateName = "update registration set Username='"+newname+"' where UserId="+LoggedId+"";
					int vals = st.executeUpdate(UpdateName);
					if(vals>0) {
						System.out.println("your name is updated");
					}
					else {
						System.out.println("Something is wrong Please Try again");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			else if(num==2) {
				System.out.println("Enter new Email");
				Scanner scanner = new Scanner(System.in);
				String newEmail = scanner.nextLine();
				try {
					st = conn.createStatement();
					String UpdateEmail = "update registration set UserEmail='"+newEmail+"' where UserId="+LoggedId+"";
					int vals = st.executeUpdate(UpdateEmail);
					if(vals>0) {
						System.out.println("your Email is updated");
					}
					else {
						System.out.println("Something is wrong Please Try again");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else if(num==3) {
				System.out.println("Enter new password");
				Scanner scanner = new Scanner(System.in);
				String newPassword = scanner.nextLine();
				try {
					st = conn.createStatement();
					String Updatepassword = "update registration set UserPass='"+newPassword+"' where UserId="+LoggedId+"";
					int vals = st.executeUpdate(Updatepassword);
					if(vals>0) {
						System.out.println("your password is updated");
					}
					else {
						System.out.println("Something is wrong Please Try again");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				System.out.println("You Entered Wrong input");
			}
		}
		else {
			System.out.println("Unable to fetch data");
		}
	}

	public void CustomerViewProfile(int loggedUserId) {
		try {
			String s = "select * from registration where UserId="+loggedUserId;
			st = conn.createStatement();
			rs = st.executeQuery(s);
			
			System.out.println("Your Details: ");
			System.out.println("Name\t\tEmail");
			
			while(rs.next()) {
				{
			        String name = rs.getString("UserName");
			        String email = rs.getString("UserEmail");
			        
			        // print the results
			        System.out.printf("%-15s  %-7s \n",name,email);
			      }
			}
			System.out.println();
		}
		catch(Exception e){
			System.err.println(e);
		}
		
	}
	
	public void CustomerViewProduct() {
		try {
			String s = "select * from products";
			st = conn.createStatement();
			rs = st.executeQuery(s);
			
			System.out.println("All Products: ");
			System.out.println("Name\t\tDescription\t\tQuantity\tPrice");
			
			while(rs.next()) {
				{
			        String name = rs.getString("pname");
			        String description = rs.getString("pdescription");
			        int quant = rs.getInt("pquantity");
			        int price = rs.getInt("pprice");
			        
			        // print the results
			        System.out.format("%s\t\t%s\t\t%s\t\t%s\n", name, description, quant, price);
			      }
			}
			System.out.println();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}

	public void BuyProduct(int uid) {
		try {
			CustomerViewProduct();
			
			System.out.println("Enter the Product name to buy: ");
			String name = sc.nextLine();
			
			System.out.println("Enter the quantity you want to buy: ");
			int quant = sc.nextInt();
			
			int oid = rand.nextInt(10000);
			
			String s = "select * from products where pname ='"+name+"'";
			st = conn.createStatement();
			rs = st.executeQuery(s);
			
			if(rs.next()) {
				int a = rs.getInt("pquantity");
				
				float price = rs.getFloat("pprice");
				
				float totalprice = (float) ((quant*price)+(0.18 *(quant*price))); //with gst
				
				int pid = rs.getInt("pid");
				
				if(a<quant) {
					System.out.println("Not enough quantity available");
				}
				else {
					String ins = "insert into cart(cquantity, orderid, totalprice, userid, pid) values(?,?,?,?,?)";
					ps = conn.prepareStatement(ins);
					
					ps.setInt(1, quant);
					ps.setInt(2, oid);
					ps.setFloat(3, totalprice);
					ps.setInt(4, uid);
					ps.setInt(5, pid);
					
					int r = ps.executeUpdate();
					
					if(r>0) {
						  System.out.println("Order is Successfully place"); 
					}else {
						   System.out.println("Some ERROR !!!!!!");
					}
					
					int updateQuant = a-quant;
					
					String query = "update products set pquantity = ? where pname =?";
					ps = conn.prepareStatement(query);
					ps.setInt(1, updateQuant);
					ps.setString(2, name);
					
					ps.executeUpdate();
				}
			}
		}
		catch(Exception e){
			System.err.println(e);
		}
	}

	public void OrderedProducts(int loggedUserId) {
		try {
			st = conn.createStatement();
			String sqlSelect = "select c.orderid, p.pname, p.pdescription, c.cquantity, c.totalprice from cart c join products p on c.pid=p.pid where c.userid="+loggedUserId;
			rs =st.executeQuery(sqlSelect);
			
			System.out.println("All Ordered Products: ");
			System.out.println("Order Id\tName\t\tDescription\t\tQuantity\tPrice");
			
			while(rs.next()) {
				int OrderId = rs.getInt("orderid");
				String name = rs.getString("pname");
		        String description = rs.getString("pdescription");
		        int quant = rs.getInt("cquantity");
		        int price = rs.getInt("totalprice");
		        
		        System.out.format("%s\t\t%s\t\t%s\t\t%s\t\t%s\n",OrderId, name, description, quant, price);
		        
			}
			
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}
