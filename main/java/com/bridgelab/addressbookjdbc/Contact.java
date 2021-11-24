package com.bridgelab.addressbookjdbc;

public class Contact {
	
public String firstName, lastName, city, state, email, zip, phoneNumber;
	

	public Contact(String firstName, String lastName, String city, String state, String email, String zip,
			String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.state = state;
		this.email = email;
		this.zip = zip;
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getEmail() {
		return email;
	}

	public String getZip() {
		return zip;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "\n First Name = " + firstName + "\n Last Name = " + lastName + "\n  City = " + city + "\n State = " + state
				+ "\n Email ID = " + email + "\n Zip Code = " + zip + "\n Phone Number = " + phoneNumber;
	}
}
