package Model;

public class User {

	// -------------------------------Class  Members------------------------------
		
		private String publicAddress;
		private String userSignature;	
		private String username;
		private String password;
		private String email;
		private String phone;
		private int type; //Type 0 for Regular User, type 1 for Employee


		// -------------------------------Constructor------------------------------
		
		public User(String publicAddress, String userSignature,String username, String password,
				String email, String phone, int type) {
			this.username = username;
			this.password = password;
			this.publicAddress = publicAddress;
			this.userSignature = userSignature;
			this.email = email;
			this.phone = phone;
			this.type = type;
		}


		// -------------------------------Getters And Setters------------------------------
		public String getUsername() {
			return username;
		}


		public void setUsername(String username) {
			this.username = username;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
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


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getPhone() {
			return phone;
		}


		public void setPhone(String phone) {
			this.phone = phone;
		}


		public int getType() {
			return type;
		}


		public void setType(int type) {
			this.type = type;
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((username == null) ? 0 : username.hashCode());
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
			User other = (User) obj;
			if (username == null) {
				if (other.username != null)
					return false;
			} else if (!username.equals(other.username))
				return false;
			return true;
		}

		public String toString() {
			return "User | name: " + username + ", Public address: " + publicAddress + ", signature: " + userSignature;
		}
		
		
}
