package com.qualiti.banco.negocio;

import com.qualiti.banco.dados.ClienteDAO;
import com.qualiti.banco.modelo.Cliente;
import com.qualiti.banco.modelo.Conta;

public class ClienteBOImpl implements ClienteBO{
 
	private ClienteDAO repositorio;
	
	public ClienteBOImpl(ClienteDAO repositorio){
		this.repositorio = repositorio;
	}
	
	@Override
	public void inserir(Cliente cliente) {
		if(cliente == null){
			System.out.println("Objeto Cliente null");
			return;
		}
		
		if(cliente.getCpf() == null || cliente.getCpf().isEmpty()){
			System.out.println("CPF deve ser informado");
			return;
		}
		
		if(cliente.getCpf().length()!= 14){
			System.out.println("CPF inv�lido!");
			return;
		}
		
		Cliente clienteBusca = repositorio.procurar(cliente.getCpf());
		if(clienteBusca == null){
			repositorio.inserir(cliente);
		}else {
			System.out.println("CPF j� foi cadastrado");
		}
	}

	@Override
	public void atualizar(Cliente cliente) {
		if (cliente == null){
			System.out.println("Objeto Conta null");
			return;
		}
		
		if(cliente.getCpf()==null || cliente.getCpf().isEmpty()){
			System.out.println("N�mero do CPF deve ser informado");
			return;
		}
		
		if(cliente.getCpf().length()!= 14){
			System.out.println("CPF inv�lido");
			return;
		}
		
		Cliente clienteBusca = repositorio.procurar(cliente.getCpf());
		if(clienteBusca == null){
			repositorio.inserir(cliente);
		}else{
			System.out.println("N�mero da conta j� cadastrado");
		}
		
	}

	@Override
	public void remover(String cpf) {
		if(cpf == null || cpf.isEmpty()){
			System.out.println("CPF deve ser informado");
			return;
		}
		
		if(cpf.length() != 14){
			System.out.println("CPF inv�lido");
			return;
		}
		
	}

	@Override
	public Cliente procurar(String cpf) {
		if(cpf == null || cpf.isEmpty()){
			System.out.println("CPF deve ser informado");
			return null;
		}
		
		if(cpf.length() != 14){
			System.out.println("N�mero de conta inv�lido");
			return null;
		}
		
		return repositorio.procurar(cpf);
		
	}

}
