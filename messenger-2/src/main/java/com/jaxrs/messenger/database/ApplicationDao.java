package com.jaxrs.messenger.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ApplicationDao {

	public static final String CONNECTION_URL = "jdbc:oracle:thin:@localhost:1521:orcl12c";

	public static final String DB_USERNAME = "SYSTEM";

	public static final String DB_PASSWORD = "Database2";
	
	public static void executeUpdate(String sql) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(CONNECTION_URL, DB_USERNAME,	DB_PASSWORD);
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			//String sql = "INSERT INTO CIRCLE (ID, CIRCLENAME) VALUES (5, 'FIFTH CIRCLE')";
			sql = "SELECT ID, CIRCLENAME FROM CIRCLE WHERE ID = 3";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				System.out.println(rs.getInt("ID"));
				System.out.println(rs.getString("CIRCLENAME"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String args[]){
		executeUpdate(null);
	}
}
