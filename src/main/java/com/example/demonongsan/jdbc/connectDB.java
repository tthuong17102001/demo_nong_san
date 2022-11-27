package com.example.demonongsan.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class connectDB {
	public connectDB(){
		super();
	}
	public static Connection getConnect(){
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/qlns","root","matkhaumoi@");
			System.out.println("Kết nối thành công!");
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Kết nối thất bại!"+e.getMessage());
		}
		return connection;
	}
	public static void main(String[] args) {
		System.out.println(getConnect());
	}
}