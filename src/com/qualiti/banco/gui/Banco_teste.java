package com.qualiti.banco.gui;

import com.qualiti.banco.excecoes.BancoException;
import com.qualiti.banco.fachada.Fachada;
import com.qualiti.banco.modelo.Conta;
import com.qualiti.banco.modelo.Poupanca;
import com.qualiti.banco.modelo.TipoConta;

public class Banco_teste {

	public static void main(String[] args) {
		
		Conta c = new Conta();
		c.setNumero("123");
		c.setSaldo(1290);
		
		Poupanca p = new Poupanca();
		p.setNumero("124");
		p.setSaldo(8650);
		
		Fachada fachada = new Fachada();
		
		try {
			fachada.inserirConta(c);
		} catch (BancoException e) {
			
			e.printStackTrace();
		}
		try {
			fachada.inserirConta(p);
		} catch (BancoException e) {
			System.out.println(e.getMessage());
			
		}
		
		
		Conta retorno;
		try {
			retorno = fachada.procurarConta("123");
			
			if(retorno != null){
				System.out.println("Conta encontrada: Sado - "+retorno.getSaldo());
			}else{
				System.out.println("Conta n�o existente!");
			}
		} catch (BancoException e) {
			System.out.println(e.getMessage());
			
		}
		
		
		
		String relatorio = fachada.gerarRelatorioContas();
		System.out.println(relatorio);
		
		double totalSaldos = fachada.recuperarSaldoTotalContas(null);
		System.out.println("O saldo total �: "+totalSaldos);
		
		double totalSaldoPoupanca = fachada.recuperarSaldoTotalContas(TipoConta.POUPANCA);
		System.out.println("O saldo da poupan�a total �: "+totalSaldoPoupanca);
		
		double totalSaldoCorrente = fachada.recuperarSaldoTotalContas(TipoConta.CORRENTE);
		System.out.println("O saldo da conta corrente total �: "+totalSaldoCorrente);
		
	}
}
