package controller;

import java.util.ArrayList;

import dao.DaoProfessor;
import entity.Professor;
import interfaces.ICrud;
import interfaces.IDao;

public class ControllerProfessor implements ICrud{

	private IDao daoProfessor;
		
	public ControllerProfessor(IDao daoProfessor) {
		this.daoProfessor = daoProfessor;
	}
	
	@Override
	public boolean adicionar(Object obj) {
		return daoProfessor.adicionar(obj);
	}

	@Override
	public boolean atualizar(Object obj) {
		return daoProfessor.atualizar(obj);
	}

	@Override
	public boolean excluir(Object obj) {
		return daoProfessor.excluir(obj);
	}

	@Override
	public Object listarUm(String obj) {
		return null;
	}

	@Override
	public ArrayList listar() {
		return daoProfessor.listar();
	} 

}
