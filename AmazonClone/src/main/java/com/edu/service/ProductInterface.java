package com.edu.service;

import java.sql.*;

public interface ProductInterface {
	  public void insertProduct(int id) throws SQLException;
	  public void updateProduct(String name, int id) throws SQLException;
	  public void deleteProduct(String name, int id) throws SQLException;
	  public void displayProduct(String name, int id) throws SQLException;
}
