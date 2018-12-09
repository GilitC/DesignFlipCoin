package Model;

import java.io.File;

public class Product {
	
	// -------------------------------Class  Members------------------------------
	
	private int productID;
	private String productName;
	private String description;
	private File picture;
	private int amountInStock;
	private double pricePerUnit;
	private String publicAddress;
	private String userSignature;
	private int categoryID;
	

	// -------------------------------Constructor------------------------------
	
	public Product(int productID, String productName, String description, File picture, int amountInStock,
			double pricePerUnit, String publicAddress, String userSignature, int categoryID) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.description = description;
		this.picture = picture;
		this.amountInStock = amountInStock;
		this.pricePerUnit = pricePerUnit;
		this.publicAddress = publicAddress;
		this.userSignature = userSignature;
		this.categoryID = categoryID;
	}
	
	// -------------------------------Getters And Setters------------------------------
	public int getProductID() {
		return productID;
	}
	
	public String getPublicAddress() {
		return publicAddress;
	}

	public void setPublicAddress(String publicAddress) {
		this.publicAddress = publicAddress;
	}

	public String getUserSignature() {
		return userSignature;
	}

	public void setUserSignature(String userSignature) {
		this.userSignature = userSignature;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public File getPicture() {
		return picture;
	}
	
	public void setPicture(File picture) {
		this.picture = picture;
	}
	
	public int getAmountInStock() {
		return amountInStock;
	}
	
	public void setAmountInStock(int amountInStock) {
		this.amountInStock = amountInStock;
	}
	
	public double getPricePerUnit() {
		return pricePerUnit;
	}
	
	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", productName=" + productName + ", description=" + description
				+ ", picture=" + picture + ", amountInStock=" + amountInStock + ", pricePerUnit=" + pricePerUnit
				+ ", publicAddress=" + publicAddress + ", userSignature=" + userSignature + ", categoryID=" + categoryID
				+ "]";
	}
	
	
	
}
