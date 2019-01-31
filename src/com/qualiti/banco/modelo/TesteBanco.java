package com.qualiti.banco.modelo;

public class TesteBanco {

	public static void main(String[] args) {
		
		Conta con = new Conta();
		
		con.setNumero("123456789.00");
		con.setSaldo(1500.00);
		con.creditar(1000);
		
		Conta con2 = new Conta();
		con2.setNumero("12345-1");
		con2.setSaldo(2000);
		
		con2.debitar(500);
		
		Cliente c1 = new Cliente();
		
		Cliente c2 = new Cliente("João Pedro","088");
		
		System.out.println("Saldo da conta 1: "+con.getSaldo());
		System.out.println("Saldo da conta 2: "+con2.getSaldo());
		
		con.transferir(con2, 500);
		System.out.println(con2.getSaldo());
		
		con2.transferir(con, 2500);
		System.out.println(con.getSaldo()); 
		
		Poupanca poup = new Poupanca();
		poup.setNumero("12345-5656");
		poup.setSaldo(2500);
		
		poup.creditar(500);
		poup.debitar(250);
		poup.renderJuros(0.1); 
		
		ContaBonus cb1 = new ContaBonus();
		cb1.creditar(1000);
		cb1.renderBonus();
		
		System.out.println("Saldo cb: "+cb1.getSaldo());
		System.out.println("Bonus cb: "+cb1.getBonus());
		
	}
}
