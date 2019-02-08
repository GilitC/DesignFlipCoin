package Model;


import java.net.URL;

public class Product {
	
	//This class is named Item in MS access
	// -------------------------------Class  Members------------------------------
	
	private int productID; // CatalogNumer in MS ACCESS
	private String productName;
	private URL picture;
	private String description;
	private double pricePerUnit;
	private int quantityInStock;
	private int categoryID;
	private String sellerAddress;
	private String sellerSignature;
	
	// -------------------------------Constructor------------------------------
	
	public Product(int productID, String productName, URL picture, String description, double pricePerUnit,
			int quantityInStock, int categoryID, String sellerAddress, String sellerSignature) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.picture = picture;
		this.description = description;
		this.pricePerUnit = pricePerUnit;
		this.quantityInStock = quantityInStock;
		this.categoryID = categoryID;
		this.sellerAddress = sellerAddress;
		this.sellerSignature = sellerSignature;
	}

	// -------------------------------Getters And Setters------------------------------
	
	public int getProductID() {
		return productID;
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

	public URL getPicture() {
		return picture;
	}

	public void setPicture(URL picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getSellerAddress() {
		return sellerAddress;
	}

	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
	}

	public String getSellerSignature() {
		return sellerSignature;
	}

	public void setSellerSignature(String sellerSignature) {
		this.sellerSignature = sellerSignature;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + productID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (productID != other.productID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", productName=" + productName + ", picture=" + picture
				+ ", description=" + description + ", pricePerUnit=" + pricePerUnit + ", quantityInStock="
				+ quantityInStock + ", categoryID=" + categoryID + ", sellerAddress=" + sellerAddress
				+ ", sellerSignature=" + sellerSignature + "]";
	}
	

	
	
	
}
