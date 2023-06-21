package com.edu.dao;

public class ProductDao {
	private int pid;
	private String pname;
	private String pdescription;
	private int pquant;
	private float pprice;
	private int did;
	public ProductDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductDao(String pname, String pdescription, int pquant, float pprice) {
		super();
		this.pname = pname;
		this.pdescription = pdescription;
		this.pquant = pquant;
		this.pprice = pprice;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPdescription() {
		return pdescription;
	}
	public void setPdescription(String pdescription) {
		this.pdescription = pdescription;
	}
	public int getPquant() {
		return pquant;
	}
	public void setPquant(int pquant) {
		this.pquant = pquant;
	}
	public float getPprice() {
		return pprice;
	}
	public void setPprice(float pprice) {
		this.pprice = pprice;
	}
	public int getDid() {
		return did;
	}
	
	public int getPid() {
		return pid;
	}
	
	
}
