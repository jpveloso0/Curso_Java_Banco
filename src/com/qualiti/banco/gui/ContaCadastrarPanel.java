package com.qualiti.banco.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class ContaCadastrarPanel extends JPanel {
	private JTextField nmeroTxt;
	private JTextField cpfTxt;
	private JLabel lblNmero;
	private JLabel lblCpf;
	private JLabel lblTipo;
	private JButton btnRemover;
	private JButton btnProcurar;
	private JButton btnCadastrar;
	private JButton btnAtualizar;
	private JRadioButton rdbtnCorrente;
	private JRadioButton rdbtnPoupana;
	private JRadioButton rdbtnBnus;
	private JTextField saldoTxt;
	private JLabel lblSaldo;

	/**
	 * Create the panel.
	 */
	public ContaCadastrarPanel() {
		setLayout(null);
		
		nmeroTxt = new JTextField();
		nmeroTxt.setBounds(97, 33, 124, 20);
		add(nmeroTxt);
		nmeroTxt.setColumns(10);
		
		lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(30, 96, 46, 14);
		add(lblCpf);
		
		lblNmero = new JLabel("N\u00FAmero:");
		lblNmero.setBounds(30, 36, 46, 14);
		add(lblNmero);
		
		cpfTxt = new JTextField();
		cpfTxt.setBounds(97, 93, 124, 20);
		add(cpfTxt);
		cpfTxt.setColumns(10);
		
		lblTipo = new JLabel("Tipo: ");
		lblTipo.setBounds(30, 155, 46, 14);
		add(lblTipo);
		
		rdbtnCorrente = new JRadioButton("Corrente");
		rdbtnCorrente.setSelected(true);
		rdbtnCorrente.setBounds(59, 195, 109, 23);
		add(rdbtnCorrente);
		
		rdbtnPoupana = new JRadioButton("Poupan\u00E7a");
		rdbtnPoupana.setBounds(204, 195, 109, 23);
		add(rdbtnPoupana);
		
		rdbtnBnus = new JRadioButton("B\u00F4nus");
		rdbtnBnus.setBounds(315, 195, 109, 23);
		add(rdbtnBnus);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(97, 242, 89, 23);
		add(btnCadastrar);
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(239, 242, 89, 23);
		add(btnAtualizar);
		
		btnRemover = new JButton("Remover");
		btnRemover.setBounds(239, 32, 89, 23);
		add(btnRemover);
		
		btnProcurar = new JButton("Procurar");
		btnProcurar.setBounds(351, 32, 89, 23);
		add(btnProcurar);
		
		lblSaldo = new JLabel("Saldo:");
		lblSaldo.setBounds(239, 96, 46, 14);
		add(lblSaldo);
		
		saldoTxt = new JTextField();
		saldoTxt.setBounds(284, 93, 124, 20);
		add(saldoTxt);
		saldoTxt.setColumns(10);

	}
}
