package Model;

public class ProductInTX {

	//This class is named itemInTransaction in MS access
	// -------------------------------Class  Members---------------------------------
	
	
	private int itemID;
	private String txID;
	
	// -------------------------------Constructor-------------------------------------
	
	public ProductInTX(int itemID, String txID) {
		super();
		this.itemID = itemID;
		this.txID = txID;
	}

	// -------------------------------Getters and Setters------------------------------
	
	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public String getTxID() {
		return txID;
	}

	public void setTxID(String txID) {
		this.txID = txID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + itemID;
		result = prime * result + ((txID == null) ? 0 : txID.hashCode());
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
		ProductInTX other = (ProductInTX) obj;
		if (itemID != other.itemID)
			return false;
		if (txID == null) {
			if (other.txID != null)
				return false;
		} else if (!txID.equals(other.txID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductInTX [itemID=" + itemID + ", txID=" + txID + "]";
	}
	
	
	
}
