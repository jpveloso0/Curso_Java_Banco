package com.qualiti.banco.dados.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.qualiti.banco.dados.ClienteDAO;
import com.qualiti.banco.excecoes.BancoException;
import com.qualiti.banco.modelo.Cliente;
import com.qualiti.banco.modelo.Endereco;
import com.qualiti.banco.modelo.Pessoa;
import com.qualiti.banco.modelo.TipoPessoa;
import com.qualiti.banco.util.DateUtil;

public class ClienteDAOJDBCImpl implements ClienteDAO {

	@Override
	public void inserir(Cliente entity) throws BancoException {
		
		Connection con = JDBCConnection.getConnection();
		
		String sql = "insert into pessoa (cpf, nome, telefone, email, login, senha, tipo, dataNascimento)"
				+ "values ('"+entity.getCpf()+"','"+entity.getNome()+"','"+entity.getTelefone()+"','"+entity.getEmail()+"','"
				+entity.getLogin()+"','"+entity.getSenha()+"','"+entity.getTipo().name()+
				"','"+DateUtil.converterDataTexto(entity.getDataNascimento())+"')";
		
		String sqlEnd = "insert into endereco(cpf, logradouro, cep, numero, complemento, cidade, uf, bairro) values ('"+
						entity.getCpf()+"','"+
						entity.getEndereco().getLogradouro()+"','"+
						entity.getEndereco().getCep()+"','"+
						entity.getEndereco().getNumero()+"','"+
						entity.getEndereco().getComplemento()+"','"+
						entity.getEndereco().getCidade()+"','"+
						entity.getEndereco().getUf()+"','"+
						entity.getEndereco().getBairro()+"')";
				
		
		try {
			Statement stm = con.createStatement();
			stm.executeUpdate(sql);
			
			stm = con.createStatement();
			stm.executeUpdate(sqlEnd);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BancoException("Problemas ao acessar a base de dados!");
			
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				throw new BancoException("Problemas ao acessar a base de dados!");
			}
		}
		
	}

	@Override
	public void atualizar(Cliente entity) throws BancoException {
		
		Connection con = JDBCConnection.getConnection();
		
		String sql2 = "update endereco set logradouro = ?, cep = ?, numero = ?, complemento = ?,"
				+ "cidade = ?, uf = ?, bairro = ? where cpf = ?";
		
		try {
			PreparedStatement pstm1 = con.prepareStatement(sql2);
			
			pstm1.setString(1, entity.getEndereco().getLogradouro());
			pstm1.setString(2, entity.getEndereco().getCep());
			pstm1.setString(3, entity.getEndereco().getNumero());
			pstm1.setString(4, entity.getEndereco().getComplemento());
			pstm1.setString(5, entity.getEndereco().getCidade());
			pstm1.setString(6, entity.getEndereco().getUf());
			pstm1.setString(7, entity.getEndereco().getBairro());
			
			pstm1.executeUpdate();
		} catch (SQLException e1) {
			throw new BancoException("Problemas ao acessar a base de dados!");
		}
			
		
		String sql = "update pessoa set nome = ?,login = ?, senha = ?, telefone = ?"
				+ ",dataNascimento = ?, email = ? where cpf = ?";
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			
			pstm.setString(1, entity.getNome());
			pstm.setString(2, entity.getLogin());
			pstm.setString(3, entity.getSenha());
			pstm.setString(4, entity.getTelefone());
			
			java.sql.Date dataSql = java.sql.Date.valueOf(entity.getDataNascimento());
			
			pstm.setDate(5, dataSql);
			pstm.setString(6, entity.getEmail());
			pstm.setString(7, entity.getCpf());
			
		} catch (SQLException e) {			
			throw new BancoException("Problemas ao acessar a base de dados!");
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				throw new BancoException("Problemas ao acessar a base de dados!");
			}
		}
		
	}

	@Override
	public void remover(String chave) throws BancoException {
		
		Connection con = JDBCConnection.getConnection();
		
		String sql3 = "delete from endereco where cpf = ?";
		
		PreparedStatement pstm1;
		try {
			pstm1 = con.prepareStatement(sql3);
			pstm1.setString(1, chave);
			
			pstm1.executeUpdate();
		} catch (SQLException e1) {
			throw new BancoException("Problemas ao acessar a base de dados!");
		}
		
		String sql = "delete from pessoa where cpf = ?";
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1,  chave);
			
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new BancoException("Problemas ao acessar a base de dados!");
			
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				throw new BancoException("Problemas ao acessar a base de dados!");
				
			}
		}
		
		
	}

	@Override
	public Cliente procurar(String chave) throws BancoException {
		
		Connection con = JDBCConnection.getConnection();
		
		String sql = "select * from pessoa where cpf = ?";
		PreparedStatement pstm;
		
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1,  chave);
			
			ResultSet rs = pstm.executeQuery();
			
			if(rs.next()){
				
				String nome = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String email = rs.getString("email");
				String login = rs.getString("login");
				String senha = rs.getString("senha");
				String telefone = rs.getString("telefone");
				String tipo = rs.getString("tipo");
				TipoPessoa tipoPessoa = TipoPessoa.valueOf(tipo);
				
				Date dataNasc = rs.getDate("dataNascimento");
				
				Cliente cliente = new Cliente();
				cliente.setNome(nome);
				cliente.setCpf(cpf);
				cliente.setEmail(email);
				cliente.setLogin(login);
				cliente.setSenha(senha);
				cliente.setTipo(tipoPessoa);
				cliente.setTelefone(telefone);
				
				ZoneId defaultZoneId = ZoneId.systemDefault();
				
				java.util.Date newDate = new java.util.Date();
				newDate.setTime(dataNasc.getTime());
				LocalDate dateLocal = newDate.toInstant().atZone(defaultZoneId).toLocalDate();
				
				cliente.setDataNascimento(dateLocal);
				
				
				String sql2 = "select * from endereco where cpf = ?";
				pstm = con.prepareStatement(sql2);
				pstm.setString(1, chave);
				
				ResultSet rs2 = pstm.executeQuery();
				
				if(rs2.next()){
					
					String logradouro = rs2. getString("logradouro");
					String numero = rs2. getString("numero");
					String complemento = rs2. getString("complemento");
					String cep = rs2. getString("cep");
					String bairro = rs2. getString("bairro");
					String cidade = rs2. getString("cidade");
					String uf = rs2. getString("uf");
					
					Endereco end = new Endereco();
					end.setLogradouro(logradouro);
					end.setNumero(numero);
					end.setComplemento(complemento);
					end.setBairro(bairro);
					end.setCep(cep);
					end.setCidade(cidade);
					end.setUf(uf);
					
					cliente.setEndereco(end);
					
					return cliente;
				}
				
			}
			
		} catch (SQLException e) {
				throw new BancoException("Problemas ao acessar a base de dados!");
		}
		
		return null;
	}
	
}
