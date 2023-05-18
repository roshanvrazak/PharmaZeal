package com.pharmazeal;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

	public static Connection connectDB() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/PharmaZeal", "root", "admin123");
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
