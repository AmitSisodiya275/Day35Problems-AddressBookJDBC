package com.bridgelab.addressbookjdbc;

import java.util.ArrayList;

public class AddressBookService {

	public ArrayList<Contact> getAllDataFromDB() {
		AddressBookDBService addressBookDBService = new AddressBookDBService();
		ArrayList<Contact> contactList = addressBookDBService.getDataFromDB();
		return contactList;
	}

	
	
}
