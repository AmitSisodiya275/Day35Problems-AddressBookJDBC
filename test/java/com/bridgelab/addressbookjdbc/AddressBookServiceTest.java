package com.bridgelab.addressbookjdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class AddressBookServiceTest {

	@Test
	public void whenRetrivedFromDB_shouldMatchTheDataCount() {
		AddressBookService addressBookService = new AddressBookService();
		ArrayList<Contact> list = addressBookService.getAllDataFromDB();
		assertEquals(2, list.size());
	}

	@Test
	public void whenUpdatedContactInfo_shouldUpdateTheDetailInMemory() {
		AddressBookService addressBookService = new AddressBookService();
		addressBookService.readContactData();
		addressBookService.updateContactEmail("Amit", "amitsisodiya@fakemail.com");
		boolean result = addressBookService.checkContactObjDataIsSyncWithDB("Amit");
		assertTrue(result);
	}
}
