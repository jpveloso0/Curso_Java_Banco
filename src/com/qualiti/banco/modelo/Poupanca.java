package com.qualiti.banco.modelo;


public class Poupanca extends Conta {
	//uma classe final n�o gera filhos/ou ningu�m pode herdar algo dela
	public void renderJuros(double taxa){
		
		double saldoPoupanca = getSaldo();
		saldoPoupanca = saldoPoupanca + saldoPoupanca*taxa;
		setSaldo(saldoPoupanca);
	}

}
