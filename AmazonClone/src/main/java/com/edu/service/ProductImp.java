package com.edu.service;

import java.sql.SQLException;

import com.edu.repository.ProductCurd;

public class ProductImp implements ProductInterface{

	ProductCurd curd = new ProductCurd();
	
	public void insertProduct(int id) throws SQLException {
		System.out.println("Add the Product: ");
		curd.insertProduct(id);
	}

	public void updateProduct(String name, int id) throws SQLException {
		curd.updateProduct(name, id);
		
	}

	public void deleteProduct(String name, int id) throws SQLException {
		curd.deleteProduct(name, id);		
	}

	public void displayProduct(String name, int id) throws SQLException {
		curd.displayProducts(name, id);
	}

}
