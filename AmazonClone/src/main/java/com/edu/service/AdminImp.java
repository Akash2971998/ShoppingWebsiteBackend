package com.edu.service;

import com.edu.repository.AdminOperation;

public class AdminImp implements AdminInterface{

	AdminOperation admin = new AdminOperation();
			
	public void viewAllProducts() {
		admin.viewAllProducts();
	}

	public void viewOrderedProducts() {
		admin.viewAllOrderedProducts();
	}

	public void viewAllCustomers() {
		admin.viewAllCustomers();
	}

	public void viewAllDealers() {
		admin.viewAllDealers();
	}

	public void dealersResgistration() {
		
	}

}
