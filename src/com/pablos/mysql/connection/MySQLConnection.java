package com.pablos.mysql.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MySQLConnection {
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/work";
	private static final String username = "root";
	private static final String pass = "";
	private static Connection connection;
	private static final String createTB = "CREATE TABLE IF NOT EXISTS users(id int NOT NULL AUTO_INCREMENT,email varchar(255),first varchar(50),last varchar(50),PRIMARY KEY(id))";
	private static final String selectAll = "Select * from users";

	public static void main(String[] args) throws Exception {
		// getConnection();
		createList();
		insertData();
		getData();
	}

	public static Connection getConnection() throws Exception {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, pass);
			System.out.println("You are connecting to DB! ");
			return connection;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static void createList() throws Exception {
		try {
			connection = getConnection();
			PreparedStatement pstatement = connection.prepareStatement(createTB);
			pstatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("It's OK");
		}
	}

	public static void insertData() throws Exception {
		final String email = "7proPS7@gmail.com";
		final String firstname = "Pawe�";
		final String lastname = "Syka�a";
		try {
			connection = getConnection();
			PreparedStatement pstatement = connection.prepareStatement("Insert into users(email,first,last) values('"
					+ email + "','" + firstname + "','" + lastname + "')");

			pstatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Insert data!");
		}
	}

	public static ArrayList<String> getData() throws Exception {
		try {
			connection = getConnection();
			PreparedStatement pstatement = connection.prepareStatement(selectAll);
			ResultSet result = pstatement.executeQuery();
			ArrayList<String> userList = new ArrayList<String>();

			while (result.next()) {
				System.out.print(result.getString("first") + " " + result.getString("last"));
				System.out.println();
				userList.add(result.getString("last"));
			}
			System.out.println("All data have been selected");
			return userList;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Select complite.");
		}

		return null;
	}
}
