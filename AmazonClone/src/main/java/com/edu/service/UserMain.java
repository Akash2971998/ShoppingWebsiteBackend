package com.edu.service;

import java.sql.*;
import java.util.*;

import com.edu.repository.UserCurd;

public class UserMain implements UserInterface {
	
	UserCurd curd = new UserCurd();
	
	public void registration() {
		curd.registration();	
	}

	public void login() throws SQLException {
		curd.login();	
	}

	public void editProfile() {
		curd.editProfile();
	}

	public void ListUser() {
		curd.ListUser();
	}

}
