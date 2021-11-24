package com.bridgelab.addressbookjdbc;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class AddressBook {
	Scanner scanner = new Scanner(System.in);
	ArrayList<Contact> person = new ArrayList<>();

	HashMap<String, ArrayList<Contact>> multipleAddBook = new HashMap<>();

	public static void main(String[] args) {
		AddressBook addrsbk = new AddressBook();
		addrsbk.mainMenuAddressBook();

	}

	public void mainMenuAddressBook() {

		boolean quite = false;
		int choice = 0;
		printDetails();

		while (!quite) {
			System.out.println("Enter Your Choice : ");
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 0:
				printDetails();
				break;
			case 1:
				addMultipleAddressBook();
				break;
			case 2:
				addContactToTheExistingAddressBook();
				break;
			case 3:
				editContacts();
				break;
			case 4:
				showAddressBook();
				break;
			case 5:
				deleteContacts();
				break;
			case 6:
				searchContacts();
				break;
			case 7:
				searchContactByStateAndCity();
				break;
			case 8:
				viewCountOfContactsFromSpecificCity();
				break;
			case 9:
				viewCountOfContactsFromSpecificState();
				break;
			case 10:
				sortContact();
				break;
			case 11:
				writeToFile();
				break;
			case 12:
				readFromFile();
				break;
			case 13:
				writeToCSVFile();
				break;
			case 14:
				quite = true;
			}
		}
	}

	public void printDetails() {
		System.out.println("Welcome to Address Book.");
		System.out.println("\t Press 0 to Print Choice Options : ");
		System.out.println("\t Press 1 to Add new Address Book to the system : ");
		System.out.println("\t Press 2 to Add contact to the Existing Address Book : ");
		System.out.println("\t Press 3 Edit the Details of Specific person : ");
		System.out.println("\t Press 4 to see Full Address Book : ");
		System.out.println("\t Press 5 to Delete Person Details from Address Book : ");
		System.out.println("\t Press 6 Search for specific Contact : ");
		System.out.println("\t Press 7 to Search contact by specific City or State : ");
		System.out.println("\t Press 8 to view Count of Contact from specific City : ");
		System.out.println("\t Press 9 to view count of Contact form specific State : ");
		System.out.println("\t Press 10 to sort the Contacts : ");
		System.out.println("\t Press 11 to save Address Book to File : ");
		System.out.println("\t Press 12 to Read data from a File : ");
		System.out.println("\t Press 13 to save Address Book to CSV File : ");
		System.out.println("\t Press 14 to Exit Address Book App : ");
	}

	public void addMultipleAddressBook() {
		System.out.println("Enter the name for the new Address book : ");
		String name = scanner.nextLine();
		ArrayList<Contact> addBook = new ArrayList<>();
		if (multipleAddBook.containsKey(name)) {
			System.out.println("Address Book already Exist!");
		} else
			multipleAddBook.put(name, addBook);
		System.out.println("Would You like to add contact to the current address book : \n" + "Press 1 to add : \n "
				+ "Press 2 to exit from this : ");
		int choice = scanner.nextInt();
		scanner.nextLine();
		if (choice == 1) {
			addContacts(addBook);
			System.out.println("Contact Added Succesfully.");
		}

	}

	public void addContactToTheExistingAddressBook() {
		System.out.println("Enter the name of the Address Book in which you want to add Contact : ");
		String name = scanner.nextLine();
		if (multipleAddBook.containsKey(name)) {
			this.addContacts(multipleAddBook.get(name));
		}
	}

	public void editContacts() {
		System.out.println("Enter the name of the Address Book in which you want to add Contact : ");
		String name = scanner.nextLine();
		if (multipleAddBook.containsKey(name)) {
			this.editContacts(multipleAddBook.get(name));
		} else {
			System.out.println("Address Book not Exist!");
		}
	}

	public void searchContacts() {
		System.out.println("Enter the name of the Address Book in which you want to search : ");
		String name = scanner.nextLine();
		if (multipleAddBook.containsKey(name)) {
			System.out.println("Enter the name of Person whose details you want : ");
			String personName = scanner.nextLine();
			searchContacts(multipleAddBook.get(name), personName);
		} else
			System.out.println("Address Book not Exist!");

	}

	public void showAddressBook() {
		System.out.println("Enter the name of the Address Book which you want to see : ");
		String name = scanner.nextLine();
		this.showAddressBook(multipleAddBook.get(name));
	}

	public void deleteContacts() {
		System.out.println("Enter the name of the Address Book from which you want to delete contact : ");
		String bookName = scanner.nextLine();
		if (multipleAddBook.containsKey(bookName)) {
			this.deleteContacts(multipleAddBook.get(bookName));
		} else
			System.out.println("Address Book not Exist!");
	}

	public void addContacts(List<Contact> contact) {

		System.out.println("Enter First Name : ");
		String fName = scanner.nextLine();
		if (contact.stream().anyMatch(n -> n.getFirstName().equals(fName))) {
			System.out.println("Person Details already present, You can't add details of same person.");
			mainMenuAddressBook();
		}
		System.out.println("Enter Last Name : ");
		String lName = scanner.nextLine();
		System.out.println("Enter your City : ");
		String city = scanner.nextLine();
		System.out.println("Enter your State : ");
		String state = scanner.nextLine();
		System.out.println("Enter your Email-ID : ");
		String email = scanner.nextLine();
		System.out.println("Enter your Zip code : ");
		String zip = scanner.nextLine();
		System.out.println("Enter your Mobile Number : ");
		String mNumber = scanner.nextLine();
		System.out.println();

		contact.add(new Contact(fName, lName, city, state, email, zip, mNumber));
	}

	private void editContacts(List<Contact> contact) {
		System.out.println("Enter the Name of the person whose details you want to Edit : ");
		String name = scanner.nextLine();
		for (int i = 0; i < contact.size(); i++) {
			if (contact.get(i).getFirstName().equalsIgnoreCase(name)) {

				System.out.println("Choose What you want to Update : ");
				System.out.println("\t Press 1 to update First Name : ");
				System.out.println("\t Press 2 to update Last Name : ");
				System.out.println("\t Press 3 to update City : ");
				System.out.println("\t Press 4 to update State : ");
				System.out.println("\t Press 5 to update Email-ID : ");
				System.out.println("\t Press 6 to update Zip code : ");
				System.out.println("\t Press 7 to update Mobile Number : ");

				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					System.out.println("Enter Updated First Name : ");
					contact.get(i).setFirstName(scanner.nextLine());
					break;
				case 2:
					System.out.println("Enter Updated Last Name : ");
					contact.get(i).setLastName(scanner.nextLine());
					break;
				case 3:
					System.out.println("Enter Updated City : ");
					contact.get(i).setCity(scanner.nextLine());
					break;
				case 4:
					System.out.println("Enter Updated State : ");
					contact.get(i).setState(scanner.nextLine());
					break;
				case 5:
					System.out.println("Enter Updated Email ID : ");
					contact.get(i).setEmail(scanner.nextLine());
					break;
				case 6:
					System.out.println("Enter Updated Zip Code : ");
					contact.get(i).setZip(scanner.nextLine());
					break;
				case 7:
					System.out.println("Enter Updated Mobile Number : ");
					contact.get(i).setPhoneNumber(scanner.nextLine());
					break;
				}
			} else {
				System.out.println("Contact Details are not present. :( ");
			}
		}
	}

	private void searchContacts(ArrayList<Contact> contact, String name) {
		contact.stream().filter(element -> element.getFirstName().equalsIgnoreCase(name)).forEach(System.out::println);
	}

	private void showAddressBook(List<Contact> contact) {
		if (contact.size() == 0) {
			System.out.println("Address Book is Empty!");
		}
		System.out.println("size " + contact.size());
		contact.stream().forEach(System.out::println);
	}

	public void deleteContacts(List<Contact> contact) {
		System.out.println("Enter Person Name of the contact to delete all the data related to that contact : ");
		String name = scanner.nextLine();
		for (int i = 0; i < contact.size(); i++) {
			if (contact.get(i).getFirstName().equalsIgnoreCase(name)) {
				contact.remove(i);
				break;
			} else {
				System.out.println("Entered Name is not matching wih any Persons details.");
			}
		}

	}

	public void searchContactByStateAndCity() {
		System.out.println("Press 1 to search contact by State Name : ");
		System.out.println("Press 2 to search contact by City Name : ");
		int choice = scanner.nextInt();
		scanner.nextLine();
		switch (choice) {
		case 1:
			this.searchContactByState();
			break;
		case 2:
			this.searchContactByCity();
			break;
		default:
			this.mainMenuAddressBook();
		}
	}

	private void searchContactByState() {
		System.out.println("Enter the State Name : ");
		String stateName = scanner.nextLine();
		for (Map.Entry<String, ArrayList<Contact>> entry : multipleAddBook.entrySet()) {
			entry.getValue().stream().filter(p -> p.getState().equalsIgnoreCase(stateName))
					.forEach(System.out::println);
		}
	}

	private void searchContactByCity() {
		System.out.println("Enter the City Name : ");
		String cityName = scanner.nextLine();
		for (Map.Entry<String, ArrayList<Contact>> entry : multipleAddBook.entrySet()) {
			entry.getValue().stream().filter(p -> p.getCity().equalsIgnoreCase(cityName)).forEach(System.out::println);
		}
	}

	public void viewCountOfContactsFromSpecificCity() {
		System.out.println("Enter the Name of the City : ");
		String cityName = scanner.nextLine();
		long count = 0;
		long totalCount = 0;
		for (Map.Entry<String, ArrayList<Contact>> entry : multipleAddBook.entrySet()) {
			count = entry.getValue().stream().filter(p -> p.getCity().equalsIgnoreCase(cityName)).count();
			totalCount += count;
		}
		System.out.println("Total contacts from " + cityName + " city is " + totalCount);

	}

	public void viewCountOfContactsFromSpecificState() {
		System.out.println("Enter the Name of the State : ");
		String stateName = scanner.nextLine();
		long count = 0;
		long totalCount = 0;
		for (Map.Entry<String, ArrayList<Contact>> entry : multipleAddBook.entrySet()) {
			count = entry.getValue().stream().filter(p -> p.getCity().equalsIgnoreCase(stateName)).count();
			totalCount += count;
		}
		System.out.println("Total contacts from " + stateName + " state is " + totalCount);
	}

	public void sortContact() {

		System.out.println("Press 1 If you want to sort the contact by their Name : ");
		System.out.println("Press 2 If you want to sort the contact by their City : ");
		System.out.println("Press 3 If you want to sort the contact by their State : ");
		System.out.println("Press 4 If you want to sort the contact by Zip Code : ");
		int choice = scanner.nextInt();
		scanner.nextLine();
		switch (choice) {
		case 1:
			sortContactByPersonName();
			break;
		case 2:
			sortContactByPersonCity();
			break;
		case 3:
			sortContactByPersonState();
			break;
		case 4:
			sortContactByPersonZipCode();
			break;
		default:
			this.mainMenuAddressBook();
		}
	}

	private void sortContactByPersonName() {
		for (Map.Entry<String, ArrayList<Contact>> entry : multipleAddBook.entrySet()) {
			entry.getValue().stream().sorted((s1, s2) -> s1.getFirstName().compareTo(s2.getFirstName()))
					.forEach(System.out::println);
		}
	}

	private void sortContactByPersonCity() {
		for (Map.Entry<String, ArrayList<Contact>> entry : multipleAddBook.entrySet()) {
			entry.getValue().stream().sorted((s1, s2) -> s1.getCity().compareTo(s2.getCity()))
					.forEach(System.out::println);
		}
	}

	private void sortContactByPersonState() {
		for (Map.Entry<String, ArrayList<Contact>> entry : multipleAddBook.entrySet()) {
			entry.getValue().stream().sorted((s1, s2) -> s1.getState().compareTo(s2.getState()))
					.forEach(System.out::println);
		}
	}

	private void sortContactByPersonZipCode() {
		for (Map.Entry<String, ArrayList<Contact>> entry : multipleAddBook.entrySet()) {
			entry.getValue().stream().sorted((s1, s2) -> s1.getZip().compareTo(s2.getZip()))
					.forEach(System.out::println);
		}
	}

	public void writeToFile() {
		System.out.println("Writing to the File.......");
		String addressBookPath = "addressBook_file.txt";
		StringBuffer addressBookBuffer = new StringBuffer();
		for (Map.Entry<String, ArrayList<Contact>> entry : multipleAddBook.entrySet()) {
			entry.getValue().forEach(contact -> {
				String addressBookString = contact.toString().concat("\n");
				addressBookBuffer.append(addressBookString);
			});
			try {
				Files.write(Paths.get(addressBookPath), addressBookBuffer.toString().getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Writting is Done.");
	}

	public void readFromFile() {
		String addressBookFileName = "addressBook_file.txt";
		try {
			Files.lines(new File(addressBookFileName).toPath()).map(line -> line.trim()).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeToCSVFile() {
		System.out.println("Enter the name of the Address Book which you want ot save to CSV : ");
		String name = scanner.nextLine();
		if (multipleAddBook.containsKey(name)) {
			String pathName = name + ".csv";
			try {
				Writer writer = Files.newBufferedWriter(Paths.get(pathName));
				StatefulBeanToCsv<Contact> beanToCsv = new StatefulBeanToCsvBuilder<Contact>(writer)
						.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();
				ArrayList<Contact> temp = multipleAddBook.get(name);
				System.out.println(temp);
				beanToCsv.write(temp);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (CsvDataTypeMismatchException e) {
				e.printStackTrace();
			} catch (CsvRequiredFieldEmptyException e) {
				e.printStackTrace();
			}
		} else
			System.out.println("Address Book not Exist.");

	}

}
