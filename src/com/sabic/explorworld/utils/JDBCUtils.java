package com.sabic.explorworld.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUtils {

	public static Connection getConnection() {
		
		try {
			// Carga el driver JDBC
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/explorworld", "root", "abc123.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
			
		}
	
}