package controller;

import dao.DaoEndereco;
import entity.Endereco;

public class ControllerEndereco {

	DaoEndereco daoEndereco;
	
	public ControllerEndereco(DaoEndereco daoEndereco){
		this.daoEndereco = daoEndereco;
	}
	
	public long adicionar(Endereco endereco) {
		
		return daoEndereco.adicionar(endereco);
	} 
}
