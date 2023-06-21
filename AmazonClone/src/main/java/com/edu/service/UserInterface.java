package com.edu.service;

import java.sql.*;

public interface UserInterface {
	
	public void registration();
	
	public void login() throws SQLException;
	
	public void editProfile();
	
	public void ListUser();
}
