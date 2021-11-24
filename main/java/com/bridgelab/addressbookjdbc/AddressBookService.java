package com.bridgelab.addressbookjdbc;

import java.util.ArrayList;
import java.util.List;

public class AddressBookService {

	ArrayList<Contact> contactList = new ArrayList<>();

	public ArrayList<Contact> getAllDataFromDB() {
		AddressBookDBService addressBookDBService = new AddressBookDBService();
		ArrayList<Contact> contactList = addressBookDBService.getDataFromDB();
		return contactList;
	}

	public void updateContactEmail(String name, String email) {
		int result = new AddressBookDBService().updateContactEmailOnDB(name, email);
		if (result == 0) {
			return;
		}
		Contact contactList = this.getContactDetailsFromList(name);
		if (contactList != null) {
			contactList.email = email;
		}
	}

	private Contact getContactDetailsFromList(String name) {
		return this.contactList.stream().filter(data -> data.firstName.equalsIgnoreCase(name)).findFirst().orElse(null);
	}

	public boolean checkContactObjDataIsSyncWithDB(String name) {
		List<Contact> contactDataList = new AddressBookDBService().getDataOfContactFromDB(name);
		return contactDataList.get(0).equals(this.getContactDetailsFromList(name));
	}

	public List<Contact> readContactData() {
		return this.contactList = new AddressBookDBService().getDataFromDB();
	}

}
