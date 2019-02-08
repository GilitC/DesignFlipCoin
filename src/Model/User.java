package Model;

public class User {

	// -------------------------------Class  Members------------------------------
		
		private String publicAddress;
		private String userSignature;	
		private String username;
		private String password;
		private String phone;
		private String email;

		// -------------------------------Constructor------------------------------
		
		public User(String publicAddress, String userSignature,String username, String password,
				 String phone, String email) {
			this.username = username;
			this.password = password;
			this.publicAddress = publicAddress;
			this.userSignature = userSignature;
			this.phone = phone;
			this.email = email;
		}

		//used for login page - UserName is unique
		public User(String username) {
			this.username = username;
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
