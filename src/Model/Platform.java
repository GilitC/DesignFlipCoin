package Model;

public class Platform {

	// -------------------------------Class  Members------------------------------
	
	private int platformID;
	private String type;
	
	// -------------------------------Constructors------------------------------
	public Platform(int platformID, String type) {
		this.platformID = platformID;
		this.type = type;
	}

	// -------------------------------Getters And Setters------------------------------
	public int getPlatformID() {
		return platformID;
	}

	public void setPlatformID(int platformID) {
		this.platformID = platformID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

	
	
}
