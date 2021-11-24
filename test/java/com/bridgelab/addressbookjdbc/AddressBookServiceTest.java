package com.bridgelab.addressbookjdbc;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class AddressBookServiceTest {

	@Test
	public void whenRetrivedFromDB_shouldMatchTheDataCount() {
		AddressBookService addressBookService = new AddressBookService();
		ArrayList<Contact> list = addressBookService.getAllDataFromDB();
		assertEquals(2, list.size());
	}
}
