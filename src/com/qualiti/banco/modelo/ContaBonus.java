package com.qualiti.banco.modelo;

public class ContaBonus extends Conta {

	private double bonus;
	
	//redefinição de metodo da classe pai/ Override
	@Override
	//Overloading é um método com parametros diferentes
	public void creditar(double valor){
		bonus = bonus + valor*0.1;
		super.creditar(valor);
		
	}

	public void renderBonus(){
		super.creditar(bonus);
		bonus = 0;
	}
	
	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	
}
