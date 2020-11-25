package controller;

import java.util.ArrayList;

import entity.Professor;
import interfaces.ICrud;
import interfaces.IDao;

public class ControllerFuncionario implements ICrud{

	private IDao daoFuncionario;
		
	public ControllerFuncionario(IDao daoFuncionario) {
		this.daoFuncionario = daoFuncionario;
	}
	
	@Override
	public boolean adicionar(Object obj) {
		return daoFuncionario.adicionar(obj);
	}

	@Override
	public boolean atualizar(Object obj) {
		return daoFuncionario.atualizar(obj);
	}

	@Override
	public boolean excluir(Object obj) {
		return daoFuncionario.excluir(obj);
	}

	@Override
	public ArrayList listar() {
		return daoFuncionario.listar();
	}

	@Override
	public ArrayList pesquisa(String obj) {
		return daoFuncionario.pesquisa(obj);
	} 

}
