package com.qualiti.banco.dados.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
	
	public static Connection getConnection(){
		
		Connection con = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			
			//jdbc:postgresql://host:port/database
			
			String url = "jdbc:postgresql://localhost:5432/Banco";
			String user = "postgres";
			String pass = "postgresql";
			
			con =  DriverManager.getConnection(url, user, pass);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}

	public static void main(String[] args) {
		
		Connection con = JDBCConnection.getConnection();
		
		if(con == null){
			System.out.println("Conex�o falhou");
		}else{
			System.out.println("Conex�o sucesso");
		}
		
	}
	
}
