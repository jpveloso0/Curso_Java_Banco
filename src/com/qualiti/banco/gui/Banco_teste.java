package com.qualiti.banco.gui;

import com.qualiti.banco.dados.ContaDAOArrayImpl;
import com.qualiti.banco.fachada.Fachada;
import com.qualiti.banco.modelo.Conta;
import com.qualiti.banco.modelo.Poupanca;
import com.qualiti.banco.modelo.TipoConta;
import com.qualiti.banco.negocio.ContaBOImpl;

public class Banco_teste {

	public static void main(String[] args) {
		
		Conta c = new Conta();
		c.setNumero("123");
		c.setSaldo(1290);
		
		Poupanca p = new Poupanca();
		p.setNumero("124");
		p.setSaldo(8650);
		
		Fachada fachada = new Fachada();
		
		fachada.inserirConta(c);
		fachada.inserirConta(p);
		
		
		Conta retorno = fachada.procurarConta("123");
		
		if(retorno != null){
			System.out.println("Conta encontrada: Sado - "+retorno.getSaldo());
		}else{
			System.out.println("Conta não existente!");
		}
		
		String relatorio = fachada.gerarRelatorioContas();
		System.out.println(relatorio);
		
		double totalSaldos = fachada.recuperarSaldoTotalContas(null);
		System.out.println("O saldo total é: "+totalSaldos);
		
		double totalSaldoPoupanca = fachada.recuperarSaldoTotalContas(TipoConta.POUPANCA);
		System.out.println("O saldo da poupança total é: "+totalSaldoPoupanca);
		
		double totalSaldoCorrente = fachada.recuperarSaldoTotalContas(TipoConta.CORRENTE);
		System.out.println("O saldo da conta corrente total é: "+totalSaldoCorrente);
		
	}
}
