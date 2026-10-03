package application;

public class Address {
	AlertTypes Alert = new AlertTypes();
	private String street;
	private String city;
	private String country;

	public Address() {
	}

	public Address(String street, String city, String country) {
		setStreet(street);
		setCity(city);
		setCountry(country);
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		if (!Validation1(country, "Country")) {
			return;
		}
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		if (!Validation2(city, "City")) {
			return;
		}
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		if (!Validation1(street, "Street")) {
			return;
		}
		this.street = street;
	}

	public boolean Validation1(String value, String fieldName) {
		if (!value.matches("[a-zA-Z0-9 ]+")) {
			Alert.ErrorAlert("Error", fieldName + " must not contain special characters.");
			return false;
		}
		return true;
	}

	public boolean Validation2(String value, String fieldName) {
		return Validation1(value, fieldName);
	}

	@Override
	public String toString() {
		return "Address [street" + street + ", city=" + city + ", country=" + country + "]";
	}

}
