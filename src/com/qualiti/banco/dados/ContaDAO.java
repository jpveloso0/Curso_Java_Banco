package com.qualiti.banco.dados;

import com.qualiti.banco.modelo.Conta;
import com.qualiti.banco.modelo.TipoConta;

public interface ContaDAO {
	
	void inserir(Conta conta);
	void atualizar(Conta conta);
	void remover(String numero);
	Conta procurar(String numero);
	String gerarRelatorioContas();
	double recuperarSaldoTotalContas(TipoConta tipo);

}
