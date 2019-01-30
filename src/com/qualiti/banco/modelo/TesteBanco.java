package com.qualiti.banco.modelo;

public class TesteBanco {

	public static void main(String[] args) {
		
		Conta con = new Conta();
		
		con.setNumero("123456789.00");
		con.setSaldo(1500.00);
		con.creditar(1000);
		
		System.out.println(con.getSaldo());
		
		
	}
}
