package com.qualiti.banco.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.qualiti.banco.excecoes.BancoException;
import com.qualiti.banco.fachada.Fachada;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class TransaçõesMovimentaçãoPanel extends JPanel {
	private JTextField nmeroOrigemTxt;
	private JComboBox comboBox;
	private JTextField nmeroDestinoTxt;
	private JTextField valorTxt;
	private JLabel lblTipo;
	private JLabel lblNmeroOrigem;
	private JLabel lblNmeroDestino;
	private JLabel lblValor;
	private JButton btnConfirmar;

	/**
	 * Create the panel.
	 */
	public TransaçõesMovimentaçãoPanel() {
		setLayout(null);
		
		lblTipo = new JLabel("Tipo: ");
		lblTipo.setBounds(28, 35, 46, 14);
		add(lblTipo);
		
		nmeroDestinoTxt = new JTextField();
		nmeroDestinoTxt.setEnabled(false);
		nmeroDestinoTxt.setBounds(120, 128, 101, 20);
		add(nmeroDestinoTxt);
		nmeroDestinoTxt.setColumns(10);
		
		lblNmeroDestino = new JLabel("N\u00FAmero Destino:");
		lblNmeroDestino.setEnabled(false);
		lblNmeroDestino.setBounds(28, 131, 82, 14);
		add(lblNmeroDestino);
		
		comboBox = new JComboBox<>();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				String tipo = (String)comboBox.getSelectedItem();
				
				if(tipo.equals("Transferência")){
					
					lblNmeroDestino.setEnabled(true);
					nmeroDestinoTxt.setEnabled(true);
					nmeroDestinoTxt.setText("");
					
				}else{
					lblNmeroDestino.setEnabled(false);
					nmeroDestinoTxt.setEnabled(false);
					nmeroDestinoTxt.setText("");
					
				}
				
			}
		});
		comboBox.setBounds(62, 32, 101, 20);
		add(comboBox);
		comboBox.addItem("");
		comboBox.addItem("Crédito");
		comboBox.addItem("Débito");
		comboBox.addItem("Transferência");
				
		
		nmeroOrigemTxt = new JTextField();
		nmeroOrigemTxt.setBounds(120, 80, 101, 20);
		add(nmeroOrigemTxt);
		nmeroOrigemTxt.setColumns(10);
		
		lblNmeroOrigem = new JLabel("N\u00FAmero Origem:");
		lblNmeroOrigem.setBounds(28, 83, 82, 14);
		add(lblNmeroOrigem);
		
		
		
		
		
		lblValor = new JLabel("Valor:");
		lblValor.setBounds(28, 179, 46, 14);
		add(lblValor);
		
		valorTxt = new JTextField();
		valorTxt.setBounds(120, 176, 101, 20);
		add(valorTxt);
		valorTxt.setColumns(10);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String tipo = (String) comboBox.getSelectedItem();
				String numero = nmeroOrigemTxt.getText();
				Double valor = Double.parseDouble(valorTxt.getText());
				
				if(tipo.isEmpty()){
					
					JOptionPane.showMessageDialog(null, "Informe o tipo da movimentação", "Movimentação Conta", 
							JOptionPane.INFORMATION_MESSAGE);
					return;
					
				}
				
				if(tipo.equals("Crédito")){
					
					try {
						Fachada.getFachada().creditar(numero, valor);
						JOptionPane.showMessageDialog(null, "Crédito realizado com sucesso", "Movimentação Conta", 
								JOptionPane.INFORMATION_MESSAGE);
						
						comboBox.setSelectedItem("");
						nmeroOrigemTxt.setText("");
						valorTxt.setText("");
						
					} catch (BancoException e) {
						
						JOptionPane.showMessageDialog(null, e.getMessage(), "Movimentação Conta", 
								JOptionPane.ERROR_MESSAGE);
					}
					
				}
				
				if(tipo.equals("Débito")){
					
					try {
						Fachada.getFachada().debitar(numero, valor);
						JOptionPane.showMessageDialog(null, "Débito realizado com sucesso", "Movimentação Conta", 
								JOptionPane.INFORMATION_MESSAGE);
					} catch (BancoException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "Movimentação Conta", 
								JOptionPane.ERROR_MESSAGE);
						
					}
					
					
				}
				
				if(tipo.equals("Transferência")){
					
					try {
						String contaDestino = nmeroDestinoTxt.getText();
						
						Fachada.getFachada().transferir(numero, contaDestino, valor);
						
						JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso", "Movimentação Conta", 
								JOptionPane.INFORMATION_MESSAGE);
						
						comboBox.setSelectedItem("");
						nmeroOrigemTxt.setText("");
						nmeroDestinoTxt.setText("");
						valorTxt.setText("");
					} catch (BancoException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "Movimentação Conta", 
								JOptionPane.ERROR_MESSAGE);
						
					}
					
					
				}
				
				
				
			}
		});
		btnConfirmar.setBounds(181, 230, 89, 23);
		add(btnConfirmar);

		
		
	}
}
