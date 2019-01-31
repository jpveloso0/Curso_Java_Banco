package com.qualiti.banco.modelo;


public class Poupanca extends Conta {
	//uma classe final não gera filhos/ou ninguém pode herdar algo dela
	public void renderJuros(double taxa){
		
		double saldoPoupanca = getSaldo();
		saldoPoupanca = saldoPoupanca + saldoPoupanca*taxa;
		setSaldo(saldoPoupanca);
	}

}
