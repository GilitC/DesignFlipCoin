package Model;

public class ProductCategory {

	// -------------------------------Class  Members------------------------------
	private int categoryID;
	private String categoryName;

	// -------------------------------Constructors------------------------------
	
	public ProductCategory(int categoryID, String categoryName) {
		super();
		this.categoryID = categoryID;
		this.categoryName = categoryName;
	}
	

	// -------------------------------Getters And Setters------------------------------
	
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	@Override
	public String toString() {
		return "ProductCategory [categoryID=" + categoryID + ", categoryName=" + categoryName + "]";
	}
	
	
	
}
