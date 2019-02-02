package com.qualiti.banco.negocio;

import com.qualiti.banco.dados.ContaDAO;
import com.qualiti.banco.modelo.Conta;
import com.qualiti.banco.modelo.TipoConta;

public class ContaBOImpl implements ContaBO {

	private ContaDAO repositorio;
	
	public ContaBOImpl(ContaDAO repositorio){
		this.repositorio = repositorio;
	}
	
	@Override
	public void inserir(Conta conta) {
		if (conta == null){
			System.out.println("Objeto Conta null");
			return;
		}
		
		if(conta.getNumero()==null || conta.getNumero().isEmpty()){
			System.out.println("N�mero da conta deve ser informado");
			return;
		}
		
		if(conta.getNumero().length() != 6){
			System.out.println("N�mero de conta inv�lido");
			return;
		}
		
		Conta contaBusca = repositorio.procurar(conta.getNumero());
		if(contaBusca == null){
			repositorio.inserir(conta);
		}else{
			System.out.println("N�mero da conta j� cadastrado");
		}
		
		
	}

	@Override
	public void atualizar(Conta conta) {
		if (conta == null){
			System.out.println("Objeto Conta null");
			return;
		}
		
		if(conta.getNumero()==null || conta.getNumero().isEmpty()){
			System.out.println("N�mero da conta deve ser informado");
			return;
		}
		
		if(conta.getNumero().length() != 6){
			System.out.println("N�mero de conta inv�lido");
			return;
		}
		
		Conta contaBusca = repositorio.procurar(conta.getNumero());
		if(contaBusca != null){
			repositorio.atualizar(conta);
		}else{
			System.out.println("N�mero da conta n�o existe");
		}
		
		
	}

	@Override
	public void remover(String numero) {
		
		if(numero == null || numero.isEmpty()){
			System.out.println("N�mero da conta deve ser informado");
			return;
		}
		
		if(numero.length() != 6){
			System.out.println("N�mero de conta inv�lido");
			return;
		}
		
		repositorio.remover(numero);
		
	}

	@Override
	public Conta procurar(String numero) {

		if(numero == null || numero.isEmpty()){
			System.out.println("N�mero da conta deve ser informado");
			return null;
		}
		
		if(numero.length() != 6){
			System.out.println("N�mero de conta inv�lido");
			return null;
		}
		
		return repositorio.procurar(numero);
	}

	@Override
	public String gerarRelatorioContas() {
		
		return repositorio.gerarRelatorioContas();
	}

	@Override
	public double recuperarSaldoTotalContas(TipoConta tipo) {
		
		return repositorio.recuperarSaldoTotalContas(tipo);
	}

}
