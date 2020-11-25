package controller;

import java.util.ArrayList;

import interfaces.ICrud;
import interfaces.IDao;

public class ControllerAluno implements ICrud{

	private IDao daoAluno;
		
	public ControllerAluno(IDao daoAluno) {
		this.daoAluno = daoAluno;
	}
	
	@Override
	public boolean adicionar(Object obj) {
		return daoAluno.adicionar(obj);
	}

	@Override
	public boolean atualizar(Object obj) {
		return daoAluno.atualizar(obj);
	}

	@Override
	public boolean excluir(Object obj) {
		return daoAluno.excluir(obj);
	}
	
	@Override
	public ArrayList listar() {
		return daoAluno.listar();
	}

	@Override
	public ArrayList pesquisa(String obj) {
		return daoAluno.pesquisa(obj);
	} 

}
