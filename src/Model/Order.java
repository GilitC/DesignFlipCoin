package Model;

public class Order {

	// -------------------------------Class  Members------------------------------
	
	private int orderID;
	private int quantity;
	private String status; //Can be either: pending or confirmed
	private double total;
	private String publicAddress;
	private String userSignature;	
	private int productID;
	

	// -------------------------------Constructor------------------------------
	
	public Order(int orderID, int quantity, String status, double total, String publicAddress, String userSignature, int productID) {
		super();
		this.orderID = orderID;
		this.quantity = quantity;
		this.status = status;
		this.total = total;
		this.publicAddress = publicAddress;
		this.userSignature = userSignature;
		this.productID = productID;
	}
	
	// -------------------------------Getters And Setters------------------------------
	
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

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", quantity=" + quantity + ", status=" + status + ", total=" + total
				+ ", publicAddress=" + publicAddress + ", userSignature=" + userSignature + ", productID=" + productID
				+ "]";
	}
	
	
}
