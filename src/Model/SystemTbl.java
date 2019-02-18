package Model;

public class SystemTbl {

	// -------------------------------Class  Members------------------------------
	
	private String version;
	private int TransactionMinSize;
	private int TransactionMaxSize;
	private int TransactionSizeForExpansion;
	private int PriceForExpansion;
	private double DiscountPercentPerFee; 
	private int PriceForDiscount;
	private int TransactionSizeFree;
	
	// -------------------------------Constructors------------------------------
	
	
	public SystemTbl(String version, int transactionMinSize, int transactionMaxSize, int transactionSizeForExpansion,
			int priceForExpansion, double discountPercentPerFee, int priceForDiscount, int transactionSizeFree) {
		super();
		this.version = version;
		TransactionMinSize = transactionMinSize;
		TransactionMaxSize = transactionMaxSize;
		TransactionSizeForExpansion = transactionSizeForExpansion;
		PriceForExpansion = priceForExpansion;
		DiscountPercentPerFee = discountPercentPerFee;
		PriceForDiscount = priceForDiscount;
		TransactionSizeFree = transactionSizeFree;
	}
	
	public SystemTbl( int transactionMinSize, int transactionMaxSize, int transactionSizeForExpansion,
			int priceForExpansion, double discountPercentPerFee, int priceForDiscount, int transactionSizeFree) {
		super();
		this.version = "11";
		TransactionMinSize = transactionMinSize;
		TransactionMaxSize = transactionMaxSize;
		TransactionSizeForExpansion = transactionSizeForExpansion;
		PriceForExpansion = priceForExpansion;
		DiscountPercentPerFee = discountPercentPerFee;
		PriceForDiscount = priceForDiscount;
		TransactionSizeFree = transactionSizeFree;
	}

	// -------------------------------Getters And Setters------------------------------


	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getTransactionMinSize() {
		return TransactionMinSize;
	}

	public void setTransactionMinSize(int transactionMinSize) {
		TransactionMinSize = transactionMinSize;
	}

	public int getTransactionMaxSize() {
		return TransactionMaxSize;
	}

	public void setTransactionMaxSize(int transactionMaxSize) {
		TransactionMaxSize = transactionMaxSize;
	}

	public int getTransactionSizeForExpansion() {
		return TransactionSizeForExpansion;
	}

	public void setTransactionSizeForExpansion(int transactionSizeForExpansion) {
		TransactionSizeForExpansion = transactionSizeForExpansion;
	}

	public int getPriceForExpansion() {
		return PriceForExpansion;
	}

	public void setPriceForExpansion(int priceForExpansion) {
		PriceForExpansion = priceForExpansion;
	}

	public double getDiscountPercentPerFee() {
		return DiscountPercentPerFee;
	}

	public void setDiscountPercentPerFee(double discountPercentPerFee) {
		DiscountPercentPerFee = discountPercentPerFee;
	}

	public int getPriceForDiscount() {
		return PriceForDiscount;
	}

	public void setPriceForDiscount(int priceForDiscount) {
		PriceForDiscount = priceForDiscount;
	}

	public int getTransactionSizeFree() {
		return TransactionSizeFree;
	}

	public void setTransactionSizeFree(int transactionSizeFree) {
		TransactionSizeFree = transactionSizeFree;
	}
	


	


	
	
}
