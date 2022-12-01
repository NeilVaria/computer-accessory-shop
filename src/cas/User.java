package cas;

public abstract class User {
	private String UserID;
	private String Username;
	private String Name;
	private String UserType;
	private Address address;
	public User(String userID, String username, String name, String userType, String house_number, String postcode, String city) {
		super();
		UserID = userID;
		Username = username;
		Name = name;
		UserType = userType;
		address = new Address(house_number, postcode, city);
	}
	public String getUserID() {
		return UserID;
	}
	public String getUsername() {
		return Username;
	}
	public String getName() {
		return Name;
	}
	public String getUserType() {
		return UserType;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	
	
	

}
