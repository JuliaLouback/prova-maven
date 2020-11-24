package controller;

import java.util.ArrayList;

import dao.DaoEndereco;
import entity.Endereco;
import interfaces.ICrud;
import interfaces.IDao;

public class ControllerEndereco implements ICrud {

	private IDao daoEndereco;
	
	public ControllerEndereco(IDao daoEndereco){
		this.daoEndereco = daoEndereco;
	}
	
	public long adicionarLong(Endereco endereco) {
		
		return ((DaoEndereco) daoEndereco).adicionarEndereco(endereco);
	}

	@Override
	public boolean adicionar(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean atualizar(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object listarUm(String obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList listar() {
		// TODO Auto-generated method stub
		return null;
	} 
}
