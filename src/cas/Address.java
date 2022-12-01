package cas;

public class Address{
	private String house_number;
	private String postcode;
	private String city;

	public Address(String house_number, String postcode, String city) {
		super();
		this.house_number = house_number;
		this.postcode = postcode;
		this.city = city;
	}

	public String getHouse_number() {
		return house_number;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getCity() {
		return city;
	}

	

}
