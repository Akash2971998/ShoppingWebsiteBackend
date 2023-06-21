package com.edu.repository;

import java.sql.*;
import java.util.*;

import com.edu.view.AdminPage;
import com.edu.view.CustomerPage;
import com.edu.view.ProductPage;

public class UserCurd {
	private ResultSet rs;
	private Statement st;
	private PreparedStatement ps;
	
	String uname = null; //user-name
	String uemail = null; //user- email
	String upass = null; // user - password
	String utype = null; // user -type (Admin,customer,dealer)
	
	String insertSql = "";
	String selectSql ="";
	
	String validuser = "";
	String lastEmail = "";
	
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
	
	public void registration() {
		// TODO Auto-generated method stub
		if(conn!=null) {
			System.out.println("Enter Your name");
			uname = sc.nextLine();
			System.out.println("Enter your email");
			uemail = sc.nextLine();
			System.out.println("Enter your pass {space not allowed}");
			upass = sc.next().toLowerCase();
			System.out.println("choose user type: Admin , customer , dealer");
			utype = sc.next().toLowerCase();
			System.out.println("We are creating your account...please wait..");
			try {
				st = conn.createStatement();
				selectSql = "select count(*) from registration where UserEmail='"+uemail+"'";
				
				insertSql = "insert into registration(UserName,UserEmail,UserPass,UserType) values('" + uname + "', '" + uemail + "', '" + upass + "', '" + utype + "')";
				
				ResultSet rs = st.executeQuery(selectSql);
				rs.next();
				int EmailCount = rs.getInt(1);
				if(EmailCount>0) { //check for Email is Exist or not
					System.err.println("Email is already Register..LogIn or try With Different Id");
				}
				else {
					//check for type that user has input
					if(utype.equals("admin") || utype.equals("customer")) {
						int result = st.executeUpdate(insertSql);
						if(result>0) {
							System.out.println("Your registration is completed");
							validuser = "yes";
							lastEmail = uemail;
							userMenu();	
						}
						else {
							System.out.println("Your data is not valid. please try again");
						}
					}
					else {
						System.err.println("You have not Choose correct user type");
					}
				}
			}
			catch(Exception e){
				System.err.print("Something Went Wrong..Please Try again");
				System.err.println(e.toString());
			}
		}
	}

	public void login() throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Enter Your Email id");
		uemail = sc.next();
		selectSql = "Select * from registration where UserEmail='"+uemail+"'";
		st = conn.createStatement();
		rs = st.executeQuery(selectSql);
		if(!rs.next()) { //email is not exist
			System.err.println(uemail+"is Not Exist"); 
		}
		else {
			selectSql="select UserPass from registration where UserEmail='"+uemail+"'";
			
			System.out.println("Enter password");
			upass = sc.next().toLowerCase();
			st = conn.createStatement();
			rs = st.executeQuery(selectSql);
			if(rs.next()) {
				String databasepass = rs.getString(1);

				if(databasepass.toLowerCase().equals(upass)) { 
					System.out.println("Login succesfully...");
					lastEmail = uemail;
					validuser = "yes";
					userMenu();
				}
				else {
					System.err.println("Password is incorrect");
				}
			}
			else {
				System.err.println("something is wrong..");
			}
			
		}
		
	}
	
	public void editProfile() {
		// TODO Auto-generated method stub
		
	}

	public void ListUser() {
		// TODO Auto-generated method stub
		
	}

	private void userMenu() {
		//make variable null
		uname = null; 
		uemail = null; 
		upass = null; 
		utype = null;
		//check user type if user then they can view product and buy product
		//if admin then they can view product view customer view dealer view order
		//if dealer they can view , edit and delete product.
		try {
			st = conn.createStatement();
			String sqlSelect = "select * from registration where UserEmail='"+lastEmail+"'";
			rs = st.executeQuery(sqlSelect);
			if(rs.next()) {
				String StringLoginUserType = rs.getString("UserType");
				
				if(StringLoginUserType.equals("customer")) {
					CustomerPage cpage = new CustomerPage();
					String Name = rs.getString("UserName");
					int id = rs.getInt("UserId");
					cpage.CustomerMenu(Name, id); 
				}
				
				else if(StringLoginUserType.equals("dealer")) {
					ProductPage pro = new ProductPage();
					String Name = rs.getString("UserName");
					int id = rs.getInt("UserId");
					pro.productMenu(Name,id);
				}
				
				else if(StringLoginUserType.equals("admin")) {
					AdminPage admin = new AdminPage();
					String Name = rs.getString("UserName");
					int id = rs.getInt("UserId");
					admin.adminMenu(Name,id);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
