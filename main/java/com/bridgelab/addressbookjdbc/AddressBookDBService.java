package com.bridgelab.addressbookjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService {

	public Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/addressbook_service?useSSL=false";
		String userName = "root";
		String password = "Amit@#$987";
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;

	}

	public ArrayList<Contact> getDataFromDB() {

		ArrayList<Contact> contactList = new ArrayList<>();
		String sql = "select * from user_details;";

		Connection connection = this.getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String firstName = resultSet.getString(2);
				String lastName = resultSet.getString(3);
				String city = resultSet.getString(4);
				String state = resultSet.getString(5);
				String email = resultSet.getString(6);
				String zip = resultSet.getString("zip");
				String phoneNumber = resultSet.getString("phonenumber");
				contactList.add(new Contact(firstName, lastName, city, state, email, zip, phoneNumber));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return contactList;
	}

	public int updateContactEmailOnDB(String name, String email) {
		String sql = String.format("update user_details set email = '%s' where firstname = '%s';", email, name);
		try (Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			return statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Contact> getDataOfContactFromDB(String name) {
		String sql = String.format("select * from user_details where firstname = '%s';", name);
		List<Contact> contactDataList = new ArrayList<>();
		try (Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String firstName = resultSet.getString("firstname");
				String lastName = resultSet.getString("lastname");
				String city = resultSet.getString("city");
				String state = resultSet.getString("state");
				String email = resultSet.getString("email");
				String zip = resultSet.getString("zip");
				String phoneNumber = resultSet.getString("phonenumber");
				contactDataList.add(new Contact(firstName, lastName, city, state, email, zip, phoneNumber));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contactDataList;
	}

}
